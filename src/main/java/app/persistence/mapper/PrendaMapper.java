package app.persistence.mapper;

import app.domain.Prenda;
import app.domain.enums.CondicionPrendaEnum;
import app.domain.enums.EstadoPrendaEnum;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrendaMapper implements RowMapper<Prenda> {

    @Override
    public Prenda mapRow(ResultSet rs) throws SQLException {
        return new Prenda(
                rs.getInt("id_prenda"),
                rs.getInt("id_vendedor"),
                rs.getInt("id_categoria"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getString("marca"),
                rs.getString("talla"),
                CondicionPrendaEnum.valueOf(rs.getString("condicion")),
                rs.getDouble("precio"),
                rs.getInt("stock"),
                EstadoPrendaEnum.valueOf(rs.getString("estado")),
                rs.getString("fecha_publicacion"));
    }
}
