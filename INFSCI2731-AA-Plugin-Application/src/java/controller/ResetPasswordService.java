/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dataAccessObject.ActivityLogDao;
import dataAccessObject.NonceDao;
import dataAccessObject.SavePasswordDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.IPAddress;
import model.Nonce;
import model.UserAccountInfo;
import utilities.CheckDateTime;

/**
 *
 * @author shaoNPC
 */
@WebServlet(name = "ResetPasswordService", urlPatterns = {"/resetpasswordservice"})
public class ResetPasswordService extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        NonceDao nonceDao = new NonceDao();
        ActivityLogDao logDao = new ActivityLogDao();
        IPAddress ipAddress = new IPAddress();
        
        //get client ip addr and request URI for activity log
        String sysSource = request.getRequestURI();
        String ipAddr = ipAddress.getClientIpAddress(request);

        
        boolean passwordMatch = password.equals(confirmPassword);
        boolean tokenValid = false;
        boolean passwordUsedBefore = false;
        boolean loggedIn = false;
        UserAccountInfo loginUser = null;
        
        if (token != null && !token.equals("") && !token.equals("userLogged")) {
            if (CheckDateTime.isValid(nonceDao.getNonceCreatetime(token))) {
                tokenValid = true;
            }
        } else if (token != null && token.equals("userLogged")) {
            if (session.getAttribute("user") != null) {
                loginUser = (UserAccountInfo)session.getAttribute("user");
                loggedIn = true;
            }
        }
        
        if (passwordMatch && (tokenValid || loggedIn)) {
            int userID = -1;
            if (tokenValid) {
                Nonce nonce = nonceDao.getNonceByNonceValue(token);
                userID = nonce.getAccountInfoID();
            } else if (loggedIn) {
                userID = loginUser.getId();
            }
            
            SavePasswordDao savePasswordDao = new SavePasswordDao();
            
            passwordUsedBefore = savePasswordDao.checkPastPassword(userID, password);
            if (passwordUsedBefore) {
                redirectError(request, response, passwordMatch, passwordUsedBefore, tokenValid, token);
            } else {
                savePasswordDao.savePassword(userID, password);

                //log activity of successfully reset pw, and update previoud reset pw record description
                int logID = logDao.logForgotPwResult(ipAddr, sysSource, (loggedIn ? "(change pw)" : "(forgot pw)") + "successfully reset pw", userID);
                //check if previous sent link record exist, if it does, update the record description
                int id = logDao.checkResetPwSentLink(userID);
                if(logID > 0 && id > 0){
                    logDao.updatePreResetPwRecord("succeeded(logid " + logID + ")", id);
                }
                
                if (tokenValid) {
                    nonceDao.deleteNonceByUserID(userID);
                }
                
                printPasswordSuccess(request, response, loggedIn);
            }
        } else {
            redirectError(request, response, passwordMatch,  passwordUsedBefore, tokenValid, token);
        }
        
        
    }
    
    protected void printPasswordSuccess(HttpServletRequest request, HttpServletResponse response, Boolean loggedIn) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Reset Password Success</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Reset Password Success</h1>");
            out.println("<p>You have successfully changed your password.</p>");
            if (loggedIn) {
                out.println("<p><a href=\"\">Click here</a> to return to the account page.</p>");
            } else {
                out.println("<p><a href=\"login.jsp\">Click here</a> to return to the login page.</p>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    protected void redirectError(HttpServletRequest request, HttpServletResponse response, boolean passwordMatch, boolean passwordUsedBefore, boolean tokenValid, String token) 
            throws ServletException, IOException {
        String url = tokenValid ? "resetpassword?token=" + token.trim() : "resetpassword?";
        url += !passwordMatch ? "&pmm=" + !passwordMatch : "";
        url += passwordUsedBefore ? "&pub=" + passwordUsedBefore : "";
        response.sendRedirect(url);
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
