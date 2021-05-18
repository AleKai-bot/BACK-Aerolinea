/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Logic.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Alejandro-PC
 */
public class DaoUsuario {

    CallableStatement cstmt = null;
    Connection conn = null;
    RelDatabase r = null;

    public DaoUsuario() {
        r = RelDatabase.getInstance(); //Hacerlo Singleton 
        conn = r.getConnection();
    }

    public void agregarUsuario(String nom, String pass, String ape, String correo, String fechaNaci, String direc, String telefono, String rol) {
        try {
            boolean resultado = false;

            String SQL = "{call insertarUsuario(?,?,?,?,?,?,?,?)}";
            cstmt = conn.prepareCall(SQL);
            cstmt.setString(1, nom);
            cstmt.setString(2, pass);
            cstmt.setString(3, ape);
            cstmt.setString(4, correo);
            cstmt.setString(5, fechaNaci);
            cstmt.setString(6, direc);
            cstmt.setString(7, telefono);
            cstmt.setString(8, rol);

            resultado = cstmt.execute();
            if (resultado == true) {
                throw new SQLException("No se realizo la inserción");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Usuario buscarUsuario(int cod) {
        try {
            boolean resultado = false;
            String SQL = "{call buscarUsuario(?)}";
            cstmt = conn.prepareCall(SQL);
            cstmt.setInt(1, cod);
            resultado = cstmt.execute();
            if (resultado == false) {
                throw new SQLException("No se encontro Usuario");
            }
            ResultSet rs = cstmt.getResultSet();
            if (rs.next()) {
                return usuario(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Usuario buscarUsuarioCorreo(String correo) {
        try {
            boolean resultado = false;
            String SQL = "{call buscarUsuarioCorreo(?)}";
            cstmt = conn.prepareCall(SQL);
            cstmt.setString(1, correo);
            resultado = cstmt.execute();
            if (resultado == false) {
                throw new SQLException("No se encontro Usuario");
            }
            ResultSet rs = cstmt.getResultSet();
            if (rs.next()) {
                return usuario(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Usuario usuario(ResultSet rs) {
        try {

            Usuario p = new Usuario();
            p.setIdUser(rs.getInt("idUsuario")); //error de consulta
            p.setNombre(rs.getString("nombre"));
            p.setPass(rs.getString("contraseña"));
            p.setApellidos(rs.getString("apellidos"));
            p.setCorreo(rs.getString("correo"));
            p.setFechaNaci(rs.getString("fechaNaci"));
            p.setDirec(rs.getString("direccion"));
            p.setTelefono(rs.getString("telefono"));
            p.setRol(rs.getString("role_name"));

            return p;
        } catch (SQLException ex) {
            System.out.print("Error en la llamada de Usuario");

        }
        return new Usuario();
    }

    public ArrayList<Usuario> listarUsers() {

        ArrayList<Usuario> lista = new ArrayList<>();

        try {
            String SQL = "{call listarUsuarios()}";
            cstmt = conn.prepareCall(SQL);
            cstmt.execute();
            ResultSet rs = cstmt.getResultSet();
            while (rs.next()) {
                lista.add(usuario(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public void eliminarUsuario(int idUser) {
        try {
            eliminarVueloUsuario(idUser);
            String SQL = "{call eliminarUsuario(?)}";
            cstmt = conn.prepareCall(SQL);
            cstmt.setInt(1, idUser);
            boolean resultado = cstmt.execute();
            if (resultado == true) {
                throw new SQLException("No se realizo la eliminacion");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarVueloUsuario(int idUser) {
        try {
            String SQL = "{call eliminarVueloUsuario(?)}";
            cstmt = conn.prepareCall(SQL);
            cstmt.setInt(1, idUser);
            boolean resultado = cstmt.execute();
            if (resultado == true) {
                throw new SQLException("No se realizo la eliminacion");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarUsuario(int cod, String nom, String pass, String ape, String correo, String fechaNaci, String direc, String telefono, String rol) {
        try {
            String SQL = "{call modificarUsuario(?,?,?,?,?,?,?,?,?)}";
            cstmt = conn.prepareCall(SQL);

            cstmt.setInt(1, cod);
            cstmt.setString(2, nom);
            cstmt.setString(3, pass);
            cstmt.setString(4, ape);
            cstmt.setString(5, correo);
            cstmt.setString(6, fechaNaci);
            cstmt.setString(7, direc);
            cstmt.setString(8, telefono);
            cstmt.setString(9, rol);

            boolean resultado = cstmt.execute();
            if (resultado == true) {
                throw new SQLException("No se realizo la modificacion");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "ERROR");
        }
    }

}
