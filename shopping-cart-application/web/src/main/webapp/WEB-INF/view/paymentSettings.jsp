<%-- 
    Document   : paymentSettings
    Created on : 4 Jan 2022, 11:55:54
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="org.solent.com504.oodd.cart.model.dto.Dates"%>
<jsp:include page="header.jsp" />

<!-- Begin page content -->
<main role="main" class="container">

    <div class="row d-flex" style="justify-content: center;">

        <div class="col-md-6 col-xs-8">
            <div style="color:red;">${errorMessage}</div>
            <div style="color:green;">${message}</div>
            <form class="form-horizontal" action="./paymentSettings" method="post">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <div class="panel-title">Payment Settings</div>
                    </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <div class="col-xs-12">
                                <label for="URL">Bank URL</label>
                                <input type="text" class="form-control" name="URL" id="URL" value="${cURL}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12 col-xs-12">
                                <label for="cardName">Name on Card</label>
                                <input type="text" class="form-control" name="cardName" id="cardName" value="${cCardName}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-8 col-sm-8 col-xs-12">
                                <label for="creditCard">Card Number</label>
                                <input type="text" class="form-control" name="creditCard" id="creditCard" value="${cCreditCard}" />
                            </div>
                            <div class="span1"></div>
                            <div class="col-md-4 col-sm-4 col-xs-12">
                                <label for="cvv">CVV</label>
                                <input type="text" class="form-control" name="cvv" id="cvv" value="${cCvv}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <label style="width: 100%; text-align: center">Expiration Date</label>
                                <c:set var="dateParts" value="${fn:split(cExpiry, '/')}" />
                                <div class="span1"></div>
                                <div class="col-xs-6" style="padding-right: 5px">
                                    <select class="form-control" id="expiryMonth" name="expiryMonth">
                                        <option value="">Month</option>
                                        <c:forEach var="value" items="${Dates.months}">
                                            <option value="${value}" <c:if test="${dateParts[0] == value}"> selected  </c:if>>${value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-xs-6" style="padding-left: 5px">
                                    <select class="form-control" name="expiryYear">
                                        <option value="">Year</option>
                                        <c:forEach var="value" items="${Dates.years}">
                                            <option value="${value}" <c:if test="${dateParts[1] == value}"> selected  </c:if>>20${value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="span1"></div>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <label for="issue">Issue Number</label>
                                <input type="text" class="form-control" name="issue" id="issue" value="${cIssue}" />
                            </div>
                        </div>  
                    </div>
                    <div class="panel-footer">
                        <input type="hidden" name="action" value="saveSettings"/>
                        <button class="btn btn-primary btn-block" type="submit" >Update Payment Settings</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</main>

<jsp:include page="footer.jsp" />

