package app.persistence.repository;

import app.domain.Categoria;
import app.persistence.mapper.RowMapper;
import app.service.outputports.CategoriaRepositoryInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriaRepositoryImplBD implements CategoriaRepositoryInterface {

    private final Connection connection;
    private final RowMapper<Categoria> rowMapper;

    public CategoriaRepositoryImplBD(Connection connection, RowMapper<Categoria> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    @Override
    public void crearCategoria(Categoria categoria) {
        String sql = "INSERT INTO categoria (nombre, descripcion, activo) VALUES (?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setCategoriaParams(ps, categoria);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    categoria.setIdCategoria(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar categoría: " + e.getMessage());
        }
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorId(int idCategoria) {
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCategoria);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(rowMapper.mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar categoría: " + e.getMessage());
        }
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorNombre(String nombre) {
        String sql = "SELECT * FROM categoria WHERE nombre = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(rowMapper.mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar categoría por nombre: " + e.getMessage());
        }
    }

    @Override
    public List<Categoria> listarCategorias() {
        String sql = "SELECT * FROM categoria ORDER BY id_categoria";
        List<Categoria> categorias = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                categorias.add(rowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar categorías: " + e.getMessage());
        }
        return categorias;
    }

    @Override
    public boolean actualizarCategoria(Categoria categoria) {
        String sql = "UPDATE categoria SET nombre = ?, descripcion = ?, activo = ? WHERE id_categoria = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setCategoriaParams(ps, categoria);
            ps.setInt(4, categoria.getIdCategoria());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar categoría: " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarCategoria(int idCategoria) {
        String sql = "DELETE FROM categoria WHERE id_categoria = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCategoria);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar categoría: " + e.getMessage());
        }
    }

    private void setCategoriaParams(PreparedStatement ps, Categoria categoria) throws SQLException {
        ps.setString(1, categoria.getNombre());
        ps.setString(2, categoria.getDescripcion());
        ps.setBoolean(3, Boolean.TRUE.equals(categoria.getActivo()));
    }
}
