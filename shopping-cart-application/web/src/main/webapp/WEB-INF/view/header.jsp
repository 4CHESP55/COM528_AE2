<%-- 
    Document   : header
    Created on : Jan 4, 2020, 11:19:01 AM
    Author     : cgallen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">
        <!--<link rel="canonical" href="https://getbootstrap.com/docs/3.3/examples/navbar/">-->

        <title>Navbar Template for Bootstrap</title>

        <!-- Bootstrap core CSS -->
        <link href="./resources/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="./resources/css/navbar.css" rel="stylesheet">
        <link href="./resources/css/imageupload.css" rel="stylesheet">
        <link href="./resources/css/common.css" rel="stylesheet">

    </head>

    <body>

        <div class="container">

            <!-- Static navbar -->
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">Project name</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li <% if ("home".equals(request.getAttribute("selectedPage"))) {%> class="active"  <% } %> ><a href="./home">Home</a></li>
                            <li <% if ("shop".equals(request.getAttribute("selectedPage"))) {%>  class="active"  <% } %> ><a href="./shop">Shop</a></li> 
                            <li <% if ("cart".equals(request.getAttribute("selectedPage"))) {%>  class="active"  <% } %> ><a href="./cart">Cart</a></li> 
                            <li <% if ("about".equals(request.getAttribute("selectedPage"))) {%>  class="active"  <% } %> ><a href="./about">About</a></li> 
                            <li <% if ("contact".equals(request.getAttribute("selectedPage"))) {%>  class="active"  <% }%> ><a href="./contact">Contact</a></li>                          
                        </ul>
                        <form class="navbar-form navbar-left" action="./shop" method="get">
                            <div class="form-group">
                                <input type="text" class="form-control" name="search" placeholder="Search">
                            </div>
                            <button type="submit" class="btn btn-default">Search</button>
                        </form>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> <span class="glyphicon glyphicon-shopping-cart"></span> Cart <span class="caret"></span></a>
                                <ul class="dropdown-menu dropdown-cart" role="menu">
                                    <c:forEach var="item" items="${shoppingCartItems}">
                                        <li>
                                            <span class="item">
                                                <span class="item-left">
                                                    <img style="width: 50px"
                                                                         <c:choose>
                                                                             <c:when test="${item.image.base64image != null}">
                                                                                 src="data:image/jpeg;base64,${item.image.base64image}"
                                                                             </c:when>
                                                                         </c:choose>
                                                         />
                                                    <span class="item-info">
                                                        <span class="item-name">${item.name}</span>
                                                        <span class="item-price">£${item.price}</span>
                                                        <span class="item-quantity">Quantity: ${item.quantity}</span>
                                                    </span>
                                                </span>
                                            </span>
                                        </li>
                                    </c:forEach>
                                    <li class="divider"></li>
                                    <li><a class="text-center">Total: £${shoppingcartTotal}</a></li>
                                    <li class="divider"></li>
                                    <li><a class="view-button" href="./cart">View Cart</a></li>
                                    <li><a class="checkout-button" href="./checkout">Checkout</a></li>
                                </ul>
                            </li>
                        </ul>

                        <ul class="nav navbar-nav navbar-right">
                            <!-- user role:  ${sessionUser.userRole}-->
                            <c:if test="${sessionUser.userRole =='ANONYMOUS'}">
                                <li><a href="./login">Login or create a new Account</a></li>
                            </c:if>
                            <c:if test="${sessionUser.userRole !='ANONYMOUS'}">
                                <li class="dropdown" >
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                        <c:choose>
                                        <c:when test="${sessionUser.userRole =='ADMINISTRATOR'}">
                                            Admin 
                                        </c:when>
                                        <c:otherwise>
                                            ${sessionUser.username}
                                        </c:otherwise>
                                        </c:choose>
                                            <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <c:if test="${sessionUser.userRole =='ADMINISTRATOR'}">
                                        <li><a href="./users">Manage Users</a></li>
                                        <li><a href="./catalog">Manage Catalogue</a></li>
                                        <li><a href="./orders">Manage Orders</a></li>
                                        <hr>
                                        </c:if>
                                        <form id="logoutForm" method="POST" action="./logout">
                                        </form>
                                        <form id="profile" method="GET" action="./viewModifyUser">
                                            <input type="hidden" name="username" value="${sessionUser.username}"/>
                                        </form>
                                        <form id="orders" method="GET" action="./orders">
                                            <input type="hidden" name="username" value="${sessionUser.username}"/>
                                        </form>
                                        <li><a onclick="document.forms['profile'].submit()">My Profile</a></li>
                                        <li><a onclick="document.forms['orders'].submit()">My Orders</a></li>
                                        <li><a onclick="document.forms['logoutForm'].submit()">Logout</a></li>
                                    </ul>
                                </li>
                                </c:if>  
                        </ul>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>