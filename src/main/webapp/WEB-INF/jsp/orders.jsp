<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Address</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">
            <tr class="${order.deliveryStatus eq 'PLACED' ? 'table-primary' : (order.deliveryStatus eq 'IN_PROGRESS' ? 'table-warning' : 'table-success')}">
                <td>${order.deliveryAddress.city} ${order.deliveryAddress.street} ${order.deliveryAddress.number}</td>
                <td>${order.totalPrice}</td>
                <td><a class="btn btn-outline-primary" href="/#">Details</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="footer.jsp"></jsp:include>