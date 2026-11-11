package app.persistence.mapper;

import app.domain.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioMapper implements RowMapper<Usuario> {

    @Override
    public Usuario mapRow(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("nombre"),
                rs.getString("correo"),
                rs.getString("contrasena"),
                rs.getString("telefono"),
                rs.getString("direccion"),
                rs.getString("fecha_registro"),
                rs.getBoolean("activo"));
    }
}
