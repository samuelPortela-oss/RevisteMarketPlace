package app.persistence.mapper;

import app.domain.Prenda;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrendaMapper implements RowMapper<Prenda> {

    @Override
    public Prenda mapRow(ResultSet rs) throws SQLException {

        Prenda prenda = new Prenda();

        prenda.setId(rs.getInt("id"));
        prenda.setNombre(rs.getString("nombre"));
        prenda.setMarca(rs.getString("marca"));
        prenda.setTalla(rs.getString("talla"));
        prenda.setEstado(rs.getString("estado"));
        prenda.setPrecio(rs.getDouble("precio"));

        return prenda;
    }
}
