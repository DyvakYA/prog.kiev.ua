<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<body>
<form name="add" method="post" action="/add">
    <p><b>Name:</b><br>
        <input type="text" name="name" size="40">
    </p>
    <p><b>Age:</b><br>
        <input type="text" name="age" size="40">
    </p>
    <p>
        <input type="submit" value="Run">
    </p>
</form>
<form name="del" action="/add" method="get">
    <table border="1">
        <tr>
            <td><b>ID</b></td>
            <td><b>Name</b></td>
            <td><b>Age</b></td>
            <td><b>Delete</b></td>
        </tr>
        <c:forEach var="item" items="${list}">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.age}</td>
                <td><input type="checkbox" name="id" value="${item.id}"></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Delete">
</form>
</body>
</html>
