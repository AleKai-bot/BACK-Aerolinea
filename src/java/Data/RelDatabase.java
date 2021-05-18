/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alejandro-PC
 */
public class RelDatabase {

    public static Connection cnx = null;
    public static RelDatabase data = null;

    public static RelDatabase getInstance() {
        if (data == null) {
            data = new RelDatabase();
        }
        return data;
    }

    private RelDatabase() {
        cnx = this.setConnection();
    }

    public Connection setConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Aerolinea?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "root");
            System.out.println("****************Se conectó Base de Datos******************** ");
            return c;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void closeConnection() {
        try {
            if (cnx != null) {
                cnx.close();
                cnx = null;
                System.out.println("Se cerró Base de Datos");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return cnx;
    }

}
