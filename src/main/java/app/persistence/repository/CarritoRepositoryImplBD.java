package app.persistence.repository;

import app.domain.Carrito;
import app.persistence.mapper.RowMapper;
import app.service.outputports.CarritoRepositoryInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarritoRepositoryImplBD implements CarritoRepositoryInterface {

    private final Connection connection;
    private final RowMapper<Carrito> rowMapper;

    public CarritoRepositoryImplBD(Connection connection, RowMapper<Carrito> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    @Override
    public void crearCarrito(Carrito carrito) {
        String sql = "INSERT INTO carrito (id_usuario, fecha_creacion) VALUES (?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setCarritoParams(ps, carrito);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    carrito.setIdCarrito(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar carrito: " + e.getMessage());
        }
    }

    @Override
    public Optional<Carrito> obtenerCarritoPorId(int idCarrito) {
        String sql = "SELECT * FROM carrito WHERE id_carrito = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCarrito);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(rowMapper.mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar carrito: " + e.getMessage());
        }
    }

    @Override
    public Optional<Carrito> obtenerCarritoPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM carrito WHERE id_usuario = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(rowMapper.mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar carrito del usuario: " + e.getMessage());
        }
    }

    @Override
    public List<Carrito> listarCarritos() {
        String sql = "SELECT * FROM carrito ORDER BY id_carrito";
        List<Carrito> carritos = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                carritos.add(rowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar carritos: " + e.getMessage());
        }
        return carritos;
    }

    @Override
    public boolean actualizarCarrito(Carrito carrito) {
        String sql = "UPDATE carrito SET id_usuario = ?, fecha_creacion = ? WHERE id_carrito = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setCarritoParams(ps, carrito);
            ps.setInt(3, carrito.getIdCarrito());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar carrito: " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarCarrito(int idCarrito) {
        String sql = "DELETE FROM carrito WHERE id_carrito = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCarrito);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar carrito: " + e.getMessage());
        }
    }

    private void setCarritoParams(PreparedStatement ps, Carrito carrito) throws SQLException {
        ps.setInt(1, carrito.getIdUsuario());
        ps.setString(2, carrito.getFechaCreacion());
    }
}
