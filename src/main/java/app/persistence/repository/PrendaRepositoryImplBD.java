package app.persistence.repository;

import app.domain.Prenda;
import app.persistence.mapper.RowMapper;
import app.service.outputports.PrendaRepositoryInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrendaRepositoryImplBD implements PrendaRepositoryInterface {

    private final Connection connection;
    private final RowMapper<Prenda> rowMapper;

    public PrendaRepositoryImplBD(Connection connection, RowMapper<Prenda> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    @Override
    public void crearPrenda(Prenda prenda) {
        String sql = "INSERT INTO prenda (id_vendedor, id_categoria, nombre, descripcion, marca, talla, condicion, precio, stock, estado, fecha_publicacion) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setPrendaParams(ps, prenda);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    prenda.setIdPrenda(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar prenda: " + e.getMessage());
        }
    }

    @Override
    public Optional<Prenda> obtenerPrendaPorId(int idPrenda) {
        String sql = "SELECT * FROM prenda WHERE id_prenda = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idPrenda);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(rowMapper.mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar prenda: " + e.getMessage());
        }
    }

    @Override
    public List<Prenda> listarPrendas() {
        return listar("SELECT * FROM prenda ORDER BY id_prenda", null);
    }

    @Override
    public List<Prenda> listarPrendasPorVendedor(int idVendedor) {
        return listar("SELECT * FROM prenda WHERE id_vendedor = ? ORDER BY id_prenda", idVendedor);
    }

    @Override
    public List<Prenda> listarPrendasPorCategoria(int idCategoria) {
        return listar("SELECT * FROM prenda WHERE id_categoria = ? ORDER BY id_prenda", idCategoria);
    }

    @Override
    public boolean actualizarPrenda(Prenda prenda) {
        String sql = "UPDATE prenda SET id_vendedor = ?, id_categoria = ?, nombre = ?, descripcion = ?, marca = ?, talla = ?, condicion = ?, precio = ?, stock = ?, estado = ?, fecha_publicacion = ? WHERE id_prenda = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setPrendaParams(ps, prenda);
            ps.setInt(12, prenda.getIdPrenda());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar prenda: " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarPrenda(int idPrenda) {
        String sql = "DELETE FROM prenda WHERE id_prenda = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idPrenda);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar prenda: " + e.getMessage());
        }
    }

    private List<Prenda> listar(String sql, Integer filtro) {
        List<Prenda> prendas = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            if (filtro != null) {
                ps.setInt(1, filtro);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    prendas.add(rowMapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar prendas: " + e.getMessage());
        }
        return prendas;
    }

    private void setPrendaParams(PreparedStatement ps, Prenda prenda) throws SQLException {
        ps.setInt(1, prenda.getIdVendedor());
        ps.setInt(2, prenda.getIdCategoria());
        ps.setString(3, prenda.getNombre());
        ps.setString(4, prenda.getDescripcion());
        ps.setString(5, prenda.getMarca());
        ps.setString(6, prenda.getTalla());
        ps.setString(7, prenda.getCondicion().name());
        ps.setDouble(8, prenda.getPrecio());
        ps.setInt(9, prenda.getStock());
        ps.setString(10, prenda.getEstado().name());
        ps.setString(11, prenda.getFechaPublicacion());
    }
}
