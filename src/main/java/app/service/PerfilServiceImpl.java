package app.service;

import app.domain.Perfil;
import app.repository.PerfilRepositoryImpl;
import app.service.inputports.PerfilUseCase;
import app.service.outputports.PerfilRepositoryInterface;

import java.util.Date;
import java.util.List;

public class PerfilServiceImpl implements PerfilUseCase {

   private final PerfilRepositoryInterface perfilRepository;

   public PerfilServiceImpl(PerfilRepositoryInterface perfilRepository) {
       this.perfilRepository = perfilRepository;
   }

    @Override
    public Perfil createPerfil(int idUsuario, String nombre, String correo, String contrasena, int celular, String direccion, String fechaRegistro, Double reputacion, String descripcionPerfil) {
       Perfil perfil = new Perfil(idUsuario, nombre, correo, contrasena, celular, direccion, fechaRegistro, reputacion, descripcionPerfil);
       return perfilRepository.savePerfil(perfil);
    }

    @Override
    public Perfil getPerfilById(int idUsuario) {
       Perfil perfil = perfilRepository.selectPerfilById(idUsuario);
       return perfil;
    }

    @Override
    public List<Perfil> getAllPerfils() {
        return perfilRepository.selectAllPerfils();
    }

    @Override
    public Perfil updatePerfil(int idUsuario, String nombre, String correo, String contrasena, int celular, String direccion, String fechaRegistro, Double reputacion, String descripcionPerfil) {
       Perfil perfil = new Perfil(idUsuario, nombre, correo, contrasena, celular, direccion, fechaRegistro, reputacion, descripcionPerfil);
       return perfilRepository.updatePerfil(perfil);
    }

    @Override
    public void deletePerfil(int idUsuario) {
        perfilRepository.deletePerfil(idUsuario);
    }
}
