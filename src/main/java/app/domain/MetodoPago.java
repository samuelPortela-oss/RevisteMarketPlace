package app.domain;

public class MetodoPago {

    private int idMetodoPago;
    private String nombre;
    private String descripcion;
    private Boolean activo;

    public MetodoPago() {
    }

    public MetodoPago(int idMetodoPago, String nombre, String descripcion, Boolean activo) {
        this.idMetodoPago = idMetodoPago;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "MetodoPago{" +
                "idMetodoPago=" + idMetodoPago +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", activo=" + activo +
                '}';
    }
}
