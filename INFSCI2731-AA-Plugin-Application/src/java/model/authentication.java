package model;

public class authentication {
	private int id;
	private String hash;
	private int account_info_id;
	private long timeStampsID;
	
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
	
}
