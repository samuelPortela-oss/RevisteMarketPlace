package app.service.outputports;

import app.domain.Perfil;

import java.util.List;

public interface PerfilRepositoryInterface {

    Perfil savePerfil (Perfil perfil);
    Perfil selectPerfilById (int id);
    List<Perfil> selectAllPerfils();
    Perfil updatePerfil (Perfil perfil);
    void deletePerfil(int id);
}
