package servlet;

import dao.PlanetDAO;
import model.Planet;
import utils.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/planets")
public class PlanetServlet extends HttpServlet {
    private PlanetDAO planetDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Connection conn = DBConnection.getConnection();
            planetDAO = new PlanetDAO(conn);
        } catch (SQLException e) {
            throw new ServletException("Error al inicializar PlanetDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
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
                    deletePlanet(req, resp);
                    break;
                case "detail":
                    showDetail(req, resp);
                    break;
                default:
                    listPlanets(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException("Error en operación con la base de datos", ex);
        }
    }

    private void listPlanets(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        List<Planet> list = planetDAO.getAllPlanets();
        req.setAttribute("planetList", list);
        req.getRequestDispatcher("/planet-list.jsp").forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/planet-form.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Planet existing = planetDAO.getPlanetById(id);
        req.setAttribute("planet", existing);
        req.getRequestDispatcher("/planet-form.jsp").forward(req, resp);
    }

    private void showDetail(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Planet planet = planetDAO.getPlanetById(id);
        req.setAttribute("planet", planet);
        req.getRequestDispatcher("/planet-detail.jsp").forward(req, resp);
    }

    private void deletePlanet(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        planetDAO.deletePlanet(id);
        resp.sendRedirect("planets");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = req.getParameter("planetId") != null && !req.getParameter("planetId").isEmpty()
                ? Integer.parseInt(req.getParameter("planetId")) : 0;
        String name = req.getParameter("name");

        Integer diameter = null;
        String diameterParam = req.getParameter("diameter");
        if (diameterParam != null && !diameterParam.isEmpty()) {
            diameter = Integer.parseInt(diameterParam);
        }

        LocalDate discoveryDate = LocalDate.parse(req.getParameter("discoveryDate"));
        boolean hasAtmosphere = "on".equals(req.getParameter("hasAtmosphere"));
        String description = req.getParameter("description");

        Planet planet = new Planet();
        planet.setPlanetId(id);
        planet.setName(name);
        planet.setDiameter(diameter);
        planet.setDiscoveryDate(discoveryDate);
        planet.setHasAtmosphere(hasAtmosphere);
        planet.setDescription(description);

        try {
            if (id == 0) {
                planetDAO.insertPlanet(planet);
            } else {
                planetDAO.updatePlanet(planet);
            }
        } catch (SQLException e) {
            throw new ServletException("Error al guardar planeta", e);
        }

        resp.sendRedirect("planets");
    }
}
