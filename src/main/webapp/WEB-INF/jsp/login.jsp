<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="header.jsp"></jsp:include>

<div class="container flex-fill">

    <div class="row h-100">
        <div class="col"></div>

        <div class="col-5 my-auto">
            <div class="card bg-light">
                <div class="card-body">
                    <form:form method="post" action="/login" modelAttribute="logInUserForm">
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <form:input type="text" class="form-control" id="email" path="email"/>
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <form:input type="password" class="form-control" id="password" path="password"/>
                        </div>
                        <c:if test="${not empty errorMessage}">
                            <div class="form-group alert alert-danger">
                                    ${errorMessage}
                            </div>
                        </c:if>
                        <button type="submit" class="btn btn-primary">Login</button>
                    </form:form>
                </div>
            </div>
        </div>

        <div class="col"></div>
    </div>

</div>

<jsp:include page="footer.jsp"></jsp:include>