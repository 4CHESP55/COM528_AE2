<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
// request set in controller
//request.setAttribute("selectedPage", "home");
%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <H1>Home</H1>
    <div style="color:red;">${errorMessage}</div>
    <div style="color:green;">${message}</div>

    <H1>Available Items</H1>
    <table class="table">

        <tr>
            <th>Item Name</th>
            <th>Price</th>
            <th>Description</th>
            <th></th>
        </tr>

        <c:forEach var="item" items="${availableItems}">

            <tr>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <c:forEach var="desc" items="${shoppingItemDescriptions}">
                    <c:choose>
                        <c:when test="${item.name == desc.name}">
                            <td>${desc.description}</td>
                        </c:when>
                        <c:otherwise>
                            <td>-- No Description yet --</td>
                        </c:otherwise>    
                    </c:choose>
                </c:forEach>
                <td>
                    <!-- post avoids url encoded parameters -->
                    <form action="./home" method="get">
                        <input type="hidden" name="itemId" value="${item.id}">
                        <input type="hidden" name="itemName" value="${item.name}">
                        <input type="hidden" name="action" value="addItemToCart">
                        <button type="submit" >Add Item</button>
                    </form> 
                </td>
            </tr>

        </c:forEach>
    </table>
    
    <form action="upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" value="Upload"/>
</form>


</main>
<jsp:include page="footer.jsp" />
