<%--
  Created by IntelliJ IDEA.
  User: Unclean Soul
  Date: 11/9/2023
  Time: 7:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*, Servlets.DatabaseConnection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>ToDo List</title>
</head>
<body>
<h2>ToDo List</h2>
<form action="AddItem" method="post">
    <label>
        <input type="text" name="item" placeholder="Add new item">
    </label>
    <input type="submit" value="Add">
</form>
<hr>
<h3>Current Items:</h3>
<%
    DatabaseConnection dao = new DatabaseConnection("jdbc:mysql://localhost:3306/todolist", "root", "pass123");
    List<String> items;
    try {
        items = dao.getAllItems();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    for(String to_Do_item: items){
%>
<p>
    <%= to_Do_item %>
    <a href="DeleteItem?item=<%= to_Do_item %>">Delete</a>
</p>
<%
    }
%>
</body>
</html>
