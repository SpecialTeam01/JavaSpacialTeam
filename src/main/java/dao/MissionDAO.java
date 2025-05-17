package dao;

import model.Mission;
import model.Astronaut;
import model.Planet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MissionDAO {
    private final Connection conn;

    public MissionDAO(Connection conn) {
        this.conn = conn;
    }

    // Obtener todas las misiones
    public List<Mission> getAllMissions() throws SQLException {
        List<Mission> missions = new ArrayList<>();
        String sql = "SELECT * FROM Missions";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                missions.add(mapRowToMission(rs));
            }
        }

        return missions;
    }

    // Obtener misión por ID
    public Mission getMissionById(int id) throws SQLException {
        String sql = "SELECT * FROM Missions WHERE mission_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToMission(rs);
                }
            }
        }
        return null;
    }

    // Insertar nueva misión
    public void insertMission(Mission mission) throws SQLException {
        String sql = "INSERT INTO Missions (astronaut_id, planet_id, mission_name, start_date, end_date, status, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mission.getAstronaut().getAstronautId());
            stmt.setInt(2, mission.getPlanet().getPlanetId());
            stmt.setString(3, mission.getMissionName());
            stmt.setDate(4, new java.sql.Date(mission.getStartDate().getTime()));
            if (mission.getEndDate() != null) {
                stmt.setDate(5, new java.sql.Date(mission.getEndDate().getTime()));
            } else {
                stmt.setNull(5, Types.DATE);
            }
            stmt.setString(6, mission.getStatus());
            stmt.setString(7, mission.getDescription());
            stmt.executeUpdate();
        }
    }

    // Actualizar misión existente
    public void updateMission(Mission mission) throws SQLException {
        String sql = "UPDATE Missions SET astronaut_id = ?, planet_id = ?, mission_name = ?, start_date = ?, end_date = ?, status = ?, description = ? WHERE mission_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mission.getAstronaut().getAstronautId());
            stmt.setInt(2, mission.getPlanet().getPlanetId());
            stmt.setString(3, mission.getMissionName());
            stmt.setDate(4, new java.sql.Date(mission.getStartDate().getTime()));
            if (mission.getEndDate() != null) {
                stmt.setDate(5, new java.sql.Date(mission.getEndDate().getTime()));
            } else {
                stmt.setNull(5, Types.DATE);
            }
            stmt.setString(6, mission.getStatus());
            stmt.setString(7, mission.getDescription());
            stmt.setInt(8, mission.getMissionId());
            stmt.executeUpdate();
        }
    }

    // Eliminar misión por ID
    public void deleteMission(int id) throws SQLException {
        String sql = "DELETE FROM Missions WHERE mission_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // ===> AÑADIDO BUSCADOR
    public List<Mission> searchByName(String keyword) throws SQLException {
        List<Mission> missions = new ArrayList<>();
        String sql = "SELECT * FROM Missions WHERE mission_name LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    missions.add(mapRowToMission(rs));
                }
            }
        }
        return missions;
    }

    // Método utilitario para mapear ResultSet a objeto Mission
    private Mission mapRowToMission(ResultSet rs) throws SQLException {
        Mission m = new Mission();
        m.setMissionId(rs.getInt("mission_id"));

        // relaciones
        int astroId  = rs.getInt("astronaut_id");
        int planetId = rs.getInt("planet_id");
        AstronautDAO aDao = new AstronautDAO(conn);
        PlanetDAO    pDao = new PlanetDAO(conn);
        Astronaut    a    = aDao.getAstronautById(astroId);
        Planet       p    = pDao.getPlanetById(planetId);

        m.setAstronaut(a);
        m.setPlanet(p);
        m.setMissionName(rs.getString("mission_name"));
        m.setStartDate(rs.getDate("start_date"));
        Date end = rs.getDate("end_date");
        if (end != null) m.setEndDate(end);
        m.setStatus(rs.getString("status"));
        m.setDescription(rs.getString("description"));
        return m;
    }
}
