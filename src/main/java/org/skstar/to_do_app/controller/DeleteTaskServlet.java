package org.skstar.to_do_app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.skstar.to_do_app.dao.ToDoDao;
import org.skstar.to_do_app.dto.Task;
import org.skstar.to_do_app.dto.User;

@WebServlet("/deleteTask")
public class DeleteTaskServlet  extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		ToDoDao dao = new ToDoDao();
		int id = Integer.parseInt(req.getParameter("id"));
		
		dao.deleteTask(dao.fetachTaskById(id));
		
		User user = (User) req.getSession().getAttribute("user");
		List<Task> tasks = dao.fetachAllTaskByUserId(user.getId());
		
		req.setAttribute("tasks", tasks);
		req.getRequestDispatcher("home.jsp").include(req, resp);
		
		
	}

	
}
