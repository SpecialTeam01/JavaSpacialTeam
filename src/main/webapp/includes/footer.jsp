<%--
  Created by IntelliJ IDEA.
  User: S1-PC63
  Date: 16/05/2025
  Time: 12:56
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
</div>
</main>

<footer class="footer py-3 text-center">
  <div class="container">
    <span>
      © 2025 <strong>Stellar Odyssey</strong> — The Dark Side |
      <a href="https://github.com/SpecialTeam01/JavaSpacialTeam" class="footer-link" target="_blank">GitHub</a> ·
      <a href="#" class="footer-link">Terms</a>
    </span>
  </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
  :root {
    --blue-oscuro: #0d1b2a;
    --blue-claro:  #a9d6e5;
  }

  footer.footer {
    position: fixed !important;
    bottom: 0 !important;
    width: 100% !important;
    background-color: var(--blue-oscuro) !important;
    color: var(--blue-claro) !important;
    z-index: 1030 !important;
    font-size: 0.9rem;
    border-top: 1px solid #1f2a36;
  }

  .footer-link {
    color: var(--blue-claro);
    text-decoration: none;
    margin: 0 5px;
  }

  .footer-link:hover {
    text-decoration: underline;
  }

  body {
    padding-bottom: 60px !important;
  }
</style>
</body>
</html>
