package org.skstar.to_do_app.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter({"/addTask" , "/deleteTask" ,"/editTaskStatus", "/updateTask" ,"home.jsp" , "edit-task.jsp"})
public class SeasionValidation implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		if (httpServletRequest.getSession().getAttribute("user")==null) {
			response.getWriter().print("<h1>Invalid seassion log in </h1>");
			httpServletRequest.getRequestDispatcher("login.html").include(httpServletRequest, response);
			
		}else {
			chain.doFilter(httpServletRequest, response);
		}
		
	}

}
