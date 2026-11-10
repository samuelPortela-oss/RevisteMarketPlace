package app.domain;

import app.domain.enums.CondicionPrendaEnum;
import app.domain.enums.EstadoPrendaEnum;

public class Prenda {

    public static final int MAXIMO_FOTOS = 5;

    private int idPrenda;
    private int idVendedor;
    private int idCategoria;
    private String nombre;
    private String descripcion;
    private String marca;
    private String talla;
    private CondicionPrendaEnum condicion;
    private double precio;
    private int stock;
    private EstadoPrendaEnum estado;
    private String fechaPublicacion;

    public Prenda() {
    }

    public Prenda(int idPrenda, int idVendedor, int idCategoria, String nombre,
                  String descripcion, String marca, String talla,
                  CondicionPrendaEnum condicion, double precio, int stock,
                  EstadoPrendaEnum estado, String fechaPublicacion) {
        this.idPrenda = idPrenda;
        this.idVendedor = idVendedor;
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.talla = talla;
        this.condicion = condicion;
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(int idPrenda) {
        this.idPrenda = idPrenda;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public CondicionPrendaEnum getCondicion() {
        return condicion;
    }

    public void setCondicion(CondicionPrendaEnum condicion) {
        this.condicion = condicion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public EstadoPrendaEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrendaEnum estado) {
        this.estado = estado;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public String toString() {
        return "Prenda{" +
                "idPrenda=" + idPrenda +
                ", idVendedor=" + idVendedor +
                ", idCategoria=" + idCategoria +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", marca='" + marca + '\'' +
                ", talla='" + talla + '\'' +
                ", condicion=" + condicion +
                ", precio=" + precio +
                ", stock=" + stock +
                ", estado=" + estado +
                ", fechaPublicacion='" + fechaPublicacion + '\'' +
                '}';
    }
}
