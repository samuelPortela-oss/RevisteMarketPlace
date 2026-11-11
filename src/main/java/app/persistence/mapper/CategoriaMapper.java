package app.persistence.mapper;

import app.domain.Categoria;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaMapper implements RowMapper<Categoria> {

    @Override
    public Categoria mapRow(ResultSet rs) throws SQLException {
        return new Categoria(
                rs.getInt("id_categoria"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getBoolean("activo"));
    }
}
