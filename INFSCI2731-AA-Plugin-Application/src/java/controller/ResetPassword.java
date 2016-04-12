/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DbConnect.DbConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shaoNPC
 */
@WebServlet(name = "ResetPassword", urlPatterns = {"/ResetPassword"})
public class ResetPassword extends HttpServlet {

    private static final int MAX_TIME_DIFF_MINS = 20;
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
        
        String nonce = request.getParameter("token");
        Timestamp timestamp;
        
        if (nonce != null && !nonce.equals("")) {
            timestamp = getIDFromNonceDB(nonce);
            if (checkDateTime(timestamp)) {
                printPasswordForm(request, response, nonce);
            }
        }
        
        printError(request, response);
        
    }
    
    protected Timestamp getIDFromNonceDB(String nonce) {
        Connection connection;
        PreparedStatement preparedStatement;
        Timestamp timestamp = null;
        
        try {
            connection = DbConnection.getConnection();
            String selectSQL = "SELECT timestamps.create_time FROM nonce, timestamps WHERE nonce = ? AND nonce.timestamps_id = timestamps.id";
            preparedStatement = connection.prepareStatement(selectSQL);  
            preparedStatement.setString(1, nonce);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            boolean val = rs.next();
            if (val) {
                timestamp = rs.getTimestamp("create_time");
            }
  
        } catch (SQLException e) {
                e.printStackTrace();
        }   
        
        return timestamp;
    }
    
    protected boolean checkDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return false;
        }
        Date nonceDate = timestamp;
        long cDate = System.currentTimeMillis();
        long nDate = nonceDate.getTime();

        System.out.println(cDate - nDate);
        System.out.println(MAX_TIME_DIFF_MINS * 60000);

        return (cDate - nDate) < (MAX_TIME_DIFF_MINS * 60000);
    }
    
    protected void printPasswordForm(HttpServletRequest request, HttpServletResponse response, String nonce) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Reset Password</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>ResetPassword</h1>");
            out.println("<form name=\"resetform-password\" method=\"POST\" action=\"ResetPasswordService\">");
            out.println("<input type=\"hidden\" name=\"token\" value=\"" + nonce +  " \">");
            out.println("Enter your new password: <input name=\"password\" type=\"password\" />");
            out.println("<br/><br/>");
            out.println("Confirm your new password: <input name=\"confirm_password\" type=\"password\"/><br/><br/><input type=\"submit\"></form>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    protected void printError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Error</h1>");
            out.println("<p>This link has expired.</p>");
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
