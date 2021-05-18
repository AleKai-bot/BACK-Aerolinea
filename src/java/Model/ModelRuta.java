/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Data.DaoRuta;
import Logic.Ruta;
import java.util.ArrayList;

/**
 *
 * @author Alejandro-PC
 */
public class ModelRuta {

    private static DaoRuta data = new DaoRuta();

    public static void agregarRuta(Ruta rut) {
        data.insertarRuta(rut.getIdRut(), rut.getDescripcion(), rut.getDuracion(), rut.getIdAvion(), rut.getIdHorario(),rut.getOri(), rut.getDes());
    }

    public static Ruta buscarRuta(int cod) {
        return data.buscarRuta(cod);
    }

    public static ArrayList<Ruta> listarRutas() {
        return data.listarRutas();
    }
    

    public static void modificarRuta(Ruta rut) {
        data.modificarRuta(rut.getIdRut(), rut.getDescripcion(), rut.getDuracion(), rut.getIdAvion(), rut.getIdHorario(),rut.getOri(), rut.getDes());
    }

    public static void eliminarRuta(int idUser) {

        data.eliminarRuta(idUser);
    }

    public static ArrayList<Ruta> byOriDes(String x, String y) {

        return data.buscarRutaOrigenDestino(x, y);
    }
}
