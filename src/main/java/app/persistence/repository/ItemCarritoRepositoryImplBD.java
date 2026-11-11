package app.persistence.repository;

import app.domain.ItemCarrito;
import app.persistence.mapper.RowMapper;
import app.service.outputports.ItemCarritoRepositoryInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemCarritoRepositoryImplBD implements ItemCarritoRepositoryInterface {

    private final Connection connection;
    private final RowMapper<ItemCarrito> rowMapper;

    public ItemCarritoRepositoryImplBD(Connection connection, RowMapper<ItemCarrito> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    @Override
    public void crearItemCarrito(ItemCarrito itemCarrito) {
        String sql = "INSERT INTO item_carrito (id_carrito, id_prenda, cantidad) VALUES (?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setItemCarritoParams(ps, itemCarrito);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    itemCarrito.setIdItemCarrito(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar item de carrito: " + e.getMessage());
        }
    }

    @Override
    public Optional<ItemCarrito> obtenerItemCarritoPorId(int idItemCarrito) {
        String sql = "SELECT * FROM item_carrito WHERE id_item_carrito = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idItemCarrito);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(rowMapper.mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar item de carrito: " + e.getMessage());
        }
    }

    @Override
    public List<ItemCarrito> listarItemsPorCarrito(int idCarrito) {
        String sql = "SELECT * FROM item_carrito WHERE id_carrito = ? ORDER BY id_item_carrito";
        List<ItemCarrito> items = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCarrito);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(rowMapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar items de carrito: " + e.getMessage());
        }
        return items;
    }

    @Override
    public boolean actualizarItemCarrito(ItemCarrito itemCarrito) {
        String sql = "UPDATE item_carrito SET id_carrito = ?, id_prenda = ?, cantidad = ? WHERE id_item_carrito = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setItemCarritoParams(ps, itemCarrito);
            ps.setInt(4, itemCarrito.getIdItemCarrito());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar item de carrito: " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarItemCarrito(int idItemCarrito) {
        String sql = "DELETE FROM item_carrito WHERE id_item_carrito = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idItemCarrito);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar item de carrito: " + e.getMessage());
        }
    }

    @Override
    public void eliminarItemsPorCarrito(int idCarrito) {
        String sql = "DELETE FROM item_carrito WHERE id_carrito = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCarrito);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al vaciar el carrito: " + e.getMessage());
        }
    }

    private void setItemCarritoParams(PreparedStatement ps, ItemCarrito itemCarrito) throws SQLException {
        ps.setInt(1, itemCarrito.getIdCarrito());
        ps.setInt(2, itemCarrito.getIdPrenda());
        ps.setInt(3, itemCarrito.getCantidad());
    }
}
