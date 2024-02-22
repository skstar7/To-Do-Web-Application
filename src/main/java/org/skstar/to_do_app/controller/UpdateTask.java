package org.skstar.to_do_app.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.skstar.to_do_app.dao.ToDoDao;
import org.skstar.to_do_app.dto.Task;
import org.skstar.to_do_app.dto.User;

@WebServlet("/updateTask")
@MultipartConfig
public class UpdateTask extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ToDoDao dao = new ToDoDao();

		Task task = new Task();
		int id = Integer.parseInt(req.getParameter("id"));
		
		User user = (User) req.getSession().getAttribute("user");
		
		
		Part image = req.getPart("image");
		
		byte[] pic = new byte[image.getInputStream().available()];
		
		image.getInputStream().read(pic);
		if(pic.length==0)
			task.setImage(dao.fetachTaskById(id).getImage());
		else 
		task.setImage(pic);
		task.setId(id);
		task.setName(req.getParameter("taskName"));
		task.setDiscription(req.getParameter("description"));
		task.setTime(LocalDateTime.now());
		task.setStatus(false);
		task.setUser(user);
		
		
		dao.updateTask(task);
		
		resp.getWriter().print("<h1>Task updated succesfully </h1>");
		
		List<Task> tasks = dao.fetachAllTaskByUserId(user.getId());
		req.setAttribute("tasks", tasks);

		req.getRequestDispatcher("home.jsp").include(req, resp);
	}

}
