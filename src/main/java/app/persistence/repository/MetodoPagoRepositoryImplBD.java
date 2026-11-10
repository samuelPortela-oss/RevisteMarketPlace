package app.persistence.repository;

import app.domain.MetodoPago;
import app.persistence.mapper.RowMapper;
import app.service.outputports.MetodoPagoRepositoryInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MetodoPagoRepositoryImplBD implements MetodoPagoRepositoryInterface {

    private final Connection connection;
    private final RowMapper<MetodoPago> rowMapper;

    public MetodoPagoRepositoryImplBD(Connection connection, RowMapper<MetodoPago> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    @Override
    public void crearMetodoPago(MetodoPago metodoPago) {
        String sql = "INSERT INTO metodo_pago (nombre, descripcion, activo) VALUES (?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setMetodoPagoParams(ps, metodoPago);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    metodoPago.setIdMetodoPago(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar método de pago: " + e.getMessage());
        }
    }

    @Override
    public Optional<MetodoPago> obtenerMetodoPagoPorId(int idMetodoPago) {
        String sql = "SELECT * FROM metodo_pago WHERE id_metodo_pago = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idMetodoPago);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(rowMapper.mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar método de pago: " + e.getMessage());
        }
    }

    @Override
    public List<MetodoPago> listarMetodosPago() {
        String sql = "SELECT * FROM metodo_pago ORDER BY id_metodo_pago";
        List<MetodoPago> metodos = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                metodos.add(rowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar métodos de pago: " + e.getMessage());
        }
        return metodos;
    }

    @Override
    public boolean actualizarMetodoPago(MetodoPago metodoPago) {
        String sql = "UPDATE metodo_pago SET nombre = ?, descripcion = ?, activo = ? WHERE id_metodo_pago = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setMetodoPagoParams(ps, metodoPago);
            ps.setInt(4, metodoPago.getIdMetodoPago());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar método de pago: " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarMetodoPago(int idMetodoPago) {
        String sql = "DELETE FROM metodo_pago WHERE id_metodo_pago = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idMetodoPago);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar método de pago: " + e.getMessage());
        }
    }

    private void setMetodoPagoParams(PreparedStatement ps, MetodoPago metodoPago) throws SQLException {
        ps.setString(1, metodoPago.getNombre());
        ps.setString(2, metodoPago.getDescripcion());
        ps.setBoolean(3, Boolean.TRUE.equals(metodoPago.getActivo()));
    }
}
