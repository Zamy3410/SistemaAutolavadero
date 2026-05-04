package org.example.Dao;

import org.example.Model.Vehiculos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculosDAOimpl implements VehiculosDAO {

    private final Connection connection;

    public VehiculosDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Vehiculos v) {
        String sql = "INSERT INTO vehiculos (clienteID, marca, modelo, placa, color, tipo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, v.getClienteID());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getModelo());
            ps.setString(4, v.getPlaca());
            ps.setString(5, v.getColor());
            ps.setString(6, v.getTipo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vehiculos leer(String placa) {
        String sql = "SELECT * FROM vehiculos WHERE placa = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, placa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Vehiculos(
                        rs.getInt("clienteID"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("placa"),
                        rs.getString("color"),
                        rs.getString("tipo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vehiculos> listar() {
        List<Vehiculos> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Vehiculos(
                        rs.getInt("clienteID"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("placa"),
                        rs.getString("color"),
                        rs.getString("tipo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void actualizar(Vehiculos v) {
        String sql = "UPDATE vehiculos SET clienteID = ?, marca = ?, modelo = ?, color = ?, tipo = ? WHERE placa = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, v.getClienteID());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getModelo());
            ps.setString(4, v.getColor());
            ps.setString(5, v.getTipo());
            ps.setString(6, v.getPlaca());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String placa) {
        String sql = "DELETE FROM vehiculos WHERE placa = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, placa);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}