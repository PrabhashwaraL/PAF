<%@page import="com.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
if(request.getParameter("itemCode") != null) {
	Item itemObj = new Item();
	itemObj.connection();
	
	String message = itemObj.insertItem(request.getParameter("itemCode"), request.getParameter("itemName"), request.getParameter("itemPrice"),
			request.getParameter("itemDesc"));
	
	session.setAttribute("status", message);
}
%>

<%
if(request.getParameter("itemID") != null) {
	Item itemObj = new Item();
	String message = itemObj.deleteItem(request.getParameter("itemID"));
	session.setAttribute("status", message);
}
%>
	
<%
if(request.getParameter("itemCode") != null) {
	session.setAttribute("ic", request.getParameter("itemCode"));
	session.setAttribute("in", request.getParameter("itemName"));
	session.setAttribute("ip", request.getParameter("itemPrice"));
	session.setAttribute("id", request.getParameter("itemDesc"));
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<!-- form -->
				<h1>Items Management</h1>
				<form method="post" action="items.jsp">
					Item code: <input name="itemCode" type="text" class="form-control"><br>
					Item name: <input name="itemName" type="text" class="form-control"><br>
					Item price:<input name="itemPrice" type="text" class="form-control"><br> Item
					description: <input name="itemDesc" type="text" class="form-control"><br> <input
						name="btnSubmit" type="submit" value="save" class="btn btn-primary">
				</form>
				
				<!-- Status of inserting process -->
				<br>
				<div class="alert alert-success">
					<%if(session.getAttribute("status") != null)
						out.print(session.getAttribute("status")); %>
				</div>
				<br>
				<%
					Item itemObj = new Item();
					out.print(itemObj.readItem());
				%>
			</div>
		</div>
	</div>
</body>
</html>