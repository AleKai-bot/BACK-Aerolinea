/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Data.DaoHorario;
import Logic.Horario;
import java.util.ArrayList;

/**
 *
 * @author Alejandro-PC
 */
public class ModelHorario {

    private static DaoHorario data = new DaoHorario();

    public static void agregarHorario(Horario ho) {
        data.agregarHorario(ho.getIdHora(), ho.getFechaLlega(), ho.getFechaSali(),ho.getHora_salida());
    }

    public static Horario buscarHorario(int cod) {
        return data.buscarHorario(cod);
    }

    public static ArrayList<Horario> listarHorarios() {
        return data.listarHorarios();
    }

    public static void modificarHorario(Horario ho) {
        data.modificarHorario(ho.getIdHora(), ho.getFechaSali(), ho.getFechaLlega(),ho.getHora_salida());
    }

    public static void eliminarHorario(int ho) {
        data.eliminarHorario(ho);
    }
 

}
