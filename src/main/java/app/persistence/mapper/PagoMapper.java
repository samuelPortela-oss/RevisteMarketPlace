package app.persistence.mapper;

import app.domain.Pago;
import app.domain.enums.EstadoPagoEnum;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PagoMapper implements RowMapper<Pago> {

    @Override
    public Pago mapRow(ResultSet rs) throws SQLException {
        return new Pago(
                rs.getInt("id_pago"),
                rs.getInt("id_orden"),
                rs.getInt("id_metodo_pago"),
                rs.getDouble("monto"),
                EstadoPagoEnum.valueOf(rs.getString("estado")),
                rs.getString("fecha_pago"));
    }
}
