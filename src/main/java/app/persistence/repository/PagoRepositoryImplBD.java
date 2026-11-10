package app.persistence.repository;

import app.domain.Pago;
import app.persistence.mapper.RowMapper;
import app.service.outputports.PagoRepositoryInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PagoRepositoryImplBD implements PagoRepositoryInterface {

    private final Connection connection;
    private final RowMapper<Pago> rowMapper;

    public PagoRepositoryImplBD(Connection connection, RowMapper<Pago> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    @Override
    public void crearPago(Pago pago) {
        String sql = "INSERT INTO pago (id_orden, id_metodo_pago, monto, estado, fecha_pago) VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setPagoParams(ps, pago);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    pago.setIdPago(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar pago: " + e.getMessage());
        }
    }

    @Override
    public Optional<Pago> obtenerPagoPorId(int idPago) {
        String sql = "SELECT * FROM pago WHERE id_pago = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idPago);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(rowMapper.mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar pago: " + e.getMessage());
        }
    }

    @Override
    public Optional<Pago> obtenerPagoPorOrden(int idOrden) {
        List<Pago> pagos = listarPagosPorOrden(idOrden);
        return pagos.isEmpty() ? Optional.empty() : Optional.of(pagos.get(pagos.size() - 1));
    }

    @Override
    public List<Pago> listarPagosPorOrden(int idOrden) {
        return listar("SELECT * FROM pago WHERE id_orden = ? ORDER BY id_pago", idOrden);
    }

    @Override
    public List<Pago> listarPagos() {
        return listar("SELECT * FROM pago ORDER BY id_pago", null);
    }

    @Override
    public boolean actualizarPago(Pago pago) {
        String sql = "UPDATE pago SET id_orden = ?, id_metodo_pago = ?, monto = ?, estado = ?, fecha_pago = ? WHERE id_pago = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setPagoParams(ps, pago);
            ps.setInt(6, pago.getIdPago());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar pago: " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarPago(int idPago) {
        String sql = "DELETE FROM pago WHERE id_pago = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idPago);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar pago: " + e.getMessage());
        }
    }

    private List<Pago> listar(String sql, Integer filtro) {
        List<Pago> pagos = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            if (filtro != null) {
                ps.setInt(1, filtro);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    pagos.add(rowMapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar pagos: " + e.getMessage());
        }
        return pagos;
    }

    private void setPagoParams(PreparedStatement ps, Pago pago) throws SQLException {
        ps.setInt(1, pago.getIdOrden());
        ps.setInt(2, pago.getIdMetodoPago());
        ps.setDouble(3, pago.getMonto());
        ps.setString(4, pago.getEstado().name());
        ps.setString(5, pago.getFechaPago());
    }
}
