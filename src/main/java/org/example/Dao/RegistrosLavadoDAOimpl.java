package org.example.Dao;

import org.example.Model.RegistrosLavado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrosLavadoDAOimpl implements RegistrosLavadoDAO {

    private final Connection connection;

    public RegistrosLavadoDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(RegistrosLavado r) {
        String sql = "INSERT INTO registroslavado (vehiculoID, servicioID, fechalavado, horainicio, horafin, preciototal) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, r.getVehiculoID());
            ps.setInt(2, r.getServicioID());
            ps.setString(3, r.getFechalavado());
            ps.setString(4, r.getHorainicio());
            ps.setString(5, r.getHorafin());
            ps.setDouble(6, r.getPreciototal());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RegistrosLavado leer(String fechalavado) {
        String sql = "SELECT * FROM registroslavado WHERE fechalavado = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, fechalavado);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new RegistrosLavado(
                        rs.getInt("vehiculoID"),
                        rs.getInt("servicioID"),
                        rs.getString("fechalavado"),
                        rs.getString("horainicio"),
                        rs.getString("horafin"),
                        rs.getDouble("preciototal")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RegistrosLavado> listar() {
        List<RegistrosLavado> lista = new ArrayList<>();
        String sql = "SELECT * FROM registroslavado";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new RegistrosLavado(
                        rs.getInt("vehiculoID"),
                        rs.getInt("servicioID"),
                        rs.getString("fechalavado"),
                        rs.getString("horainicio"),
                        rs.getString("horafin"),
                        rs.getDouble("preciototal")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void actualizar(RegistrosLavado r) {
        String sql = "UPDATE registroslavado SET vehiculoID = ?, servicioID = ?, horainicio = ?, horafin = ?, preciototal = ? WHERE fechalavado = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, r.getVehiculoID());
            ps.setInt(2, r.getServicioID());
            ps.setString(3, r.getHorainicio());
            ps.setString(4, r.getHorafin());
            ps.setDouble(5, r.getPreciototal());
            ps.setString(6, r.getFechalavado());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(String fechalavado) {
        String sql = "DELETE FROM registroslavado WHERE fechalavado = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, fechalavado);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}