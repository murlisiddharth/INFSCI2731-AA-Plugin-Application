/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dataAccessObject.NonceDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        
        boolean passwordMatch = password.equals(confirmPassword);
        boolean tokenValid = false;
        
        if (token != null && !token.equals("")) {
            if (CheckDateTime.isValid(nonceDao.getNonceCreatetime(token))) {
                tokenValid = true;
            }
        }
        
        if (passwordMatch && tokenValid) {
            Nonce nonce = nonceDao.getNonceByNonceValue(token);       
        } else {
            redirectError(request, response, passwordMatch, tokenValid, token);
        }
        
        
    }
    
    protected void redirectError(HttpServletRequest request, HttpServletResponse response, boolean passwordMatch, boolean tokenValid, String token) 
            throws ServletException, IOException {
        String url = tokenValid ? "resetpassword?token=" + token.trim() : "resetpassword";
        url += !passwordMatch ? "&pmm=" + !passwordMatch : "";
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
