<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>select</title>
    </head>
    <body>
        <h1>掲示板</h1>
        <c:forEach var="b" items="${bbss}">
            <p><strong>${b.name}</strong>: ${b.time}</p>
            <p>${b.text}</p>
            <hr>
        </c:forEach>
        <br>
        <a href="index.html">戻る</a>
    </body>
</html>