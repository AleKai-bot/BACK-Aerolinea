/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Logic.Bitacora;
import Model.ModelVuelo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Alejandro-PC
 */
public class DaoBitacora {

    CallableStatement cstmt = null;
    Connection conn = null;
    RelDatabase r = null;

    public DaoBitacora() {
        r = RelDatabase.getInstance(); //Hacerlo Singleton 
        conn = r.getConnection();
    }

    public void agregarRegistro(int idUser, String correo, int idVuelo, String asientos, Date fecha,float total) {
        try {
            boolean resultado = false;

            String SQL = "{call insertarRegistro(?,?,?,?,?,?)}";
            cstmt = conn.prepareCall(SQL);
            java.sql.Date date1 = new java.sql.Date(fecha.getTime());
            cstmt.setInt(1, idUser);
            cstmt.setString(2, correo);
            cstmt.setInt(3, idVuelo);
            cstmt.setString(4, asientos);
            cstmt.setDate(5, date1);
            cstmt.setFloat(6, total);
            resultado = cstmt.execute();
            if (resultado == true) {
                throw new SQLException("No se realizo la inserción");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Bitacora> listarRegistros() {
        ArrayList<Bitacora> lista = new ArrayList<Bitacora>();
        try {
            String SQL = "{call listarBitacora()}";
            cstmt = conn.prepareCall(SQL);
            cstmt.execute();
            ResultSet rs = cstmt.getResultSet();
            while (rs.next()) {
                lista.add(bitacora(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public ArrayList<Bitacora> listarBitacoraPorUser(int id) {
        ArrayList<Bitacora> lista = new ArrayList<>();
        boolean resultado = false;
        try {
            String SQL = "{call listarBitacoraPorUser(?)}";
            cstmt = conn.prepareCall(SQL);
            cstmt.setInt(1, id);
            resultado = cstmt.execute();
            if (resultado == false) {
                throw new SQLException("No se encontro la Categoria");
            }
            ResultSet rs = cstmt.getResultSet();
            while (rs.next()) {
                lista.add(bitacora(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    private Bitacora bitacora(ResultSet rs) {
        try {

            Bitacora p = new Bitacora();

            p.setIdBitacora(rs.getInt("registro"));
            p.setIdUser(rs.getInt("idUser"));
            p.setCorreo(rs.getString("correo"));
            p.setIdVuelo(rs.getInt("idVuelo"));
            p.setAsientos(rs.getString("asientos"));
            p.setFecha(rs.getDate("fecha"));
            p.setTotal(rs.getFloat("total"));
            p.setVuelo(ModelVuelo.buscarVuelos(p.getIdVuelo()));
            return p;
        } catch (SQLException ex) {
            System.out.print("Error en la llamada de Usuario");
        }
        return null;
    }

}
