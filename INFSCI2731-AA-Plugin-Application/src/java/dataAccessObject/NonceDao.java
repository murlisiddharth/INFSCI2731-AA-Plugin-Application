/*
 * This class contains methods to manipulate nonce
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

/**
 *
 * @author Siwei Jiao
 */
public class NonceDao {
    private Connection connection;
    private String nonceval = "";
    Statement st = null;
    ResultSet rs = null;

    String sql = "";
    int autoKey = 0;
    
    public NonceDao() {
        //connect to database and select the record
        connection = DbConnection.getConnection();	
    }
       
    /**
     * create a new nonce that related to a user account
     * @param accoutInfoID
     * @return String Nonce
     */
    public int createNewNonce(int accoutInfoID) {
        SecureRandom srnd = new SecureRandom();
    /*This BigInteger constructor is used to construct a randomly generated BigInteger, 
      uniformly distributed over the range 0 to (2^maxBitLength - 1), inclusive. 
       mysql signed BIGINT ranges from  -9223372036854775808 to 9223372036854775808, equal to 2^63 − 1
    */
        int maxBitLength = 63;
        BigInteger val = new BigInteger(maxBitLength, srnd);
        BigDecimal nonceValue = new BigDecimal(val);
        
        TimeStampDao timeStampDao = new TimeStampDao();
        long timeStampsID = timeStampDao.setUpTimeStamp();
                
        try {
                sql = "INSERT INTO INFSCI2731.nonce (nonce, timestamps_id, account_info_id) values (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);  
                // Parameters start with 1
                preparedStatement.setBigDecimal(1, nonceValue);               
                preparedStatement.setLong(2, timeStampsID);               
                preparedStatement.setInt(3, accoutInfoID);               
                preparedStatement.executeUpdate();
                
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if(rs.next()) {
                    nonceval = nonceValue.toPlainString();
                    return autoKey = rs.getInt(1);
                }
                else
                    return 0;
                
		} catch (SQLException e) {
			e.printStackTrace();
                    return -1;            
		}                 
    }
    
    /**
     * get Nonce object which include all db nonce info related to a user account by nonce id 
     * @param nonceID
     * @return Nonce object
     */
    public Nonce getNonceById(int nonceID) {
        Nonce nonce = new Nonce();

        try{
            //prepare and execute search query
            sql = "SELECT * FROM INFSCI2731.nonce WHERE id = ?"; 
            PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1,nonceID) ;                
                rs = ps.executeQuery() ;

            while (rs.next()) { 
                nonce.setNonceID(rs.getInt("id"));
                nonce.setNonceValue(rs.getBigDecimal("nonce"));
                nonce.setTimeStampsID(rs.getInt("timestamps_id"));
                nonce.setAccountInfoID(rs.getInt("account_info_id"));                        
            }

        rs.close();

        } catch (SQLException e) {
                e.printStackTrace();
        }    
               
        return nonce;
    }
    
    public String getNonceValue() {
        return nonceval;
    }
    
    
    
}
