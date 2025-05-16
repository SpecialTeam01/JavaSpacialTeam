<%@ page contentType="text/html; charset=UTF-8" language="java"
         import="model.Planet" %>
<%@ include file="/includes/header.jsp" %>

<%
  Planet planet = (Planet) request.getAttribute("planet");
%>

<div class="container my-4">
  <h2>Planet Details</h2>

  <dl class="row mt-3">
    <dt class="col-sm-3">ID</dt>
    <dd class="col-sm-9"><%= planet.getPlanetId() %></dd>

    <dt class="col-sm-3">Name</dt>
    <dd class="col-sm-9"><%= planet.getName() %></dd>

    <dt class="col-sm-3">Diameter</dt>
    <dd class="col-sm-9"><%= planet.getDiameter() != null ? planet.getDiameter() : "-" %> km</dd>

    <dt class="col-sm-3">Discovery Date</dt>
    <dd class="col-sm-9"><%= planet.getDiscoveryDate() %></dd>

    <dt class="col-sm-3">Has Atmosphere</dt>
    <dd class="col-sm-9"><%= planet.isHasAtmosphere() ? "Yes" : "No" %></dd>

    <dt class="col-sm-3">Description</dt>
    <dd class="col-sm-9"><%= planet.getDescription() != null ? planet.getDescription() : "-" %></dd>
  </dl>

  <a href="<%= request.getContextPath() %>/planets"
     class="btn btn-secondary">← Back to list</a>
</div>

<%@ include file="/includes/footer.jsp" %>
