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
            '<div class="col-4 mt-4">' +
            ' <div class="card text-center">' +
            '  <img src="https://via.placeholder.com/150" class="card-img-top">' +
            '  <div class="card-body">' +
            '   <input type="hidden" name="id" value="' + product.id + '">' +
            '   <b class="card-title">' + product.name + '</b>' +
            '   <p class="card-text">Price: ' + product.price + ' $</p>' +
            '   <div class="input-group">' +
            '    <input type="number" min=0 class="form-control" placeholder="Quantity">' +
            '   </div>' +
            '  </div>' +
            ' </div>' +
            '</div>');
    }
});