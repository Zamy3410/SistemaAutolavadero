package org.example.Dao;

import org.example.Model.Servicios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiciosDAOimpl implements ServiciosDAO {

    private final Connection connection;

    public ServiciosDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Servicios s) {
        String sql = "INSERT INTO Servicios (nombre, precio) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, s.getNombre());
            ps.setDouble(2, s.getPrecio());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Servicios leer(String nombre) {
        String sql = "SELECT * FROM Servicios WHERE nombre = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Servicios(
                        rs.getString("nombre"),
                        rs.getDouble("precio")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Servicios> listar() {
        List<Servicios> lista = new ArrayList<>();
        String sql = "SELECT * FROM Servicios";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Servicios(
                        rs.getString("nombre"),
                        rs.getDouble("precio")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void actualizar(Servicios s) {
        String sql = "UPDATE Servicios SET precio = ? WHERE nombre = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, s.getPrecio());
            ps.setString(2, s.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String nombre) {
        String sql = "DELETE FROM Servicios WHERE nombre = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}