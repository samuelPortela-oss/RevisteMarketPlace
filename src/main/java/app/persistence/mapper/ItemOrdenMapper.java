package app.persistence.mapper;

import app.domain.ItemOrden;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemOrdenMapper implements RowMapper<ItemOrden> {

    @Override
    public ItemOrden mapRow(ResultSet rs) throws SQLException {
        return new ItemOrden(
                rs.getInt("id_item_orden"),
                rs.getInt("id_orden"),
                rs.getInt("id_prenda"),
                rs.getString("nombre_prenda"),
                rs.getInt("cantidad"),
                rs.getDouble("precio_unitario"));
    }
}
