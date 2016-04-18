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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import model.HostileStructure;
import dataAccessObject.TimeStampDao;
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
    
    protected Hostile(int countAttempts, String IPAddress,  String SYSTEM_SOURCE)
    {
        super(); 
        //hostileEntry.setIPAddress(IPAddress);
        //hostileEntry.setSYSTEM_SOURCE(SYSTEM_SOURCE);

        WriteHostileToDB(countAttempts, IPAddress, SYSTEM_SOURCE);  
    }
    
    protected int WriteHostileToDB(int countAttempts, String IPAddress,  String SYSTEM_SOURCE) {
        Connection connection;
        PreparedStatement preparedStatement;
        
        TimeStampDao timeStampDao = new TimeStampDao();
        long timeStampsID = timeStampDao.setUpTimeStamp();
        
        try {
            connection = DbConnection.getConnection();

            String sql = "INSERT INTO infsci2731.activity_log (ip_addr,system_source,activity_count,timestamps_id,description,account_info_id) VALUES (?,?,?,?,?,?)";
            
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);            
            preparedStatement.setString(1, IPAddress);
            preparedStatement.setString(2, SYSTEM_SOURCE); 
            preparedStatement.setInt(3, countAttempts); 
            preparedStatement.setLong(4, timeStampsID);               
            preparedStatement.setString(5, "");    
            preparedStatement.setInt(6, -1); 
            preparedStatement.executeUpdate();
            
            return 0;
            
        } catch (SQLException e) {
                e.printStackTrace();
                return -1;
        }   
    }    
    
    protected HostileStructure GetHostileFromLogDB() {
        
        HostileStructure temphostileEntry = new HostileStructure();
        Connection connection;
        PreparedStatement preparedStatement;
        
        TimeStampDao timeStampDao = new TimeStampDao();
        long timeStampsID = timeStampDao.setUpTimeStamp();
        
        try {
            connection = DbConnection.getConnection();

            String sql = "SELECT * FROM infsci2731.activity_log WHERE system_source = ?";
            
            preparedStatement = connection.prepareStatement(sql);            
            preparedStatement.setString(1, "QuestionForm");
            
            ResultSet rs = preparedStatement.executeQuery();
            
            boolean val = rs.next();
            if (!val) {
                return null;
            } else {
                temphostileEntry.setIPAddress(rs.getString("ip_addr"));
                temphostileEntry.setSYSTEM_SOURCE(rs.getString("system_source"));
                return hostileEntry; 
            }
            
        } catch (SQLException e) {
                e.printStackTrace();
                return null;
        }   
    }  
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
         if(action.equalsIgnoreCase("getHostile")) 
         {
            HostileStructure temphostileEntry = GetHostileFromLogDB();
             
                
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Hostile entry list</title>");            
                out.println("</head>");
                out.println("<body>");
                
                while(temphostileEntry != null){
                    out.println("<p>IPAddress: " + temphostileEntry.getIPAddress() + "</p>");
                    out.println("<p>System Source: " + temphostileEntry.getSYSTEM_SOURCE() + "</p>");
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
