package app.persistence.mapper;

import app.domain.Orden;
import app.domain.enums.EstadoOrdenEnum;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdenMapper implements RowMapper<Orden> {

    @Override
    public Orden mapRow(ResultSet rs) throws SQLException {
        return new Orden(
                rs.getInt("id_orden"),
                rs.getInt("id_usuario"),
                rs.getDouble("monto_total"),
                EstadoOrdenEnum.valueOf(rs.getString("estado")),
                rs.getString("fecha_creacion"));
    }
}
