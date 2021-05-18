/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Data.DaoVuelo;
import Logic.Vuelos;
import java.util.ArrayList;

/**
 *
 * @author Alejandro-PC
 */
public class ModelVuelo {

    private static DaoVuelo data = new DaoVuelo();

    public static void agregarVuelo(Vuelos ho) {

        data.insertarVuelo(ho.getOri(), ho.getDesti(), ho.getCantPasa(),ho.getRuta());

    }
    public static Vuelos buscarVuelos(int cod) {
        return data.buscarVuelos(cod);
    }

    public static ArrayList<Vuelos> listarVuelos() {
        return data.listarVuelos();
    }

    public static void modificarVuelos(Vuelos ho) {
        data.modificarVuelos(ho.getIdVuelo(), ho.getOri(), ho.getDesti(), ho.getCantPasa(), ho.getRuta());
    }

    public static void eliminarVuelo(int ho) {
        data.eliminarVuelo(ho);
    }

}
