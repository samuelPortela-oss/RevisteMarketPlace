package app.persistence.mapper;

import app.domain.Carrito;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarritoMapper implements RowMapper<Carrito> {

    @Override
    public Carrito mapRow(ResultSet rs) throws SQLException {
        return new Carrito(
                rs.getInt("id_carrito"),
                rs.getInt("id_usuario"),
                rs.getString("fecha_creacion"));
    }
}
