<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="org.solent.com504.oodd.cart.model.dto.CardType"%>
<%@page import="org.solent.com504.oodd.cart.model.dto.Countries"%>
<%@page import="org.solent.com504.oodd.cart.model.dto.Dates"%>
<%
// request set in controller
//request.setAttribute("selectedPage", "home");
%>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">

    <p class="text-success">${message}</p>
    <p class="text-danger">${errorMessage}</p>
    <form class="form-horizontal" method="post" action="./placeOrder" >
        <div class="col-md-6">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">
                        <div class="row">
                            <div class="col-xs-6">
                                <h5><span class="glyphicon glyphicon-shopping-cart"></span> Your Cart</h5>
                            </div>
                            <div class="col-xs-6">
                                <a href="./cart" class="btn btn-primary btn-sm btn-block">
                                    <span class="glyphicon glyphicon-share-alt"></span> Edit Cart
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
                            <div class="col-xs-6">
                                <h4 class="product-name"><strong>${item.name}</strong></h4>
                                <h4>
                                    <small>${fn:substring(item.description, 0, 100)}...</small>
                                </h4>
                            </div>
                            <div class="col-xs-4 text-right">
                                <h6><strong>${item.price} <span class="text-muted">x</span> ${item.quantity}</strong></h6>
                            </div>
                        </div>
                        <hr>
                    </c:forEach>
                </div>
                <div class="panel-footer">
                    <div class="row text-center">
                        <div class="col-xs-12">
                            <h4 class="text-right">Total <strong>£${shoppingcartTotal}</strong></h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <c:if test="${sessionUser.userRole =='ANONYMOUS'}">
            <div class="collapse in" id="anonymous">       
                <div class="col-md-6">
                    <p>Not currently logged in</p>
                    <div class="row">
                        <div class="col-md-6">
                            <button class="btn btn-success btn-block" type="button" data-toggle="collapse" data-target="#address, #anonymous" data-parent="#checkout_form">
                                Continue as guest
                            </button>
                        </div>
                        <div class="col-md-6">
                            <a href="./login?page=checkout" class="btn btn-success btn-block">
                                Login
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:choose>
            <c:when test="${sessionUser.userRole =='ANONYMOUS'}">
                <div class="collapse" id="address">
                </c:when>
                <c:otherwise>
                    <div class="collapse in" id="address">
                    </c:otherwise>
                </c:choose>
                <div class="col-md-6">
                    <!--SHIPPING METHOD-->
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title">
                                Address
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="form-group">
                                <div class="col-md-12">
                                    <h4>Shipping Address</h4>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-6 col-xs-12">
                                    <strong>First Name:</strong>
                                    <input type="text" name="firstName" class="form-control" value="${checkoutUser.firstName}" />
                                </div>
                                <div class="span1"></div>
                                <div class="col-md-6 col-xs-12">
                                    <strong>Last Name:</strong>
                                    <input type="text" name="secondName" class="form-control" value="${checkoutUser.secondName}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-6 col-xs-12">
                                    <strong>House Number:</strong>
                                    <input type="text" name="houseNumber" class="form-control" value="${checkoutUser.address.houseNumber}" />
                                </div>
                                <div class="span1"></div>
                                <div class="col-md-6 col-xs-12">
                                    <strong>Address Line 1:</strong>
                                    <input type="text" name="addressLine1" class="form-control" value="${checkoutUser.address.addressLine1}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-6 col-xs-12 pull-right">
                                    <strong>Address Line 2:</strong>
                                    <input type="text" name="addressLine2" class="form-control" value="${checkoutUser.address.addressLine2}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-6 col-xs-12">
                                    <strong>City:</strong>
                                    <input type="text" name="city" class="form-control" value="${checkoutUser.address.city}" />
                                </div>
                                <div class="span1"></div>
                                <div class="col-md-6 col-xs-12">
                                    <strong>County:</strong>
                                    <input type="text" name="county" class="form-control" value="${checkoutUser.address.county}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-6 col-xs-12">
                                    <strong>Postal Code:</strong>
                                    <input type="text" name="postcode" class="form-control" value="${checkoutUser.address.postcode}" />
                                </div>
                                <div class="span1"></div>
                                <div class="col-md-6 col-xs-12">
                                    <strong>Country:</strong>
                                    <select name="country" class="form-control" id="country">
                                        <option value="0" label="Select a country ... " <c:if test="${checkoutUser.address.country == null}"> selected  </c:if>>Select a country ... </option>
                                            <optgroup id="country-optgroup-Africa" label="Africa">
                                            <c:forEach var="country" items="${Countries.africaMap}">
                                                <option value="${country.key}" <c:if test="${checkoutUser.address.country == country.key}"> selected  </c:if>>${country.value}</option>
                                            </c:forEach>
                                        </optgroup>
                                        <optgroup id="country-optgroup-Americas" label="Americas">
                                            <c:forEach var="country" items="${Countries.americasMap}">
                                                <option value="${country.key}" <c:if test="${checkoutUser.address.country == country.key}"> selected  </c:if>>${country.value}</option>
                                            </c:forEach>
                                        </optgroup>
                                        <optgroup id="country-optgroup-Asia" label="Asia">
                                            <c:forEach var="country" items="${Countries.asiaMap}">
                                                <option value="${country.key}" <c:if test="${checkoutUser.address.country == country.key}"> selected  </c:if>>${country.value}</option>
                                            </c:forEach>
                                        </optgroup>
                                        <optgroup id="country-optgroup-Europe" label="Europe">
                                            <c:forEach var="country" items="${Countries.europeMap}">
                                                <option value="${country.key}" <c:if test="${checkoutUser.address.country == country.key}"> selected  </c:if>>${country.value}</option>
                                            </c:forEach>
                                        </optgroup>
                                        <optgroup id="country-optgroup-Oceania" label="Oceania">
                                            <c:forEach var="country" items="${Countries.oceaniaMap}">
                                                <option value="${country.key}" <c:if test="${checkoutUser.address.country == country.key}"> selected  </c:if>>${country.value}</option>
                                            </c:forEach>
                                        </optgroup>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-6 col-xs-12">
                                    <strong>Phone Number:</strong>
                                    <input type="text" name="telephone" class="form-control" value="${checkoutUser.address.telephone}" />
                                </div>
                                <div class="span1"></div>
                                <div class="col-md-6 col-xs-12">
                                    <strong>Mobile Number:</strong>
                                    <input type="text" name="mobile" class="form-control" value="${checkoutUser.address.mobile}" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--SHIPPING METHOD END-->
                    <button class="btn btn-success" type="button" data-toggle="collapse" data-target="#payment,#address" data-parent="#checkout_form">
                        Continue to payment
                    </button>
                </div>

            </div>
            <div class="collapse" id="payment">  
                <div class="col-md-6">
                    <!--CREDIT CART PAYMENT-->
                    <div class="panel panel-info">
                        <div class="panel-heading"><span><i class="glyphicon glyphicon-lock"></i></span> Secure Payment</div>
                        <div class="panel-body">
                            <div class="form-group">
                                <div class="col-md-12 col-xs-12">
                                    <strong>Card Type:</strong>
                                    <select id="cardType" name="cardType" class="form-control">
                                        <c:forEach var="value" items="${CardType.values()}">
                                            <option value="${value}" <c:if test="${checkoutUser.cardType == value}"> selected  </c:if>>${value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12 col-xs-12">
                                    <strong>Name on Card:</strong>
                                    <input type="text" class="form-control" name="cardName" value="${checkoutUser.creditCard.name}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-8 col-sm-8 col-xs-12">
                                    <strong>Card Number:</strong>
                                    <input type="text" class="form-control" name="cardNumber" value="${checkoutUser.creditCard.cardnumber}" />
                                </div>
                                <div class="span1"></div>
                                <div class="col-md-4 col-sm-4 col-xs-6">
                                    <strong>CVV:</strong>
                                    <input type="text" class="form-control" name="cardCvv" value="" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <strong>Expiration Date</strong>
                                </div>
                                <c:set var="dateParts" value="${fn:split(checkoutUser.creditCard.endDate, '/')}" />
                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
                                    <select class="form-control" id="expiry_month" name="expiryMonth">
                                        <option value="">Month</option>
                                        <c:forEach var="value" items="${Dates.months}">
                                            <option value="${value}" <c:if test="${dateParts[0] == value}"> selected  </c:if>>${value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
                                    <select class="form-control" name="expiryYear">
                                        <option value="">Year</option>
                                        <c:forEach var="value" items="${Dates.years}">
                                            <option value="${value}" <c:if test="${dateParts[1] == value}"> selected  </c:if>>20${value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="span1"></div>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="hidden" name="action" value="checkout"/>
                                    <button type="submit" class="btn btn-success btn-block">Place Order</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--CREDIT CART PAYMENT END-->
                </div>
            </div>
    </form>
</main>
<jsp:include page="footer.jsp" />
