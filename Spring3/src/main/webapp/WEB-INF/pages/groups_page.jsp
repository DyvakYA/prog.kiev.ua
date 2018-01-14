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
    <h3><img height="50" width="55" src="<c:url value="/static/logo.png"/>"/><a href="/">Contacts List</a></h3>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="groupList" class="nav navbar-nav">
                    <li>
                        <button type="button" id="add_contact" class="btn btn-default navbar-btn">Add Contact</button>
                    </li>
                    <li>
                        <button type="button" id="add_group" class="btn btn-default navbar-btn">Add Group</button>
                    </li>
                    <li>
                        <button type="button" id="groups" class="btn btn-default navbar-btn">Groups</button>
                    </li>

                    <form class="navbar-form navbar-left" role="search" action="/search" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" name="pattern" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default ">Submit</button>
                    </form>
                    <li>
                        <button type="button" id="export" class="btn btn-default navbar-btn">Export</button>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <table class="table table-striped">
        <thead>
        <tr>
            <td><b>del</b></td>
            <td><b>name</b></td>
            <td><b>count</b></td>
        </tr>
        </thead>
        <c:forEach items="${groups}" var="groups">
            <tr>
                <td><input type="checkbox" name="toDeleteGroup[]" value="${groups.key.id}"
                           id="checkbox_${groups.key.id}"/></td>
                <td><a href="/group/${groups.key.id}">${groups.key.name}</a></td>
                <td>${groups.value}</td>
            </tr>
        </c:forEach>
    </table>
    <div class="input-group">
        <button type="button" id="delete_group" class="btn btn-default navbar-btn">Delete Group
        </button>
        <button type="button" id="delete_group_with_contacts" class="btn btn-default navbar-btn">Delete Group With Contacts
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
    $('.dropdown-toggle').dropdown();

    $('#add_contact').click(function () {
        window.location.href = '/contact_add_page';
    });

    $('#add_group').click(function () {
        window.location.href = '/group_add_page';
    });

    $('#groups').click(function () {
        window.location.href = '/groups';
    });

    $('#export').click(function () {
        window.location.href = '/export';
    });

    $('#delete_group').click(function () {
        var data = {'toDeleteGroup[]': []};
        $(":checked").each(function () {
            data['toDeleteGroup[]'].push($(this).val());
        });
        $.post("/group/delete", data, function (data, status) {
            window.location.reload();
        });
    });

    $('#delete_group_with_contacts').click(function () {
        var data = {'toDeleteGroup[]': []};
        $(":checked").each(function () {
            data['toDeleteGroup[]'].push($(this).val());
        });
        $.post("/group/deleteGroupAndContacts", data, function (data, status) {
            window.location.reload();
        });
    });

</script>
</body>
</html>