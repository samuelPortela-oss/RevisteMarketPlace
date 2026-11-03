package app.persistence.repository;

import app.domain.Prenda;
import app.persistence.mapper.RowMapper;
import app.service.outputports.PrendaRepositoryInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrendaRepositoryImplBD implements PrendaRepositoryInterface {

    private final Connection connection;
    private final RowMapper<Prenda> rowMapper;

    public PrendaRepositoryImplBD(Connection connection, RowMapper<Prenda> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    @Override
    public void savePrenda(Prenda prenda) {

        String sql = "INSERT INTO prenda (id, nombre, marca, talla, estado, precio) " +
                "VALUES (?,?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            setPrendaParams(ps, prenda);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar prenda " + e.getMessage());
        }
    }

    @Override
    public Prenda getPrendaById(int id) {

        String sql = "SELECT * FROM prenda WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rowMapper.mapRow(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar prenda " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Prenda> getAllPrendas() {

        List<Prenda> prendas = new ArrayList<>();

        String sql = "SELECT * FROM prenda";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                prendas.add(rowMapper.mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar prendas " + e.getMessage());
        }

        return prendas;
    }

    @Override
    public void deletePrenda(int id) {

        String sql = "DELETE FROM prenda WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar prenda " + e.getMessage());
        }
    }

    @Override
    public Prenda updatePrenda(Prenda prendaActualizada) {

        String sql = "UPDATE prenda SET nombre = ?, marca = ?, talla = ?, estado = ?, precio = ? " +
                "WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, prendaActualizada.getNombre());
            ps.setString(2, prendaActualizada.getMarca());
            ps.setString(3, prendaActualizada.getTalla());
            ps.setString(4, prendaActualizada.getEstado());
            ps.setDouble(5, prendaActualizada.getPrecio());
            ps.setInt(6, prendaActualizada.getId());

            ps.executeUpdate();
            return prendaActualizada;

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar prenda " + e.getMessage());
        }
    }

    private void setPrendaParams(PreparedStatement ps, Prenda prenda) throws SQLException {

        ps.setInt(1, prenda.getId());
        ps.setString(2, prenda.getNombre());
        ps.setString(3, prenda.getMarca());
        ps.setString(4, prenda.getTalla());
        ps.setString(5, prenda.getEstado());
        ps.setDouble(6, prenda.getPrecio());
    }
}
