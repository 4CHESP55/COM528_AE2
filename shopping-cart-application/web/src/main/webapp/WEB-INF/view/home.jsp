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



    <div class="row">
        <c:forEach var="item" items="${availableItems}">
            <c:forEach var="enabled" items="${enabledItems}">
                <c:choose>
                    <c:when test="${item.id == enabled.itemId}">
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
                                </div>
                                <div class="panel-footer">
                                    <div class="row text-center">
                                        <div class="col-xs-6">
                                            <h4 class="text-left"><strong>£${item.price}</strong></h4>
                                        </div>
                                        <div class="col-xs-6">
                                            <!-- post avoids url encoded parameters -->
                                            <form action="./home" method="get">
                                                <input type="hidden" name="itemId" value="${item.id}">
                                                <input type="hidden" name="itemName" value="${item.name}">
                                                <input type="hidden" name="action" value="addItemToCart">
                                                <c:choose>
                                                    <c:when test="${item.quantity == 0}">
                                                        <button type="button" class="btn btn-danger btn-block" >Out of stock</button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button type="submit" class="btn btn-success btn-block" >Add to cart</button>
                                                    </c:otherwise>
                                                </c:choose>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                </c:choose>
            </c:forEach>
        </c:forEach>
    </div>
</main>
<jsp:include page="footer.jsp" />
