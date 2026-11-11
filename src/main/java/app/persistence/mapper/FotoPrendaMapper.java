package app.persistence.mapper;

import app.domain.FotoPrenda;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FotoPrendaMapper implements RowMapper<FotoPrenda> {

    @Override
    public FotoPrenda mapRow(ResultSet rs) throws SQLException {
        return new FotoPrenda(
                rs.getInt("id_foto"),
                rs.getInt("id_prenda"),
                rs.getString("ruta"),
                rs.getInt("orden"));
    }
}
