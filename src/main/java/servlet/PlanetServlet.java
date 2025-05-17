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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/planets")
public class PlanetServlet extends HttpServlet {
    private PlanetDAO planetDAO;

    //Mapeo de planetId Nombre de fichero de imagen
    private static final Map<Integer, String> IMAGE_MAP = new HashMap<>();
    static {
        IMAGE_MAP.put(1, "mars.png");
        IMAGE_MAP.put(2, "europa.png");
        IMAGE_MAP.put(3, "titan.png");
        IMAGE_MAP.put(4, "Kepler-186f.png");
        IMAGE_MAP.put(5, "Proxima-Centauri-b.png");
    }

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
        req.getRequestDispatcher("/jsp/planet-list.jsp").forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/planet-form.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Planet existing = planetDAO.getPlanetById(id);
        req.setAttribute("planet", existing);
        req.getRequestDispatcher("/jsp/planet-form.jsp").forward(req, resp);
    }

    private void showDetail(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Planet planet = planetDAO.getPlanetById(id);

        // ===> AÑADIDO: determinamos el fichero de imagen
        String imageFile = IMAGE_MAP.getOrDefault(id, "default.png");
        req.setAttribute("imageFile", imageFile);

        req.setAttribute("planet", planet);
        req.getRequestDispatcher("/jsp/planet-detail.jsp").forward(req, resp);
    }

    private void deletePlanet(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        planetDAO.deletePlanet(id);
        resp.sendRedirect(req.getContextPath() + "/planets");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = req.getParameter("planetId") != null && !req.getParameter("planetId").isEmpty()
                ? Integer.parseInt(req.getParameter("planetId")) : 0;
        String name = req.getParameter("name");
        Integer diameter = null;
        String diamParam = req.getParameter("diameter");
        if (diamParam != null && !diamParam.isEmpty()) {
            diameter = Integer.parseInt(diamParam);
        }
        java.sql.Date discoveryDate = java.sql.Date.valueOf(req.getParameter("discoveryDate"));
        boolean hasAtmosphere = "on".equals(req.getParameter("hasAtmosphere"));
        String description = req.getParameter("description");

        Planet p = new Planet();
        p.setPlanetId(id);
        p.setName(name);
        p.setDiameter(diameter);
        p.setDiscoveryDate(discoveryDate.toLocalDate());
        p.setHasAtmosphere(hasAtmosphere);
        p.setDescription(description);

        try {
            if (id == 0) {
                planetDAO.insertPlanet(p);
            } else {
                planetDAO.updatePlanet(p);
            }
        } catch (SQLException e) {
            throw new ServletException("Error al guardar planeta", e);
        }

        resp.sendRedirect(req.getContextPath() + "/planets");
    }
}
