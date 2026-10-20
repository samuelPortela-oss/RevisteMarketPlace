package app.service.inputports;

import app.domain.Perfil;

import java.util.Date;
import java.util.List;

public interface PerfilUseCase {

    public Perfil createPerfil (int idUsuario, String nombre, String correo, String contrasena, int celular, String direccion, String fechaRegistro, Double reputacion, String descripcionPerfil);
    public Perfil getPerfilById(int idUsuario);
    public List<Perfil> getAllPerfils();
    public Perfil updatePerfil(int idUsuario, String nombre, String correo, String contrasena, int celular, String direccion, String fechaRegistro, Double reputacion, String descripcionPerfil);
    public void deletePerfil(int idUsuario);
}
