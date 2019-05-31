$('.js-update-address').click(function () {
    const city = $('input[id=city]').val();
    const street = $('input[id=street]').val();
    const number = $('input[id=number]').val();
    const address = {city: city, street: street, number: number};

    $.ajax({
        type: 'POST',
        url: '/order/updateAddress',
        data: JSON.stringify(address),
        contentType: "application/json; charset=utf-8"
    })
});

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
                    restaurantId: $selectedRestaurantId,
                    restaurantName: selectedRestaurantName
                };
                products.push(productJSON);
            }
        }

        if (products.length > 0) {
            saveProductsOnSession(products);
        }

        function saveProductsOnSession(products) {
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
    }
});

$('.js-product-remove').click(function () {
    const $productToRemove = $(this).parent().siblings('.js-product-data');
    const productId = $productToRemove.find('input[name=product-id]').val();
    const restaurantId = $productToRemove.find('input[name=restaurant-id]').val();

    let product = {id: productId, restaurantId: restaurantId};

    $.ajax({
        type: 'DELETE',
        url: '/order/removeProduct',
        data: JSON.stringify(product),
        contentType: "application/json; charset=utf-8",
        success: function () {
            location.reload();
        }
    })
});