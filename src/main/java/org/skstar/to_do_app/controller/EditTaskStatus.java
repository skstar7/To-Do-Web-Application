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

@WebServlet("/editTaskStatus")
public class EditTaskStatus extends HttpServlet {
	
	ToDoDao dao = new ToDoDao();
	@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));
		
		Task task = dao.fetachTaskById(id);
		task.setStatus(true);
		dao.updateTask(task);
		
		User user = (User) req.getSession().getAttribute("user");
		List<Task> tasks = dao.fetachAllTaskByUserId(user.getId());
		
		req.setAttribute("tasks", tasks);
		req.getRequestDispatcher("home.jsp").include(req, resp);
		
		
	}

}
