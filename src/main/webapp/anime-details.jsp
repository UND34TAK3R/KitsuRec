<%@include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <h1>${anime.title}</h1>
    <a href="${pageContext.request.contextPath}/browse" class="back-link">← Back to Browse</a>
</header>

<main class="anime-details">
    <div class="anime-info-container">
        <div class="anime-image">
            <c:if test="${anime.main_picture != null}">
                <img src="${anime.main_picture.medium}" alt="${anime.title}">
            </c:if>
        </div>

        <div class="anime-info">
            <h2>${anime.title}</h2>

            <p><strong>Rating:</strong> ${anime.mean}/10</p>
            <p><strong>Status:</strong> ${anime.status}</p>
            <p><strong>Episodes:</strong> ${anime.num_episodes}</p>
            <p><strong>Start Date:</strong> ${anime.start_date}</p>
            <c:if test="${anime.end_date != null}">
                <p><strong>End Date:</strong> ${anime.end_date}</p>
            </c:if>

            <div class="genres">
                <strong>Genres:</strong>
                <ul>
                    <c:forEach var="genre" items="${anime.genres}">
                        <li>${genre.name}</li>
                    </c:forEach>
                </ul>
            </div>

            <!-- Add to Watch List form -->
            <form action="${pageContext.request.contextPath}/watchlist" method="POST">
                <input type="hidden" name="animeId" value="${anime.id}">

                <c:choose>
                    <c:when test="${inWatchlist}">
                        <div class="watchlist-controls">
                            <button type="submit" name="action" value="remove" class="btn btn-danger">Remove from Watch List</button>

                            <div class="watchlist-options">
                                <label>
                                    <input type="checkbox" name="watched" value="true" ${watchListItem.watched ? 'checked' : ''}>
                                    Watched
                                </label>
                                <label>
                                    <input type="checkbox" name="favorite" value="true" ${watchListItem.favorite ? 'checked' : ''}>
                                    Favorite
                                </label>
                                <button type="submit" name="action" value="update" class="btn btn-secondary">Update Status</button>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" name="action" value="add" class="btn btn-primary">Add to Watch List</button>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
    </div>

    <div class="anime-synopsis">
        <h3>Synopsis</h3>
        <p>${anime.synopsis}</p>
    </div>
</main>

<%@include file="footer.jsp"%>