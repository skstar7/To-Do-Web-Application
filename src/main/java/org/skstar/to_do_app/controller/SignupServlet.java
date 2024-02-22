package org.skstar.to_do_app.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.skstar.to_do_app.dao.ToDoDao;
import org.skstar.to_do_app.dto.User;

@WebServlet("/signUp")
public class SignupServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uname = req.getParameter("uname");
		long umob = Long.parseLong(req.getParameter("umob"));
		String umail = req.getParameter("umail");
		String upass = req.getParameter("upass");
		LocalDate udob = LocalDate.parse(req.getParameter("udob"));
		String gender = req.getParameter("gender");

		User user = new User();
		ToDoDao dao = new ToDoDao();

		user.setUname(uname);
		user.setUmob(umob);
		user.setUmail(umail);
		user.setUpass(upass);
		user.setUdob(udob);
		user.setGender(gender);
		
		
		if (dao.findByEmail(umail).isEmpty() && dao.findByMob(umob).isEmpty()) {
			
			dao.insert(user);
			
			resp.getWriter().print("<h1>Account created succesfully </h1>");
			
			req.getRequestDispatcher("login.html").include(req, resp);
			
		} else {
			if ((!dao.findByEmail(umail).isEmpty()) && (!dao.findByMob(umob).isEmpty()) ) {
				
				resp.getWriter().print(" <h1>Enter different Email and Phone Number </h1>");
				
			} else if (!dao.findByEmail(umail).isEmpty()){
				
				resp.getWriter().print("<h1>Enter different email</h1>");
			}	else {
				
				resp.getWriter().print("<h1>Enter different phone number </h1>");
			}
	
			req.getRequestDispatcher("signup.html").include(req, resp);
		}

	}

}
