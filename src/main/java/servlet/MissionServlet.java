package servlet;

import dao.AstronautDAO;
import dao.MissionDAO;
import dao.PlanetDAO;
import model.Astronaut;
import model.Mission;
import model.Planet;
import utils.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/missions")
public class MissionServlet extends HttpServlet {
    private MissionDAO missionDAO;
    private AstronautDAO astronautDAO;
    private PlanetDAO planetDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Connection conn = DBConnection.getConnection();
            missionDAO   = new MissionDAO(conn);
            astronautDAO = new AstronautDAO(conn);
            planetDAO    = new PlanetDAO(conn);
        } catch (SQLException e) {
            throw new ServletException("Error al inicializar DAOs", e);
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
                    deleteMission(req, resp);
                    break;
                case "detail":
                    showDetail(req, resp);
                    break;
                default:
                    listMissions(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException("Error en operación con la base de datos", ex);
        }
    }

    private void listMissions(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        List<Mission> list = missionDAO.getAllMissions();
        req.setAttribute("missionList", list);
        req.getRequestDispatcher("/mission-list.jsp").forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        req.setAttribute("astronautList", astronautDAO.getAllAstronauts());
        req.setAttribute("planetList",    planetDAO.getAllPlanets());
        req.getRequestDispatcher("/mission-form.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Mission existing = missionDAO.getMissionById(id);
        req.setAttribute("mission",       existing);
        req.setAttribute("astronautList", astronautDAO.getAllAstronauts());
        req.setAttribute("planetList",    planetDAO.getAllPlanets());
        req.getRequestDispatcher("/mission-form.jsp").forward(req, resp);
    }

    private void showDetail(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Mission mission = missionDAO.getMissionById(id);
        req.setAttribute("mission", mission);
        req.getRequestDispatcher("/mission-detail.jsp").forward(req, resp);
    }

    private void deleteMission(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        missionDAO.deleteMission(id);
        resp.sendRedirect("missions");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = req.getParameter("missionId") != null && !req.getParameter("missionId").isEmpty()
                ? Integer.parseInt(req.getParameter("missionId")) : 0;
        int astronautId = Integer.parseInt(req.getParameter("astronautId"));
        int planetId    = Integer.parseInt(req.getParameter("planetId"));
        String missionName = req.getParameter("missionName");
        java.util.Date startDate = java.sql.Date.valueOf(req.getParameter("startDate"));
        String endDateStr = req.getParameter("endDate");
        java.util.Date endDate = (endDateStr != null && !endDateStr.isEmpty())
                ? java.sql.Date.valueOf(endDateStr) : null;
        String status      = req.getParameter("status");
        String description = req.getParameter("description");

        Astronaut astro = new Astronaut();
        astro.setAstronautId(astronautId);
        Planet planet = new Planet();
        planet.setPlanetId(planetId);

        Mission mission = new Mission(id, astro, planet,
                missionName, startDate, endDate,
                status, description);

        try {
            if (id == 0) {
                missionDAO.insertMission(mission);
            } else {
                missionDAO.updateMission(mission);
            }
        } catch (SQLException e) {
            throw new ServletException("Error al guardar misión", e);
        }
        resp.sendRedirect("missions");
    }
}
