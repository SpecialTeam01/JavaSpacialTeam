<%@ page contentType="text/html; charset=UTF-8" language="java"
         import="java.util.List, model.Astronaut" %>
<%@ include file="/includes/header.jsp" %>

<div class="container my-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>
            <i class="fa-solid fa-user-astronaut"></i> Astronauts
        </h2>
        <a href="${pageContext.request.contextPath}/astronaut?action=new"
           class="btn btn-success">
            <i class="bi bi-plus-circle"></i>  New Astronaut
        </a>
    </div>

    <div class="table-responsive">

            <div class="table-responsive">
                <table class="table table-dark table-hover table-bordered align-middle mb-0 rounded">
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
                        <td>
                            <%= a.isActive() ? "<span class='text-success fw-bold'>&#10003;</span>"
                                    : "<span class='text-danger fw-bold'>&#10007;</span>" %>
                        </td>
                        <td class="text-center align-middle">
                            <a href="<%= request.getContextPath() %>/astronaut?action=detail&id=<%= a.getAstronautId() %>"
                               class="btn btn-sm btn-primary rounded-pill shadow-sm"><i class="bi bi-eye-fill"></i> View</a>
                            <a href="<%= request.getContextPath() %>/astronaut?action=edit&id=<%= a.getAstronautId() %>"
                               class="btn btn-sm btn-primary rounded-pill shadow-sm"><i class="bi bi-pencil-square"></i> Edit</a>
                            <a href="<%= request.getContextPath() %>/astronaut?action=delete&id=<%= a.getAstronautId() %>"
                               class="btn btn-sm btn-danger rounded-pill shadow-sm"
                               onclick="return confirm('Delete this astronaut?');"><i class="bi bi-trash3-fill"></i> Delete</a>
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
</div>

<%@ include file="/includes/footer.jsp" %>
