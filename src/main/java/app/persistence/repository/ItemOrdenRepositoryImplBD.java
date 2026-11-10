package app.persistence.repository;

import app.domain.ItemOrden;
import app.persistence.mapper.RowMapper;
import app.service.outputports.ItemOrdenRepositoryInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemOrdenRepositoryImplBD implements ItemOrdenRepositoryInterface {

    private final Connection connection;
    private final RowMapper<ItemOrden> rowMapper;

    public ItemOrdenRepositoryImplBD(Connection connection, RowMapper<ItemOrden> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    @Override
    public void crearItemOrden(ItemOrden itemOrden) {
        String sql = "INSERT INTO item_orden (id_orden, id_prenda, nombre_prenda, cantidad, precio_unitario) VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setItemOrdenParams(ps, itemOrden);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    itemOrden.setIdItemOrden(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar item de orden: " + e.getMessage());
        }
    }

    @Override
    public Optional<ItemOrden> obtenerItemOrdenPorId(int idItemOrden) {
        String sql = "SELECT * FROM item_orden WHERE id_item_orden = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idItemOrden);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(rowMapper.mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar item de orden: " + e.getMessage());
        }
    }

    @Override
    public List<ItemOrden> listarItemsPorOrden(int idOrden) {
        String sql = "SELECT * FROM item_orden WHERE id_orden = ? ORDER BY id_item_orden";
        List<ItemOrden> items = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idOrden);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(rowMapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar items de orden: " + e.getMessage());
        }
        return items;
    }

    @Override
    public boolean actualizarItemOrden(ItemOrden itemOrden) {
        String sql = "UPDATE item_orden SET id_orden = ?, id_prenda = ?, nombre_prenda = ?, cantidad = ?, precio_unitario = ? WHERE id_item_orden = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setItemOrdenParams(ps, itemOrden);
            ps.setInt(6, itemOrden.getIdItemOrden());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar item de orden: " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarItemOrden(int idItemOrden) {
        String sql = "DELETE FROM item_orden WHERE id_item_orden = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idItemOrden);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar item de orden: " + e.getMessage());
        }
    }

    private void setItemOrdenParams(PreparedStatement ps, ItemOrden itemOrden) throws SQLException {
        ps.setInt(1, itemOrden.getIdOrden());
        ps.setInt(2, itemOrden.getIdPrenda());
        ps.setString(3, itemOrden.getNombrePrenda());
        ps.setInt(4, itemOrden.getCantidad());
        ps.setDouble(5, itemOrden.getPrecioUnitario());
    }
}
