<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tone Engine - Cart</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

   <!-- fonts -->
       <link rel="preconnect" href="https://fonts.googleapis.com">
       <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
       <link href="https://fonts.googleapis.com/css2?family=Caesar+Dressing&family=Cormorant+Unicase:wght@300;400;500;600;700&display=swap" rel="stylesheet">
       <link href="https://fonts.googleapis.com/css2?family=Cormorant+Unicase:wght@300;400;500;600;700&display=swap" rel="stylesheet">
</head>
<body class="bg-black text-white">

<nav class="navbar navbar-expand-lg navbar-dark bg-black border-bottom border-info p-3">
    <div class="container-fluid">
        <a class="brand-logo fw-bold" href="/home" style="letter-spacing: 3px;">TONE ENGINE</a>
        <div class="navbar-nav ms-auto">
            <a href="/home" class="nav-link">STORE</a>
            <a href="/cart" class="nav-link active text-info"><i class="bi bi-cart-fill"></i>CART</a>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="row g-4">
        <div class="col-lg-8">
            <div class="rpg-panel">
                <div class="d-flex justify-content-between align-items-center mb-4 border-bottom border-secondary pb-3">
                    <h4 class="cormorant-unicase-regular mb-0 text-uppercase fw-bold">Current Loadout</h4>
                    <span class="badge bg-secondary">
                        ${not empty sessionScope.cart ? sessionScope.cart.cartItems.size() : 0} Items
                    </span>
                </div>

                <c:if test="${empty sessionScope.cart.cartItems}">
                    <div class="text-center py-5">
                        <p class="text-white-50">Inventory is empty.</p>
                        <a href="/home" class="btn btn-outline-info btn-sm">Back to Store</a>
                    </div>
                </c:if>

                <c:forEach items="${sessionScope.cart.cartItems}" var="item">
                    <div class="gear-slot d-flex align-items-center justify-content-between mb-3">
                        <div class="d-flex align-items-center">
                            <div class="bg-dark rounded p-1 me-3" style="height: 60px; width: 60px; border: 1px solid var(--border-color);">
                                <img src="${item.imageUrl}" class="img-fluid h-100 w-100 object-fit-contain">
                            </div>
                            <div>
                                <div class="fw-bold text-uppercase small">${item.brand}</div>
                                <div class="text-white-50 small">${item.model}</div>
                            </div>
                        </div>
                        <div class="d-flex align-items-center">
                            <span class="price-tag me-4">$${item.price}</span>
                            <form action="${pageContext.request.contextPath}/cart/remove" method="post" class="m-0">
                                <input type="hidden" name="id" value="${item.id}">
                                <button type="submit" class="btn btn-link text-danger p-0"><i class="bi bi-x-lg"></i></button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="col-lg-4">
            <div class="rpg-panel">
                <h5 class="text-uppercase mb-4 border-bottom border-secondary pb-2">Summary</h5>
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <span class="h5 mb-0">Total</span>
                    <span class="price-tag" style="font-size: 1.8rem;">$${sessionScope.cart.totalPrice}</span>
                </div>
                <button class="btn btn-outline-info w-100 py-3 text-uppercase fw-bold" ${empty sessionScope.cart.cartItems ? 'disabled' : ''}>
                    Buy
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
