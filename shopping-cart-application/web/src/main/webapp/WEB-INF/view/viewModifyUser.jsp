<%-- 
    Document   : content
    Created on : Jan 4, 2020, 11:19:47 AM
    Author     : cgallen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="org.solent.com504.oodd.cart.model.dto.User"%>
<%@page import="org.solent.com504.oodd.cart.model.dto.UserRole"%>
<c:set var = "selectedPage" value = "users" scope="request"/>
<jsp:include page="header.jsp" />

<!-- Begin page content -->
<main role="main" class="container">

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
                            <div class="row">
                                <div class="col-md-6">
                                    <form class="form" action="./viewModifyUser" method="POST">
                                        <div class="panel panel-default">
                                            <div class="panel-heading"><h1>Account details</h1></div>

                                            <div class="panel-body">

                                                <div class="form-group">
                                                    <label for="userId">User ID</label>
                                                    <input type="text" class="form-control" name="userId" id="userId" value="${modifyUser.id}" disabled>
                                                </div>

                                                <div class="form-group">
                                                    <label for="userName">Username</label>
                                                    <input type="text" class="form-control" name="userName" id="userName" value="${modifyUser.username}" disabled>
                                                </div>
                                                <div class="form-group">
                                                    <label for="firstName">First Name</label>
                                                    <input type="text" class="form-control" name="firstName" id="firstName" value="${modifyUser.firstName}" >
                                                </div>
                                                <div class="form-group">
                                                    <label for="secondName">Second Name</label>
                                                    <input type="text" class="form-control" name="secondName" id="secondName" value="${modifyUser.secondName}" >
                                                </div>

                                            </div>
                                            <div class="panel-heading"><h2>User Status and role</h2></div>
                                            <div class="panel-body">
                                                <c:if test="${sessionUser.userRole !='ADMINISTRATOR'}">
                                                    <div class="form-group">
                                                        <label for="userRole">Role</label>
                                                        <input type="text" class="form-control" name="userRole" id="userRole" value="${modifyUser.userRole}" disabled>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="userEnabled">Enabled</label>
                                                        <input type="text" class="form-control" name="userEnabled" id="userEnabled" value="<c:if test="${modifyUser.enabled}">ENABLED</c:if><c:if test="${!modifyUser.enabled}">DISABLED</c:if>" disabled>
                                                        </div>
                                                </c:if>

                                                <c:if test="${sessionUser.userRole =='ADMINISTRATOR'}">
                                                    <div class="form-group">
                                                        <label for="userRole">Role</label>
                                                        <select class="form-control" name="userRole" >
                                                            <c:forEach var="value" items="${UserRole.values()}">
                                                                <option value="${value}" <c:if test="${modifyUser.userRole == value}"> selected  </c:if>>${value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="userEnabled">Enabled</label>
                                                        <select class="form-control" name="userEnabled" >
                                                            <option value="true" <c:if test="${modifyUser.enabled}"> selected  </c:if> >ENABLED</option>
                                                            <option value="false" <c:if test="${!modifyUser.enabled}"> selected  </c:if> >DISABLED</option>
                                                            </select>
                                                        </div>

                                                </c:if>
                                            </div>
                                            <div class="panel-footer">
                                                <input type="hidden" name="username" value="${modifyUser.username}"/>
                                                <button class="btn btn-primary" type="submit" >Update User ${modifyUser.username}</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-md-6">
                                    <form class="form" action="./viewModifyUser" method="post">
                                        <div class="panel panel-default">
                                            <div class="panel-heading"><h2>Update Password</h2></div>

                                            <div class="panel-body">

                                                <div class="form-group">
                                                    <label for="password">Password</label>
                                                    <input class="form-control" type="password" id="password" name="password" ></input>
                                                </div>
                                                <div class="form-group">
                                                    <label for="password2">Re Enter Password</label>
                                                    <input class="form-control" type="password" id="password2" name="password2" ></input>
                                                </div>
                                            </div>
                                            <div class="panel-footer">
                                                <input type="hidden" name="username" value="${modifyUser.username}"/>
                                                <input type="hidden" name="action" value="updatePassword"/>
                                                <button class="btn btn-primary" type="submit" >Update ${modifyUser.username} Password</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane fade" id="address">
                <div class="table-responsive">
                    <div class="panel panel-default">
                        <div class="panel-heading">Address details</div>
                        <div class="panel-body">
                            Panel content
                        </div>
                        <div class="panel-footer">
                            update
                        </div>
                    </div>
                </div>
            </div>
            <div role="tabpanel" class="tab-pane fade" id="payment-method">
                <div class="table-responsive">
                    <div class="panel panel-default">
                        <div class="panel-heading">Payment details</div>
                        <div class="panel-body">
                            Panel content
                        </div>
                        <div class="panel-footer">
                            update
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div>
        <H1>User Details ${modifyUser.username} </H1>
        <!-- print error message if there is one -->
        <div style="color:red;">${errorMessage}</div>
        <div style="color:green;">${message}</div>

        <form class="form" action="./viewModifyUser" method="POST">
            <table class="table">
                <thead>
                </thead>

                <tbody>
                    <tr>
                        <td>User ID</td>
                        <td>${modifyUser.id}</td>
                    </tr>
                    <tr>
                        <td>username</td>
                        <td>${modifyUser.username}</td>
                    </tr>
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" name="firstName" value="${modifyUser.firstName}" /></td>
                    </tr>
                    <tr>
                        <td>Second Name</td>
                        <td><input type="text" name="secondName" value="${modifyUser.secondName}" /></td>
                    </tr>
                    <tr>
                        <td>House Number</td>
                        <td><input type="text" name="houseNumber" value="${modifyUser.address.houseNumber}" /></td>
                    </tr>
                    <tr>
                        <td>Address Line 1</td>
                        <td><input type="text" name="addressLine1" value="${modifyUser.address.addressLine1}" /></td>
                    </tr>
                    <tr>
                        <td>Address Line 2</td>
                        <td><input type="text" name="addressLine2" value="${modifyUser.address.addressLine2}" /></td>
                    </tr>
                    <tr>
                        <td>city</td>
                        <td><input type="text" name="county" value="${modifyUser.address.city}" /></td>
                    </tr>
                    <tr>
                        <td>county</td>
                        <td><input type="text" name="county" value="${modifyUser.address.county}" /></td>
                    </tr>
                    <tr>
                        <td>country</td>
                        <td><input type="text" name="country" value="${modifyUser.address.country}" /></td>
                    </tr>
                    <tr>
                        <td>postcode</td>
                        <td><input type="text" name="postcode" value="${modifyUser.address.postcode}" /></td>
                    </tr>
                    <tr>
                        <td>telephone</td>
                        <td><input type="text" name="telephone" value="${modifyUser.address.telephone}" /></td>
                    </tr>
                    <tr>
                        <td>mobile</td>
                        <td><input type="text" name="mobile" value="${modifyUser.address.mobile}" /></td>
                    </tr>

                </tbody>

            </table>

            <c:if test="${sessionUser.userRole !='ADMINISTRATOR'}">
                <p>User Status and role </p>
                <table class="table">
                    <thead>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Role</td>
                            <td>${modifyUser.userRole}</td>
                        </tr>
                        <tr>
                            <td>enabled</td>
                            <td><c:if test="${modifyUser.enabled}">ENABLED</c:if><c:if test="${!modifyUser.enabled}">DISABLED</c:if></td>
                            </tr>
                        </tbody>
                    </table>
            </c:if>

            <c:if test="${sessionUser.userRole =='ADMINISTRATOR'}">
                <p>Manage User Status and role </p>
                <table class="table">
                    <thead>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Role</td>
                            <td>
                                <select class="form-control" name="userRole" >
                                    <c:forEach var="value" items="${UserRole.values()}">
                                        <option value="${value}" <c:if test="${modifyUser.userRole == value}"> selected  </c:if>>${value}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>enabled</td>
                            <td>
                                <select class="form-control" name="userEnabled" >
                                    <option value="true" <c:if test="${modifyUser.enabled}"> selected  </c:if> >ENABLED</option>
                                    <option value="false" <c:if test="${!modifyUser.enabled}"> selected  </c:if> >DISABLED</option>
                                    </select>
                                </td>
                            </tr>
                        </tbody>
                    </table>
            </c:if>

            <input type="hidden" name="username" value="${modifyUser.username}"/>
            <button class="btn" type="submit" >Update User ${modifyUser.username}</button>
        </form>
        <p>Update Password</p>
        <form action="./viewModifyUser" method="post">
            <input type="hidden" name="username" value="${modifyUser.username}"/>
            <input type="hidden" name="action" value="updatePassword"/>
            <p>Password <input type="password" name="password" ></input></p>
            <p>Re Enter Password <input type="password" name="password2" ></input></p>
            <button class="btn" type="submit" >Update ${modifyUser.username} Password</button>
        </form>
        <c:if test="${sessionUser.userRole =='ADMINISTRATOR'}">
            <BR>
            <form action="./users">
                <button class="btn" type="submit" >Return To Users</button>
            </form> 
        </c:if> 

    </div>

</main>

<jsp:include page="footer.jsp" />
