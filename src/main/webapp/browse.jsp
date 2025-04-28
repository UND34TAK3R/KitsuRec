<%@include file="header.jsp"%>

<header class="text-center my-4">
    <h1>Browse Anime</h1>
</header>

<main class="container">
    <div class="row justify-content-center">
        <c:forEach var="anime" items="${animeList}">
            <div class="col-12 col-sm-6 col-md-3 mb-4 d-flex align-items-stretch">
                <div class="card text-center w-100 shadow-sm">
                    <c:if test="${anime.main_picture != null}">
                        <img src="${anime.main_picture.medium}" class="card-img-top" alt="${anime.title}" loading="lazy">
                    </c:if>
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title">${anime.title}</h5>
                        <p class="card-text">Rating: ${anime.mean}/10</p>
                        <a href="${pageContext.request.contextPath}/browse?id=${anime.anime_id}" class="btn btn-primary mt-auto">View Details</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</main>

<div class="d-flex justify-content-center my-4">
    <div class="btn-group">
        <c:if test="${currentPage > 1}">
            <a href="?page=${currentPage - 1}" class="btn btn-outline-primary">Previous</a>
        </c:if>
        <button class="btn btn-primary disabled">Page ${currentPage}</button>
        <a href="?page=${currentPage + 1}" class="btn btn-outline-primary">Next</a>
    </div>
</div>

<%@include file="footer.jsp"%>
