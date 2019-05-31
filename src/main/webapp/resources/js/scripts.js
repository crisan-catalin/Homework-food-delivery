$('.js-restaurant-select').change(function () {
    const restaurantId = $(this).val();

    const $productContainer = $('.js-product-container');
    $productContainer.empty();

    $.get('/restaurant/' + restaurantId + '/products', function (products) {
        for (const product of products) {
            $productContainer.append(createProductComponent(product));
        }
    });

    function createProductComponent(product) {
        return $(
            '<div class="col-4 mt-4 js-product">' +
            ' <div class="card text-center">' +
            '  <img src="https://via.placeholder.com/150" class="card-img-top">' +
            '  <div class="card-body">' +
            '   <input type="hidden" name="product-id" value="' + product.id + '">' +
            '   <input type="hidden" name="product-name" value="' + product.name + '">' +
            '   <input type="hidden" name="product-price" value="' + product.price + '">' +
            '   <b class="card-title">' + product.name + '</b>' +
            '   <p class="card-text">Price: ' + product.price + ' $</p>' +
            '   <div class="input-group">' +
            '    <input type="number" name="product-quantity" value=0 class="form-control" placeholder="Quantity">' +
            '   </div>' +
            '  </div>' +
            ' </div>' +
            '</div>');
    }
});

$('.js-products-add').click(function () {
    const $selectedRestaurantId = $('.js-restaurant-select').val();

    if ($selectedRestaurantId !== null) {
        const selectedRestaurantName = $('.js-restaurant-select option:selected').text();
        const $productContainer = $('.js-product-container');
        const $productsForm = $productContainer.parent();
        $productsForm.submit();

        let products = [];
        const $products = $productContainer.find('.js-product');
        for (const product of $products) {
            const productQty = $(product).find('input[name=product-quantity]').val();

            if (productQty > 0) {
                const productId = $(product).find('input[name=product-id]').val();
                const productName = $(product).find('input[name=product-name]').val();
                const productPrice = $(product).find('input[name=product-price]').val();
                let productJSON = {
                    id: productId,
                    name: productName,
                    price: productPrice,
                    quantity: productQty,
                    restaurantName: selectedRestaurantName
                };
                products.push(productJSON);
            }
        }

        $.ajax({
            type: 'POST',
            url: '/order/addProducts',
            data: JSON.stringify(products),
            contentType: "application/json; charset=utf-8",
            success: function () {
                location.reload();
            }
        })
    }
});