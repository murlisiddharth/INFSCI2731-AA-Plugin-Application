/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dataAccessObject.ActivityLogDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Answer;
import model.Authentication;
import model.IPAddress;
import model.UserAccountInfo;
/**
 *This class is the controller to deal with user information in signup page
 * @author Hanwei Cheng
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
        System.out.println("==in servlet==");
        UserAccountInfo user = new UserAccountInfo();
        user.setFirstName(request.getParameter("firstname"));
        user.setLastName(request.getParameter("lastname"));
        user.setEmailAddress(request.getParameter("email"));
        user.setAccess_role_id(1);
        System.out.println("==in servlet==" + "firstname: "+ request.getParameter("firstname")+" lastname: "+request.getParameter("lastname"));
        request.setAttribute("user", user); 
        
        //create a session?
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        
        int accountId = user.register(); //modified by Siwei in order to get the new generated account id
        
        
        //log activity of new created account, by Siwei
        IPAddress ipAddress = new IPAddress();
//        String sysSource = request.getRequestURL().toString();
        String sysSource = request.getRequestURI();
        String ipAddr = ipAddress.getClientIpAddress(request);
        ActivityLogDao logDao = new ActivityLogDao();
        logDao.logNewAccountCreated(ipAddr, sysSource, accountId);
        
        
        //transfer the password from the signup page
        Authentication aut = new Authentication();
        aut.createNewAuth(accountId, request.getParameter("password"));
        
        //set security Question &answer 1
        Answer qa1 = new Answer();
        qa1.setAccount_info_id(user.getId());
        int question1 = Integer.parseInt(request.getParameter("secQue1"));
        qa1.setSecurity_question_id(question1);
        qa1.setAnswer(request.getParameter("answer1"));
        
 
        //set security question &answer 2
        Answer qa2 = new Answer();
        qa2.setAccount_info_id(user.getId());
        int question2 = Integer.parseInt(request.getParameter("secQue2"));
        qa2.setSecurity_question_id(question2);
        qa2.setAnswer(request.getParameter("answer2"));
        
        
        //set security question 3
        Answer qa3 = new Answer();
        qa3.setAccount_info_id(user.getId());
        int question3 = Integer.parseInt(request.getParameter("secQue3")); //selector???? 
        qa3.setSecurity_question_id(question3);
        qa3.setAnswer(request.getParameter("answer3"));
        

        //create 3 question &answer records for one user
        qa1.generateQARecord();
        qa2.generateQARecord();
        qa3.generateQARecord();
        
        //forward server's request to jsp
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
       
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
