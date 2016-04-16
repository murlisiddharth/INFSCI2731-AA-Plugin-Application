package dataAccessObject;

import java.lang.*;
import java.security.*;
import java.sql.*;
import java.util.Arrays;

import DbConnect.DbConnection;

public class AuthenticationDao {
	
    private Connection connection;
    Statement st = null;
    ResultSet rs = null;
    String sqlCallforAccountID,sqlCallForHash;
    
    public AuthenticationDao(){
    		connection = DbConnection.getConnection();
    }
    
    //function would validateUser based on the provided username and password
    // Success : Account_ID of User is returned
    // Failure : NULL Value is returned
	public Integer validateUser(String username, String passwd){
		
		byte[] retrievedHash;
		String retrievedSalt;
		Integer account_info_id;
	   
		try{
	    	sqlCallforAccountID = "Select id from account_info where email_addr = ?";
	    	PreparedStatement preparedStatement = connection.prepareStatement(sqlCallforAccountID);
	    	preparedStatement.setString(1, username);
	    	rs = preparedStatement.executeQuery();
	    	if(rs.next()){
	    		
	    		account_info_id = rs.getInt("id");
	    		System.out.println("Account ID: "+account_info_id);
	    		
	    		sqlCallForHash = "Select hash, salt "
		    			+ "from authentication "
		    			+ "where account_info_id = ?";
	    		
	    		preparedStatement = connection.prepareStatement(sqlCallForHash);  
		    	preparedStatement.setInt(1, account_info_id);
		    	rs = null;
		    	rs = preparedStatement.executeQuery();
		    	
		    	if(rs.next()){
		    		retrievedHash = rs.getBytes("hash");
		    		retrievedSalt = rs.getString("salt");
		    		byte[] calculatedHash = computeHash(passwd, retrievedSalt);
		    		if(MessageDigest.isEqual(calculatedHash, retrievedHash)){
		    			//passwords matched
		    			return account_info_id;
		    		}else{
		    			//Password does not match..
		    			System.out.print("Passwd does not match");
		    			return null;
		    		}
		    	}else{
		    		//Password does not exist
		    		System.out.print("Passwd does not exist");
		    		return null;
		    	}
	    		
	    	}else{
	    		//User name does not exist
	    		System.out.print("Username does not exist");
	    		return null;
	    	}
	    }catch(Exception e){
	    	e.printStackTrace();
	    	return null;
	    }
		
	}
	
	//function computes MD5 hash for given salt and password
	// Returns byte[] for computed hash
	public byte[] computeHash(String passwd, String salt){
		
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] passwdBytes = passwd.getBytes();
			byte[] saltBytes = salt.getBytes();
			md.update(passwdBytes);
			md.update(saltBytes);
			byte[] hash = md.digest();
			return hash;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
