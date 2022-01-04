<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="org.solent.com504.oodd.cart.model.dto.User"%>
<%@page import="org.solent.com504.oodd.cart.model.dto.UserRole"%>
<%@page import="org.solent.com504.oodd.cart.model.dto.CardType"%>
<%@page import="org.solent.com504.oodd.cart.model.dto.Countries"%>
<%@page import="org.solent.com504.oodd.cart.model.dto.Dates"%>
<c:set var = "selectedPage" value = "users" scope="request"/>
<jsp:include page="header.jsp" />

<!-- Begin page content -->
<main role="main" class="container">
    <div style="color:red;">${errorMessage}</div>
    <div style="color:green;">${message}</div>

    <div class="row d-flex" style="justify-content: center;">

        <div class="col-xs-8">
            <c:if test="${sessionUser.userRole =='ADMINISTRATOR'}">
                <form action="./users">
                    <button class="btn btn-primary btn-block" type="submit" >Return To Users</button>
                </form> 
                <br />
            </c:if> 
            <div class="tabs-left"> 
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#account-details" aria-controls="account-details" role="tab" data-toggle="tab">Account Details</a></li>
                    <li role="presentation"><a href="#address" aria-controls="address" role="tab" data-toggle="tab">Address</a></li>
                    <li role="presentation"><a href="#payment-method" aria-controls="payment-method" role="tab" data-toggle="tab">Payment Method</a></li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane fade in active" id="account-details">
                        <div class="table-responsive">
                            <div class="panel panel-default" style="border-radius: 0px 4px 4px 4px;">
                                <div class="panel-body">
                                    <form class="form-horizontal" action="./viewModifyUser" method="POST">
                                        <h1>Account details</h1>
                                        <div class="form-group">
                                            <div class="col-md-6 col-xs-12">
                                                <label for="userId">User ID</label>
                                                <input type="text" class="form-control" name="userId" id="userId" value="${modifyUser.id}" disabled />
                                            </div>
                                            <div class="span1"></div>
                                            <div class="col-md-6 col-xs-12">
                                                <label for="userName">Username</label>
                                                <input type="text" class="form-control" name="userName" id="userName" value="${modifyUser.username}" disabled />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-6 col-xs-12">
                                                <label for="firstName">First Name</label>
                                                <input type="text" class="form-control" name="firstName" id="firstName" value="${modifyUser.firstName}" />
                                            </div>
                                            <div class="span1"></div>
                                            <div class="col-md-6 col-xs-12">
                                                <label for="secondName">Second Name</label>
                                                <input type="text" class="form-control" name="secondName" id="secondName" value="${modifyUser.secondName}" />
                                            </div>
                                        </div>
                                        <hr>
                                        <h2>User Status and role</h2>
                                        <c:if test="${sessionUser.userRole !='ADMINISTRATOR'}">
                                            <div class="form-group">
                                                <div class="col-md-6 col-xs-12">
                                                    <label for="userRole">Role</label>
                                                    <input type="text" class="form-control" name="userRole" id="userRole" value="${modifyUser.userRole}" disabled />
                                                </div>
                                                <div class="span1"></div>
                                                <div class="col-md-6 col-xs-12">
                                                    <label for="userEnabled">Enabled</label>
                                                    <input type="text" class="form-control" name="userEnabled" id="userEnabled" value="<c:if test="${modifyUser.enabled}">ENABLED</c:if><c:if test="${!modifyUser.enabled}">DISABLED</c:if>" disabled />
                                                    </div>
                                                </div>
                                        </c:if>
                                        <c:if test="${sessionUser.userRole =='ADMINISTRATOR'}">
                                            <div class="form-group">
                                                <div class="col-md-6 col-xs-12">
                                                    <label for="userRole">Role</label>
                                                    <select class="form-control" name="userRole" >
                                                        <c:forEach var="value" items="${UserRole.values()}">
                                                            <option value="${value}" <c:if test="${modifyUser.userRole == value}"> selected  </c:if>>${value}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="span1"></div>
                                                <div class="col-md-6 col-xs-12">
                                                    <label for="userEnabled">Enabled</label>
                                                    <select class="form-control" name="userEnabled" >
                                                        <option value="true" <c:if test="${modifyUser.enabled}"> selected  </c:if> >ENABLED</option>
                                                        <option value="false" <c:if test="${!modifyUser.enabled}"> selected  </c:if> >DISABLED</option>
                                                        </select>
                                                    </div>
                                                </div>
                                        </c:if>
                                        <input type="hidden" name="username" value="${modifyUser.username}"/>
                                        <input type="hidden" name="action" value="updateDetails"/>
                                        <button class="btn btn-primary" type="submit" >Save Changes</button>
                                    </form>
                                    <form class="form-horizontal" action="./viewModifyUser" method="post">
                                        <hr><h2>Update Password</h2>
                                        <div class="form-group">
                                            <div class="col-md-6 col-xs-12">
                                                <label for="password">Password</label>
                                                <input class="form-control" type="password" id="password" name="password" ></input>
                                            </div>
                                            <div class="span1"></div>
                                            <div class="col-md-6 col-xs-12">
                                                <label for="password2">Re Enter Password</label>
                                                <input class="form-control" type="password" id="password2" name="password2" ></input>
                                            </div>
                                        </div>
                                        <input type="hidden" name="username" value="${modifyUser.username}"/>
                                        <input type="hidden" name="action" value="updatePassword"/>
                                        <button class="btn btn-primary" type="submit" >Update Password</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane fade" id="address">
                        <div class="table-responsive">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <form class="form-horizontal" action="./viewModifyUser" method="POST">
                                        <h1>Address</h1>

                                        <div class="form-group">
                                            <div class="col-md-6 col-xs-12">
                                                <label for="houseNumber">House Number</label>
                                                <input type="text" class="form-control" name="houseNumber" id="houseNumber" value="${modifyUser.address.houseNumber}" />
                                            </div>
                                            <div class="span1"></div>
                                            <div class="col-md-6 col-xs-12">
                                                <label for="addressLine1">Address Line 1</label>
                                                <input type="text" class="form-control" name="addressLine1" id="addressLine1" value="${modifyUser.address.addressLine1}" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-6 col-xs-12 pull-right">
                                                <label for="addressLine2">Address Line 2</label>
                                                <input type="text" class="form-control" name="addressLine2" id="addressLine2" value="${modifyUser.address.addressLine2}" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-6 col-xs-12">
                                                <label for="city">City</label>
                                                <input type="text" class="form-control" name="city" id="city" value="${modifyUser.address.city}" />
                                            </div>
                                            <div class="span1"></div>
                                            <div class="col-md-6 col-xs-12">
                                                <label for="county">County</label>
                                                <input type="text" class="form-control" name="county" id="county" value="${modifyUser.address.county}" />
                                            </div>
                                        </div>                
                                        <div class="form-group">
                                            <div class="col-md-6 col-xs-12">
                                                <label for="postcode">Postal Code</label>
                                                <input type="text" name="postcode" id="postcode" class="form-control" value="${checkoutUser.address.postcode}" />
                                            </div>
                                            <div class="span1"></div>
                                            <div class="col-md-6 col-xs-12">
                                                <label for="country">Country</label>
                                                <select name="country" class="form-control" id="country">
                                                    <option value="0" label="Select a country ... " <c:if test="${modifyUser.address.country == null}"> selected  </c:if>>Select a country ... </option>
                                                    <optgroup id="country-optgroup-Africa" label="Africa">
                                                        <c:forEach var="country" items="${Countries.africaMap}">
                                                            <option value="${country.key}" <c:if test="${modifyUser.address.country == country.key}"> selected  </c:if>>${country.value}</option>
                                                        </c:forEach>
                                                    </optgroup>
                                                    <optgroup id="country-optgroup-Americas" label="Americas">
                                                        <c:forEach var="country" items="${Countries.americasMap}">
                                                            <option value="${country.key}" <c:if test="${modifyUser.address.country == country.key}"> selected  </c:if>>${country.value}</option>
                                                        </c:forEach>
                                                    </optgroup>
                                                    <optgroup id="country-optgroup-Asia" label="Asia">
                                                        <c:forEach var="country" items="${Countries.asiaMap}">
                                                            <option value="${country.key}" <c:if test="${modifyUser.address.country == country.key}"> selected  </c:if>>${country.value}</option>
                                                        </c:forEach>
                                                    </optgroup>
                                                    <optgroup id="country-optgroup-Europe" label="Europe">
                                                        <c:forEach var="country" items="${Countries.europeMap}">
                                                            <option value="${country.key}" <c:if test="${modifyUser.address.country == country.key}"> selected  </c:if>>${country.value}</option>
                                                        </c:forEach>
                                                    </optgroup>
                                                    <optgroup id="country-optgroup-Oceania" label="Oceania">
                                                        <c:forEach var="country" items="${Countries.oceaniaMap}">
                                                            <option value="${country.key}" <c:if test="${modifyUser.address.country == country.key}"> selected  </c:if>>${country.value}</option>
                                                        </c:forEach>
                                                    </optgroup>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-md-6 col-xs-12">
                                                <label for="telephone">Telephone</label>
                                                <input type="text" class="form-control" name="telephone" id="telephone" value="${modifyUser.address.telephone}" />
                                            </div>
                                            <div class="span1"></div>
                                            <div class="col-md-6 col-xs-12">
                                                <label for="mobile">Mobile</label>
                                                <input type="text" class="form-control" name="mobile"  id="mobile" value="${modifyUser.address.mobile}" />
                                            </div>
                                        </div>
                                        <input type="hidden" name="username" value="${modifyUser.username}"/>
                                        <input type="hidden" name="action" value="updateAddress"/>
                                        <button class="btn btn-primary" type="submit" >Save Changes</button>

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane fade" id="payment-method">
                        <div class="table-responsive">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <form class="form-horizontal" action="./viewModifyUser" method="post">
                                        <h1>Payment Method</h1>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <label for="CreditCardType">Card Type</label>
                                                <select id="CreditCardType" name="cardType" class="form-control">
                                                    <c:forEach var="value" items="${CardType.values()}">
                                                            <option value="${value}" <c:if test="${modifyUser.cardType == value}"> selected  </c:if>>${value}</option>
                                                        </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-12 col-xs-12">
                                                <label for="cardName">Name on Card</label>
                                                <input type="text" class="form-control" name="cardName" id="cardName" value="${modifyUser.creditCard.name}" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <label for="cardNumber">Card Number</label>
                                                <input type="text" class="form-control" name="cardNumber" id="cardNumber" value="${modifyUser.creditCard.cardnumber}" />
                                            </div>
                                            <div class="span1"></div>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <label style="width: 100%; text-align: center">Expiration Date</label>
                                                <c:set var="dateParts" value="${fn:split(modifyUser.creditCard.endDate, '/')}" />
                                                <div class="span1"></div>
                                                <div class="col-xs-6">
                                                    <select class="form-control" id="expiry_month" name="expiryMonth">
                                                        <option value="">Month</option>
                                                        <c:forEach var="value" items="${Dates.months}">
                                                            <option value="${value}" <c:if test="${dateParts[0] == value}"> selected  </c:if>>${value}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="col-xs-6">
                                                    <select class="form-control" name="expiryYear">
                                                        <option value="">Year</option>
                                                        <c:forEach var="value" items="${Dates.years}">
                                                            <option value="${value}" <c:if test="${dateParts[1] == value}"> selected  </c:if>>20${value}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>  
                                        <input type="hidden" name="username" value="${modifyUser.username}"/>
                                        <input type="hidden" name="action" value="updatePayment"/>
                                        <button class="btn btn-primary" type="submit" >Update Details</button>
                                    </form>
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
