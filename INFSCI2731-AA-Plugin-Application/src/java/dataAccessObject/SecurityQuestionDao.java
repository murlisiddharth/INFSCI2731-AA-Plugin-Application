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
import model.Nonce;

/**
 *This class is to create a DAO for security question
 * @author Hanwei Cheng
 */
public class SecurityQuestionDao {
    private String question;
    private int id;
    private Connection connection;
    Statement st = null;
    ResultSet rs = null;
    String sql = "";
    int recordNum;
    
     public SecurityQuestionDao() {
        //connect to database and select the record
        connection = DbConnection.getConnection();
        System.out.println("==SecurityQuestionDao connection==");
    }
    
    //check whether if there are any records in the table
     public boolean checkEmpty(){
         try{
             sql = "SELECT count(id) from INFSCI2731.security_question";
             PreparedStatement ps = connection.prepareStatement(sql);
             rs = ps.executeQuery() ;
             if(rs.next()){
                 recordNum = rs.getInt("count(id)");
                 if(recordNum == 0){
                     return true;
                 }
             }else
                return false;
         
        } catch (SQLException e) {
                e.printStackTrace();
                return false;
        }    
         
     }
     
     public void fillTable(){
         if(checkEmpty()){
             
         }
     }
    
    //get the content of the question according to the id
    public String getQuestionContent(int id) {
        
        try{
            //prepare and execute search query
            sql = "SELECT question FROM INFSCI2731.security_question WHERE id = ?"; 
            PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, id) ;                
                rs = ps.executeQuery() ;

            if (rs.next()) { 
                return rs.getString("question");                    
            }else
                return "error";
         
        } catch (SQLException e) {
                e.printStackTrace();
                return "error";
        }    
    }

}
