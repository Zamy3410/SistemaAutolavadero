package org.example.Dao;

import org.example.Model.Servicios;
import java.util.List;

public interface ServiciosDAO {
    void crear(Servicios s);
    Servicios leer(String nombre);
    List<Servicios> listar();
    void actualizar(Servicios s);
    void eliminar(String nombre);
}
