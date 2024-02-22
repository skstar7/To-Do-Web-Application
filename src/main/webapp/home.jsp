<%@page import="java.util.List"%>
<%@page import="org.skstar.to_do_app.dto.Task"%>
<%@page import="org.skstar.to_do_app.controller.AddTaskServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home page</title>
</head>
<body>
	<h1>Welcome to home page</h1>
	<%List<Task> tasks = (List<Task>) request.getAttribute("tasks");%>

	<%
	if (!tasks.isEmpty()) {
	%>
	<table border="">

		<tr>
			<th>Task Image</th>
			<th>Task Name</th>
			<th>Task description</th>
			<th>Task Status</th>
			<th>Task date and time</th>
			<th>Edit</th>
			<th>Delete</th>

		</tr>
		<%
		for (Task task : tasks) {
		%>
		<tr>
			<th> <img height="40px" width="40px" alt="Task Image" src="data:image/png;base64,<%=task.getEncodedImage()%>"> </th>
			<th><%=task.getName()%></th>
			<th>		
		<%=task.getDiscription()%></th>
			<th>		
		<%if(task.isStatus()) {%>
		Completed
		<%} else { %>
		<a href="editTaskStatus?id=<%=task.getId()%>" ><button>Complete</button></a>
		<%}%>
		
			<th>		
			<%=task.getTime()%></th>
			<th>		
			<a href="edit-task.jsp?id=<%=task.getId()%>"><button>Edit</button></a>
			</th>
			<th> <a href="deleteTask?id=<%=task.getId()%>"> <button>Delete</button></a>
			</th>

			</tr>
		
		 <%}%>
		 	 
		 
		</table>

		<%}%>

<a href="add-task.html">Add task</a>  <br> <br>  <a
		href="logOut">Logout</a>

</body>
</html>