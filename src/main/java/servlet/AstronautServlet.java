package servlet;

import dao.AstronautDAO;
import model.Astronaut;
import utils.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/astronauts")
public class AstronautServlet extends HttpServlet {
    private AstronautDAO astronautDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            // Conexion a la base de datos
            Connection conn = DBConnection.getConnection();
            astronautDAO = new AstronautDAO(conn);
        } catch (SQLException e) {
            throw new ServletException("Error al inicializar AstronautDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "new":
                    showNewForm(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteAstronaut(req, resp);
                    break;
                default:
                    listAstronauts(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAstronauts(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        List<Astronaut> list = astronautDAO.getAllAstronauts();
        req.setAttribute("astronautList", list);
        req.getRequestDispatcher("/astronaut-list.jsp").forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/astronaut-form.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Astronaut existing = astronautDAO.getAstronautById(id);
        req.setAttribute("astronaut", existing);
        req.getRequestDispatcher("/astronaut-form.jsp").forward(req, resp);
    }

    private void deleteAstronaut(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        astronautDAO.deleteAstronaut(id);
        resp.sendRedirect("astronauts");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = req.getParameter("astronautId") != null && !req.getParameter("astronautId").isEmpty()
                ? Integer.parseInt(req.getParameter("astronautId")) : 0;
        String name = req.getParameter("name");
        java.util.Date birth = java.sql.Date.valueOf(req.getParameter("birthDate"));
        String nationality = req.getParameter("nationality");
        int missionsCompleted = Integer.parseInt(req.getParameter("missionsCompleted"));
        boolean active = "on".equals(req.getParameter("active"));

        Astronaut astro = new Astronaut(id, name, birth, nationality, missionsCompleted, active);

        try {
            if (id == 0) {
                astronautDAO.insertAstronaut(astro);
            } else {
                astronautDAO.updateAstronaut(astro);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        resp.sendRedirect("astronauts");
    }
}
