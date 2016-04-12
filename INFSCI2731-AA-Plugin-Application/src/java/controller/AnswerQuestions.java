/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DbConnect.DbConnection;
import dataAccessObject.NonceDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ResetPasswordObj;

/**
 *
 * @author shaoNPC
 */
@WebServlet(name = "AnswerQuestions", urlPatterns = {"/AnswerQuestions"})
public class AnswerQuestions extends HttpServlet {

    private final int MAX_QUESTION_ATTEMPTS = 5;
    private final String SYSTEM_SOURCE = "QuestionForm";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        int questionAttempts = 0;
        String questionString = "";
        
        if (session == null) { //No session yet
            response.sendRedirect("login.jsp");
        } else { //Session already created
            if (session.getAttribute("resetPasswordObj") == null) {
                response.sendRedirect("login.jsp");
            }
            
            if (session.getAttribute("questionAttempts") != null) {
                questionAttempts = (Integer)session.getAttribute("questionAttempts");
            } 
            if (session.getAttribute("questionString") != null) {
                questionString = (String)session.getAttribute("questionString");
            }
        }  
        
        if(questionAttempts > MAX_QUESTION_ATTEMPTS) {
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            // if ip behind proxy
            if (ipAddress == null) {  
                    ipAddress = request.getRemoteAddr();  
            }
            //hostile(questionAttempts, ipAddress, SYSTEM_SOURCE);
            response.sendRedirect("hostile");
        } else {
            String securityAnswer = request.getParameter("security_answer");
            ResetPasswordObj resetPasswordObj = (ResetPasswordObj)session.getAttribute("resetPasswordObj"); 
            String checkUserID = checkValidAnswerDB(securityAnswer, resetPasswordObj.securityAnswerID);
            
            if (resetPasswordObj.userID.equals(checkUserID)) {
                NonceDao nonce = new NonceDao();
                nonce.createNewNonce(Integer.parseInt(resetPasswordObj.userID));
                String userNonce = nonce.getNonceValue();
                if(!userNonce.equals("")) {
                    printEmail(request, response, userNonce);
                }
                session.invalidate();
            } else {
                questionAttempts++;
                questionString += securityAnswer + ":" + SYSTEM_SOURCE + ";";
                
                session.setAttribute("questionAttempts", questionAttempts);       
                session.setAttribute("questionString", questionString);
                request.setAttribute("question_string", resetPasswordObj.securityQuestion);
                
                RequestDispatcher rd = request.getRequestDispatcher("questions");
                rd.forward(request, response);
            }
        }
        
    }
    
    protected String checkValidAnswerDB(String securityAnswer, String securityAnswerID) {
        Connection connection;
        PreparedStatement preparedStatement;
        
        try {
            connection = DbConnection.getConnection();
            String selectSQL = "SELECT account_info_id FROM INFSCI2731.security_question_answer WHERE answer = ? AND id = ?";
            preparedStatement = connection.prepareStatement(selectSQL);  
            preparedStatement.setString(1, securityAnswer);
            preparedStatement.setString(2, securityAnswerID);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            boolean val = rs.next();
            if (!val) {
                return "";
            } else {
                return rs.getString("account_info_id"); 
            }
  
        } catch (SQLException e) {
                e.printStackTrace();
                return "";
        }   
    }
    
    protected void printEmail(HttpServletRequest request, HttpServletResponse response, String userNonce) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = request.getRequestURL() + "resetpassword?token=" + userNonce;
        url = url.replace("AnswerQuestions", "");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Reset Password Email Page</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Reset Password Email Page</h1>");
            out.println("<p><strong>Hi {User's name},</strong></p>");
            out.println("<p>You're receiving this email because you requested a password reset.  "
                    + "If you did not request this change, you can safely ignore this email.</p>");
            out.println("<p>To choose a new password and complete your request, please follow the link below:</p>");
            out.println("<a href=" + url + ">" + url + "</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
