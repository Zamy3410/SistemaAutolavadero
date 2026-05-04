package org.example.Dao;

import org.example.Model.Clientes;
import java.util.List;

public interface ClientesDAO {
    void crear(Clientes c);
    Clientes leer(String nombre);
    List<Clientes> listar();
    void actualizar(Clientes c);
    void eliminar(String nombre);
}
