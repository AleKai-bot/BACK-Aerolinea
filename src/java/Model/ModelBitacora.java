/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Data.DaoBitacora;
import Logic.Bitacora;
import java.util.ArrayList;

/**
 *
 * @author Alejandro-PC
 */
public class ModelBitacora {

    private static DaoBitacora data = new DaoBitacora();

    public static void agregarBitacora(Bitacora u) {
        data.agregarRegistro(u.getIdUser(),u.getCorreo(), u.getIdVuelo(),u.getAsientos(),u.getFecha(),u.getTotal());
    }

    public static ArrayList<Bitacora> listarBitacora() {
        return data.listarRegistros();
    }

    public static ArrayList<Bitacora> listarBitacoraUser(int u) {
        return data.listarBitacoraPorUser(u);
    }

}
