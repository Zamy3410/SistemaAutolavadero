package org.example.Dao;

import org.example.Model.Clientes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAOimpl implements ClientesDAO {

    private final Connection connection;

    public ClientesDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Clientes c) {
        String sql = "INSERT INTO clientes (nombre, apellido, telefono, email, direccion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getDireccion());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Clientes leer(String nombre) {
        String sql = "SELECT * FROM clientes WHERE nombre = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Clientes(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Clientes> listar() {
        List<Clientes> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Clientes(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void actualizar(Clientes c) {
        String sql = "UPDATE clientes SET apellido = ?, telefono = ?, email = ?, direccion = ? WHERE nombre = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, c.getApellido());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getDireccion());
            ps.setString(5, c.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String nombre) {
        String sql = "DELETE FROM clientes WHERE nombre = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}