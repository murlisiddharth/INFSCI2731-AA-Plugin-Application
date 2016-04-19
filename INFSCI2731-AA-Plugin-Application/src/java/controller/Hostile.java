/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DbConnect.DbConnection;
import dataAccessObject.HostileDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import model.HostileStructure;
import dataAccessObject.TimeStampDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zhirun Tian
 */
public class Hostile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected HostileStructure hostileEntry;

    protected List<HostileStructure> HostileList = new ArrayList<HostileStructure>();

    public HostileDao hostileDao = new HostileDao();

    public Hostile() {
        super();
    }

    /**
     *
     * @param countAttempts
     * @param IPAddress
     * @param SYSTEM_SOURCE
     */
    protected Hostile(int countAttempts, String IPAddress, String SYSTEM_SOURCE) {
        super();
        hostileEntry.setIPAddress(IPAddress);
        hostileEntry.setSYSTEM_SOURCE(SYSTEM_SOURCE);

    }
//
//    public Hostile(int countAttempts, String IPAddress, String SYSTEM_SOURCE) {
//        super();
//        hostileEntry.setIPAddress(IPAddress);
//        hostileEntry.setSYSTEM_SOURCE(SYSTEM_SOURCE);
//
//        HostileList = new ArrayList<HostileStructure>();
//
//        hostileDao.WriteHostileToDB(countAttempts, IPAddress, SYSTEM_SOURCE);
//
//    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        if (request.getParameter("countAttempt") != null) {

            String countString = request.getParameter("countAttempt");
            String IPaddress = request.getParameter("IPAddress");
            String SystemValue = request.getParameter("SYSTEM_SOURCE");

            int count2 = Integer.parseInt(countString);

            hostileDao.WriteHostileToDB(count2, IPaddress, SystemValue);

            int count = Integer.valueOf(request.getParameter("CountAttempts"));

            //response.setContentType("text/html;charset=UTF-8");
//            try (PrintWriter out = response.getWriter()) {
//                /* TODO output your page here. You may use following sample code. */
//                out.println("<!DOCTYPE html>");
//                out.println("<html>");
//                out.println("<head>");
//                out.println("<title>Hostile entry list</title>");
//                out.println("</head>");
//                out.println("<body>");
//                out.println("write to db successful");
//                out.println("count=" + count2 + "ipaddress=" + IPaddress + "systemvariable=" + SystemValue);
//                out.println("</body>");
//                out.println("</html>");
//
//            }

        }

        String action = request.getParameter("action");

        boolean i2 = action.equalsIgnoreCase("getHostile");

        
        if (action.equalsIgnoreCase("getHostile")) {
            HostileList = hostileDao.GetHostileFromLogDB();

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Hostile entry list</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>TEST</h1>");

                for (HostileStructure tempHostile : HostileList) {

                    out.println("<p>IPAddress: " + tempHostile.getIPAddress() + "</p>");
                    out.println("<p>System Source: " + tempHostile.getSYSTEM_SOURCE() + "</p>");

                }

                out.println("</body>");
                out.println("</html>");

            }
        }

    }

    public void redirectHostile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Hostile Redirect</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Hostile Redirect Example Page</h1>");
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
