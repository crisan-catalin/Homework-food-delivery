<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="restaurants" required="true" type="java.util.List<com.example.fooddelivery.model.Restaurant>" %>

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