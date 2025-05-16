<%@ page contentType="text/html; charset=UTF-8" language="java"
         import="java.util.List, model.Planet" %>
<%@ include file="/includes/header.jsp" %>

<div class="container my-4">
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h2>Planets</h2>
    <a href="<%= request.getContextPath() %>/planets?action=new"
       class="btn btn-success">
      + New Planet
    </a>
  </div>

  <div class="table-responsive">
    <table class="table table-striped">
      <thead class="table-light">
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Diameter (km)</th>
        <th>Discovery Date</th>
        <th>Has Atmosphere</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <%
        @SuppressWarnings("unchecked")
        List<Planet> list = (List<Planet>) request.getAttribute("planetList");
        if (list != null) {
          for (Planet p : list) {
      %>
      <tr>
        <td><%= p.getPlanetId() %></td>
        <td><%= p.getName() %></td>
        <td><%= p.getDiameter() %></td>
        <td><%= p.getDiscoveryDate() %></td>
        <td><%= p.isHasAtmosphere() ? "✔" : "✘" %></td>
        <td>
          <a href="<%= request.getContextPath() %>/planets?action=detail&id=<%= p.getPlanetId() %>"
             class="btn btn-sm btn-info">View</a>
          <a href="<%= request.getContextPath() %>/planets?action=edit&id=<%= p.getPlanetId() %>"
             class="btn btn-sm btn-primary">Edit</a>
          <a href="<%= request.getContextPath() %>/planets?action=delete&id=<%= p.getPlanetId() %>"
             class="btn btn-sm btn-danger"
             onclick="return confirm('Delete this planet?');">Delete</a>
        </td>
      </tr>
      <%
          }
        }
      %>
      </tbody>
    </table>
  </div>
</div>

<%@ include file="/includes/footer.jsp" %>
