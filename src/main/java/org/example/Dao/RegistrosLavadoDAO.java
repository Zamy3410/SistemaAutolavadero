package org.example.Dao;

import org.example.Model.RegistrosLavado;
import java.util.List;


public interface RegistrosLavadoDAO {
    void crear(RegistrosLavado r);
    RegistrosLavado leer(String fechalavado);
    List<RegistrosLavado> listar();
    void actualizar(RegistrosLavado r);
    void eliminar(String fechalavado);
}
