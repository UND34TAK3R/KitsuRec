
<%@include file="header.jsp"%>
<header>
    <h1>Browse Anime</h1>
    <!-- Navigation links -->
</header>

<main class="anime-grid">
    <c:forEach var="anime" items="${animeList}">
        <div class="anime-card">
            <c:if test="${anime.main_picture != null}">
                <img src="${anime.main_picture.medium}" alt="${anime.title}">
            </c:if>
            <h3>${anime.title}</h3>
            <p>Rating: ${anime.mean}/10</p>
            <a href="${pageContext.request.contextPath}/browse?id=${anime.id}">View Details</a>
        </div>
    </c:forEach>
</main>

<div class="pagination">
    <c:if test="${currentPage > 1}">
        <a href="?page=${currentPage - 1}">Previous</a>
    </c:if>
    <span>Page ${currentPage}</span>
    <a href="?page=${currentPage + 1}">Next</a>
</div>
<%@include file="footer.jsp"%>
