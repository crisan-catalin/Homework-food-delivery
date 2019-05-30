<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="header.jsp"></jsp:include>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Add product in basket</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <select class="custom-select">
                    <option selected disabled>Select restaurant</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                </select>

                <div class="row mt-4">
                    <div class="col-4 col-lg-3">
                        <div class="card text-center">
                            <img src="https://via.placeholder.com/150" class="card-img-top">
                            <div class="card-body">
                                <b class="card-title">Pizza Quatro Fromaggi</b>
                                <p class="card-text">Price: 20$</p>
                                <div class="input-group">
                                    <input type="number" min=0 class="form-control" placeholder="Quantity">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-success">Confirm <i class="fa fa-check" aria-hidden="true"></i>
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