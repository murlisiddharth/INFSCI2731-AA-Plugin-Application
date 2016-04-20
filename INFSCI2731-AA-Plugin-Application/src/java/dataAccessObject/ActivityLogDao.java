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
    
    public int logAccessAttempt(String ip, String sysSource, String desc) {
         
        TimeStamp time = new TimeStamp();
        long timeStampsID = time.getTimeStampsID();
                
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
    
    /**
     * 
     * @param ip
     * @param sysSource
     * @param accoutInfoID
     * @return 
     */
    public int logNewAccountCreated(String ip, String sysSource, int accoutInfoID) {
         
        TimeStamp time = new TimeStamp();
        long timeStampsID = time.getTimeStampsID();
        String desc = "new account created";
                
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
    
    /**
     * 
     * @param ip
     * @param sysSource
     * @param userEneteredEmail
     * @return 
     */
    public int logEmailFailedLoginAttempt(String ip, String sysSource, String userEneteredEmail) {
         
        TimeStamp time = new TimeStamp();
        long timeStampsID = time.getTimeStampsID();
        String desc = "(login)non exist email: ";
                
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
    
    /**
     * 
     * @param ip
     * @param sysSource
     * @param accoutInfoID
     * @return 
     */
    public int logPwFailedLoginAttempt(String ip, String sysSource, int accoutInfoID, String desc) {
         
        TimeStamp time = new TimeStamp();
        long timeStampsID = time.getTimeStampsID();
                
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
    
    /**
     * 
     * @param ip
     * @param sysSource
     * @param accoutInfoID
     * @return 
     */
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
    
    /**
     * 
     * @param ip
     * @param sysSource
     * @param userEneteredEmail
     * @return 
     */
    public int logEmailFailedOnForgotPw(String ip, String sysSource, String userEneteredEmail) {
         
        TimeStamp time = new TimeStamp();
        long timeStampsID = time.getTimeStampsID();
        String desc = "(forgot pw)non exist email: ";
                
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
    
    /**
     * 
     * @param ip
     * @param sysSource
     * @param accoutInfoID
     * @return 
     */
    public int logAnswerSecurQuestFailedOnForgotPw(String ip, String sysSource, int accoutInfoID) {
         
        TimeStamp time = new TimeStamp();
        long timeStampsID = time.getTimeStampsID();
        String desc = "(forgot pw)failed answer securQuest";
                
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
    
    /**
     * 
     * @param accoutInfoID
     * @return 
     */
    public int checkResetPwSentLink(int accoutInfoID) {
        String desc = "(forgot pw)reset pw : sent link";

        try{
            //prepare and execute search query
            sql = "SELECT id FROM INFSCI2731.activity_log WHERE account_info_id = ? AND description = ?"; 
            PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1,accoutInfoID) ;                
                ps.setString(2,desc) ;                
                rs = ps.executeQuery() ;

            if(rs.next()) {
                return rs.getInt("id");
            }else
                return 0 ;


        } catch (SQLException e) {
                e.printStackTrace();
                return -1;
        }    
               
    }
    
    /**
     * 
     * @param ip
     * @param sysSource
     * @param accoutInfoID
     * @return 
     */
    public int logPreResetPwOnForgotPw(String ip, String sysSource, int accoutInfoID) {
         
        TimeStamp time = new TimeStamp();
        long timeStampsID = time.getTimeStampsID();
        String desc = "(forgot pw)reset pw : sent link";
                
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
    
    /**
     * 
     * @param ip
     * @param sysSource
     * @param accoutInfoID
     * @return 
     */
    public int logExpiredLinkOnForgotPw(String ip, String sysSource, int accoutInfoID) {
         
        TimeStamp time = new TimeStamp();
        long timeStampsID = time.getTimeStampsID();
        String desc = "(forgot pw)expired reset pw link";
                
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
    
    /**
     * 
     * @param desc
     * @param logID
     * @return 
     */
    public int updatePreResetPwRecord(String desc, int logID) {
               
        String preDesc = "(forgot pw)reset pw : sent link";
                
        //prepare and execute search query
        try{               
                sql = "UPDATE INFSCI2731.activity_log SET description = ? WHERE id = ?";
                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);  
                ps.setString(1, preDesc + ", " + desc); 
                ps.setInt(2, logID); 
                           
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
    
    /**
     * Role Based Permission check
     * @param ip
     * @param sysSource
     * @param desc
     * @param accoutInfoID
     * @return 
     */
    public int logRBPCheck(String ip, String sysSource, String desc, int accoutInfoID) {
         
        TimeStamp time = new TimeStamp();
        long timeStampsID = time.getTimeStampsID();
                
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
