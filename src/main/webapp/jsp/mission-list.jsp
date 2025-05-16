<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.List, model.Mission" %>
<%@ include file="/includes/header.jsp" %>

<div class="container my-4">
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h2>
      <i class="fa-solid fa-hammer"></i> Missions
    </h2>
    <a href="${pageContext.request.contextPath}/mission?action=new" class="btn btn-success">
      <i class="bi bi-plus-circle"></i> New Mission
    </a>
  </div>

  <div class="table-responsive">
    <table class="table table-dark table-hover table-bordered align-middle mb-0 rounded">
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
        <td class="<%= m.getStatus().equalsIgnoreCase("Completed") ? "status-completed"
               : m.getStatus().equalsIgnoreCase("In Progress") ? "status-inprogress"
               : "text-white" %>">
          <%= m.getStatus() %>
        </td>
        <td class="text-center align-middle">
          <a href="${pageContext.request.contextPath}/mission?action=detail&id=<%= m.getMissionId() %>" class="btn btn-sm btn-primary rounded-pill shadow-sm"><i class="bi bi-eye"></i> View</a>
          <a href="${pageContext.request.contextPath}/mission?action=edit&id=<%= m.getMissionId() %>" class="btn btn-sm btn-primary rounded-pill shadow-sm"><i class="bi bi-pencil"></i> Edit</a>
          <a href="${pageContext.request.contextPath}/mission?action=delete&id=<%= m.getMissionId() %>" class="btn btn-sm btn-danger rounded-pill shadow-sm" onclick="return confirm('Delete this mission?');"><i class="bi bi-trash"></i> Delete</a>
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
