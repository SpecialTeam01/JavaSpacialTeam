<%@ page contentType="text/html; charset=UTF-8" language="java"
         import="model.Astronaut" %>
<%@ include file="/includes/header.jsp" %>

<%
  Astronaut astro = (Astronaut) request.getAttribute("astronaut");
  // Recibimos el nombre de fichero que pasó el servlet
  String imgFile    = (String) request.getAttribute("imageFile");
  String imgPath    = request.getContextPath() + "/images/" + imgFile;
  String defaultImg = request.getContextPath() + "/images/defautuser.jpg";
%>
<div class="container my-4">
  <h2>Astronaut Details</h2>
<div class="row mt-3 align-items-start">
  <!-- Columna izquierda: Detalles -->
  <div class="col-md-7">
    <dl class="row">
      <dt class="col-sm-4">ID</dt>
      <dd class="col-sm-8"><%= astro.getAstronautId() %></dd>

      <dt class="col-sm-4">Name</dt>
      <dd class="col-sm-8"><%= astro.getName() %></dd>

      <dt class="col-sm-4">Birth Date</dt>
      <dd class="col-sm-8"><%= astro.getBirthDate() %></dd>

      <dt class="col-sm-4">Nationality</dt>
      <dd class="col-sm-8"><%= astro.getNationality() %></dd>

      <dt class="col-sm-4">Missions Completed</dt>
      <dd class="col-sm-8"><%= astro.getMissionsCompleted() %></dd>

      <dt class="col-sm-4">Active</dt>
      <dd class="col-sm-8"><%= astro.isActive() ? "Yes" : "No" %></dd>
    </dl>
    <a href="<%= request.getContextPath() %>/astronaut" class="btn btn-light">← Back to list</a>
  </div>

  <!-- Columna derecha: Imagen -->
  <div class="col-md-5 text-center">
    <img src="<%= imgPath %>"
         alt="Photo of <%= astro.getName() %>"
         class="img-fluid rounded shadow"
         style="max-height: 300px;"
         onerror="this.onerror=null; this.src='<%= defaultImg %>';">
  </div>
</div>
</div>

<%@ include file="/includes/footer.jsp" %>
