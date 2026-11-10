package app.persistence.repository;

import app.domain.FotoPrenda;
import app.persistence.mapper.RowMapper;
import app.service.outputports.FotoPrendaRepositoryInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FotoPrendaRepositoryImplBD implements FotoPrendaRepositoryInterface {

    private final Connection connection;
    private final RowMapper<FotoPrenda> rowMapper;

    public FotoPrendaRepositoryImplBD(Connection connection, RowMapper<FotoPrenda> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    @Override
    public void crearFotoPrenda(FotoPrenda fotoPrenda) {
        String sql = "INSERT INTO foto_prenda (id_prenda, ruta, orden) VALUES (?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setFotoPrendaParams(ps, fotoPrenda);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    fotoPrenda.setIdFoto(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar foto de prenda: " + e.getMessage());
        }
    }

    @Override
    public Optional<FotoPrenda> obtenerFotoPrendaPorId(int idFoto) {
        String sql = "SELECT * FROM foto_prenda WHERE id_foto = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idFoto);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(rowMapper.mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar foto de prenda: " + e.getMessage());
        }
    }

    @Override
    public List<FotoPrenda> listarFotosPorPrenda(int idPrenda) {
        String sql = "SELECT * FROM foto_prenda WHERE id_prenda = ? ORDER BY orden";
        List<FotoPrenda> fotos = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idPrenda);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    fotos.add(rowMapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar fotos de prenda: " + e.getMessage());
        }
        return fotos;
    }

    @Override
    public boolean actualizarFotoPrenda(FotoPrenda fotoPrenda) {
        String sql = "UPDATE foto_prenda SET id_prenda = ?, ruta = ?, orden = ? WHERE id_foto = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setFotoPrendaParams(ps, fotoPrenda);
            ps.setInt(4, fotoPrenda.getIdFoto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar foto de prenda: " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarFotoPrenda(int idFoto) {
        String sql = "DELETE FROM foto_prenda WHERE id_foto = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idFoto);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar foto de prenda: " + e.getMessage());
        }
    }

    private void setFotoPrendaParams(PreparedStatement ps, FotoPrenda fotoPrenda) throws SQLException {
        ps.setInt(1, fotoPrenda.getIdPrenda());
        ps.setString(2, fotoPrenda.getRuta());
        ps.setInt(3, fotoPrenda.getOrden());
    }
}
