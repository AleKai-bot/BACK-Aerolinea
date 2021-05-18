/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Data.DaoAvion;
import Logic.Avion;
import java.util.ArrayList;

/**
 *
 * @author Alejandro-PC
 */
public class ModelAvion {

    private static DaoAvion data = new DaoAvion();

    public static void agregarAvion(Avion u) {
        data.agregarAvion(u.getIdAvion(), u.getAnno(), u.getModel(), u.getMarca(), u.getCantFila(), u.getCantAsci());
    }

    public static Avion buscarAvion(int cod) {
        return data.buscarAvion(cod);
    }

    public static void eliminarAvion(int cod) {
        data.eliminarAvion(cod);
    }

    public static ArrayList<Avion> listarAviones() {
        return data.listarAviones();
    }

    public static void modificarAvion(Avion u) {
        data.modificarAvion(u.getIdAvion(), u.getAnno(), u.getModel(), u.getMarca(), u.getCantFila(), u.getCantAsci());
    }

}
