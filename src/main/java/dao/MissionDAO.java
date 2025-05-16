package dao;

import model.Astronaut;
import model.Mission;
import model.Planet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MissionDAO {
    private final Connection conn;

    public MissionDAO(Connection conn) {
        this.conn = conn;
    }

    // Listar todas las misiones con su astronauta y planeta
    public List<Mission> getAllMissions() throws SQLException {
        List<Mission> missions = new ArrayList<>();
        String sql =
                "SELECT m.mission_id, m.mission_name, m.start_date, m.end_date, m.status, m.description, " +
                        " a.astronaut_id AS a_id, a.name AS a_name, a.birth_date AS a_birth_date, " +
                        " a.nationality AS a_nationality, a.missions_completed AS a_missions_completed, a.active AS a_active, " +
                        " p.planet_id AS p_id, p.name AS p_name, p.diameter AS p_diameter, " +
                        " p.discovery_date AS p_discovery_date, p.has_atmosphere AS p_has_atmosphere, p.description AS p_description " +
                        "FROM Missions m " +
                        "JOIN Astronauts a ON m.astronaut_id = a.astronaut_id " +
                        "JOIN Planets   p ON m.planet_id   = p.planet_id";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                missions.add(mapRowToMission(rs));
            }
        }
        return missions;
    }

    // Obtener una misión por ID
    public Mission getMissionById(int id) throws SQLException {
        String sql =
                "SELECT m.mission_id, m.mission_name, m.start_date, m.end_date, m.status, m.description, " +
                        " a.astronaut_id AS a_id, a.name AS a_name, a.birth_date AS a_birth_date, " +
                        " a.nationality AS a_nationality, a.missions_completed AS a_missions_completed, a.active AS a_active, " +
                        " p.planet_id AS p_id, p.name AS p_name, p.diameter AS p_diameter, " +
                        " p.discovery_date AS p_discovery_date, p.has_atmosphere AS p_has_atmosphere, p.description AS p_description " +
                        "FROM Missions m " +
                        "JOIN Astronauts a ON m.astronaut_id = a.astronaut_id " +
                        "JOIN Planets   p ON m.planet_id   = p.planet_id " +
                        "WHERE m.mission_id = ?";
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
        String sql = "INSERT INTO Missions " +
                "(astronaut_id, planet_id, mission_name, start_date, end_date, status, description) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
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

    // Actualizar una misión existente
    public void updateMission(Mission mission) throws SQLException {
        String sql = "UPDATE Missions SET " +
                "astronaut_id = ?, planet_id = ?, mission_name = ?, " +
                "start_date = ?, end_date = ?, status = ?, description = ? " +
                "WHERE mission_id = ?";
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

    // ---- Métodos auxiliares para mapear fila a objeto Mission ----
    private Mission mapRowToMission(ResultSet rs) throws SQLException {
        Mission m = new Mission();
        m.setMissionId(rs.getInt("mission_id"));
        m.setMissionName(rs.getString("mission_name"));
        m.setStartDate(rs.getDate("start_date"));
        m.setEndDate(rs.getDate("end_date"));
        m.setStatus(rs.getString("status"));
        m.setDescription(rs.getString("description"));
        m.setAstronaut(mapRowToAstronaut(rs));
        m.setPlanet(mapRowToPlanet(rs));
        return m;
    }

    private Astronaut mapRowToAstronaut(ResultSet rs) throws SQLException {
        Astronaut a = new Astronaut();
        a.setAstronautId(rs.getInt("a_id"));
        a.setName(rs.getString("a_name"));
        a.setBirthDate(rs.getDate("a_birth_date"));
        a.setNationality(rs.getString("a_nationality"));
        a.setMissionsCompleted(rs.getInt("a_missions_completed"));
        a.setActive(rs.getBoolean("a_active"));
        return a;
    }

    private Planet mapRowToPlanet(ResultSet rs) throws SQLException {
        Planet p = new Planet();
        p.setPlanetId(rs.getInt("p_id"));
        p.setName(rs.getString("p_name"));
        p.setDiameter(rs.getInt("p_diameter"));
        p.setDiscoveryDate(rs.getDate("p_discovery_date").toLocalDate());
        p.setHasAtmosphere(rs.getBoolean("p_has_atmosphere"));
        p.setDescription(rs.getString("p_description"));
        return p;
    }
}
