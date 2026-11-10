package app.persistence.mapper;

import app.domain.ItemCarrito;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemCarritoMapper implements RowMapper<ItemCarrito> {

    @Override
    public ItemCarrito mapRow(ResultSet rs) throws SQLException {
        return new ItemCarrito(
                rs.getInt("id_item_carrito"),
                rs.getInt("id_carrito"),
                rs.getInt("id_prenda"),
                rs.getInt("cantidad"));
    }
}
