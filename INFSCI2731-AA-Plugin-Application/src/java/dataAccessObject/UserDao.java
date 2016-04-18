
package dataAccessObject;
import DbConnect.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *This class is to create DAO for user
 * @author Hanwei Cheng
 */

public class UserDao {
    private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private long timeStampsID;
    private int access_role_id;
    private Connection connection;
    Statement st = null;
    ResultSet rs = null;
    String sql = "";
    
     public UserDao() {
        //connect to database and select the record
        connection = DbConnection.getConnection();
        System.out.println("UserDao connection");
    }
    
    //create account by passing variable value from user instance
    public int createAccount(String firstName,String lastName, String emailAddress,long timeStampsID,int access_role_id){
        
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
                    int autoKey = rs.getInt(1);
                    return autoKey;
                } else
                    return -1;
                
        } catch (SQLException e) {
                e.printStackTrace();
            return -1;            
        }         
       
    }
    
    //update account
//    public void updateAccount(){
//    
//    }
        
        
}

