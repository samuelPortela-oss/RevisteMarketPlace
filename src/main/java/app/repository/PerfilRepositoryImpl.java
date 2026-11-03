package app.repository;

import app.domain.Perfil;
import app.service.outputports.PerfilRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class PerfilRepositoryImpl implements PerfilRepositoryInterface {

    List<Perfil> perfils = new ArrayList<>();

    @Override
    public Perfil savePerfil(Perfil perfil) {
        perfils.add(perfil);
        return perfil;
    }

    @Override
    public Perfil selectPerfilById(int id) {
        return perfils.get(id);
    }

    @Override
    public List<Perfil> selectAllPerfils() {
        return perfils;
    }

    @Override
    public Perfil updatePerfil(Perfil perfil) {
        perfils.set(perfils.indexOf(perfil), perfil);
        return perfil;
    }

    @Override
    public void deletePerfil(int id) {

    }
}
