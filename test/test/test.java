/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Logic.Bitacora;
import Logic.Ruta;
import Logic.Tiquete;
import Logic.Vuelos;
import Model.ModelBitacora;
import Model.ModelRuta;
import Model.ModelTiquete;
import Model.ModelVuelo;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author orell
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          Gson json = new Gson();
//          ArrayList<Vuelos> vv = ModelVuelo.listarVuelos();
//          System.out.println(vv);
//          ArrayList<Ruta> rr= ModelRuta.listarRutas();
//          System.out.println(rr);
//          //String vuelos = json.toJson(vv, ArrayList.class);
          //System.out.println(vuelos);
      
          
           Tiquete tk = ModelTiquete.buscarTiquete(6);
           System.out.println(tk);
           
    }
    
}
