package dao;

import model.Astronaut;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AstronautDAO {
    private final Connection conn;

    public AstronautDAO(Connection conn) {
        this.conn = conn;
    }

    // Obtener todos los astronautas
    public List<Astronaut> getAllAstronauts() throws SQLException {
        List<Astronaut> astronauts = new ArrayList<>();
        String sql = "SELECT * FROM Astronauts";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                astronauts.add(mapRowToAstronaut(rs));
            }
        }

        return astronauts;
    }

    // Obtener astronauta por ID
    public Astronaut getAstronautById(int id) throws SQLException {
        String sql = "SELECT * FROM Astronauts WHERE astronaut_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToAstronaut(rs);
                }
            }
        }
        return null;
    }

    // Insertar nuevo astronauta
    public void insertAstronaut(Astronaut astronaut) throws SQLException {
        String sql = "INSERT INTO Astronauts (name, birth_date, nationality, missions_completed, active) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, astronaut.getName());
            stmt.setDate(2, new java.sql.Date(astronaut.getBirthDate().getTime()));
            stmt.setString(3, astronaut.getNationality());
            stmt.setInt(4, astronaut.getMissionsCompleted());
            stmt.setBoolean(5, astronaut.isActive());
            stmt.executeUpdate();
        }
    }

    // Actualizar astronauta existente
    public void updateAstronaut(Astronaut astronaut) throws SQLException {
        String sql = "UPDATE Astronauts SET name = ?, birth_date = ?, nationality = ?, missions_completed = ?, active = ? WHERE astronaut_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, astronaut.getName());
            stmt.setDate(2, new java.sql.Date(astronaut.getBirthDate().getTime()));
            stmt.setString(3, astronaut.getNationality());
            stmt.setInt(4, astronaut.getMissionsCompleted());
            stmt.setBoolean(5, astronaut.isActive());
            stmt.setInt(6, astronaut.getAstronautId());
            stmt.executeUpdate();
        }
    }

    // Eliminar astronauta por ID
    public void deleteAstronaut(int id) throws SQLException {
        String sql = "DELETE FROM Astronauts WHERE astronaut_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método utilitario para mapear ResultSet a objeto Astronaut
    private Astronaut mapRowToAstronaut(ResultSet rs) throws SQLException {
        Astronaut a = new Astronaut();
        a.setAstronautId(rs.getInt("astronaut_id"));
        a.setName(rs.getString("name"));
        a.setBirthDate(rs.getDate("birth_date"));
        a.setNationality(rs.getString("nationality"));
        a.setMissionsCompleted(rs.getInt("missions_completed"));
        a.setActive(rs.getBoolean("active"));
        return a;
    }
}

