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
    <div class="panel panel-default">
    <div id="collapse" class="collapse" aria-labelledby="heading">
        <div class="panel-body">
            <form class="form">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="itemName">Item Name</label>
                                <input type="text" class="form-control" name="itemName" id="itemName" placeholder="Name">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="itemPrice">Price</label>
                                <div class="input-group">
                                    <div class="input-group-addon">£</div>
                                    <input type="text" class="form-control" name="itemPrice" id="itemPrice" placeholder="Amount">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="itemQuantity">Quantity</label>
                                <input type="text" class="form-control" name="itemQuantity" id="itemQuantity" placeholder="Amount">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                        <div class="form-group">
                            <label>Image</label>
                            <img id='img-upload'/>
                            <div class="input-group">
                                <span class="input-group-btn">
                                    <span class="btn btn-default btn-file">
                                    Browse… <input type="file" id="imgInp">
                                    </span>
                                </span>
                                <input type="text" class="form-control" readonly>
                            </div>
                        </div>
                        </div>
                        <div class="col-md-8">
                        <div class="form-group">
                            <label for="itemDesc">Description</label>
                            <textarea class="form-control" name="itemDesc" id="itemDesc" style="resize: none" rows="10"></textarea>
                        </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="btn btn-success">
                                    <input type="radio" name="optionsRadios" id="optionsRadios" value="enabled" checked>
                                    Enabled
                                </label>
                                <label class="btn btn-secondary">
                                    <input type="radio" name="optionsRadios" id="optionsRadios" value="disabled">
                                    Disabled
                                </label>
                            <button type="submit" class="btn btn-default">Add Item</button>
                            </div>
                        </div>
                    </div>
                </form>
        </div>
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
                                    <button class="btn btn-danger" type="submit" name="action" value="removeItemFromCatalog">
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
                    <form class="form">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="itemName">Item Name</label>
                                <input type="text" class="form-control" name="itemName" id="itemName" value="${item.name}">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="itemPrice">Price</label>
                                <div class="input-group">
                                    <div class="input-group-addon">£</div>
                                    <input type="text" class="form-control" name="itemPrice" id="itemPrice" value="${item.price}">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="itemQuantity">Quantity</label>
                                <input type="text" class="form-control" name="itemQuantity" id="itemQuantity" value="${item.quantity}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                        <div class="form-group">
                            <label>Image</label>
                            <img id='img-upload'/>
                            <div class="input-group">
                                <span class="input-group-btn">
                                    <span class="btn btn-default btn-file">
                                    Browse… <input type="file" id="imgInp">
                                    </span>
                                </span>
                                <input type="text" class="form-control" readonly>
                            </div>
                        </div>
                        </div>
                        <div class="col-md-8">
                        <div class="form-group">
                            <label for="itemDesc">Description</label>
                            <c:forEach var="desc" items="${shoppingItemDescriptions}">
                                    <c:choose>
                                        <c:when test="${item.name == desc.name}">
                                            <textarea class="form-control" name="itemDesc" id="itemDesc" style="resize: none" rows="10" >${desc.description}</textarea>
                                        </c:when>
                                        <c:otherwise>
                                            <textarea class="form-control" name="itemDesc" id="itemDesc" style="resize: none" rows="10"></textarea>
                                        </c:otherwise>    
                                    </c:choose>
                             </c:forEach>
                            
                        </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="btn btn-success">
                                    <input type="radio" name="optionsRadios" id="optionsRadios" value="enabled" checked>
                                    Enabled
                                </label>
                                <label class="btn btn-secondary">
                                    <input type="radio" name="optionsRadios" id="optionsRadios" value="disabled">
                                    Disabled
                                </label>
                            <button type="submit" class="btn btn-default">Update Item</button>
                            </div>
                        </div>
                    </div>
                </form>
                </div>
            </div>
        </div>
    </c:forEach>
</main>
<jsp:include page="footer.jsp" />