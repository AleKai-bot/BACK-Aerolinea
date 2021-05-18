/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Logic.Avion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Alejandro-PC
 */
public class DaoAvion {

    CallableStatement cstmt = null;
    Connection conn = null;
    RelDatabase r = null;

    public DaoAvion() {
        r = RelDatabase.getInstance(); //Hacerlo Singleton 
        conn = r.getConnection();
    }

    public void agregarAvion(int id, int annio, String model, String marca, int cantFi, int cantAsci) {
        try {
            boolean resultado = false;

            String SQL = "{call insertarAvion(?,?,?,?,?,?)}";
            cstmt = conn.prepareCall(SQL);

            cstmt.setInt(1, id);
            cstmt.setInt(2, annio);
            cstmt.setString(3, model);
            cstmt.setString(4, marca);
            cstmt.setInt(5, cantFi);
            cstmt.setInt(6, cantAsci);

            resultado = cstmt.execute();
            if (resultado == true) {
                throw new SQLException("No se realizo la inserción");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Avion buscarAvion(int cod) {
        try {
            boolean resultado = false;
            String SQL = "{call buscarAvion(?)}";
            cstmt = conn.prepareCall(SQL);
            cstmt.setInt(1, cod);
            resultado = cstmt.execute();
            if (resultado == false) {
                throw new SQLException("No se encontro Usuario");
            }
            ResultSet rs = cstmt.getResultSet();
            if (rs.next()) {
                return avion(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Avion avion(ResultSet rs) {
        try {

            Avion p = new Avion();

            p.setIdAvion(rs.getInt("idAvion"));
            p.setAnno(rs.getInt("año"));
            p.setModel(rs.getString("modelo"));
            p.setMarca(rs.getString("marca"));
            p.setCantFila(rs.getInt("cantFila"));
            p.setCantAsci(rs.getInt("cantAsci"));

            return p;
        } catch (SQLException ex) {
            System.out.print("Error en la llamada de Usuario");
        }
        return null;
    }

    public ArrayList<Avion> listarAviones() {

        ArrayList<Avion> lista = new ArrayList<Avion>();

        try {
            String SQL = "{call listarAviones()}";
            cstmt = conn.prepareCall(SQL);
            cstmt.execute();
            ResultSet rs = cstmt.getResultSet();
            while (rs.next()) {
                lista.add(avion(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public void modificarAvion(int id, int annio, String model, String marca, int cantFi, int cantAsci) {
        try {
            String SQL = "{call modificarAvion(?,?,?,?,?,?)}";
            cstmt = conn.prepareCall(SQL);

            cstmt.setInt(1, id);
            cstmt.setInt(2, annio);
            cstmt.setString(3, model);
            cstmt.setString(4, marca);
            cstmt.setInt(5, cantFi);
            cstmt.setInt(6, cantAsci);

            boolean resultado = cstmt.execute();
            if (resultado == true) {
                throw new SQLException("No se realizo la modificacion");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarAvion(int idUser) {
        try {
            String SQL = "{call eliminarAvion(?)}";
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

}
