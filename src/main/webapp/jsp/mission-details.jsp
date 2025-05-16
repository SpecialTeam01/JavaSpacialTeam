<%@ page contentType="text/html; charset=UTF-8" language="java"
         import="model.Mission" %>
<%@ include file="/includes/header.jsp" %>

<%
    Mission m = (Mission) request.getAttribute("mission");
%>

<div class="container my-4">
    <h2>Mission Details</h2>

    <dl class="row mt-3">
        <dt class="col-sm-3">ID</dt>
        <dd class="col-sm-9"><%= m.getMissionId() %></dd>

        <dt class="col-sm-3">Name</dt>
        <dd class="col-sm-9"><%= m.getMissionName() %></dd>

        <dt class="col-sm-3">Start Date</dt>
        <dd class="col-sm-9"><%= m.getStartDate() %></dd>

        <dt class="col-sm-3">End Date</dt>
        <dd class="col-sm-9"><%= m.getEndDate() != null ? m.getEndDate() : "—" %></dd>

        <dt class="col-sm-3">Status</dt>
        <dd class="col-sm-9"><%= m.getStatus() %></dd>

        <dt class="col-sm-3">Description</dt>
        <dd class="col-sm-9"><%= m.getDescription() %></dd>

        <dt class="col-sm-3">Planet</dt>
        <dd class="col-sm-9"><%= m.getPlanet().getName() %></dd>

        <dt class="col-sm-3">Astronaut</dt>
        <dd class="col-sm-9"><%= m.getAstronaut().getName() %></dd>
    </dl>

    <a href="<%= request.getContextPath() %>/missions"
       class="btn btn-secondary">← Back to list</a>
</div>

<%@ include file="/includes/footer.jsp" %>
