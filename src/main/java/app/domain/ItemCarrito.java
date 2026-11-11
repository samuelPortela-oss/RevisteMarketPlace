package app.domain;

public class ItemCarrito {

    private int idItemCarrito;
    private int idCarrito;
    private int idPrenda;
    private int cantidad;

    public ItemCarrito() {
    }

    public ItemCarrito(int idItemCarrito, int idCarrito, int idPrenda, int cantidad) {
        this.idItemCarrito = idItemCarrito;
        this.idCarrito = idCarrito;
        this.idPrenda = idPrenda;
        this.cantidad = cantidad;
    }

    public int getIdItemCarrito() {
        return idItemCarrito;
    }

    public void setIdItemCarrito(int idItemCarrito) {
        this.idItemCarrito = idItemCarrito;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(int idPrenda) {
        this.idPrenda = idPrenda;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ItemCarrito{" +
                "idItemCarrito=" + idItemCarrito +
                ", idCarrito=" + idCarrito +
                ", idPrenda=" + idPrenda +
                ", cantidad=" + cantidad +
                '}';
    }
}
