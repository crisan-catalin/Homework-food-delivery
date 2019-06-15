<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/orders" %>


<jsp:include page="header.jsp"></jsp:include>

<div class="container-fluid flex-fill order-list-background-left">

    <c:forEach items="${ordersList}" var="order">
        <order:orderListElement order="${order}"/>
    </c:forEach>
</div>

<jsp:include page="footer.jsp"></jsp:include>