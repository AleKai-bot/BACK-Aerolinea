/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Logic.Ruta;
import Model.ModelAvion;
import Model.ModelHorario;
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
public class DaoRuta {

    CallableStatement cstmt = null;
    Connection conn = null;
    RelDatabase r = null;

    public DaoRuta() {
        r = RelDatabase.getInstance(); //Hacerlo Singleton 
        conn = r.getConnection();
    }

    public void insertarRuta(int id, String descrip, int duracion, int idA, int idH, String ori, String des) {
        try {
            boolean resultado = false;
            String SQL = "{call insertarRuta(?,?,?,?,?,?,?)}";
            cstmt = conn.prepareCall(SQL);

            cstmt.setInt(1, id);
            cstmt.setString(2, descrip);
            cstmt.setInt(3, duracion);
            cstmt.setInt(4, idA);
            cstmt.setInt(5, idH);
            cstmt.setString(6, ori);
            cstmt.setString(7, des);
            resultado = cstmt.execute();
            if (resultado == true) {
                throw new SQLException("No se realizo la inserción");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarRuta(int id, String ruta, int duracion, int idA, int idH, String ori, String des) {
        try {
            String SQL = "{call modificarRuta(?,?,?,?,?,?,?)}";
            cstmt = conn.prepareCall(SQL);

            cstmt.setInt(1, id);
            cstmt.setString(2, ruta);
            cstmt.setInt(3, duracion);
            cstmt.setInt(4, idA);
            cstmt.setInt(5, idH);
            cstmt.setString(6, ori);
            cstmt.setString(7, des);
            boolean resultado = cstmt.execute();
            if (resultado == true) {
                throw new SQLException("No se realizo la modificacion");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarRuta(int idUser) {
        try {
            String SQL = "{call eliminarRuta(?)}";
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

    public Ruta buscarRuta(int cod) {
        try {
            boolean resultado = false;
            String SQL = "{call buscarRuta(?)}";
            cstmt = conn.prepareCall(SQL);

            cstmt.setInt(1, cod);
            resultado = cstmt.execute();
            if (resultado == false) {
                throw new SQLException("No se encontro Ruta");
            }

            ResultSet rs = cstmt.getResultSet();
            if (rs.next()) {
                return ruta(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Ruta> listarRutas() {

        ArrayList<Ruta> lista = new ArrayList<>();

        try {
            String SQL = "{call listarRutas()}";
            cstmt = conn.prepareCall(SQL);
            cstmt.execute();
            ResultSet rs = cstmt.getResultSet();
            while (rs.next()) {
                lista.add(ruta(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    private Ruta ruta(ResultSet rs) {
        try {
            Ruta p = new Ruta();

            p.setIdRut(rs.getInt("idRuta"));
            p.setOri(rs.getString("ori"));
            p.setDes(rs.getString("des"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setDuracion(rs.getInt("duracion"));
            p.setIdAvion(rs.getInt("avion_idAvion"));
            p.setIdHorario(rs.getInt("horario_idHorario"));
            p.setAvion(ModelAvion.buscarAvion(p.getIdAvion()));
            p.setHorario(ModelHorario.buscarHorario(p.getIdHorario()));
            return p;
        } catch (SQLException ex) {
            System.out.print("Error en la llamada de Ruta");
            return null;
        }
    }

    public ArrayList<Ruta> buscarRutaOrigenDestino(String a, String b) {
        ArrayList<Ruta> lista = new ArrayList<>();
        boolean resultado = false;
        try {
            String SQL = "{call buscarRutaOrigenDestino(?, ?)}";

            cstmt = conn.prepareCall(SQL);
            cstmt.setString(1, a);
            cstmt.setString(2, b);
            resultado = cstmt.execute();
            if (resultado == false) {
                throw new SQLException("No se encontro Ruta");
            }
            ResultSet rs = cstmt.getResultSet();

            while (rs.next()) {
                lista.add(ruta(rs));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
}
