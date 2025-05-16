package dao;

import model.Planet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanetDAO {
    private final Connection conn;

    public PlanetDAO(Connection conn) {
        this.conn = conn;
    }

    // Obtener todos los planetas
    public List<Planet> getAllPlanets() throws SQLException {
        List<Planet> planets = new ArrayList<>();
        String sql = "SELECT * FROM Planets";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                planets.add(mapRowToPlanet(rs));
            }
        }

        return planets;
    }

    // Obtener planeta por ID
    public Planet getPlanetById(int id) throws SQLException {
        String sql = "SELECT * FROM Planets WHERE planet_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToPlanet(rs);
                }
            }
        }
        return null;
    }

    // Insertar nuevo planeta
    public void insertPlanet(Planet planet) throws SQLException {
        String sql = "INSERT INTO Planets (name, diameter, discovery_date, has_atmosphere, description) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, planet.getName());
            if (planet.getDiameter() != null) {
                stmt.setInt(2, planet.getDiameter());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setDate(3, Date.valueOf(planet.getDiscoveryDate()));
            stmt.setBoolean(4, planet.isHasAtmosphere());
            stmt.setString(5, planet.getDescription());
            stmt.executeUpdate();
        }
    }

    // Actualizar planeta existente
    public void updatePlanet(Planet planet) throws SQLException {
        String sql = "UPDATE Planets SET name = ?, diameter = ?, discovery_date = ?, has_atmosphere = ?, description = ? WHERE planet_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, planet.getName());
            if (planet.getDiameter() != null) {
                stmt.setInt(2, planet.getDiameter());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setDate(3, Date.valueOf(planet.getDiscoveryDate()));
            stmt.setBoolean(4, planet.isHasAtmosphere());
            stmt.setString(5, planet.getDescription());
            stmt.setInt(6, planet.getPlanetId());
            stmt.executeUpdate();
        }
    }

    // Eliminar planeta por ID
    public void deletePlanet(int id) throws SQLException {
        String sql = "DELETE FROM Planets WHERE planet_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método utilitario para mapear ResultSet a objeto Planet
    private Planet mapRowToPlanet(ResultSet rs) throws SQLException {
        Planet p = new Planet();
        p.setPlanetId(rs.getInt("planet_id"));
        p.setName(rs.getString("name"));
        int diameter = rs.getInt("diameter");
        if (rs.wasNull()) {
            p.setDiameter(null);
        } else {
            p.setDiameter(diameter);
        }
        p.setDiscoveryDate(rs.getDate("discovery_date").toLocalDate());
        p.setHasAtmosphere(rs.getBoolean("has_atmosphere"));
        p.setDescription(rs.getString("description"));
        return p;
    }
}
