/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccessObject;

import DbConnect.DbConnection;
import model.Nonce;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.TimeStamp;
/**
 *
 * @author Siwei Jiao
 */
public class ActivityLogDao {
    private Connection connection;
    ResultSet rs = null;

    String sql = "";
    int autoKey = 0;
    
    public ActivityLogDao() {
        //connect to database and select the record
        connection = DbConnection.getConnection();	
    }
    
    public int logNewAccountCreated(String ip, String sysSource, int accoutInfoID) {
         
        TimeStamp time = new TimeStamp();
        long timeStampsID = time.getTimeStampsID();
        String desc = "new acount created";
                
        //prepare and execute search query
        try{   
                sql = "INSERT INTO INFSCI2731.activity_log (ip_addr, system_source, activity_count, timestamps_id, description, account_info_id) "
                        + "values (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);  
                ps.setString(1, ip);          
                ps.setString(2, sysSource);          
                ps.setInt(3, 1); 
                ps.setLong(4, timeStampsID);               
                ps.setString(5, desc); 
                ps.setInt(6, accoutInfoID); 
                           
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();

                //check if query returns one valid result 
                if(rs.next())
                    return autoKey = rs.getInt(1);
                else
                    return 0;

            }catch (Exception e) {
                    System.out.println(e.getMessage()) ;
                    return -1;
            }        
    }
    
    public int logUserNameFailedLoginAttempt(String ip, String sysSource, String userEneteredEmail) {
         
        TimeStamp time = new TimeStamp();
        long timeStampsID = time.getTimeStampsID();
        String desc = "non exist email: ";
                
        //prepare and execute search query
        try{   
                sql = "INSERT INTO INFSCI2731.activity_log (ip_addr, system_source, activity_count, timestamps_id, description, account_info_id) "
                        + "values (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);  
                ps.setString(1, ip);          
                ps.setString(2, sysSource);          
                ps.setInt(3, 1); 
                ps.setLong(4, timeStampsID);               
                ps.setString(5, desc+userEneteredEmail); 
                ps.setInt(6, 0); 
                           
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();

                //check if query returns one valid result 
                if(rs.next())
                    return autoKey = rs.getInt(1);
                else
                    return 0;

            }catch (Exception e) {
                    System.out.println(e.getMessage()) ;
                    return -1;
            }        
    }
    
    public int logPwFailedLoginAttempt(String ip, String sysSource, int accoutInfoID) {
         
        TimeStamp time = new TimeStamp();
        long timeStampsID = time.getTimeStampsID();
        String desc = "password failed";
                
        //prepare and execute search query
        try{   
                sql = "INSERT INTO INFSCI2731.activity_log (ip_addr, system_source, activity_count, timestamps_id, description, account_info_id) "
                        + "values (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);  
                ps.setString(1, ip);          
                ps.setString(2, sysSource);          
                ps.setInt(3, 1); 
                ps.setLong(4, timeStampsID);               
                ps.setString(5, desc); 
                ps.setInt(6, accoutInfoID); 
                           
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();

                //check if query returns one valid result 
                if(rs.next())
                    return autoKey = rs.getInt(1);
                else
                    return 0;

            }catch (Exception e) {
                    System.out.println(e.getMessage()) ;
                    return -1;
            }        
    }
    
    public int logSucceedLogon(String ip, String sysSource, int accoutInfoID) {
         
        TimeStamp time = new TimeStamp();
        long timeStampsID = time.getTimeStampsID();
        String desc = "successfully logon";
                
        //prepare and execute search query
        try{   
                sql = "INSERT INTO INFSCI2731.activity_log (ip_addr, system_source, activity_count, timestamps_id, description, account_info_id) "
                        + "values (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);  
                ps.setString(1, ip);          
                ps.setString(2, sysSource);          
                ps.setInt(3, 1); 
                ps.setLong(4, timeStampsID);               
                ps.setString(5, desc); 
                ps.setInt(6, accoutInfoID); 
                           
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();

                //check if query returns one valid result 
                if(rs.next())
                    return autoKey = rs.getInt(1);
                else
                    return 0;

            }catch (Exception e) {
                    System.out.println(e.getMessage()) ;
                    return -1;
            }        
    }
    
}
