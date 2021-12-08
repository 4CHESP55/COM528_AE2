<%-- 
    Document   : catalog
    Created on : 1 Dec 2021, 17:21:28
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="header.jsp" />
<!-- Begin page content -->
<main role="main" class="container">
    <c:if test="${message != ''}">
        <div class="alert alert-success" role="alert">
            ${message}
        </div>
    </c:if>
    <c:if test="${errorMessage != ''}">
        <div class="alert alert-danger" role="alert">
            ${errorMessage}
        </div>
    </c:if>
    <div class="row">
        <div class="col-md-6">
            <span class="pull-left">
                <h1>Catalog</h1>
            </span>
        </div>
        <div class="col-md-6">
            <span class="pull-right">
                <button class="btn btn-success" type="button" data-toggle="collapse" data-target="#collapse" aria-expanded="false" aria-controls="collapse">
                    New item
                </button>
            </span>
        </div>
    </div>
    <div id="collapse" class="collapse" aria-labelledby="heading">
        <div class="panel-body">
            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
        </div>
    </div>
    <c:forEach var="item" items="${availableItems}">
        <div class="panel panel-default">

            <div class="panel-heading" id="heading${item.id}">
                <h5 class="mb-0">
                    <form class="form-inline" action="./catalog" method="post">
                        <div class="row" style="align-items: center; display: flex;">
                            <div class="col-md-1">
                                ID ${item.id}
                            </div>
                            <div class="col-md-2">
                                Item: ${item.name}
                            </div>
                            <div class="col-md-2">
                                Price: ${item.price}
                            </div>
                            <div class="col-md-3">
                                <c:forEach var="desc" items="${shoppingItemDescriptions}">
                                    <c:choose>
                                        <c:when test="${item.name == desc.name}">
                                            Description: ${fn:substring(desc.description, 0, 30)}...
                                        </c:when>
                                        <c:otherwise>
                                            Description:
                                        </c:otherwise>    
                                    </c:choose>
                                </c:forEach>
                            </div>
                            <div class="col-md-4">
                                <span class="pull-right">
                                    <button class="btn btn-info" type="button" data-toggle="collapse" data-target="#collapse${item.id}" aria-expanded="false" aria-controls="collapse${item.id}">
                                        Edit
                                    </button>
                                    <input type="hidden" name="itemUUID" value="${item.uuid}">
                                    <input type="hidden" name="itemName" value="${item.name}">
                                    <input type="hidden" name="action" value="removeItemFromCatalog">
                                    <button class="btn btn-danger" type="submit">
                                        Delete
                                    </button>
                                </span>
                            </div>
                        </div>
                    </form> 
                </h5>
            </div>

            <div id="collapse${item.id}" class="collapse" aria-labelledby="heading${item.id}" data-parent="#accordionExample">
                <div class="panel-body">
                    Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                </div>
            </div>
        </div>
    </c:forEach>
</main>
<jsp:include page="footer.jsp" />