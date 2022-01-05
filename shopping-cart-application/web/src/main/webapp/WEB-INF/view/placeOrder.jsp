<%-- 
    Document   : placeOrder
    Created on : 5 Jan 2022, 17:24:56
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <c:forEach var="item" items="${orderItems}">
        <p>${item.name} : ${item.quantity} : ${item.price}</p>
        <hr>
    </c:forEach>
</main>




<jsp:include page="footer.jsp" />
