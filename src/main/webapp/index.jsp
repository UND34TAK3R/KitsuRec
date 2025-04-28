<%@include file="header.jsp"%>
<main class="container">
    <!-- Hero Section -->
    <section class="hero py-5">
        <div class="hero-content">
            <div class="row">
                <div class="col-md-6">
                    <h1 class="display-4 fw-bold">Discover Your Next Favorite Anime</h1>
                    <p class="lead">Keep track of what you're watching, discover new shows, and never lose your place with KitsuRec - your personal anime watchlist.</p>
                    <button class="btn btn-primary btn-lg mt-3">Get Started <i class="fas fa-arrow-right ms-2"></i></button>
                </div>
            </div>
        </div>
    </section>

    <!-- Trending Now Section -->
    <section class="mb-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="mb-0 fw-bold">Trending Now</h2>
            <a href="#" class="text-decoration-none text-primary">View All <i class="fas fa-chevron-right"></i></a>
        </div>
        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">
                <div class="col">
                    <div class="card h-100">
                        <div class="anime-card">
                            <div class="anime-image">
                                <img src="https://m.media-amazon.com/images/M/MV5BMWU1OGEwNmQtNGM3MS00YTYyLThmYmMtN2FjYzQzNzNmNTE0XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg" width="400px" height="250px" alt="Anime Cover">
                                <div class="anime-badge bg-dark text-warning">8.9</div>
                                <div class="anime-type">TV</div>
                            </div>
                            <div class="anime-info">
                                <h5 class="card-title">Demon Slayer</h5>
                                <div class="d-flex justify-content-between">
                                    <small>2023</small>
                                    <small>24 Episodes</small>
                                </div>
                                <button class="btn btn-outline-primary w-100 mt-3">
                                    <i class="fas fa-plus me-2"></i> Add to Watchlist
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class = "col">
                    <div class = "card h-100">
                        <div class="anime-card">
                            <div class="anime-image">
                                <img src="https://m.media-amazon.com/images/M/MV5BN2NhYzU2NDEtYzI1NS00MjgzLThjZGUtOTYxNGJkZjZmNDdjXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg" width="400px" height="250px" alt="Anime Cover">
                                <div class="anime-badge bg-dark text-warning">8.7</div>
                                <div class="anime-type">TV</div>
                            </div>
                            <div class="anime-info">
                                <h5 class="card-title">Sword Art Online</h5>
                                <div class="d-flex justify-content-between">
                                    <small>2012</small>
                                    <small>25 Episodes</small>
                                </div>
                                <button class="btn btn-outline-primary w-100 mt-3">
                                    <i class="fas fa-plus me-2"></i> Add to Watchlist
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class = "col">
                    <div class = "card h-100">
                        <div class="anime-card">
                            <div class="anime-image">
                                <img src="https://m.media-amazon.com/images/M/MV5BMDg3MGVhNWUtYTQ2NS00ZDdiLTg5MTMtZmM5MjUzN2IxN2I4XkEyXkFqcGc@._V1_.jpg" width="400px" height="250px" alt="Anime Cover">
                                <div class="anime-badge bg-dark text-warning">9.2</div>
                                <div class="anime-type">TV</div>
                            </div>
                            <div class="anime-info">
                                <h5 class="card-title">Classroom of the Elite</h5>
                                <div class="d-flex justify-content-between">
                                    <small>2017</small>
                                    <small>13 Episodes</small>
                                </div>
                                <button class="btn btn-outline-primary w-100 mt-3">
                                    <i class="fas fa-plus me-2"></i> Add to Watchlist
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class = "col">
                    <div class = "card h-100">
                        <div class="anime-card">
                            <div class="anime-image">
                                <img src="https://pbs.twimg.com/media/FY0t4qLWAAEfcIk.jpg:large" width="400px" height="250px" alt="Anime Cover">
                                <div class="anime-badge bg-dark text-warning">9.2</div>
                                <div class="anime-type">TV</div>
                            </div>
                            <div class="anime-info">
                                <h5 class="card-title">Chainsaw Man</h5>
                                <div class="d-flex justify-content-between">
                                    <small>2022</small>
                                    <small>12 Episodes</small>
                                </div>
                                <button class="btn btn-outline-primary w-100 mt-3">
                                    <i class="fas fa-plus me-2"></i> Add to Watchlist
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </section>

    <!-- Categories Section -->
    <section class="mb-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="mb-0 fw-bold">Categories</h2>
            <a href="#" class="text-decoration-none text-primary">View All <i class="fas fa-chevron-right"></i></a>
        </div>
        <div class="row row-cols-2 row-cols-md-3 row-cols-lg-6 g-3">
            <div class="col">
                <div class="card category-card">
                    <div class="category-icon">
                        <i class="fas fa-fist-raised"></i>
                    </div>
                    <h6 class="card-title">Action</h6>
                </div>
            </div>
            <div class="col">
                <div class="card category-card">
                    <div class="category-icon">
                        <i class="fas fa-heart"></i>
                    </div>
                    <h6 class="card-title">Romance</h6>
                </div>
            </div>
            <div class="col">
                <div class="card category-card">
                    <div class="category-icon">
                        <i class="fas fa-ghost"></i>
                    </div>
                    <h6 class="card-title">Horror</h6>
                </div>
            </div>
            <div class="col">
                <div class="card category-card">
                    <div class="category-icon">
                        <i class="fas fa-rocket"></i>
                    </div>
                    <h6 class="card-title">Sci-Fi</h6>
                </div>
            </div>
            <div class="col">
                <div class="card category-card">
                    <div class="category-icon">
                        <i class="fas fa-dragon"></i>
                    </div>
                    <h6 class="card-title">Fantasy</h6>
                </div>
            </div>
            <div class="col">
                <div class="card category-card">
                    <div class="category-icon">
                        <i class="fas fa-theater-masks"></i>
                    </div>
                    <h6 class="card-title">Drama</h6>
                </div>
            </div>
        </div>
    </section>

    <!-- Continue Watching Section -->
    <section class="continue-watching mb-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="mb-0 fw-bold">Continue Watching</h2>
            <a href="#" class="text-decoration-none text-primary">View All <i class="fas fa-chevron-right"></i></a>
        </div>
        <c:forEach begin="1" end="1">
            <div class="card mb-3">
                <div class="row g-0">
                    <div class="col-md-2">
                        <div class="continue-image">
                            <img src="https://images.justwatch.com/poster/301574476/s718/season-1.jpg" height = "200px" width = "300px" alt="Anime Cover" class="img-fluid">
                        </div>
                    </div>
                    <div class="col-md-10">
                        <div class="card-body d-flex flex-column justify-content-between h-100">
                            <div>
                                <div class="d-flex justify-content-between mb-2">
                                    <h5 class="card-title">One Piece</h5>
                                    <span>Episode 8 of ???</span>
                                </div>
                                <div class="progress">
                                    <div class="progress-bar" role="progressbar" style="width: 35%" aria-valuenow="35" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                            </div>
                            <div class="mt-3">
                                <button class="btn btn-primary">
                                    <i class="fas fa-play me-2"></i> Resume
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </section>

    <!-- Our Recommendations -->
    <section class="mb-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="mb-0 fw-bold">Our Recommendations</h2>
            <a href="#" class="text-decoration-none text-primary">View All <i class="fas fa-chevron-right"></i></a>
        </div>
        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">
                <div class="col">
                    <div class="card h-100">
                        <div class="anime-card">
                            <div class="anime-image">
                                <img src="https://m.media-amazon.com/images/M/MV5BM2MzMGRlN2QtM2FhMS00Y2FhLWE4MTEtMGNiZTU5YTdiY2JiXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg" width="200px" height="200px" alt="Anime Cover">
                                <div class="anime-badge bg-dark text-warning">8.2</div>
                                <div class="anime-type">TV</div>
                            </div>
                            <div class="anime-info">
                                <h5 class="card-title">Toilet-Bound Hanako-kun</h5>
                                <div class="d-flex justify-content-between">
                                    <small>2020</small>
                                    <small>?? episodes</small>
                                </div>
                                <button class="btn btn-outline-primary w-100 mt-3">
                                    <i class="fas fa-plus me-2"></i> Add to Watchlist
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            <div class="col">
                <div class="card h-100">
                    <div class="anime-card">
                        <div class="anime-image">
                            <img src="https://m.media-amazon.com/images/M/MV5BMTc5MTE3MTktN2MwYy00M2UwLTlhMzEtNDE5YjBmMTUzMGRkXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg" width="200px" height="200px" alt="Anime Cover">
                            <div class="anime-badge bg-dark text-warning">9.2</div>
                            <div class="anime-type">TV</div>
                        </div>
                        <div class="anime-info">
                            <h5 class="card-title">100 Girlfriends Who Really, Really, Really, REALLY Love You</h5>
                            <div class="d-flex justify-content-between">
                                <small>2023</small>
                                <small>?? Episodes</small>
                            </div>
                            <button class="btn btn-outline-primary w-100 mt-3">
                                <i class="fas fa-plus me-2"></i> Add to Watchlist
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-100">
                    <div class="anime-card">
                        <div class="anime-image">
                            <img src="https://m.media-amazon.com/images/M/MV5BNjEyZmRmMGYtNGY1Zi00MTFiLWE0ZTMtNzUzMWJmMTgzNWE5XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg" width="200px" height="200px" alt="Anime Cover">
                            <div class="anime-badge bg-dark text-warning">9.2</div>
                            <div class="anime-type">TV</div>
                        </div>
                        <div class="anime-info">
                            <h5 class="card-title">Toradora!</h5>
                            <div class="d-flex justify-content-between">
                                <small>2008</small>
                                <small>25 Episodes</small>
                            </div>
                            <button class="btn btn-outline-primary w-100 mt-3">
                                <i class="fas fa-plus me-2"></i> Add to Watchlist
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-100">
                    <div class="anime-card">
                        <div class="anime-image">
                            <img src="https://m.media-amazon.com/images/M/MV5BOWNmY2IzOGItMmQyNy00ZTM0LThiNjItODM3YzdkYjRlNWU1XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg" width="200px" height="200px" alt="Anime Cover">
                            <div class="anime-badge bg-dark text-warning">10.0</div>
                            <div class="anime-type">TV</div>
                        </div>
                        <div class="anime-info">
                            <h5 class="card-title">86: Eighty Six</h5>
                            <div class="d-flex justify-content-between">
                                <small>2021</small>
                                <small>23 Episodes</small>
                            </div>
                            <button class="btn btn-outline-primary w-100 mt-3">
                                <i class="fas fa-plus me-2"></i> Add to Watchlist
                            </button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </section>
</main>
<%@include file="footer.jsp"%>