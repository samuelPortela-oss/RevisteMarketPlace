package app.domain;

import java.util.Date;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String correo;
    private String contrasena;
    private String celular;
    private String direccion;
    private String fechaRegistro;
    private Boolean activo;

    // Seller attributes
    private String calificacion;
    private int numeroVentas;
    private int productosPublicados;

    // Buyer attributes
    private int comprasRealizadas;
    private String listaDeseos;
    private String metodoPago;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String correo, String contrasena, String celular, String direccion, String fechaRegistro, Boolean activo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.celular = celular;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
        this.calificacion = "0.0";
        this.numeroVentas = 0;
        this.productosPublicados = 0;
        this.comprasRealizadas = 0;
        this.listaDeseos = "";
        this.metodoPago = "";
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public int getNumeroVentas() {
        return numeroVentas;
    }

    public void setNumeroVentas(int numeroVentas) {
        this.numeroVentas = numeroVentas;
    }

    public int getProductosPublicados() {
        return productosPublicados;
    }

    public void setProductosPublicados(int productosPublicados) {
        this.productosPublicados = productosPublicados;
    }

    public int getComprasRealizadas() {
        return comprasRealizadas;
    }

    public void setComprasRealizadas(int comprasRealizadas) {
        this.comprasRealizadas = comprasRealizadas;
    }

    public String getListaDeseos() {
        return listaDeseos;
    }

    public void setListaDeseos(String listaDeseos) {
        this.listaDeseos = listaDeseos;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void sellProduct() {
        this.numeroVentas++;
    }

    public void publishProduct() {
        this.productosPublicados++;
    }

    public void buyProduct() {
        this.comprasRealizadas++;
    }

    public void addToWishlist() {
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", celular='" + celular + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                ", activo=" + activo +
                ", calificacion='" + calificacion + '\'' +
                ", numeroVentas=" + numeroVentas +
                ", productosPublicados=" + productosPublicados +
                ", comprasRealizadas=" + comprasRealizadas +
                ", listaDeseos='" + listaDeseos + '\'' +
                ", metodoPago='" + metodoPago + '\'' +
                '}';
    }
}
