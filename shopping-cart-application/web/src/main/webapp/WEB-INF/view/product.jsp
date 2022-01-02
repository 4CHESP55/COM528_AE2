<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <div style="color:green;">${message}</div>
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="panel-title">
                <div class="row">
                    <div class="col-xs-6">
                        <h5><span class="glyphicon glyphicon-info-sign"></span> Product Details</h5>
                    </div>
                    <div class="col-xs-6">
                        <a href="./shop" class="btn btn-primary btn-sm btn-block">
                            <span class="glyphicon glyphicon-share-alt"></span> Continue shopping
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-6">
                    <img style="height: 600px; width: 100%; background-color: lightgrey;"
                         <c:choose>
                             <c:when test="${itemImage != ''}">
                                 <img style="width: 100%" src="data:image/jpeg;base64,${itemImage}" />
                         </c:when>
                         <c:otherwise>
                             <img style="height: 600px; width: 100%; background-color: lightgrey;" />
                         </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-md-6">
                    <div class="row">
                        <div class="col-md-6">
                            <H1>${itemName}</H1>
                        </div>
                        <div class="col-md-6">
                            <H1>£${itemPrice}</H1>
                        </div>
                    </div>
                    <hr style="margin-bottom: 20px !important">
                    <div class="row">
                        <div class="col-md-12">
                            <p>${fn:substring(itemDescription, 0, 500)}...</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <form class="form-inline" action="./shop" method="get">
                                <table style="margin-left: auto; margin-right: auto;">
                                    <tr><td style="text-align: right">
                                            <div class="form-group">
                                                <label for="itemQuantity">Quantity: </label>
                                                <div class="input-group">
                                                    <c:choose>
                                                        <c:when test="${itemQuantity == 0}">
                                                            <input type="number" class="form-control" id="buy_quantity" name="buy_quantity"  min="1" max="100" disabled>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="number" class="form-control" id="buy_quantity" name="buy_quantity"  min="1" max="100" value="1">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div></td><td style="width: 60%">
                                            <input type="hidden" name="itemId" value="${itemId}">
                                            <input type="hidden" name="itemName" value="${itemName}">
                                            <input type="hidden" name="action" value="addItemToCart">
                                            <c:choose>
                                                <c:when test="${itemQuantity == 0}">
                                                    <button type="button" class="btn btn-danger btn-block" >Out of stock</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="submit" class="btn btn-success btn-block" >Add to cart</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading" id="headingOne">
                                    <h4 class="panel-title">
                                        <a role="button" data-toggle="collapse" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                            Description <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseOne" class="collapse" aria-labelledby="headingOne">
                                    <div class="panel-body">
                                        <div id="product">
                                            <p class="collapse" id="collapseExample" aria-expanded="false" style="white-space: pre-wrap">${itemDescription}</p>
                                            <hr class="rounded">
                                            <a role="button" class="collapsed" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp" />
