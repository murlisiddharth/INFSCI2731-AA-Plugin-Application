/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccessObject;

import DbConnect.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Hanwei
 */
public class RoleDao {
    private Connection connection;
    Statement st = null;
    ResultSet rs = null;
    String sql = "";
    
     public RoleDao() {
        //connect to database and select the record
        connection = DbConnection.getConnection();
        System.out.println("==RoleDao connection==");
    }
     
     //get the id number for who is normal user
    public int getUserID(){
        
         try {
                sql = "INSERT INTO INFSCI2731.account_info(first_name, last_name, email_addr, timestamps_id, access_role_id) values (?, ?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);  
                // Parameters start with 1
                ps.setString(1, firstName);               
                ps.setString(2, lastName);               
                ps.setString(3, emailAddress);  
                ps.setLong(4, timeStampsID);
                ps.setInt(5,access_role_id);
                ps.executeUpdate();
                
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) {
                    long autoKey = rs.getLong(1);
                    return autoKey;
                } else
                    return -1;
                
        } catch (SQLException e) {
                e.printStackTrace();
            return -1;            
        }      
         
        //get the id number for who is normal user
        public long getAdminID(String role){
        
         try {
                sql = "INSERT INTO INFSCI2731.account_info(first_name, last_name, email_addr, timestamps_id, access_role_id) values (?, ?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);  
                // Parameters start with 1
                ps.setString(1, firstName);               
                ps.setString(2, lastName);               
                ps.setString(3, emailAddress);  
                ps.setLong(4, timeStampsID);
                ps.setInt(5,access_role_id);
                ps.executeUpdate();
                
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) {
                    long autoKey = rs.getLong(1);
                    return autoKey;
                } else
                    return -1;
                
        } catch (SQLException e) {
                e.printStackTrace();
            return -1;            
        }        
       
    }
     
    
}
