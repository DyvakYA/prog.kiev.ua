<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
<div align="center">
    <form action="/view" method="POST">
        Photo id: <input type="text" name="photo_id">
        <input type="submit"/>
    </form>

    <form action="/add_photo" enctype="multipart/form-data" method="POST">
        Photo: <input type="file" name="photo"><br>
        Photo: <input type="file" name="photo"><br>
        Photo: <input type="file" name="photo"><br>
        <input type="submit"/>
    </form>
</div>
<div align="center">
<form action="/delete" method="POST">
    <table border="1">
        <tr>
            <td>delete</td>
            <td>number</td>
            <td>name</td>
            <td>link</td>
        </tr>
        <c:forEach items="${photos}" var="photos">
            <tr>
                <td><input type="checkbox" name="photo_id" value="${photos.id}"></td>
                <td>${photos.id}</td>
                <td>${photos.name}</td>
                <td><a href="/photo/${photos.id}">/photo/${photos.id}</a></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="delete"/>
</form>
</div>
</body>
</html>
