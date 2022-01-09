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
    <div class="row d-flex" style="justify-content: center;">
        <div class="col-sm-8 col-xs-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h1>Your Order has been placed!</h1>
                    <strong>Hello ${viewOrder.purchaser.firstName}</strong>
                    <p>We have received your order and will be working on getting it to you as soon as possible.</p>
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
</main>


<jsp:include page="footer.jsp" />
