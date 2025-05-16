<!-- File: src/main/webapp/jsp/astronaut-form.jsp -->
<%@ page contentType="text/html; charset=UTF-8" language="java"
         import="model.Astronaut, java.text.SimpleDateFormat" %>
<%@ include file="/includes/header.jsp" %>

<%
  Astronaut astro = (Astronaut) request.getAttribute("astronaut");
  boolean edit = astro != null;
  // Para formatear la fecha en yyyy-MM-dd
  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
  String birthValue = edit && astro.getBirthDate() != null
          ? df.format(astro.getBirthDate())
          : "";
%>

<div class="container my-4">
  <h2><%= edit ? "Edit Astronaut" : "New Astronaut" %></h2>

  <form method="post" action="<%= request.getContextPath() %>/astronaut">
    <% if (edit) { %>
    <input type="hidden" name="astronautId"
           value="<%= astro.getAstronautId() %>"/>
    <% } %>

    <div class="mb-3">
      <label for="name" class="form-label">Name</label>
      <input type="text" id="name" name="name"
             class="form-control" required
             value="<%= edit ? astro.getName() : "" %>"/>
    </div>

    <div class="mb-3">
      <label for="birthDate" class="form-label">Birth Date</label>
      <input type="date" id="birthDate" name="birthDate"
             class="form-control" required
             value="<%= birthValue %>"/>
    </div>

    <div class="mb-3">
      <label for="nationality" class="form-label">Nationality</label>
      <input type="text" id="nationality" name="nationality"
             class="form-control"
             value="<%= edit ? astro.getNationality() : "" %>"/>
    </div>


    <div class="mb-3">
      <label for="missionsCompleted" class="form-label">
        Missions Completed
      </label>
      <input type="number" id="missionsCompleted"
             name="missionsCompleted"
             class="form-control" min="0"
             value="<%= edit ? astro.getMissionsCompleted() : 0 %>"/>
    </div>

    <div class="form-check mb-3">
      <input class="form-check-input" type="checkbox" id="active"
             name="active"
              <%= edit && astro.isActive() ? "checked" : "" %>/>
      <label class="form-check-label" for="active">Active</label>
    </div>

    <button type="submit" class="btn btn-primary">
      <%= edit ? "Update" : "Create" %>
    </button>
    <a href="<%= request.getContextPath() %>/astronaut"
       class="btn btn-secondary">Cancel</a>
  </form>
</div>

<%@ include file="/includes/footer.jsp" %>
