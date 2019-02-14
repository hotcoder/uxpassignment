<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User List</title>
    <link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <h2>User List</h2>
    <table class="table table-striped">
        <thead>
        <th scope="row">#ID</th>
        <th scope="row">Name</th>
        <th scope="row">Status</th>
        <th scope="row">Update</th>
        <th scope="row">Delete</th>
        </thead>
        <tbody>
        <c:forEach items="${userList }" var="user" >
            <tr>
                <td>${user.id }</td>
                <td>${user.userName }</td>
                <td>${user.status }</td>
                <td>
                    <spring:url value="/assignment/updateUser/${user.id }" var="updateURL" />
                    <a class="btn btn-primary" href="${updateURL }" role="button" >Update</a>
                </td>
                <td>
                    <spring:url value="/assignment/deleteUser/${user.id }" var="deleteURL" />
                    <a class="btn btn-primary" href="${deleteURL }" role="button" >Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <spring:url value="/assignment/addUser/" var="addURL" />
    <a class="btn btn-primary" href="${addURL }" role="button" >Add New User</a>
</div>
</body>
</html>