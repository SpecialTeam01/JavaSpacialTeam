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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private AstronautDAO astronautDAO;
    private PlanetDAO planetDAO;
    private MissionDAO missionDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Connection conn = DBConnection.getConnection();
            astronautDAO = new AstronautDAO(conn);
            planetDAO    = new PlanetDAO(conn);
            missionDAO   = new MissionDAO(conn);
        } catch (SQLException e) {
            throw new ServletException("Error inicializando SearchServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String type    = req.getParameter("type");
        String keyword = req.getParameter("keyword");
        if (type == null || keyword == null || keyword.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        try {
            switch (type) {
                case "astronaut":
                    List<Astronaut> astronauts = astronautDAO.searchByName(keyword);
                    req.setAttribute("astronautList", astronauts);
                    req.getRequestDispatcher("/jsp/astronaut-list.jsp")
                            .forward(req, resp);
                    break;
                case "planet":
                    List<Planet> planets = planetDAO.searchByName(keyword);
                    req.setAttribute("planetList", planets);
                    req.getRequestDispatcher("/jsp/planet-list.jsp")
                            .forward(req, resp);
                    break;
                case "mission":
                    List<Mission> missions = missionDAO.searchByName(keyword);
                    req.setAttribute("missionList", missions);
                    req.getRequestDispatcher("/jsp/mission-list.jsp")
                            .forward(req, resp);
                    break;
                default:
                    resp.sendRedirect(req.getContextPath() + "/index.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException(
                    "Error al buscar \"" + keyword + "\" en " + type, e
            );
        }
    }
}
