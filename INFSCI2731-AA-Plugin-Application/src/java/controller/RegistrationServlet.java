/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserAccountInfo;

/**
 *
 * @author Carol
 */
@WebServlet(name = "AccountInfoServlet", urlPatterns = {"/AccountInfoServlet"})
public class RegistrationServlet extends HttpServlet {

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
        System.out.println("in servlet");
        System.out.println("==" + request.getParameter("firstname"));
        UserAccountInfo user = new UserAccountInfo();
        user.setFirstName(request.getParameter("firstname"));
        user.setLastName(request.getParameter("lastname"));
        user.setEmailAddress(request.getParameter("email"));
        // user.setTimeStampsID();
        // setAccess_role_id();
        request.setAttribute("user", user);
        
        user.register();
        //forward server's request to jsp
        getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
       
//        String firstname =(String)request.getParameter("firstname");
//        String lastname =(String)request.getParameter("lastname");
//        String email =(String)request.getParameter("email");
//        
		
//        PrintWriter writer = response.getWriter();
//        String htmlResponse = "<html>";
//        htmlResponse += "<h2>Your name is: " + firstname + "</h2>";
//        htmlResponse += "<h2>Your name is: " + lastname + "</h2>";
//        htmlResponse += "<h2>Your mail is: " +email+ "</h2>";
//        htmlResponse += "</html>";
//
//        writer.println(htmlResponse);

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
