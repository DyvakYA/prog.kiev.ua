<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <li>
                        <button type="button" id="logout" class="btn btn-default navbar-btn">LOGOUT</button>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <table class="table table-striped">
        <thead>
        <tr>
            <td><b>delete</b></td>
            <td><b>Login</b></td>
            <td><b>Password</b></td>
            <td><b>Role</b></td>
            <td><b>E-mail</b></td>
            <td><b>Phone</b></td>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><input type="checkbox" name="toDeleteContact[]" value="${user.id}" id="checkbox_${user.id}"/>
                </td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
            </tr>
        </c:forEach>
    </table>
    <div class="input-group">
        <button type="button" id="delete_user" class="btn btn-default navbar-btn">Delete User
        </button>
    </div>

    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${allPages ne null}">
                <c:forEach var="i" begin="1" end="${allPages}">
                    <li><a href="/?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                </c:forEach>
            </c:if>
            <c:if test="${byGroupPages ne null}">
                <c:forEach var="i" begin="1" end="${byGroupPages}">
                    <li><a href="/group/${groupId}?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                </c:forEach>
            </c:if>
        </ul>
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

