<%@ page import="ua.kiev.prog.UserAnswers" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
<form action="/answer" method="POST">
    <p>What isolation levels support Spring framework?</p>
    <p><input type="checkbox" name="a" value="READ_COMMITED"> READ_COMMITED</p>
    <p><input type="checkbox" name="b" value="DEFAULT"> DEFAULT</p>
    <p><input type="checkbox" name="c" value="SERIALIZIBLE"> SERIALIZIBLE</p>
    <p><input type="checkbox" name="d" value="NONREPEATABLE_READ"> NONREPEATABLE_READ</p>
    <p>Is it possible in an application running on EJB, integrate support for Spring?</p>
    <p><input type="checkbox" name="i" value="Perhaps"> Perhaps</p>
    <p><input type="checkbox" name="f" value="Impossible"> Impossible</p>
    <input type="submit"/>
    <a href="/answer">MyStatictic</a>
    <form action="/answer" method="post">
        <button type="submit">AllStatistic</button>
    </form>
</form>
<div>
    <%
        if (request.getAttribute("UserAnswers") != null) {
           List<UserAnswers> list = (ArrayList<UserAnswers>) request.getAttribute("UserAnswers");
            for (UserAnswers unit: list) {
                response.getWriter().print(unit);
            }
        }
    %>
</div>


</body>
</html>
