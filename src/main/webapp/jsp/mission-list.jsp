<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.List, model.Mission" %>
<%@ include file="/includes/header.jsp" %>

<div class="container my-4">
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h2>Missions</h2>
    <a href="${pageContext.request.contextPath}/mission?action=new" class="btn btn-success">
      <i class="bi bi-plus-circle"></i> New Mission
    </a>
  </div>

  <div class="table-responsive">
    <table class="table table-striped">
      <thead class="table-light">
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Astronaut</th>
        <th>Planet</th>
        <th>Start Date</th>
        <th>Status</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <%
        List<Mission> list = (List<Mission>) request.getAttribute("missionList");
        if (list != null && !list.isEmpty()) {
          for (Mission m : list) {
      %>
      <tr>
        <td><%= m.getMissionId() %></td>
        <td><%= m.getMissionName() %></td>
        <td><%= m.getAstronaut().getName() %></td>
        <td><%= m.getPlanet().getName() %></td>
        <td><%= m.getStartDate() %></td>
        <td><%= m.getStatus() %></td>
        <td>
          <a href="${pageContext.request.contextPath}/mission?action=detail&id=<%= m.getMissionId() %>" class="btn btn-sm btn-info"><i class="bi bi-eye"></i></a>
          <a href="${pageContext.request.contextPath}/mission?action=edit&id=<%= m.getMissionId() %>" class="btn btn-sm btn-primary"><i class="bi bi-pencil"></i></a>
          <a href="${pageContext.request.contextPath}/mission?action=delete&id=<%= m.getMissionId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Delete this mission?');"><i class="bi bi-trash"></i></a>
        </td>
      </tr>
      <%   }
      } else { %>
      <tr><td colspan="7" class="text-center">No missions found.</td></tr>
      <% } %>
      </tbody>
    </table>
  </div>
</div>

<%@ include file="/includes/footer.jsp" %>
