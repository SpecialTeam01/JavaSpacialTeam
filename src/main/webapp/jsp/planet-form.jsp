<!-- File: src/main/webapp/jsp/planet-form.jsp -->
<%@ page contentType="text/html; charset=UTF-8" language="java"
         import="model.Planet" %>
<%@ include file="/includes/header.jsp" %>

<%
  Planet planet = (Planet) request.getAttribute("planet");
  boolean edit = planet != null;

  String nameValue         = edit ? planet.getName() : "";
  String diameterValue     = edit && planet.getDiameter() != null
          ? planet.getDiameter().toString() : "";
  String discoveryValue    = "";
  if (edit && planet.getDiscoveryDate() != null) {
    discoveryValue = planet.getDiscoveryDate().toString();  // ISO yyyy-MM-dd
  }
  boolean hasAtmos        = edit && planet.isHasAtmosphere();
  String descriptionValue = edit && planet.getDescription() != null
          ? planet.getDescription() : "";
%>

<div class="container my-5" style="max-width: 600px;">
  <h2>
    <i class="fa-solid fa-globe"></i>
    <%= edit ? "Edit Planet" : "New Planet" %></h2>

  <form method="post" action="<%= request.getContextPath() %>/planets">
    <% if (edit) { %>
    <input type="hidden" name="planetId" value="<%= planet.getPlanetId() %>"/>
    <% } %>

    <div class="mb-3">
      <label for="name" class="form-label">Name</label>
      <input type="text" id="name" name="name"
             class="form-control rounded-pill" required
             value="<%= nameValue %>"/>
    </div>

    <div class="mb-3">
      <label for="diameter" class="form-label">Diameter (km)</label>
      <input type="number" id="diameter" name="diameter"
             class="form-control rounded-pill" min="0"
             value="<%= diameterValue %>"/>
    </div>

    <div class="mb-3">
      <label for="discoveryDate" class="form-label">Discovery Date</label>
      <input type="date" id="discoveryDate" name="discoveryDate"
             class="form-control rounded-pill" required
             value="<%= discoveryValue %>"/>
    </div>

    <div class="form-check mb-3">
      <input class="form-check-input rounded-pill" type="checkbox"
             id="hasAtmosphere" name="hasAtmosphere"
              <%= hasAtmos ? "checked" : "" %>/>
      <label class="form-check-label" for="hasAtmosphere">
        Has Atmosphere
      </label>
    </div>

    <div class="mb-3">
      <label for="description" class="form-label">Description</label>
      <textarea id="description" name="description"
                class="form-control" rows="3"><%= descriptionValue %></textarea>
    </div>

    <button type="submit" class="btn btn-primary">
      <%= edit ? "Update" : "Create" %>
    </button>
    <a href="<%= request.getContextPath() %>/planets"
       class="btn btn-secondary">Cancel</a>
  </form>
</div>

<%@ include file="/includes/footer.jsp" %>
