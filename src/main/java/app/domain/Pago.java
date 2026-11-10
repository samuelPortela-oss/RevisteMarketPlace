package app.domain;

import app.domain.enums.EstadoPagoEnum;

public class Pago {

    private int idPago;
    private int idOrden;
    private int idMetodoPago;
    private double monto;
    private EstadoPagoEnum estado;
    private String fechaPago;

    public Pago() {
    }

    public Pago(int idPago, int idOrden, int idMetodoPago,
                double monto, EstadoPagoEnum estado, String fechaPago) {
        this.idPago = idPago;
        this.idOrden = idOrden;
        this.idMetodoPago = idMetodoPago;
        this.monto = monto;
        this.estado = estado;
        this.fechaPago = fechaPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public EstadoPagoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoPagoEnum estado) {
        this.estado = estado;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "idPago=" + idPago +
                ", idOrden=" + idOrden +
                ", idMetodoPago=" + idMetodoPago +
                ", monto=" + monto +
                ", estado=" + estado +
                ", fechaPago='" + fechaPago + '\'' +
                '}';
    }
}
