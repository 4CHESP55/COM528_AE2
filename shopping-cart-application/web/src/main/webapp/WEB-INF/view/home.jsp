<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <div class="row">
        <c:forEach var="item" items="${availableItems}">
            <div class="col-12 col-md-6 col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-heading" style="padding: 0">
                        <img style="height: 200px; width: 100%; background-color: lightgrey;"
                        <c:forEach var="desc" items="${shoppingItemDescriptions}">
                            <c:choose>
                                <c:when test="${item.id == desc.itemId}">
                                    <c:forEach var="img" items="${images}">
                                        <c:choose>
                                            <c:when test="${desc.image == img.id}">
                                                src="data:image/jpeg;base64,${img.base64image}"
                                            </c:when>
                                        </c:choose>
                                    </c:forEach>
                                </c:when>   
                            </c:choose>
                        </c:forEach>
                        />
                    </div>
                    <div class="panel-body">
                        <h4>
                            <form action="./product" method="get">
                                    <input type="hidden" name="itemId" value="${item.id}">
                                    <input type="hidden" name="action" value="viewProduct">
                                    <input type="submit" style="color:#00f;border:0px #000 solid;background-color:#fff;" value="${item.name}">
                            </form>
                        </h4>
                        <c:forEach var="desc" items="${shoppingItemDescriptions}">
                            <c:choose>
                                <c:when test="${item.id == desc.itemId}">
                                    <p class="card-text">${fn:substring(desc.description, 0, 30)}...</p>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                        <div class="row">
                            <div class="col-md-6">
                                <p>£${item.price}</p>
                            </div>
                            <div class="col-md-6">
                                <!-- post avoids url encoded parameters -->
                                <form action="./home" method="get">
                                    <input type="hidden" name="itemId" value="${item.id}">
                                    <input type="hidden" name="itemName" value="${item.name}">
                                    <input type="hidden" name="action" value="addItemToCart">
                                    <button type="submit" class="btn btn-success btn-block" >Add to cart</button>
                                </form> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>         
        </c:forEach>
    </div>
</main>
<jsp:include page="footer.jsp" />
