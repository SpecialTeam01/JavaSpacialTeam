<%@ page contentType="text/html; charset=UTF-8" language="java"
         import="model.Astronaut, java.text.SimpleDateFormat" %>
<%@ include file="/includes/header.jsp" %>

<%
  Astronaut astro = (Astronaut) request.getAttribute("astronaut");
  boolean edit = astro != null;
  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
  String birthValue = edit && astro.getBirthDate() != null ? df.format(astro.getBirthDate()) : "";
%>

<div class="container my-5" style="max-width: 600px;">
  <h2 class="mb-4">
    <i class="fa-solid fa-user-astronaut me-2"></i>
    <%= edit ? "Edit Astronaut" : "New Astronaut" %>
  </h2>

  <form method="post" action="<%= request.getContextPath() %>/astronaut">
    <% if (edit) { %>
    <input type="hidden" name="astronautId" value="<%= astro.getAstronautId() %>"/>
    <% } %>

    <div class="mb-3">
      <label for="name" class="form-label">Name</label>
      <input type="text" id="name" name="name" class="form-control rounded-pill"
             required value="<%= edit ? astro.getName() : "" %>"/>
    </div>

    <div class="mb-3">
      <label for="birthDate" class="form-label">Birth Date</label>
      <input type="date" id="birthDate" name="birthDate"
             class="form-control rounded-pill" required value="<%= birthValue %>"/>
    </div>

    <div class="mb-3">
      <label for="nationality" class="form-label">Nationality</label>
      <input type="text" id="nationality" name="nationality"
             class="form-control rounded-pill" value="<%= edit ? astro.getNationality() : "" %>"/>
    </div>

    <div class="mb-3">
      <label for="missionsCompleted" class="form-label">Missions Completed</label>
      <input type="number" id="missionsCompleted" name="missionsCompleted"
             class="form-control rounded-pill" min="0"
             value="<%= edit ? astro.getMissionsCompleted() : 0 %>"/>
    </div>

    <div class="form-check form-switch mb-4">
      <input class="form-check-input" type="checkbox" id="active" name="active"
              <%= edit && astro.isActive() ? "checked" : "" %>/>
      <label class="form-check-label text-light" for="active">Active</label>
    </div>

    <div class="d-flex gap-2">
      <button type="submit" class="btn btn-info px-4">
        <%= edit ? "Update" : "Create" %>
      </button>
      <a href="<%= request.getContextPath() %>/astronaut" class="btn btn-secondary">
        Cancel
      </a>
    </div>
  </form>
</div>

<%@ include file="/includes/footer.jsp" %>
