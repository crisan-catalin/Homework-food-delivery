<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<c:set var="sessionUser" value="${sessionScope.sessionUser }"/>

<!doctype html>
<html lang="en">
<head>
    <title>Food Delivery | SIBIU</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css"
          integrity="sha384-3AB7yXWz4OeoZcPbieVW64vVXEwADiYyAEhwilzWsLw+9FgqpyjjStpPnpBO8o8S" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/style.css">
</head>

<body class="d-flex flex-column">
<div class="d-flex flex-column flex-md-row justify-content-between align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
    <a href="/" class="navbar-brand">
        <img class="nav-logo" src="/resources/images/logo2.png">
    </a>

    <div class="d-flex flex-column flex-md-row align-self-center">
        <div class="mr-md-4">
            <a class="p-2 text-dark" href="/order">
                <i class="fas fa-shopping-cart pr-md-2"></i> Place an order
            </a>
        </div>

        <c:choose>
            <c:when test="${empty sessionUser.name}">
                <div class="p-2">
                    <a class="btn btn-outline-primary" href="/login">Login</a>
                </div>
                <div class="p-2">
                    <a class="btn btn-outline-primary" href="/register">Register</a>
                </div>
            </c:when>
            <c:otherwise>
                <h6 class="mr-md-3 d-none d-md-block">|</h6>
                <div class="dropdown mr-md-4">
                    <span class="dropdown-toggle" id="userpanel-dropdown" data-toggle="dropdown" aria-haspopup="true"
                          aria-expanded="false">
                                        Welcome <b class="pr-2 my-2 my-md-0"> ${sessionUser.name}</b>
                    </span>
                    <div class="dropdown-menu" aria-labelledby="userpanel-dropdown">
                        <a class="dropdown-item" href="/">Pending orders</a>
                        <a class="dropdown-item" href="/">Orders history</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/order/list">Browse open orders</a>
                        <a class="dropdown-item" href="/">Orders to deliver</a>
                    </div>
                </div>
                <a class="btn btn-outline-primary" href="/logout">Logout</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>