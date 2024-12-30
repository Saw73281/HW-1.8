package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private final Database database = Database.getInstance();

    public long create(String name) {
        validateName(name);
        String sql = "INSERT INTO client (NAME) VALUES (?)";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, name);
            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getLong(1);
            } else {
                throw new RuntimeException("Failed to retrieve generated ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create client", e);
        }
    }

    public String getById(long id) {
        String sql = "SELECT NAME FROM client WHERE ID = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("NAME");
            } else {
                throw new IllegalArgumentException("Client with ID " + id + " not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get client by ID", e);
        }
    }

    public void setName(long id, String name) {
        validateName(name);
        String sql = "UPDATE client SET NAME = ? WHERE ID = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setLong(2, id);

            if (pstmt.executeUpdate() == 0) {
                throw new IllegalArgumentException("Client with ID " + id + " not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update client name", e);
        }
    }

    public void deleteById(long id) {
        String sql = "DELETE FROM client WHERE ID = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            if (pstmt.executeUpdate() == 0) {
                throw new IllegalArgumentException("Client with ID " + id + " not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete client", e);
        }
    }

    public List<Client> listAll() {
        String sql = "SELECT ID, NAME FROM client";
        List<Client> clients = new ArrayList<>();

        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                clients.add(new Client(rs.getLong("ID"), rs.getString("NAME")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list all clients", e);
        }

        return clients;
    }

    private void validateName(String name) {
        if (name == null || name.length() < 2 || name.length() > 1000) {
            throw new IllegalArgumentException("Name must be between 2 and 1000 characters");
        }
    }
}
