import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaAlmacen sistema = new SistemaAlmacen(50, 20); // Capacidad de arreglos

        // Datos iniciales de prueba
        Proveedor prov1 = new Proveedor(1, "Carlos López", "555-1234", "Distribuidora Alfa");
        Proveedor prov2 = new Proveedor(2, "María Gómez", "555-5678", "Comercializadora Beta");
        sistema.registrarProveedor(prov1);
        sistema.registrarProveedor(prov2);

        int opcion = 0;
        int secProductos = 101;
        int secProveedores = 3;

        do {
            System.out.println("\n--- SISTEMA DE GESTIÓN DE ALMACÉN ---");
            System.out.println("1. Registrar nuevo proveedor");
            System.out.println("2. Registrar mercancía/producto");
            System.out.println("3. Cambiar proveedor a un producto");
            System.out.println("4. Generar reporte 'ENTRADA DE PRODUCTOS'");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del repartidor: ");
                    String repartidor = scanner.nextLine();
                    System.out.print("Teléfono de contacto: ");
                    String tel = scanner.nextLine();
                    System.out.print("Nombre de la empresa: ");
                    String empresa = scanner.nextLine();

                    Proveedor nuevoProv = new Proveedor(secProveedores++, repartidor, tel, empresa);
                    if (sistema.registrarProveedor(nuevoProv)) {
                        System.out.println("¡Proveedor registrado correctamente!");
                    } else {
                        System.out.println("Error: Capacidad máxima de proveedores alcanzada.");
                    }
                    break;

                case 2:
                    sistema.listarProveedores();
                    System.out.print("Ingrese el ID del proveedor que surte la mercancía: ");
                    int idProv = Integer.parseInt(scanner.nextLine());
                    Proveedor provEncontrado = sistema.buscarProveedorPorId(idProv);

                    if (provEncontrado != null) {
                        System.out.print("Nombre del producto: ");
                        String nombreProd = scanner.nextLine();
                        System.out.print("Cantidad (Stock): ");
                        int stock = Integer.parseInt(scanner.nextLine());
                        System.out.print("Precio de compra acordado: ");
                        double precio = Double.parseDouble(scanner.nextLine());

                        Producto nuevoProducto = new Producto(secProductos++, nombreProd, stock, precio, provEncontrado);
                        if (sistema.registrarProducto(nuevoProducto)) {
                            System.out.println("¡Producto registrado con éxito!");
                        } else {
                            System.out.println("Error: Capacidad máxima de almacén alcanzada.");
                        }
                    } else {
                        System.out.println("Error: Proveedor no encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el ID del producto a modificar: ");
                    int idProdMod = Integer.parseInt(scanner.nextLine());
                    sistema.listarProveedores();
                    System.out.print("Ingrese el ID del nuevo proveedor: ");
                    int idNuevoProv = Integer.parseInt(scanner.nextLine());

                    if (sistema.cambiarProveedorProducto(idProdMod, idNuevoProv)) {
                        System.out.println("¡Proveedor actualizado correctamente!");
                    } else {
                        System.out.println("Error: No se pudo realizar el cambio. Verifique los IDs.");
                    }
                    break;

                case 4:
                    sistema.generarReporteEntrada();
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}