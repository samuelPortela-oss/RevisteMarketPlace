package app.persistence.mapper;

import app.domain.MetodoPago;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MetodoPagoMapper implements RowMapper<MetodoPago> {

    @Override
    public MetodoPago mapRow(ResultSet rs) throws SQLException {
        return new MetodoPago(
                rs.getInt("id_metodo_pago"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getBoolean("activo"));
    }
}
