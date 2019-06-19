<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<jsp:include page="header.jsp"></jsp:include>

<div class="container-fluid flex-fill order-list-background-left">
    <div class="container h-100">
        <table class="table table-hover my-auto">
            <thead>
            <tr>
                <th>Address</th>
                <th>Price</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr class="${order.deliveryStatus eq 'PLACED' ? 'table-primary' : (order.deliveryStatus eq 'IN_PROGRESS' ? 'table-warning' : 'table-success')}">
                    <td>${order.deliveryAddress.city} ${order.deliveryAddress.street} ${order.deliveryAddress.number}</td>
                    <td>${order.totalPrice}</td>
                    <td>${order.deliveryStatus}</td>
                    <td><a class="btn ${order.deliveryStatus eq 'PLACED' ? 'btn-outline-primary' : (order.deliveryStatus eq 'IN_PROGRESS' ? 'btn-outline-warning' : 'btn-outline-success')}"
                           href="/orders/details/${order.id}">Details</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>