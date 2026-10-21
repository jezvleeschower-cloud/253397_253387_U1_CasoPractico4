import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SistemaAlmacen sistema = new SistemaAlmacen(50, 20); // Capacidad de arreglos

        // Datos iniciales de prueba
        Proveedor prov1 = new Proveedor(1, "Carlos López", "555-1234", "Distribuidora Alfa");
        Proveedor prov2 = new Proveedor(2, "María Gómez", "555-5678", "Comercializadora Beta");
        sistema.registrarProveedor(prov1);
        sistema.registrarProveedor(prov2);

        int opcion = 0;
        int secProductos = 101;
        int secProveedores = 3;

        try {
            do {
                System.out.println("\n--- SISTEMA DE GESTIÓN DE ALMACÉN ---");
                System.out.println("1. Registrar nuevo proveedor");
                System.out.println("2. Registrar mercancía/producto");
                System.out.println("3. Cambiar proveedor a un producto");
                System.out.println("4. Generar reporte 'ENTRADA DE PRODUCTOS'");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");

                try {
                    opcion = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    opcion = 0;
                }

                switch (opcion) {
                    case 1:
                        // Se verifica la capacidad ANTES de pedir datos y de consumir un ID
                        if (!sistema.hayEspacioProveedores()) {
                            System.out.println("Error: Capacidad máxima de proveedores alcanzada.");
                            break;
                        }
                        String repartidor = leerTexto("Nombre del repartidor: ");
                        String tel = leerTexto("Teléfono de contacto: ");
                        String empresa = leerTexto("Nombre de la empresa: ");

                        Proveedor nuevoProv = new Proveedor(secProveedores, repartidor, tel, empresa);
                        if (sistema.registrarProveedor(nuevoProv)) {
                            secProveedores++;
                            System.out.println("¡Proveedor registrado correctamente! (ID: " + nuevoProv.getIdProveedor() + ")");
                        } else {
                            System.out.println("Error: No se pudo registrar el proveedor.");
                        }
                        break;

                    case 2:
                        if (!sistema.hayEspacioProductos()) {
                            System.out.println("Error: Capacidad máxima de almacén alcanzada.");
                            break;
                        }
                        sistema.listarProveedores();
                        int idProv = leerEntero("Ingrese el ID del proveedor que surte la mercancía: ");
                        Proveedor provEncontrado = sistema.buscarProveedorPorId(idProv);

                        if (provEncontrado != null) {
                            String nombreProd = leerTexto("Nombre del producto: ");
                            int stock = leerEnteroPositivo("Cantidad (Stock): ");
                            double precio = leerDoublePositivo("Precio de compra acordado: ");

                            Producto nuevoProducto = new Producto(secProductos, nombreProd, stock, precio, provEncontrado);
                            if (sistema.registrarProducto(nuevoProducto)) {
                                secProductos++;
                                System.out.println("¡Producto registrado con éxito! (ID: " + nuevoProducto.getIdProducto() + ")");
                            } else {
                                System.out.println("Error: No se pudo registrar el producto.");
                            }
                        } else {
                            System.out.println("Error: Proveedor no encontrado.");
                        }
                        break;

                    case 3:
                        if (sistema.getContadorProductos() == 0) {
                            System.out.println("Error: No hay productos registrados para modificar.");
                            break;
                        }
                        // Se muestran los productos para que el usuario conozca sus IDs
                        sistema.listarProductos();
                        int idProdMod = leerEntero("Ingrese el ID del producto a modificar: ");
                        if (sistema.buscarProductoPorId(idProdMod) == null) {
                            System.out.println("Error: Producto no encontrado.");
                            break;
                        }
                        sistema.listarProveedores();
                        int idNuevoProv = leerEntero("Ingrese el ID del nuevo proveedor: ");

                        if (sistema.cambiarProveedorProducto(idProdMod, idNuevoProv)) {
                            System.out.println("¡Proveedor actualizado correctamente!");
                        } else {
                            System.out.println("Error: Proveedor no encontrado.");
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
        } catch (NoSuchElementException e) {
            // Fin de la entrada (Ctrl+D / Ctrl+Z o entrada redirigida agotada)
            System.out.println("\nEntrada finalizada. Saliendo del sistema...");
        }

        scanner.close();
    }

    // ----- Métodos auxiliares de lectura con validación -----

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = scanner.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("Error: El campo no puede estar vacío.");
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
            }
        }
    }

    private static int leerEnteroPositivo(String mensaje) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor > 0) {
                return valor;
            }
            System.out.println("Error: La cantidad debe ser mayor que 0.");
        }
    }

    private static double leerDoublePositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                // Se acepta coma o punto como separador decimal
                double valor = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                if (valor > 0 && !Double.isNaN(valor) && !Double.isInfinite(valor)) {
                    return valor;
                }
                System.out.println("Error: El precio debe ser un número mayor que 0.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un precio válido (ej. 25.50).");
            }
        }
    }
}
