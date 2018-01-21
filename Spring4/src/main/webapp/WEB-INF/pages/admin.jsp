<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/logout" var="logoutUrl"/>
<html>
<head>
    <title>Prog.kiev.ua</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="groupList" class="nav navbar-nav">
                    <li>
                        <button type="button" id="contacts" class="btn btn-default navbar-btn">Contacts</button>
                    </li>
                    <li>
                        <button type="button" id="users" class="btn btn-default navbar-btn">Users</button>
                    </li>
                    <ul id="list" class="nav navbar-nav navbar-right">
                        <li>
                            <button type="button" id="logout" class="btn btn-default navbar-btn">LOGOUT</button>
                        </li>
                    </ul>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</div>

<script>

    $('#contacts').click(function () {
        window.location.href = '${contacts_link}';
    });

    $('#users').click(function () {
        window.location.href = '${users_link}';
    });

    $('#logout').click(function () {
        window.location.href = '/logout';
    });
</script>
</body>
</html>
