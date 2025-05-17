<!-- File: src/main/webapp/jsp/planet-detail.jsp -->
<%@ page contentType="text/html; charset=UTF-8" language="java"
         import="model.Planet" %>
<%@ include file="/includes/header.jsp" %>

<%
  Planet p = (Planet) request.getAttribute("planet");
  // Recibimos el nombre de fichero que pasó el servlet
  String imgFile    = (String) request.getAttribute("imageFile");
  // Ahora buscamos en /images/
  String imgPath    = request.getContextPath() + "/images/" + imgFile;
  String defaultImg = request.getContextPath() + "/images/default.png";
%>

<div class="container my-4">
  <h2>Planet Details</h2>

  <div class="row mt-3 align-items-start">
    <!-- Columna izquierda: Datos -->
    <div class="col-md-7">
      <dl class="row">
        <dt class="col-sm-4">ID</dt>
        <dd class="col-sm-8"><%= p.getPlanetId() %></dd>

        <dt class="col-sm-4">Name</dt>
        <dd class="col-sm-8"><%= p.getName() %></dd>

        <dt class="col-sm-4">Diameter</dt>
        <dd class="col-sm-8"><%= p.getDiameter() != null ? p.getDiameter() : "—" %></dd>

        <dt class="col-sm-4">Discovery Date</dt>
        <dd class="col-sm-8"><%= p.getDiscoveryDate() %></dd>

        <dt class="col-sm-4">Has Atmosphere</dt>
        <dd class="col-sm-8"><%= p.isHasAtmosphere() ? "Yes" : "No" %></dd>

        <dt class="col-sm-4">Description</dt>
        <dd class="col-sm-8"><%= p.getDescription() %></dd>
      </dl>
      <a href="<%= request.getContextPath() %>/planets" class="btn btn-light">← Back to list</a>
    </div>

    <!-- Columna derecha: Imagen -->
    <div class="col-md-5 text-center">
      <img src="<%= imgPath %>"
           alt="Image of <%= p.getName() %>"
           class="img-fluid rounded shadow"
           style="max-height: 300px;"
           onerror="this.onerror=null; this.src='<%= defaultImg %>';">
    </div>
  </div>
</div>

<%@ include file="/includes/footer.jsp" %>
