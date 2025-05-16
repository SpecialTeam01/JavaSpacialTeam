<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/includes/header.jsp" %>

<section class="hero text-center">
    <div class="container">
        <h1 class="display-4">Welcome to Stellar Odyssey</h1>
        <p class="lead">Explore the galaxy</p>
    </div>

    <div class="container py-5">
        <div class="row g-4">
            <div class="col-md-4">
                <div class="card card-hover h-100">
                    <img src="${pageContext.request.contextPath}/images/astronaut.png"
                         class="card-img-top" alt="Astronauts">
                    <div class="card-body">
                        <h5 class="card-title">Astronauts</h5>
                        <p class="card-text">Meet our brave explorers.</p>
                    </div>
                    <div class="card-footer bg-transparent">
                        <a href="${pageContext.request.contextPath}/astronauts"
                           class="btn btn-primary">
                            View Astronauts <i class="fa-solid fa-arrow-right ms-1"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card card-hover h-100">
                    <img src="${pageContext.request.contextPath}/images/planet.png"
                         class="card-img-top" alt="Planets">
                    <div class="card-body">
                        <h5 class="card-title">Planets</h5>
                        <p class="card-text">Discover the worlds we’ve visited.</p>
                    </div>
                    <div class="card-footer bg-transparent">
                        <a href="${pageContext.request.contextPath}/planets" class="btn btn-primary">
                            View Planets <i class="fa-solid fa-arrow-right ms-1"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card card-hover h-100">
                    <img src="${pageContext.request.contextPath}/images/mission.png"
                         class="card-img-top" alt="Missions">
                    <div class="card-body">
                        <h5 class="card-title">Missions</h5>
                        <p class="card-text">Browse the history of our expeditions.</p>
                    </div>
                    <div class="card-footer bg-transparent">
                        <a href="${pageContext.request.contextPath}/missions"
                           class="btn btn-primary">
                            View Missions <i class="fa-solid fa-arrow-right ms-1"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="/includes/footer.jsp" %>

