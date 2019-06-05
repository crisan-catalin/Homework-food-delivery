<%@ attribute name="order" required="true" type="com.example.fooddelivery.dto.OrderListDto" %>

<div class="row mb-5">
    <div class="col-12 col-md-8 offset-md-4 col-lg-6 offset-lg-4">
        <div class="product-card p-3 mt-2">
            <div class="row">
                <div class="col-8">
                    <small>
                        <a href="/order/details/${order.id}">#${order.id}</a> posted by ${order.customerName}
                    </small>
                    <div class="pt-2 d-flex justify-content-between">
                        <div class="d-flex flex-column">
                            <b>Delivery address:</b>
                            <span>${order.deliveryAddress.city}, str. ${order.deliveryAddress.street}, nr. ${order.deliveryAddress.number}</span>
                        </div>
                        <div class="d-flex flex-column">
                            <b>Total price:</b>
                            <span class="text-right">${order.totalPrice} $</span>
                        </div>
                    </div>
                </div>
                <div class="col-4 d-flex align-items-center">
                    <a href="/order/details/${order.id}">
                        <button type="button"
                                class="btn btn-primary product-remove mr-3 js-product-remove">
                            <i class="fa fa-info" aria-hidden="true"></i> Order details
                        </button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>