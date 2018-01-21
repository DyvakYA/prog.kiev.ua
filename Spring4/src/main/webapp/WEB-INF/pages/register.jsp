<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
<div align="center">
    <c:if test="${exists ne null}">
        <p>User already exists!</p>
    </c:if>
    <form:form action="/newuser" method="post" modelAttribute="user">
        <table>
            <tr>
                <th>Name</th>
                <td>
                    <form:input path="login" />
                    <form:errors path="login" cssClass="error" />
                </td>
            </tr>
            <tr>
                <th>Password</th>
                <td>
                    <form:input path="password" />
                    <form:errors path="password" cssClass="error" />
                </td>
            </tr>
            <tr>
                <th>Email</th>
                <td>
                    <form:input path="email" />
                    <form:errors path="email" cssClass="error" />
                </td>
            </tr>
            <tr>
                <th>Phone</th>
                <td>
                    <form:input path="phone" />
                    <form:errors path="phone" cssClass="error" />
                </td>
            </tr>


            <tr>
                <td><button type="submit">Submit</button></td>
            </tr>
        </table>
    </form:form>

</div>
</body>
</html>
