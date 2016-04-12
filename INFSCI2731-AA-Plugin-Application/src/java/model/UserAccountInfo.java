package model;

public class UserAccountInfo {
	
	private int id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private long timeStampsID;
	private int access_role_id;
	
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
}
