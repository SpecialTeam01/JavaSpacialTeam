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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/astronaut")
public class AstronautServlet extends HttpServlet {
    private AstronautDAO astronautDAO;

    // mapeo de astronautId para asignarle una imagen png
    private static final Map<Integer, String> IMAGE_MAP = new HashMap<>();
    static {
        IMAGE_MAP.put(1, "nerea.png");
        IMAGE_MAP.put(2, "miguel.png");
        IMAGE_MAP.put(3, "kenny.png");
        IMAGE_MAP.put(4, "vanessa.png");
        IMAGE_MAP.put(5, "angel.png");
        IMAGE_MAP.put(6, "santiago.png");

    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Connection conn = DBConnection.getConnection();
            astronautDAO = new AstronautDAO(conn);
        } catch (SQLException e) {
            throw new ServletException("Error al inicializar AstronautDAO", e);
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
                    deleteAstronaut(req, resp);
                    break;
                case "detail":
                    showDetail(req, resp);
                    break;
                default:
                    listAstronauts(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException("Error en operación con la base de datos", ex);
        }
    }

    private void listAstronauts(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        List<Astronaut> list = astronautDAO.getAllAstronauts();
        req.setAttribute("astronautList", list);
        req.getRequestDispatcher("/jsp/astronaut-list.jsp").forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/astronaut-form.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Astronaut existing = astronautDAO.getAstronautById(id);
        req.setAttribute("astronaut", existing);
        req.getRequestDispatcher("/jsp/astronaut-form.jsp").forward(req, resp);
    }

    private void showDetail(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Astronaut astronaut = astronautDAO.getAstronautById(id);

        // ===> AÑADIDO: determinamos el fichero de imagen correspondiente
        String imageFile = IMAGE_MAP.getOrDefault(id, "defautuser.jpg");
        req.setAttribute("imageFile", imageFile);

        req.setAttribute("astronaut", astronaut);
        req.getRequestDispatcher("/jsp/astronaut-detail.jsp").forward(req, resp);
    }

    private void deleteAstronaut(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        astronautDAO.deleteAstronaut(id);
        resp.sendRedirect("astronaut");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
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
            throw new ServletException("Error al guardar astronauta", e);
        }

        resp.sendRedirect("astronaut");
    }
}
