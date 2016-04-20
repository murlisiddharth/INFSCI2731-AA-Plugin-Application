
package model;

import dataAccessObject.AuthenticationDao;
import dataAccessObject.SavePasswordDao;
import java.security.SecureRandom;
import java.util.Random;

public class Authentication {
	private int id;
	private byte[] hash;
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
	public byte[] getHash() {
		return hash;
	}
	public void setHash(byte[] hash) {
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
        public void createNewAuth(int accountID, String password){
             TimeStamp time = new TimeStamp();
            long timeID = time.getTimeStampsID();
            System.out.println("===== Authentication bean " + " hash: " + hash + " salt: " + password_salt + " account_info_id: "+ account_info_id +" ====");
            SavePasswordDao savePasswordDao = new SavePasswordDao();
            Boolean res = savePasswordDao.setNewPassword(accountID, password);
            System.out.println("==password&salt==" + res);
                
//                AuthenticationDao dao = new AuthenticationDao();
//                bytesalt = getNextSalt();
//                int salt = srnd.nextInt(90000000) + 10000000;
//                this.password_salt = String.valueOf(salt);
//                this.hash = dao.computeHash(password,password_salt);
//                this.id = dao.createAuthentication(hash, password_salt, account_info_id, true, timeID);
    
        }
        
}
