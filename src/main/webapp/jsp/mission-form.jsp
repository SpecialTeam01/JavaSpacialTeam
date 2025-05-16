<%@ page contentType="text/html; charset=UTF-8" language="java"
         import="model.Mission, model.Astronaut, model.Planet, java.util.List, java.text.SimpleDateFormat" %>
<%@ include file="/includes/header.jsp" %>

<%
  Mission mission = (Mission) request.getAttribute("mission");
  boolean edit = mission != null;
  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
  String startValue = edit && mission.getStartDate() != null ? df.format(mission.getStartDate()) : "";
  String endValue   = edit && mission.getEndDate()   != null ? df.format(mission.getEndDate())   : "";

  List<Astronaut> astronautList = (List<Astronaut>) request.getAttribute("astronautList");
  List<Planet> planetList = (List<Planet>) request.getAttribute("planetList");
%>

<div class="container my-4">
  <h2><%= edit ? "Edit Mission" : "New Mission" %></h2>

  <form method="post" action="<%= request.getContextPath() %>/mission">
    <% if (edit) { %>
    <input type="hidden" name="missionId" value="<%= mission.getMissionId() %>"/>
    <% } %>

    <div class="mb-3">
      <label class="form-label" for="missionName">Mission Name</label>
      <input type="text" id="missionName" name="missionName"
             class="form-control" required
             value="<%= edit ? mission.getMissionName() : "" %>"/>
    </div>

    <div class="mb-3">
      <label for="astronautId" class="form-label">Astronaut</label>
      <select id="astronautId" name="astronautId" class="form-select" required>
        <option value="">-- Select Astronaut --</option>
        <% for (Astronaut a : astronautList) { %>
        <option value="<%= a.getAstronautId() %>"
                <%= edit && a.getAstronautId() == mission.getAstronaut().getAstronautId() ? "selected" : "" %>>
          <%= a.getName() %>
        </option>
        <% } %>
      </select>
    </div>

    <div class="mb-3">
      <label for="planetId" class="form-label">Planet</label>
      <select id="planetId" name="planetId" class="form-select" required>
        <option value="">-- Select Planet --</option>
        <% for (Planet p : planetList) { %>
        <option value="<%= p.getPlanetId() %>"
                <%= edit && p.getPlanetId() == mission.getPlanet().getPlanetId() ? "selected" : "" %>>
          <%= p.getName() %>
        </option>
        <% } %>
      </select>
    </div>

    <div class="mb-3">
      <label for="startDate" class="form-label">Start Date</label>
      <input type="date" id="startDate" name="startDate" class="form-control" required
             value="<%= startValue %>"/>
    </div>

    <div class="mb-3">
      <label for="endDate" class="form-label">End Date</label>
      <input type="date" id="endDate" name="endDate" class="form-control"
             value="<%= endValue %>"/>
    </div>

    <div class="mb-3">
      <label for="status" class="form-label">Status</label>
      <input type="text" id="status" name="status" class="form-control"
             value="<%= edit ? mission.getStatus() : "" %>"/>
    </div>

    <div class="mb-3">
      <label for="description" class="form-label">Description</label>
      <textarea id="description" name="description"
                class="form-control" rows="4"><%= edit ? mission.getDescription() : "" %></textarea>
    </div>

    <button type="submit" class="btn btn-primary"><%= edit ? "Update" : "Create" %></button>
    <a href="<%= request.getContextPath() %>/missions" class="btn btn-secondary">Cancel</a>
  </form>
</div>

<%@ include file="/includes/footer.jsp" %>
