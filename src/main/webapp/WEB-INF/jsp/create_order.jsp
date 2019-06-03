<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/cart" %>

<jsp:include page="header.jsp"></jsp:include>

<cart:addProductsModal restaurants="${restaurants}"/>

<div class="container flex-fill create-order-background-right">
    <form:form method="post" action="/order/create" modelAttribute="addressForm">
        <div class="row">
            <div class="col-12 col-md-8 col-lg-6">

                <h2>Delivery address</h2>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="delivery-address-city">City</span>
                    </div>
                    <form:input type="text" class="form-control" aria-describedby="delivery-address-city"
                                required="required" minlength="3"
                                value="${sessionAddress.city}" path="city"/>
                </div>
                <div class="row">
                    <div class="col-12 col-sm-7">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="delivery-address-street">Street</span>
                            </div>
                            <form:input type="text" class="form-control" aria-describedby="delivery-address-street"
                                        required="required" minlength="3"
                                        value="${sessionAddress.street}" path="street"/>
                        </div>

                    </div>
                    <div class="col-12 col-sm-5">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="delivery-address-number">Number</span>
                            </div>
                            <form:input type="text" class="form-control" aria-describedby="delivery-address-number"
                                        required="required" minlength="1"
                                        value="${sessionAddress.number}" path="number"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mb-5">
            <div class="col-12 col-md-8 col-lg-6">
                <div class="d-flex justify-content-between">
                    <h2 class="pr-3">Products</h2>
                    <button type="button" class="btn btn-warning js-update-address" data-toggle="modal"
                            data-target="#addProductsModal">
                        <i class="fa fa-plus" aria-hidden="true"></i> Add product
                    </button>
                </div>

                <c:choose>
                    <c:when test="${fn:length(sessionProducts) > 0}">
                        <c:forEach items="${sessionProducts}" var="product">
                            <cart:cartEntry product="${product}"/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        Your cart is empty &#128533;
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
        <div class="row">
            <div class="col-12 col-md-8 col-lg-6">
                <button type="submit" class="btn btn-success btn-block"
                    ${fn:length(sessionProducts)  > 0 ? '' : 'disabled'} >
                    Confirm order <i class="fa fa-chevron-right" aria-hidden="true"></i>
                </button>
            </div>
        </div>
    </form:form>
</div>

<jsp:include page="footer.jsp"></jsp:include>