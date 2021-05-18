/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Logic.Tiquete;
import Model.ModelVuelo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Alejandro-PC
 */
public class DaoTiquete {

    CallableStatement cstmt = null;
    Connection conn = null;
    RelDatabase r = null;

    public DaoTiquete() {
        r = RelDatabase.getInstance(); //Hacerlo Singleton 
        conn = r.getConnection();
    }

    public void agregarTiquete(int vuelo, float precio, String des) {
        try {
            boolean resultado = false;

            String SQL = "{call crearTiquete(?,?,?)}";
            cstmt = conn.prepareCall(SQL);

            cstmt.setInt(1, vuelo);
            cstmt.setFloat(2, precio);
            cstmt.setString(3, des);

            resultado = cstmt.execute();
            if (resultado == true) {
                throw new SQLException("No se realizo la inserción");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Tiquete buscarTiquete(int id) {
        try {
            boolean resultado = false;
            String SQL = "{call buscarTiquete(?)}";
            cstmt = conn.prepareCall(SQL);
            cstmt.setInt(1, id);
            resultado = cstmt.execute();
            if (resultado == false) {
                throw new SQLException("No se encontro Tiquete");
            }
            ResultSet rs = cstmt.getResultSet();
            if (rs.next()) {
                return tiquete(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Tiquete buscarTiqueteDeVuelo(int id) {
        try {
            boolean resultado = false;
            String SQL = "{call buscarTiqueteDeVuelo(?)}";
            cstmt = conn.prepareCall(SQL);
            cstmt.setInt(1, id);
            resultado = cstmt.execute();
            if (resultado == false) {
                throw new SQLException("No se encontro Tiquete");
            }
            ResultSet rs = cstmt.getResultSet();
            if (rs.next()) {
                return tiquete(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Tiquete> listarTiquetes() {

        ArrayList<Tiquete> lista = new ArrayList<Tiquete>();

        try {
            String SQL = "{call listarTiquetes()}";
            cstmt = conn.prepareCall(SQL);
            cstmt.execute();
            ResultSet rs = cstmt.getResultSet();
            while (rs.next()) {
                lista.add(tiquete(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    private Tiquete tiquete(ResultSet rs) {
        try {

            Tiquete p = new Tiquete();
            p.setIdTiquete(rs.getInt("idTiquete"));
            p.setIdVuelo(rs.getInt("idVue"));
            p.setPrecio(rs.getFloat("precio"));
            p.setDescrip(rs.getString("descrip"));
            p.setAsientos(rs.getString("asientos"));
            //p.setDisponible(ModelVuelo.buscarVuelos(p.getIdVuelo()).getCantPasa());
            return p;
        } catch (SQLException ex) {
            System.out.print("Error en la llamada de Usuario");
        }
        return null;
    }

    public void eliminarTiquete(int idTi) {
        try {
            String SQL = "{call eliminarTiquete(?)}";
            cstmt = conn.prepareCall(SQL);
            cstmt.setInt(1, idTi);
            boolean resultado = cstmt.execute();
            if (resultado == true) {
                throw new SQLException("No se realizo la eliminacion");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarTiquete(int id, int vue, float precio, String des,String asientos) {
        try {
            String SQL = "{call modificarTiquete(?,?,?,?,?)}";
            cstmt = conn.prepareCall(SQL);

            cstmt.setInt(1, id);
            cstmt.setInt(2, vue);
            cstmt.setFloat(3, precio);
            cstmt.setString(4, des);
            cstmt.setString(5, asientos);

            boolean resultado = cstmt.execute();
            if (resultado == true) {
                throw new SQLException("No se realizo la modificacion");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
