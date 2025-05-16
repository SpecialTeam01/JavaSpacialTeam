package servlet;

import dao.AstronautDAO;
import dao.PlanetDAO;
import dao.MissionDAO;
import model.Astronaut;
import model.Planet;
import model.Mission;
import utils.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/index")
public class HomeServlet extends HttpServlet {
    private AstronautDAO astronautDAO;
    private PlanetDAO    planetDAO;
    private MissionDAO   missionDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Connection conn = DBConnection.getConnection();
            astronautDAO = new AstronautDAO(conn);
            planetDAO    = new PlanetDAO(conn);
            missionDAO   = new MissionDAO(conn);
        } catch (SQLException e) {
            throw new ServletException("Error al inicializar DAOs", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<Astronaut> astronauts = astronautDAO.getAllAstronauts();
            List<Planet>    planets    = planetDAO.getAllPlanets();
            List<Mission>   missions   = missionDAO.getAllMissions();

            req.setAttribute("astronautList", astronauts);
            req.setAttribute("planetList",    planets);
            req.setAttribute("missionList",   missions);

            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (SQLException ex) {
            throw new ServletException("Error cargando datos para el dashboard", ex);
        }
    }
}
