package app.domain;

import java.util.Date;

public class Perfil extends Usuario {

    private String descripcionPerfil;
    private Double reputacion;

    public Perfil() {
        super();
    }

    public Perfil(int idUsuario, String nombre, String correo, String contrasena, int celular, String direccion, String fechaRegistro, Double reputacion, String descripcionPerfil) {
        super(idUsuario, nombre, correo, contrasena, celular, direccion, fechaRegistro);
        this.reputacion = reputacion;
        this.descripcionPerfil = descripcionPerfil;
    }

    public String getDescripcionPerfil() {
        return descripcionPerfil;
    }

    public void setDescripcionPerfil(String descripcionPerfil) {
        this.descripcionPerfil = descripcionPerfil;
    }

    public Double getReputacion() {
        return reputacion;
    }

    public void setReputacion(Double reputacion) {
        this.reputacion = reputacion;
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "descripcionPerfil='" + descripcionPerfil + '\'' +
                ", reputacion=" + reputacion +
                "} " + super.toString();
    }
}
