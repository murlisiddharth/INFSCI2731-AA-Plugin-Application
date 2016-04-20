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
import model.IPAddress;
import model.Nonce;
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
        
        if (token != null && !token.equals("")) {
            if (CheckDateTime.isValid(nonceDao.getNonceCreatetime(token))) {
                tokenValid = true;
            }
        }
        
        if (passwordMatch && tokenValid) {
            Nonce nonce = nonceDao.getNonceByNonceValue(token);
            SavePasswordDao savePasswordDao = new SavePasswordDao();
            
            passwordUsedBefore = savePasswordDao.checkPastPassword(nonce.getAccountInfoID(), password);
            if (passwordUsedBefore) {
                redirectError(request, response, passwordMatch, passwordUsedBefore, tokenValid, token);
            } else {
                savePasswordDao.savePassword(nonce.getAccountInfoID(), password);

                //log activity of successfully reset pw, and update previoud reset pw record description
                int logID = logDao.logExpiredLinkOnForgotPw(ipAddr, sysSource, nonce.getAccountInfoID());
                //check if previous sent link record exist, if it does, update the record description
                int id = logDao.checkResetPwSentLink(nonce.getAccountInfoID());
                if(logID > 0 && id > 0){
                    logDao.updatePreResetPwRecord("succeeded(logid " + logID + ")", id);
                }

                nonceDao.deleteNonceByUserID(nonce.getAccountInfoID());
                response.sendRedirect("login.jsp");
            }
        } else {
            redirectError(request, response, passwordMatch,  passwordUsedBefore, tokenValid, token);
        }
        
        
    }
    
    protected void redirectError(HttpServletRequest request, HttpServletResponse response, boolean passwordMatch, boolean passwordUsedBefore, boolean tokenValid, String token) 
            throws ServletException, IOException {
        String url = tokenValid ? "resetpassword?token=" + token.trim() : "resetpassword";
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
