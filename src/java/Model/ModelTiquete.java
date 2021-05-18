/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Data.DaoTiquete;
import Logic.Tiquete;
import java.util.ArrayList;

/**
 *
 * @author Alejandro-PC
 */
public class ModelTiquete {

    private static DaoTiquete data = new DaoTiquete();

    public static void agregarTiquete(Tiquete u) {
        data.agregarTiquete(u.getIdVuelo(), u.getPrecio(), u.getDescrip());
    }

    public static Tiquete buscarTiquete(int id) {
        return data.buscarTiquete(id);
    }
    public static Tiquete buscarTiqueteDeVuelo(int id) {
        return data.buscarTiqueteDeVuelo(id);
    }
    public static ArrayList<Tiquete> listarTiquetes() {
        return data.listarTiquetes();
    }

    public static void modificarTiquete(Tiquete ho) {
        data.modificarTiquete(ho.getIdTiquete(), ho.getIdVuelo(), ho.getPrecio(), ho.getDescrip(),ho.getAsientos());
    }

    public static void eliminarTiquete(int ho) {
        data.eliminarTiquete(ho);
    }

}
