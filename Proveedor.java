public class Proveedor {
    private int idProveedor;
    private String repartidor;
    private String telefono;
    private String empresa;

    public Proveedor(int idProveedor, String repartidor, String telefono, String empresa) {
        this.idProveedor = idProveedor;
        this.repartidor = repartidor;
        this.telefono = telefono;
        this.empresa = empresa;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public String getRepartidor() {
        return repartidor;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmpresa() {
        return empresa;
    }

    @Override
    public String toString() {
        return "Empresa: " + empresa + " | Repartidor: " + repartidor + " | Tel: " + telefono;
    }
}