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
    <div style="color:green;">${message}</div>
    <div class="panel panel-default">
        <div class="panel-body">
            <div class="row">
                <div class="col-md-12">
                    <H1>${itemName}</H1>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6"><img style="width: 100%" src="data:image/jpeg;base64,${itemImage}" /></div>
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
                            <form class="form-inline" action="./home" method="get">
                                <table style="margin-left: auto; margin-right: auto;">
                                    <tr><td style="text-align: right">
                                            <div class="form-group">
                                                <label for="itemQuantity">Quantity: </label>
                                                <div class="input-group">
                                                    <input type="number" class="form-control" id="itemQuantity" name="itemQuantity"  min="1" max="100">
                                                </div>
                                            </div></td><td style="width: 60%">
                                            <input type="hidden" name="itemId" value="${itemId}">
                                            <input type="hidden" name="itemName" value="${itemName}">
                                            <input type="hidden" name="action" value="addItemToCart">
                                            <button type="submit" class="btn btn-success btn-block" >Add to cart</button>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                                <div class="panel panel-default">
                                    <div class="panel-heading" role="tab" id="headingOne">
                                        <h4 class="panel-title">
                                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                                Description
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
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
    </div>
</main>
<jsp:include page="footer.jsp" />
