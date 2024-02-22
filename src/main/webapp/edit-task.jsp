<%@page import="org.skstar.to_do_app.dto.Task"%>
<%@page import="org.skstar.to_do_app.dao.ToDoDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="updateTask"  method="post" enctype="multipart/form-data">

<% int id = Integer.parseInt(request.getParameter("id")); %>

<%ToDoDao dao = new ToDoDao();%>
<%Task task = dao.fetachTaskById(id); %>

<label>Name </label> <input type="text" name="taskName" value="<%=task.getName()%>"> <br> 

			<input type="hidden"  name="id" value="<%=task.getId()%>">
		
		<label>Description</label><input type="text" name="description" value="<%=task.getDiscription()%>"> <br>
		
		 <label>Select image of task</label> <input type="file"  name="image"> <br>
		 
		  <img height="50px" width="50px" alt="Task Image" src="data:image/png;base64,<%=task.getEncodedImage()%>">


		<button type="submit">Update task</button>





</form>

</body>
</html>