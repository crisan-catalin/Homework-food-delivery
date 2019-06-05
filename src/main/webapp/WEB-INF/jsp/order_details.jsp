<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="header.jsp"/>

<div class="p-3 mt-2">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="customer-details-block border-bottom pb-4">
                <b>Customer</b>

                <c:set var="customerDetails" value="${orderDetails.customerDetails}"/>
                <c:set var="customerAddress" value="${customerDetails.address}"/>

                <div class="customer-details-block--properties">
                    <div>Name: ${customerDetails.customerName}</div>
                    <div>Phone number: ${customerDetails.customerPhone}</div>
                    <div>Address: ${customerAddress.city},
                        str. ${customerAddress.street},
                        nr. ${customerAddress.number}</div>
                </div>
            </div>

            <c:forEach items="${orderDetails.orderEntriesByRestaurant}" var="entriesByRestaurant">
                <div class="pl-4 py-1 mb-4">
                    <c:forEach items="${entriesByRestaurant.products}" var="product">
                        <div class="order-entry-block pb-2 mt-4">
                            <b>Product:</b>
                            <div class="order-entry-block--properties">
                                <div>${product.name}
                                    <small>(Qty x ${product.quantity})</small>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <span>Price: ${product.price} $</span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="pt-2"><b>Restaurant:</b> ${entriesByRestaurant.restaurantName}</div>
                </div>
            </c:forEach>
            <div class="border-top pt-4">
                <div class="row">
                    <div class="col-6">
                        <b>Total price:</b>
                        <span class="text-right">${orderDetails.totalPrice} $</span>
                    </div>
                    <div class="col-6">
                        <form:form action="/order/process" method="post">
                            <input type="hidden" value="${orderId}" name="orderId">
                            <button class="btn btn-warning btn-block" ${orderDetails.orderAvailable? '' : 'disabled'}>
                                Take order >
                            </button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>