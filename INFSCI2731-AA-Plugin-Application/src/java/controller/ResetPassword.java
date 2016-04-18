/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import utilities.CheckDateTime;
import dataAccessObject.NonceDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shao dai
 */
@WebServlet(name = "ResetPassword", urlPatterns = {"/resetpassword"})
public class ResetPassword extends HttpServlet {
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
        boolean passwordMismatch = Boolean.parseBoolean(request.getParameter("pmm"));
        Timestamp timestamp;
        NonceDao nonceDao = new NonceDao();
        
        if (nonce != null && !nonce.equals("")) {
            timestamp = nonceDao.getNonceCreatetime(nonce);
            if (CheckDateTime.isValid(timestamp)) {
                printPasswordForm(request, response, nonce, passwordMismatch);
            }
        }
        
        printError(request, response);
        
    } 
    
    protected void printPasswordForm(HttpServletRequest request, HttpServletResponse response, String nonce, boolean passwordMismatch) throws IOException {
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
            out.println("<form name=\"resetform-password\" method=\"POST\" action=\"resetpasswordservice\">");
            if (passwordMismatch) {
                out.println("<p>Your passwords do not match, please re-enter your new password.</p>");
            }
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
