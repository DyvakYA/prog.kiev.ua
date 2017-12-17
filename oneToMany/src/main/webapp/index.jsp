<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<body>
<form name="add" method="post" action="/add">
    <p><b>Name:</b><br>
        <input type="text" name="name" size="40">
    </p>
    <p>
        <b>Age:</b><br>
        <input type="text" name="age" size="40">
    </p>
    <h4>Main address</h4>
    <p><b>Country:</b><br>
        <input type="text" name="country" size="40">
    </p>
    <p><b>City:</b><br>
        <input type="text" name="city" size="40">
    </p>
    <p><b>Street:</b><br>
        <input type="text" name="street" size="40">
    </p>
    <h4>Second address</h4>
    <p><b>Country:</b><br>
        <input type="text" name="country2" size="40">
    </p>
    <p><b>City:</b><br>
        <input type="text" name="city2" size="40">
    </p>
    <p><b>Street:</b><br>
        <input type="text" name="street2" size="40">
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
            <td><b>Address</b></td>
            <td><b>Delete</b></td>
        </tr>
        <c:forEach var="item" items="${list}">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.age}</td>
                <td>
                <table border="1">
                    <c:forEach var="address" items="${item.getAddresses()}">
                        <tr>
                            <td>${address.country}</td>
                            <td>${address.city}</td>
                            <td>${address.street}</td>
                        </tr>
                    </c:forEach>
                </table>
                </td>
                <td><input type="checkbox" name="id" value="${item.id}"></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Delete">
</form>
</body>
</html>
