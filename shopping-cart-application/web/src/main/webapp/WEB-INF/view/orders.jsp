<%-- 
    Document   : orders
    Created on : 7 Jan 2022, 10:35:27
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="org.solent.com504.oodd.cart.model.dto.OrderStatus"%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <h1>ORDERS</h1>
    <c:if test="${not empty fn:trim(message)}">
        <div class="alert alert-success" role="alert">
            ${message}
        </div>
    </c:if>
    <c:if test="${not empty fn:trim(errorMessage)}">
        <div class="alert alert-danger" role="alert">
            ${errorMessage}
        </div>
    </c:if>
    <c:if test="${viewOrders == null && viewOrder == null}">
        <div class="row d-flex" style="justify-content: center;">
            <div class="col-sm-6 col-xs-8">
                <form class="form" action="./orders" method="post">
                    <div class="col-xs-12">
                        <c:choose>
                            <c:when test="${sessionUser.userRole == 'ANONYMOUS'}">
                                <p>To view orders please login to your account. If you placed an order please use the box below to enter your invoice number to view your order</p>
                            </c:when>
                            <c:otherwise>
                                <p>You do not have any orders. If you ordered with a guest account, use the box below to enter your invoice number to view your order</p>
                            </c:otherwise>
                        </c:choose>
                    </div>                               
                    <div class="col-xs-8"><input type="text" style="width: 100%" class="form-control" name="orderId" placeholder="invoice number"></div>
                    <div class="col-xs-4"><input type="hidden" name="action" value="viewOrder">
                        <button type="submit" class="btn btn-default btn-block">View Order</button></div>
                </form>
            </div>
        </div>
    </c:if>
    <c:if test="${viewOrders != null}">
        <c:forEach var="order" items="${viewOrders}">
            <div class="panel panel-default">
                <div class="panel-heading" id="heading${order.id}">
                    <h5 class="mb-0">
                        <form class="form-inline" action="./orders" method="post">
                            <div class="row" style="align-items: center; display: flex;">
                                <div class="col-md-1">
                                    ID ${order.id}
                                </div>
                                <div class="col-md-3">
                                    Invoice: ${order.invoiceNumber}
                                </div>
                                <div class="col-md-2">
                                    Total: £${order.amountDue}
                                </div>
                                <div class="col-md-2">
                                    Status: ${order.orderStatus}        
                                </div>
                                <div class="col-md-4">
                                    <span class="pull-right">
                                        <c:if test="${sessionUser.userRole == 'ADMINISTRATOR'}">
                                            <button class="btn btn-info" type="button" data-toggle="collapse" data-target="#collapse${order.id}" aria-expanded="false" aria-controls="collapse${order.id}" >
                                                Edit Order
                                            </button>
                                        </c:if>
                                        <input type="hidden" name="orderId" value="${order.invoiceNumber}">
                                        <button class="btn btn-danger" type="submit" name="action" value="viewOrder">
                                            View Order
                                        </button>
                                    </span>
                                </div>
                            </div>
                        </form> 
                    </h5>
                </div>
                <c:if test="${sessionUser.userRole == 'ADMINISTRATOR'}">
                    <div id="collapse${order.id}" class="collapse" aria-labelledby="heading${order.id}" data-parent="#accordionExample">
                        <div class="panel-body">
                            <form class="form" action="./orders" method="post">
                                <div class="col-xs-3">Order Status</div>
                                <div class="col-xs-6">
                                    <select class="form-control" name="status" >
                                        <c:forEach var="value" items="${OrderStatus.values()}">
                                            <option value="${value}" <c:if test="${order.orderStatus == value}"> selected  </c:if>>${value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-xs-3">
                                    <input type="hidden" name="orderId" value="${order.invoiceNumber}">
                                    <input type="hidden" name="action" value="updateStatus">
                                    <button type="submit" class="btn btn-primary btn-block">Update Status</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${viewOrder != null}">
        <div class="row d-flex" style="justify-content: center;">
            <div class="col-sm-8 col-xs-12">
                <div class="row">
                    <div class="col-xs-4">
                        <a class="btn btn-primary btn-block" role="button" href="./orders" >
                            <span class="glyphicon glyphicon-arrow-left"></span> Back</a>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h1>Your Order status is: <strong>${viewOrder.orderStatus}</strong></h1>
                        <strong>Hello ${viewOrder.purchaser.firstName}</strong>
                        <c:if test="${viewOrder.orderStatus == 'PAID'}">
                            <p>We have received your order and will be working on getting it to you as soon as possible.</p>
                        </c:if>
                        <c:if test="${viewOrder.orderStatus == 'PROCESSING'}">
                            <p>We are currently processing your order ready for dispatch.</p>
                        </c:if>
                        <c:if test="${viewOrder.orderStatus == 'DISPATCHED'}">
                            <p>Your order has been processed and now dispatched. It will arrive shortly.</p>
                        </c:if>
                        <hr>
                        <div class="col-xs-12">
                            <div class="col-sm-6 col-xs-12">
                                <div class="col-xs-6">
                                    <span class="text-muted" style="display: block">Order Date</span>
                                    <span>${viewOrder.dateOfPurchase}</span>
                                </div>
                                <div class="col-xs-6">
                                    <span class="text-muted" style="display: block">Order Number</span>
                                    <span>${viewOrder.invoiceNumber}</span>
                                </div>
                            </div>
                            <div class="col-sm-6 col-xs-12">
                                <div class="col-xs-6">
                                    <span class="text-muted" style="display: block">Payment</span>
                                    <c:set var = "start"  value = "${fn:length(viewOrder.purchaser.creditCard.cardnumber) -3}"/>
                                    <c:set var = "end"  value = "${fn:length(viewOrder.purchaser.creditCard.cardnumber)}"/>
                                    <span>Card ending ${fn:substring(viewOrder.purchaser.creditCard.cardnumber, start, end)}</span>
                                </div>
                                <div class="col-xs-6">
                                    <span class="text-muted" style="display: block">Address</span>
                                    <span>${viewOrder.purchaser.address.houseNumber}, ${viewOrder.purchaser.address.addressLine1}</span>
                                </div>
                            </div>

                        </div>
                        <div class="col-xs-12">
                            <hr>
                            <c:forEach var="item" items="${viewOrder.purchasedItems}">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <img style="width: 100%"

                                             <c:choose>
                                                 <c:when test="${item.shoppingItem.image.base64image != null}">
                                                     src="data:image/jpeg;base64,${item.shoppingItem.image.base64image}"
                                                 </c:when>
                                             </c:choose>
                                             />
                                    </div>
                                    <div class="col-xs-4">
                                        <h4 class="product-name"><strong>${item.shoppingItem.name}</strong></h4>
                                        <h4>

                                            <small>${fn:substring(item.shoppingItem.description, 0, 100)}...</small>

                                        </h4>
                                    </div>
                                    <div class="col-xs-4 text-right">
                                        <h6><strong>£${item.price} <span class="text-muted">x</span> ${item.quantity}</strong></h6>

                                    </div>
                                </div>
                            </c:forEach>
                            <hr>
                        </div>
                        <div class="col-xs-5 pull-right">
                            <div class="col-xs-6"><strong>Total</strong></div>
                            <div class="col-xs-6 text-right">£${viewOrder.amountDue}</div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </c:if>
</main>
<jsp:include page="footer.jsp" />
