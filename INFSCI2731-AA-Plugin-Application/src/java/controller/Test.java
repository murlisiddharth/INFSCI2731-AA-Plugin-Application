/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dataAccessObject.NonceDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Nonce;

/**
 *
 * @author wazi1221
 */
public class Test extends HttpServlet {
    private Nonce nonce;
    
    final private NonceDao nonceDao;
    

    public Test() {
        super();              
        nonceDao = new NonceDao();            
    }
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
        
            String action = request.getParameter("action");

            if(action.equalsIgnoreCase("getNonce")) {
                int accoutInfoID = 1;
                String n = nonceDao.getNewNonce(accoutInfoID);
                nonce = nonceDao.getNonceByNonceValue(n);
                
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Test</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<p>Servlet" + request.getContextPath() + "</p>");
                out.println("<p>nonce id " + nonce.getNonceID() + "</p>");
                out.println("<p>val " + nonce.getNonceValue() + "</p>");
                out.println("<p>create time" + nonce.getTimeStampsID()+ "</p>");
                out.println("<p>acct id" + nonce.getAccountInfoID() + "</p>");
                out.println("</body>");
                out.println("</html>");
                    
            }
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
