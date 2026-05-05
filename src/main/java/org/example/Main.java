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
                System.out.println("5. Registrar Cliente");
                System.out.println("6. Registrar Vehiculo");
                System.out.println("7. Registrar Lavado");
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

                    case 5:
                        System.out.println("\n--- REGISTRAR CLIENTES ---");
                        for (int i = 1; i <= 3; i++) {
                            System.out.println("Cliente " + i + ":");
                            System.out.print("Nombre: "); String nombre = scanner.nextLine();
                            System.out.print("Apellido: "); String apellido = scanner.nextLine();
                            System.out.print("Telefono: "); String telefono = scanner.nextLine();
                            System.out.print("Email: "); String email = scanner.nextLine();
                            System.out.print("Direccion: "); String direccion = scanner.nextLine();
                            clienteDAO.crear(new Clientes(nombre, apellido, telefono, email, direccion));
                            System.out.println("Cliente registrado.");
                        }
                        break;

                    case 6:
                        System.out.println("\n--- REGISTRAR VEHICULOS ---");
                        for (int i = 1; i <= 3; i++) {
                            System.out.println("Vehiculo " + i + ":");
                            System.out.print("ClienteID: "); int clienteID = scanner.nextInt(); scanner.nextLine();
                            System.out.print("Marca: "); String marca = scanner.nextLine();
                            System.out.print("Modelo: "); String modelo = scanner.nextLine();
                            System.out.print("Placa: "); String placa = scanner.nextLine();
                            System.out.print("Color: "); String color = scanner.nextLine();
                            System.out.print("Tipo: "); String tipo = scanner.nextLine();
                            vehiculoDAO.crear(new Vehiculos(clienteID, marca, modelo, placa, color, tipo));
                            System.out.println("Vehiculo registrado.");
                        }
                        break;

                    case 7:
                        System.out.println("\n--- REGISTRAR LAVADOS ---");
                        for (int i = 1; i <= 3; i++) {
                            System.out.println("Lavado " + i + ":");
                            System.out.print("VehiculoID: "); int vID = scanner.nextInt(); scanner.nextLine();
                            System.out.print("ServicioID: "); int sID = scanner.nextInt(); scanner.nextLine();
                            System.out.print("Fecha (yyyy-MM-dd): "); String fecha = scanner.nextLine();
                            System.out.print("Hora inicio (HH:mm:ss): "); String horainicio = scanner.nextLine();
                            System.out.print("Hora fin (HH:mm:ss): "); String horafin = scanner.nextLine();
                            System.out.print("Precio total: "); double precio = scanner.nextDouble(); scanner.nextLine();
                            registroDAO.crear(new RegistrosLavado(vID, sID, fecha, horainicio, horafin, precio));
                            System.out.println("Lavado registrado.");
                        }
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