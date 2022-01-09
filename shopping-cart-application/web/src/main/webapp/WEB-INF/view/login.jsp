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
    <div class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3">
        <div class="panel panel-default" >
            <div class="panel-heading">
                <div class="panel-title text-center">Login</div>
            </div>     
            <div class="panel-body" >
                <form action="./login" method="post">
                    <input type="hidden" name="action" value="login">
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input id="user" type="text" class="form-control" name="username" value="" placeholder="Username">                                        
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input id="password" type="password" class="form-control" name="password" placeholder="Password">
                        </div>                                                                  
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12 controls">
                            <button type="submit" class="btn btn-primary pull-right"><i class="glyphicon glyphicon-log-in"></i> Log in</button>                          
                        </div>
                    </div>
                </form>     
            </div>                     
        </div>
        <a href="./register">Create a new account</a>
    </div>
</main>
<jsp:include page="footer.jsp" />
