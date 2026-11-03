package app.persistence.mapper;

import app.domain.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioMapper implements RowMapper<Usuario> {

    @Override
    public Usuario mapRow(ResultSet rs) throws SQLException {

        Usuario usuario = new Usuario();

        usuario.setIdUsuario(rs.getInt("id_usuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setCorreo(rs.getString("correo"));
        usuario.setContrasena(rs.getString("contrasena"));
        usuario.setCelular(rs.getString("celular"));
        usuario.setDireccion(rs.getString("direccion"));
        usuario.setFechaRegistro(rs.getString("fecha_registro"));
        usuario.setEstado(rs.getString("estado"));
        usuario.setCalificacion(rs.getString("calificacion"));
        usuario.setNumeroVentas(rs.getInt("numero_ventas"));
        usuario.setProductosPublicados(rs.getInt("productos_publicados"));
        usuario.setComprasRealizadas(rs.getInt("compras_realizadas"));
        usuario.setListaDeseos(rs.getString("lista_deseos"));
        usuario.setMetodoPago(rs.getString("metodo_pago"));

        return usuario;
    }
}
