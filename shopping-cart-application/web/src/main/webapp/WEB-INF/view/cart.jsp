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

    <p class="text-success">${message}</p>
    <p class="text-danger">${errorMessage}</p>


    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="panel-title">
                <div class="row">
                    <div class="col-xs-6">
                        <h5><span class="glyphicon glyphicon-shopping-cart"></span> Shopping Cart</h5>
                    </div>
                    <div class="col-xs-6">
                        <a href="./home" class="btn btn-primary btn-sm btn-block">
                            <span class="glyphicon glyphicon-share-alt"></span> Continue shopping
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel-body">
            <c:forEach var="item" items="${shoppingCartItems}">
                <div class="row">
                    <div class="col-xs-2">
                        <img style="width: 100%"

                                             <c:choose>
                                                 <c:when test="${item.image.base64image != null}">
                                                     src="data:image/jpeg;base64,${item.image.base64image}"
                                                 </c:when>
                                             </c:choose>
                             />
                    </div>
                    <div class="col-xs-4">
                        <h4 class="product-name"><strong>${item.name}</strong></h4>
                        <h4>

                                        <small>${fn:substring(item.description, 0, 100)}...</small>

                        </h4>
                    </div>
                    <div class="col-xs-6">
                        <div class="col-xs-6 text-right">
                            <h6><strong>${item.price} <span class="text-muted">x</span> ${item.quantity}</strong></h6>
                        </div>
                        <div class="col-xs-1">
                            <form action="./cart" method="post">
                                <input type="hidden" name="itemUUID" value="${item.uuid}">
                                <input type="hidden" name="itemName" value="${item.name}">
                                <input type="hidden" name="action" value="reduceItemFromCart">                  
                                <button type="submit" class="btn btn-default">
                                    <span class="glyphicon glyphicon-minus"></span>
                                </button>
                            </form> 
                        </div>
                        <div class="col-xs-1">
                            <form action="./cart" method="post">
                                <input type="hidden" name="itemUUID" value="${item.uuid}">
                                <input type="hidden" name="itemName" value="${item.name}">
                                <input type="hidden" name="action" value="increaseItemFromCart">                  
                                <button type="submit" class="btn btn-default">
                                    <span class="glyphicon glyphicon-plus"></span>
                                </button>
                            </form> 
                        </div>
                        <div class="col-xs-4">
                            <form action="./cart" method="post">
                                <input type="hidden" name="itemUUID" value="${item.uuid}">
                                <input type="hidden" name="itemName" value="${item.name}">
                                <input type="hidden" name="action" value="removeItemFromCart">                  
                                <button type="submit" class="btn btn-danger">
                                    <span class="glyphicon glyphicon-remove"></span> Remove
                                </button>
                            </form> 
                        </div>
                    </div>
                </div>
                <hr>
            </c:forEach>
        </div>
        <div class="panel-footer">
            <div class="row text-center">
                <div class="col-xs-9">
                    <h4 class="text-right">Total <strong>£${shoppingcartTotal}</strong></h4>
                </div>
                <div class="col-xs-3">
                    <form action="./checkout">
                        <button type="sumbit" class="btn btn-success btn-block">
                            Checkout
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</main>
<jsp:include page="footer.jsp" />
