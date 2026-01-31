<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>

<div class="container">

    <h2 class="mb-3">${restaurant.name}</h2>

    <!-- VIDEO -->
    <div class="mb-4">
        <iframe width="100%" height="400"
                src="${restaurant.videoUrl}"
                frameborder="0"
                allowfullscreen>
        </iframe>
    </div>

    <!-- POSTER + INFO -->
    <div class="row">
        <div class="col-md-4">
            <img src="${pageContext.request.contextPath}/images/${restaurant.posterUrl}"
                 class="img-fluid rounded shadow">
        </div>

        <div class="col-md-8">
            <p><strong>Lượt xem:</strong> ${restaurant.viewCount}</p>
            <a href="${pageContext.request.contextPath}/share?id=${restaurant.restaurantId}"
               class="btn btn-outline-secondary">
                Share
            </a>

            <a href="${pageContext.request.contextPath}/home"
               class="btn btn-secondary">
                ← Quay lại
            </a>
        </div>
    </div>

</div>
