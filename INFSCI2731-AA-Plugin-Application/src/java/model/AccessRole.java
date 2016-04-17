/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Carol
 */
public class AccessRole {
        private int id;
	private String role;
 
        public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
        
        //create a regular role
        public int AccessRole(){
           return this.id;
        }
        
        //create a administrator
//        public int AccessRole(String s){
//        }
        
        //update a regular role to administrator
//        public void update{
//        
//        }
        
}
