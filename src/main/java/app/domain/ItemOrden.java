package app.domain;

public class ItemOrden {

    private int idItemOrden;
    private int idOrden;
    private int idPrenda;
    private String nombrePrenda;
    private int cantidad;
    private double precioUnitario;

    public ItemOrden() {
    }

    public ItemOrden(int idItemOrden, int idOrden, int idPrenda,
                     String nombrePrenda, int cantidad, double precioUnitario) {
        this.idItemOrden = idItemOrden;
        this.idOrden = idOrden;
        this.idPrenda = idPrenda;
        this.nombrePrenda = nombrePrenda;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getIdItemOrden() {
        return idItemOrden;
    }

    public void setIdItemOrden(int idItemOrden) {
        this.idItemOrden = idItemOrden;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(int idPrenda) {
        this.idPrenda = idPrenda;
    }

    public String getNombrePrenda() {
        return nombrePrenda;
    }

    public void setNombrePrenda(String nombrePrenda) {
        this.nombrePrenda = nombrePrenda;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return precioUnitario * cantidad;
    }

    @Override
    public String toString() {
        return "ItemOrden{" +
                "idItemOrden=" + idItemOrden +
                ", idOrden=" + idOrden +
                ", idPrenda=" + idPrenda +
                ", nombrePrenda='" + nombrePrenda + '\'' +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                '}';
    }
}
