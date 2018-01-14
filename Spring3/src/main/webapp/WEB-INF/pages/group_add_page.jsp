<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>New Group</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
    <form role="form" class="form-horizontal" action="/group/add" method="post">
        <div class="form-group"><h3>New Group</h3></div>
        <div class="form-group"><input type="text" class="form-control" name="group_name" placeholder="Name"></div>
        <div class="form-group"><input type="submit" class="btn btn-primary" value="Add group"></div>

        <hr>
        <h3>Contact 1</h3>
        <input class="form-control form-group" type="text" name="name" placeholder="Name">
        <input class="form-control form-group" type="text" name="surname" placeholder="Surname">
        <input class="form-control form-group" type="text" name="phone" placeholder="Phone">
        <input class="form-control form-group" type="text" name="email" placeholder="E-mail">
        <hr>
        <h3>Contact 2</h3>
        <input class="form-control form-group" type="text" name="name" placeholder="Name">
        <input class="form-control form-group" type="text" name="surname" placeholder="Surname">
        <input class="form-control form-group" type="text" name="phone" placeholder="Phone">
        <input class="form-control form-group" type="text" name="email" placeholder="E-mail">
        <hr>
        <h3>Contact 3</h3>
        <input class="form-control form-group" type="text" name="name" placeholder="Name">
        <input class="form-control form-group" type="text" name="surname" placeholder="Surname">
        <input class="form-control form-group" type="text" name="phone" placeholder="Phone">
        <input class="form-control form-group" type="text" name="email" placeholder="E-mail">
    </form>
</div>

</body>
</html>