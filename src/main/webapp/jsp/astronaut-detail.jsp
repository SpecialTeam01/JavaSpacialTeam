<!-- File: src/main/webapp/jsp/astronaut-detail.jsp -->
<%@ page contentType="text/html; charset=UTF-8" language="java"
         import="model.Astronaut" %>
<%@ include file="/includes/header.jsp" %>

<%
  Astronaut astro = (Astronaut) request.getAttribute("astronaut");
%>
<div class="container my-4">
  <h2>Astronaut Details</h2>

  <dl class="row mt-3">
    <dt class="col-sm-3">ID</dt>
    <dd class="col-sm-9"><%= astro.getAstronautId() %></dd>

    <dt class="col-sm-3">Name</dt>
    <dd class="col-sm-9"><%= astro.getName() %></dd>

    <dt class="col-sm-3">Birth Date</dt>
    <dd class="col-sm-9"><%= astro.getBirthDate() %></dd>

    <dt class="col-sm-3">Nationality</dt>
    <dd class="col-sm-9"><%= astro.getNationality() %></dd>

    <dt class="col-sm-3">Missions Completed</dt>
    <dd class="col-sm-9"><%= astro.getMissionsCompleted() %></dd>

    <dt class="col-sm-3">Active</dt>
    <dd class="col-sm-9">
      <%= astro.isActive() ? "Yes" : "No" %>
    </dd>
  </dl>

  <a href="<%= request.getContextPath() %>/astronauts"
     class="btn btn-secondary">← Back to list</a>
</div>

<%@ include file="/includes/footer.jsp" %>
