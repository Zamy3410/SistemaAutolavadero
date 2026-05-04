package org.example;

import org.example.Dao.*;
import org.example.Model.*;
import org.example.Util.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = ConexionBD.obtenerConexion()) {

            ClientesDAO clienteDAO = new ClientesDAOimpl(connection);
            VehiculosDAO vehiculoDAO = new VehiculosDAOimpl(connection);
            ServiciosDAO servicioDAO = new ServiciosDAOimpl(connection);
            RegistrosLavadoDAO registroDAO = new RegistrosLavadoDAOimpl(connection);

            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\n=== AUTOLAVADO ===");
                System.out.println("1. Ver Clientes");
                System.out.println("2. Ver Vehiculos");
                System.out.println("3. Ver Servicios");
                System.out.println("4. Ver Registros de Lavado");
                System.out.println("0. Salir");
                System.out.print("Elige una opcion: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("\n--- CLIENTES ---");
                        List<Clientes> clientes = clienteDAO.listar();
                        for (Clientes c : clientes) System.out.println(c);
                        break;

                    case 2:
                        System.out.println("\n--- VEHICULOS ---");
                        List<Vehiculos> vehiculos = vehiculoDAO.listar();
                        for (Vehiculos v : vehiculos) System.out.println(v);
                        break;

                    case 3:
                        System.out.println("\n--- SERVICIOS ---");
                        List<Servicios> servicios = servicioDAO.listar();
                        for (Servicios s : servicios) System.out.println(s);
                        break;

                    case 4:
                        System.out.println("\n--- REGISTROS DE LAVADO ---");
                        List<RegistrosLavado> registros = registroDAO.listar();
                        for (RegistrosLavado r : registros) System.out.println(r);
                        break;

                    case 0:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opcion no valida.");
                }
            } while (opcion != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}