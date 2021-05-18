/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Logic.Horario;
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
public class DaoHorario {

    CallableStatement cstmt = null;
    Connection conn = null;
    RelDatabase r = null;

    public DaoHorario() {
        r = RelDatabase.getInstance(); //Hacerlo Singleton 
        conn = r.getConnection();
    }

    public void agregarHorario(int id, String feS, String feL, String hora_salida) {
        try {
            boolean resultado = false;
            String SQL = "{call insertarHorario(?,?,?,?)}";
            cstmt = conn.prepareCall(SQL);

            cstmt.setInt(1, id);
            cstmt.setString(2, feS);
            cstmt.setString(3, feL);
            cstmt.setString(4, hora_salida);
            resultado = cstmt.execute();
            if (resultado == true) {
                throw new SQLException("No se realizo la inserción");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarHorario(int idUser) {
        try {
            String SQL = "{call eliminarHorario(?)}";
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

    public void modificarHorario(int id, String feS, String feL, String hora_salida) {
        try {
            String SQL = "{call modificarHorario(?,?,?,?)}";
            cstmt = conn.prepareCall(SQL);

            cstmt.setInt(1, id);
            cstmt.setString(2, feS);
            cstmt.setString(3, feL);
            cstmt.setString(4, hora_salida);
            boolean resultado = cstmt.execute();
            if (resultado == true) {
                throw new SQLException("No se realizo la modificacion");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Horario> listarHorarios() {
        ArrayList<Horario> lista = new ArrayList<Horario>();
        try {
            String SQL = "{call listarHorarios()}";
            cstmt = conn.prepareCall(SQL);
            cstmt.execute();
            ResultSet rs = cstmt.getResultSet();
            while (rs.next()) {
                lista.add(horario(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public Horario buscarHorario(int cod) {
        try {
            boolean resultado = false;
            String SQL = "{call buscarHorario(?)}";
            cstmt = conn.prepareCall(SQL);
            cstmt.setInt(1, cod);
            resultado = cstmt.execute();
            if (resultado == false) {
                throw new SQLException("No se encontro Usuario");
            }
            ResultSet rs = cstmt.getResultSet();
            if (rs.next()) {
                return horario(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Horario horario(ResultSet rs) {
        try {
            Horario p = new Horario();
            p.setIdHora(rs.getInt("idHorario"));
            p.setFechaLlega(rs.getString("fechaSalida"));
            p.setFechaSali(rs.getString("fechaLlegada"));
            p.setHora_salida(rs.getString("hora_salida"));
            return p;
        } catch (SQLException ex) {
            System.out.print("Error en la llamada de Usuario");
        }
        return null;
    }

}
