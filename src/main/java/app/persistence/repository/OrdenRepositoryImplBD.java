package app.persistence.repository;

import app.domain.Orden;
import app.persistence.mapper.RowMapper;
import app.service.outputports.OrdenRepositoryInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdenRepositoryImplBD implements OrdenRepositoryInterface {

    private final Connection connection;
    private final RowMapper<Orden> rowMapper;

    public OrdenRepositoryImplBD(Connection connection, RowMapper<Orden> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    @Override
    public void crearOrden(Orden orden) {
        String sql = "INSERT INTO orden (id_usuario, monto_total, estado, fecha_creacion) VALUES (?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setOrdenParams(ps, orden);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    orden.setIdOrden(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar orden: " + e.getMessage());
        }
    }

    @Override
    public Optional<Orden> obtenerOrdenPorId(int idOrden) {
        String sql = "SELECT * FROM orden WHERE id_orden = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idOrden);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(rowMapper.mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar orden: " + e.getMessage());
        }
    }

    @Override
    public List<Orden> listarOrdenesPorUsuario(int idUsuario) {
        return listar("SELECT * FROM orden WHERE id_usuario = ? ORDER BY id_orden", idUsuario);
    }

    @Override
    public List<Orden> listarOrdenes() {
        return listar("SELECT * FROM orden ORDER BY id_orden", null);
    }

    @Override
    public boolean actualizarOrden(Orden orden) {
        String sql = "UPDATE orden SET id_usuario = ?, monto_total = ?, estado = ?, fecha_creacion = ? WHERE id_orden = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setOrdenParams(ps, orden);
            ps.setInt(5, orden.getIdOrden());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar orden: " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarOrden(int idOrden) {
        String sql = "DELETE FROM orden WHERE id_orden = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idOrden);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar orden: " + e.getMessage());
        }
    }

    private List<Orden> listar(String sql, Integer filtro) {
        List<Orden> ordenes = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            if (filtro != null) {
                ps.setInt(1, filtro);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ordenes.add(rowMapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar órdenes: " + e.getMessage());
        }
        return ordenes;
    }

    private void setOrdenParams(PreparedStatement ps, Orden orden) throws SQLException {
        ps.setInt(1, orden.getIdUsuario());
        ps.setDouble(2, orden.getMontoTotal());
        ps.setString(3, orden.getEstado().name());
        ps.setString(4, orden.getFechaCreacion());
    }
}
