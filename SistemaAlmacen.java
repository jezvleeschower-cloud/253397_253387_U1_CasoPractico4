public class SistemaAlmacen {
    // Uso obligatorio de arreglos de objetos
    private Producto[] productos;
    private Proveedor[] proveedores;
    private int contadorProductos;
    private int contadorProveedores;

    public SistemaAlmacen(int capacidadProductos, int capacidadProveedores) {
        this.productos = new Producto[capacidadProductos];
        this.proveedores = new Proveedor[capacidadProveedores];
        this.contadorProductos = 0;
        this.contadorProveedores = 0;
    }

    public boolean registrarProveedor(Proveedor p) {
        if (contadorProveedores < proveedores.length) {
            proveedores[contadorProveedores] = p;
            contadorProveedores++;
            return true;
        }
        return false;
    }

    public boolean registrarProducto(Producto p) {
        if (contadorProductos < productos.length) {
            productos[contadorProductos] = p;
            contadorProductos++;
            return true;
        }
        return false;
    }

    public Proveedor buscarProveedorPorId(int id) {
        for (int i = 0; i < contadorProveedores; i++) {
            if (proveedores[i].getIdProveedor() == id) {
                return proveedores[i];
            }
        }
        return null;
    }

    public Producto buscarProductoPorId(int id) {
        for (int i = 0; i < contadorProductos; i++) {
            if (productos[i].getIdProducto() == id) {
                return productos[i];
            }
        }
        return null;
    }

    public boolean cambiarProveedorProducto(int idProducto, int idNuevoProveedor) {
        Producto prod = buscarProductoPorId(idProducto);
        Proveedor prov = buscarProveedorPorId(idNuevoProveedor);

        if (prod != null && prov != null) {
            prod.setProveedor(prov);
            return true;
        }
        return false;
    }

    public void generarReporteEntrada() {
        System.out.println("\n=======================================================");
        System.out.println("        REPORTE DE ENTRADA DE PRODUCTOS              ");
        System.out.println("=======================================================");
        if (contadorProductos == 0) {
            System.out.println("No hay productos registrados en el almacén.");
        } else {
            for (int i = 0; i < contadorProductos; i++) {
                System.out.println(productos[i]);
            }
        }
        System.out.println("=======================================================\n");
    }

    public void listarProveedores() {
        System.out.println("\n--- LISTA DE PROVEEDORES ---");
        for (int i = 0; i < contadorProveedores; i++) {
            System.out.println("ID: " + proveedores[i].getIdProveedor() + " | " + proveedores[i]);
        }
    }
}