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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	ToDoDao dao = new ToDoDao();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			Long umob = Long.parseLong(req.getParameter("uname"));

			if (dao.findByMob(umob).isEmpty()) {
				resp.getWriter().print("<h1>Enter correct phone number / Account is not available </h1>");

			} else {
				if (dao.findByMob(umob).get(0).getUpass().equals(req.getParameter("upass"))) {
					resp.getWriter().print("<h1>Log in succesfulll </h1>");

					User user = dao.findByMob(umob).get(0);

					req.getSession().setAttribute("user", user);

					List<Task> tasks = dao.fetachAllTaskByUserId(user.getId());
					
					req.setAttribute("tasks", tasks);

					req.getRequestDispatcher("home.jsp").include(req, resp);

				} else {

					resp.getWriter().print("<h1>Enter valid password </h1>");
					req.getRequestDispatcher("login.html").include(req, resp);

				}
			}
		} catch (NumberFormatException e) {

			if (dao.findByEmail(req.getParameter("uname")).isEmpty()) {
				resp.getWriter().print("<h1>Enter correct Mail id / Account is not available </h1>");

			} else {

				if (dao.findByEmail(req.getParameter("uname")).get(0).getUpass().equals(req.getParameter("upass"))) {

					User user = dao.findByEmail(req.getParameter("uname")).get(0);

					req.getSession().setAttribute("user", user);
					resp.getWriter().print("<h1>Log in succesfulll </h1>");
					List<Task> tasks = dao.fetachAllTaskByUserId(user.getId());
					req.setAttribute("tasks", tasks);
					req.getRequestDispatcher("home.jsp").include(req, resp);

				} else {
					resp.getWriter().print("<h1>Enter valid password </h1>");
					req.getRequestDispatcher("login.html").include(req, resp);

				}

			}

		}

	}

}
