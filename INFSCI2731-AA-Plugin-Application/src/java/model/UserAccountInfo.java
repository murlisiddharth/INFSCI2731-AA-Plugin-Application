package model;


/**
 *
 * @modified by Carol
 */

import dataAccessObject.UserDao;

public class UserAccountInfo {

    private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private long timeStampsID;
    private int access_role_id;
    private UserDao dao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public long getTimeStampsID() {
        return timeStampsID;
    }

    public void setTimeStampsID(long timeStampsID) {
        this.timeStampsID = timeStampsID;
    }

    public int getAccess_role_id() {
        return access_role_id;
    }

    public void setAccess_role_id(int access_role_id) {
        this.access_role_id = access_role_id;
    }

    public UserDao getUserDao() {
        return dao;
    }

    public void setUserDao(UserDao dao) {
        this.dao = dao;
    }

    //create new account
    public int register() {
        // create new timestamp and return id
        TimeStamp time = new TimeStamp();
        long timeID = time.getTimeStampsID();
        //create new role and return id
        Role role = new Role();
        int roleID = role.getId();
        System.out.println("==roleID=="+ roleID);
        //create a new account
        dao = new UserDao();
        System.out.println("===== bean " + firstName + "   " + emailAddress);
        
        //create account and return the generated id
        return this.id = dao.createAccount(firstName,lastName, emailAddress, timeID, roleID);
        
        
    }

}
