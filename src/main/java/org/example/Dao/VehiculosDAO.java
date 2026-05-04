package org.example.Dao;

import org.example.Model.Vehiculos;
import java.util.List;


public interface VehiculosDAO {
    void crear(Vehiculos v);
    Vehiculos leer(String placa);
    List<Vehiculos> listar();
    void actualizar(Vehiculos v);
    void eliminar(String placa);
}
