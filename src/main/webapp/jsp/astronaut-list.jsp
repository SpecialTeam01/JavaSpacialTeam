<%@ page contentType="text/html; charset=UTF-8" language="java"
         import="java.util.List, model.Astronaut" %>
<%@ include file="/includes/header.jsp" %>

<div class="container my-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Astronauts</h2>
        <a href="${pageContext.request.contextPath}/astronaut?action=new"
           class="btn btn-success">
            + New Astronaut
        </a>
    </div>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead class="table-light">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Birth Date</th>
                <th>Nationality</th>
                <th># Missions</th>
                <th>Active</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                @SuppressWarnings("unchecked")
                List<Astronaut> list = (List<Astronaut>) request.getAttribute("astronautList");
                if (list != null) {
                    for (Astronaut a : list) {
            %>
            <tr>
                <td><%= a.getAstronautId() %></td>
                <td><%= a.getName() %></td>
                <td><%= a.getBirthDate() %></td>
                <td><%= a.getNationality() %></td>
                <td><%= a.getMissionsCompleted() %></td>
                <td><%= a.isActive() ? "✔" : "✘" %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/astronaut?action=detail&id=<%= a.getAstronautId() %>"
                       class="btn btn-sm btn-info">View</a>
                    <a href="<%= request.getContextPath() %>/astronaut?action=edit&id=<%= a.getAstronautId() %>"
                       class="btn btn-sm btn-primary">Edit</a>
                    <a href="<%= request.getContextPath() %>/astronaut?action=delete&id=<%= a.getAstronautId() %>"
                       class="btn btn-sm btn-danger"
                       onclick="return confirm('Delete this astronaut?');">Delete</a>
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
