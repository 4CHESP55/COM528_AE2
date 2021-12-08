<%-- 
    Document   : upload
    Created on : 8 Dec 2021, 15:46:42
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:forEach var="img" items="${images}">
            <li><img alt="img" src="data:image/jpeg;base64,${img.base64image}"/></li>
        </c:forEach>
    </body>
</html>
