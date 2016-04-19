
package model;

import dataAccessObject.AuthenticationDao;
import java.security.SecureRandom;
import java.util.Random;

public class Authentication {
	private int id;
	private String hash;
	private int account_info_id;
	private long timeStampsID;
        private String password_salt;
        private boolean active;
        
        //for generate salt use
        private static final Random random = new SecureRandom();
        private static final int ITERATIONS = 10000;
        private static final int KEY_LENGTH = 256;
        

        public void setActive(boolean active) {
            this.active = active;
        }

        public boolean isActive() {
            return active;
        }

        public void setPassword_salt(String password_salt) {
            this.password_salt = password_salt;
        }

        public String getPassword_salt() {
            return password_salt;
        }
        
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public int getAccount_info_id() {
		return account_info_id;
	}
	public void setAccount_info_id(int account_info_id) {
		this.account_info_id = account_info_id;
	}
	public long getTimeStampsID() {
		return timeStampsID;
	}
	public void setTimeStampsID(long timeStampsID) {
		this.timeStampsID = timeStampsID;
	}
	

  /**
    *This method is to create a salt
    * @author: Hanwei Cheng
    */  
    public static byte[] getNextSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
        
    }
    
    /**
    *This method is to create a authentication table record for new user
    * @author: Hanwei Cheng
    */     
        public void createNewAuth(String password){
            TimeStamp time = new TimeStamp();
            long timeID = time.getTimeStampsID();
            //salt ???
            this.password_salt = new String(getNextSalt());
            System.out.println("==salt: " + password_salt + "==");
            AuthenticationDao dao = new AuthenticationDao();
            //hash ???
            this.hash = new String(dao.computeHash(password, password_salt));
            System.out.println("==hash: " + hash + "==");
            //create a new record
            System.out.println("===== Authentication bean " + " hash: " + hash + " salt: " + password_salt + " account_info_id: "+ account_info_id +" ====");
            this.id =(int)dao.createAuthentication(hash, password_salt, account_info_id, true, timeID);
        }
        
}
