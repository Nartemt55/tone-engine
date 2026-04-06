<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Tone engine - Store</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <!-- fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Caesar+Dressing&family=Cormorant+Unicase:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Cormorant+Unicase:wght@300;400;500;600;700&display=swap" rel="stylesheet">

</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-black border-bottom border-info p-3">
    <div class="container-fluid">
        <a class="brand-logo fw-bold" href="/home" style="letter-spacing: 3px;">TONE ENGINE</a>
        <div class="navbar-nav ms-auto">
            <a href="/home" class="nav-link active text-info">
                STORE
            </a>
            <a href="/cart" class="nav-link">
                <i class="bi bi-cart-fill"></i>CART
            </a>
        </div>
    </div>
</nav>

<div class="container-fluid mt-4">
    <div class="row g-4">
        <!-- sidebar -->
        <aside class="col-lg-3 col-xl-2">
            <form action="${pageContext.request.contextPath}/home" method="get">
                <input type="hidden" name="type" value="${param.type}">
                <div class="rpg-panel sticky-top" style="top: 20px;">
                    <h6 class="cormorant-unicase-regular text-uppercase mb-3">Musical equipment</h6>

                    <div class="list-group list-group-flush">
                        <a href="?type=guitar${not empty param.sort ? '&sort=' : ''}${param.sort}" class="list-group-item list-group-item-action bg-transparent text-white border-secondary small ${param.type == 'guitar' ? 'active text-info' : ''}">
                            <i class="bi bi-music-note-beamed me-2"></i> GUITARS
                        </a>
                        <a href="?type=pedal${not empty param.sort ? '&sort=' : ''}${param.sort}" class="list-group-item list-group-item-action bg-transparent text-white border-secondary small  ${param.type == 'pedal' ? 'active text-info' : ''}">
                            <i class="bi bi-cpu me-2"></i> PEDALS
                        </a>
                        <a href="?type=amplifier${not empty param.sort ? '&sort=' : ''}${param.sort}" class="list-group-item list-group-item-action bg-transparent text-white border-secondary small  ${param.type == 'amplifier' ? 'active text-info' : ''}">
                            <i class="bi bi-speaker me-2"></i> AMPLIFIERS
                        </a>
                        <a href="?${not empty param.sort ? 'sort=' : ''}${param.sort}" class="list-group-item list-group-item-action bg-transparent text-white-50 border-secondary x-small">
                            <i class="bi bi-grid-fill me-2"></i> SHOW ALL
                        </a>
                    </div>
                    <div class="mb-4">
                        <select name="sort" class="form-select bg-dark text-white border-secondary" onchange="this.form.submit()">
                            <option value="asc" ${param.sort == 'asc' ? 'selected' : ''}>Cheap first</option>
                            <option value="desc" ${param.sort == 'desc' || empty param.sort ? 'selected' : ''}>Expensive first</option>
                        </select>
                    </div>
                </div>
            </form>
        </aside>

        <!-- main grid -->
        <main class="col-lg-9 col-xl-10">
            <div class="rpg-panel shadow-lg">
                <div class="d-flex justify-content-between align-items-center mb-4 border-bottom border-secondary pb-3">
                    <h4 class="cormorant-unicase-regular mb-0 text-uppercase fw-bold">Available Gear</h4>
                    <span class="badge bg-secondary">${gearList.size()} items</span>
                </div>

                <div class="row row-cols-1 row-cols-md-2 row-cols-xl-3 row-cols-xxl-4 g-4">
                    <c:forEach items="${gearList}" var="gear">
                        <div class="col">
                            <div class="gear-card p-3 h-100 d-flex flex-column">
                                <div class="text-center bg-dark rounded mb-3 overflow-hidden" style="height: 200px;">
                                    <img src="${gear.imageUrl}" alt="${gear.model}" class="img-fluid h-100 w-100 object-fit-contain">
                                </div>
                                <h6 class="mb-1">${gear.brand}</h6>
                                <p class="small text-white-50 flex-grow-1">${gear.model}</p>
                                <div class="d-flex justify-content-between align-items-end mt-3">

                                    <div class="price-tag">$${gear.price}</div>
                                    <form action="${pageContext.request.contextPath}/cart/add" method="post" class="m-0">
                                        <input type="hidden" name="id" value="${gear.id}">

                                        <button type="submit" class="btn btn-sm btn-outline-success">
                                            <i class="bi bi-plus-lg"></i> Add to cart
                                        </button>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </main>
    </div>
</div>
</body>
</html>
