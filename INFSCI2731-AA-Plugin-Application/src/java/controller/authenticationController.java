package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccessObject.AuthenticationDao;

public class authenticationController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Integer MAX_NUMBER_OF_ATTEMPTS = 3;

	public authenticationController() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		AuthenticationDao auth = new AuthenticationDao();
		PrintWriter out = resp.getWriter();
		
		String username = req.getParameter("username");
		String passwd = req.getParameter("password");
                
                
		
		Integer lastAttempt = (Integer) session.getAttribute("lastAttempt");
		
		System.out.println("Number of login attempts :" + lastAttempt);
		
		if (lastAttempt == null) {
			session.setAttribute("lastAttempt", 1);
		} else {
			if (lastAttempt >= MAX_NUMBER_OF_ATTEMPTS) {
				// Redirect servlet to Hostile module
				System.out.println("Number of login attempts :" + lastAttempt);
				
				System.out.println("Redirect to Hostile Module");
			}
		}
		
		Integer account_id = auth.validateUser(username, passwd);
		if (account_id != null) {
			// User authenticated successfully
			// User account ID has been retrieved and
			// can used in session attribute to track for access control module
			System.out.println("Logged In Successfully");
			// remove lastAttempt from session object
			session.removeAttribute("lastAttempt");
			// add account_id into session for access control
			session.setAttribute("account_id", account_id);
                        
		} else {
			// authentication failed
			out.println("Username Or Password Is Incorrect");
			if(lastAttempt==null){
				lastAttempt = 1; //if first failed login attempt
				session.setAttribute("lastAttempt", lastAttempt);
			}else{
				session.setAttribute("lastAttempt", lastAttempt+1);
			}
			out.close();
			out.flush();
		}
	}
}
