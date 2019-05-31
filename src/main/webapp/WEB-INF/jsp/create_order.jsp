<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"></jsp:include>

<div class="modal fade" id="addProductsModal" tabindex="-1" role="dialog" aria-labelledby="addProductsModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Add something tasty in your basket!</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form:select path="restaurants" class="custom-select js-restaurant-select">
                    <option selected disabled>Select restaurant</option>
                    <c:forEach items="${restaurants}" var="restaurant">
                        <form:option value="${restaurant.id}" label="${restaurant.name}"/>
                    </c:forEach>
                </form:select>

                <div class="row js-product-container"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-success js-products-add">
                    Confirm <i class="fa fa-check" aria-hidden="true"></i>
                </button>
            </div>
        </div>
    </div>
</div>


<div class="container">
    <form:form method="post" action="/order/create" modelAttribute="orderForm">
        <div class="row">
            <div class="col-12 col-md-8 col-lg-6">

                <h2>Delivery address</h2>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="delivery-address-city">City</span>
                    </div>
                    <form:input type="text" class="form-control" aria-describedby="delivery-address-city"
                                path="address.city"/>
                </div>
                <div class="row">
                    <div class="col-12 col-sm-7">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="delivery-address-street">Street</span>
                            </div>
                            <form:input type="text" class="form-control" aria-describedby="delivery-address-street"
                                        path="address.street"/>
                        </div>

                    </div>
                    <div class="col-12 col-sm-5">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="delivery-address-number">Number</span>
                            </div>
                            <form:input type="text" class="form-control" aria-describedby="delivery-address-number"
                                        path="address.number"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mb-5">
            <div class="col-12 col-md-8 col-lg-6">
                <div class="d-flex justify-content-between">
                    <h2 class="pr-3">Products</h2>
                    <button class="btn btn-warning" data-toggle="modal" data-target="#exampleModal"><i
                            class="fa fa-plus"
                            aria-hidden="true"></i>
                        Add product
                    </button>
                </div>
                <div class="product-card p-3 mt-2">
                    <div class="row">
                        <div class="col-8">
                            <span>Product name</span> (x<span>2</span>)
                            <div class="pt-2 d-flex justify-content-between">
                                <span>Restaurant name</span>
                                <span>Price: 30$</span>
                            </div>
                        </div>

                        <div class="col-2 offset-2">
                            <button class="btn btn-danger remove-product mr-3">Remove <i class="fa fa-trash"
                                                                                         aria-hidden="true"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-md-8 col-lg-6">
                <button type="submit" class="btn btn-success btn-block">
                    Confirm order <i class="fa fa-chevron-right" aria-hidden="true"></i>
                </button>
            </div>
        </div>
    </form:form>
</div>

<jsp:include page="footer.jsp"></jsp:include>