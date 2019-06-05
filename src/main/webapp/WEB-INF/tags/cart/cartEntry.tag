<%@ attribute name="product" required="true" type="com.example.fooddelivery.dto.ProductSessionDto" %>

<div class="product-card p-3 mt-2">
    <div class="row">
        <div class="col-8">
            <span><b>Product:</b> ${product.name}</span> <span><small>(Qty x ${product.quantity})</small></span>
            <div class="pt-2 d-flex justify-content-between">
                <span><b>Restaurant:</b> ${product.restaurantName}</span>
                <span><b>Price:</b> ${product.price} $</span>
            </div>
        </div>
        <div class="d-none js-product-data">
            <input type="hidden" name="product-id" value="${product.id}">
            <input type="hidden" name="restaurant-id" value="${product.restaurantId}">
        </div>
        <div class="col-2 offset-2">
            <button type="button"
                    class="btn btn-danger product-remove mr-3 js-product-remove">
                Remove <i class="fa fa-trash" aria-hidden="true"></i>
            </button>
        </div>
    </div>
</div>