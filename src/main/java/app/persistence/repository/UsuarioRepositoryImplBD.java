package app.persistence.repository;

import app.domain.Usuario;
import app.persistence.mapper.RowMapper;
import app.service.outputports.UsuarioRepositoryInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImplBD implements UsuarioRepositoryInterface {

    private final Connection connection;
    private final RowMapper<Usuario> rowMapper;

    public UsuarioRepositoryImplBD(Connection connection, RowMapper<Usuario> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    @Override
    public void createUsuario(Usuario usuario) {

        String sql = "INSERT INTO usuario (id_usuario, nombre, correo, contrasena, celular, " +
                "direccion, fecha_registro, estado, calificacion, numero_ventas, " +
                "productos_publicados, compras_realizadas, lista_deseos, metodo_pago) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            setUsuarioParams(ps, usuario);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar usuario " + e.getMessage());
        }
    }

    @Override
    public Usuario getUsuarioById(int idUsuario) {

        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rowMapper.mapRow(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar usuario " + e.getMessage());
        }

        return null;
    }

    @Override
    public Usuario getUsuarioByEmail(String email) {

        String sql = "SELECT * FROM usuario WHERE correo = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rowMapper.mapRow(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar usuario " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Usuario> getAllUsuarios() {

        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM usuario";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                usuarios.add(rowMapper.mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar usuarios " + e.getMessage());
        }

        return usuarios;
    }

    @Override
    public boolean updateUsuario(Usuario usuarioActualizado) {

        String sql = "UPDATE usuario SET nombre = ?, correo = ?, contrasena = ?, celular = ?, " +
                "direccion = ?, fecha_registro = ?, estado = ?, calificacion = ?, numero_ventas = ?, " +
                "productos_publicados = ?, compras_realizadas = ?, lista_deseos = ?, metodo_pago = ? " +
                "WHERE id_usuario = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, usuarioActualizado.getNombre());
            ps.setString(2, usuarioActualizado.getCorreo());
            ps.setString(3, usuarioActualizado.getContrasena());
            ps.setString(4, usuarioActualizado.getCelular());
            ps.setString(5, usuarioActualizado.getDireccion());
            ps.setString(6, usuarioActualizado.getFechaRegistro());
            ps.setString(7, usuarioActualizado.getEstado());
            ps.setString(8, usuarioActualizado.getCalificacion());
            ps.setInt(9, usuarioActualizado.getNumeroVentas());
            ps.setInt(10, usuarioActualizado.getProductosPublicados());
            ps.setInt(11, usuarioActualizado.getComprasRealizadas());
            ps.setString(12, usuarioActualizado.getListaDeseos());
            ps.setString(13, usuarioActualizado.getMetodoPago());
            ps.setInt(14, usuarioActualizado.getIdUsuario());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar usuario " + e.getMessage());
        }
    }

    @Override
    public boolean deleteUsuario(int idUsuario) {

        String sql = "DELETE FROM usuario WHERE id_usuario = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar usuario " + e.getMessage());
        }
    }

    private void setUsuarioParams(PreparedStatement ps, Usuario usuario) throws SQLException {

        ps.setInt(1, usuario.getIdUsuario());
        ps.setString(2, usuario.getNombre());
        ps.setString(3, usuario.getCorreo());
        ps.setString(4, usuario.getContrasena());
        ps.setString(5, usuario.getCelular());
        ps.setString(6, usuario.getDireccion());
        ps.setString(7, usuario.getFechaRegistro());
        ps.setString(8, usuario.getEstado());
        ps.setString(9, usuario.getCalificacion());
        ps.setInt(10, usuario.getNumeroVentas());
        ps.setInt(11, usuario.getProductosPublicados());
        ps.setInt(12, usuario.getComprasRealizadas());
        ps.setString(13, usuario.getListaDeseos());
        ps.setString(14, usuario.getMetodoPago());
    }
}
