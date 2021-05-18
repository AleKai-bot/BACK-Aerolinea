/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Logic.Ruta;
import Logic.Vuelos;
import Model.ModelHorario;
import Model.ModelRuta;
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
public class DaoVuelo {

    CallableStatement cstmt = null;
    Connection conn = null;
    RelDatabase r = null;

    public DaoVuelo() {
        r = RelDatabase.getInstance(); //Hacerlo Singleton 
        conn = r.getConnection();
    }

    public void insertarVuelo(String ori, String des, int cantPa,int idRuta) {
        try {
            boolean resultado = false;
            String SQL = "{call insertarVuelo(?,?,?,?)}";
            cstmt = conn.prepareCall(SQL);

            cstmt.setString(1, ori);
            cstmt.setString(2, des);
            cstmt.setInt(3, cantPa);
            cstmt.setInt(4, idRuta);

            resultado = cstmt.execute();
            if (resultado == true) {
                throw new SQLException("No se realizo la inserción");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarVuelo(int idUser) {
        try {
            String SQL = "{call eliminarVuelo(?)}";
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

    public void modificarVuelos(int id, String ori, String des, int cantPa,int idRuta) {
        try {
            String SQL = "{call modificarVuelo(?,?,?,?,?)}";
            cstmt = conn.prepareCall(SQL);

      
            cstmt.setInt(1, id);
            cstmt.setString(2, ori);
            cstmt.setString(3, des);
            cstmt.setInt(4, cantPa);
            cstmt.setInt(5, idRuta);

            boolean resultado = cstmt.execute();
            if (resultado == true) {
                throw new SQLException("No se realizo la modificacion");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Vuelos buscarVuelos(int cod) {
        try {
            boolean resultado = false;
            String SQL = "{call buscarVuelo(?)}";
            cstmt = conn.prepareCall(SQL);
            cstmt.setInt(1, cod);
            resultado = cstmt.execute();
            if (resultado == false) {
                throw new SQLException("No se encontro Usuario");
            }
            ResultSet rs = cstmt.getResultSet();
            if (rs.next()) {
                return vuelos(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Vuelos> listarVuelos() {

        ArrayList<Vuelos> lista = new ArrayList<Vuelos>();

        try {
            String SQL = "{call listarVuelos()}";
            cstmt = conn.prepareCall(SQL);
            cstmt.execute();
            ResultSet rs = cstmt.getResultSet();
            while (rs.next()) {
                Vuelos v = vuelos(rs);
                lista.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    private Vuelos vuelos(ResultSet rs) {
        try {
            Vuelos p = new Vuelos();
            p.setIdVuelo(rs.getInt("idVuelos"));
            p.setOri(rs.getString("origen"));
            p.setDesti(rs.getString("destino"));
            p.setCantPasa(rs.getInt("cantPasajeros"));
            p.setRuta(rs.getInt("Ruta_idRuta"));
            p.setRuta_vuelo(ModelRuta.buscarRuta(p.getRuta()));
            return p;
        } catch (SQLException ex) {
            System.out.print("Error en la llamada de Usuario");
        }
        return null;
    }

}
