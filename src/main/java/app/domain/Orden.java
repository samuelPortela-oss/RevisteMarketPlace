package app.domain;

import app.domain.enums.EstadoOrdenEnum;

public class Orden {

    private int idOrden;
    private int idUsuario;
    private double montoTotal;
    private EstadoOrdenEnum estado;
    private String fechaCreacion;

    public Orden() {
    }

    public Orden(int idOrden, int idUsuario, double montoTotal,
                 EstadoOrdenEnum estado, String fechaCreacion) {
        this.idOrden = idOrden;
        this.idUsuario = idUsuario;
        this.montoTotal = montoTotal;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public EstadoOrdenEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrdenEnum estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "idOrden=" + idOrden +
                ", idUsuario=" + idUsuario +
                ", montoTotal=" + montoTotal +
                ", estado=" + estado +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                '}';
    }
}
