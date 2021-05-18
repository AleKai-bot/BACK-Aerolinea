/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Date;

/**
 *
 * @author Alejandro-PC
 */
public class Vuelos {

    private int idVuelo;
    private String ori;
    private String desti;
    private int cantPasa;
    private int ruta;
    private Ruta ruta_vuelo;

    public Vuelos() {
        this.idVuelo = 0;
        this.ori = "";
        this.desti = "";
        this.cantPasa = 0;
        this.ruta = 0;
        this.ruta_vuelo=new Ruta();
       
    }

    public Vuelos(int idVuelo, String ori, String desti, int cantPasa, int horario, int ruta) {
        this.idVuelo = idVuelo;
        this.ori = ori;
        this.desti = desti;
        this.cantPasa = cantPasa;
        this.ruta = ruta;
        this.ruta_vuelo=new Ruta();
    }

    public Vuelos(String ori, String desti, int cantPasa, int horario, int ruta) {
        this.ori = ori;
        this.desti = desti;
        this.cantPasa = cantPasa;
        this.ruta = ruta;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getOri() {
        return ori;
    }

    public void setOri(String ori) {
        this.ori = ori;
    }

    public String getDesti() {
        return desti;
    }

    public void setDesti(String desti) {
        this.desti = desti;
    }

    public int getCantPasa() {
        return cantPasa;
    }

    public void setCantPasa(int cantPasa) {
        this.cantPasa = cantPasa;
    }

    public int getRuta() {
        return ruta;
    }

    public void setRuta(int ruta) {
        this.ruta = ruta;
    }

    public Ruta getRuta_vuelo() {
        return ruta_vuelo;
    }

    public void setRuta_vuelo(Ruta ruta_vuelo) {
        this.ruta_vuelo = ruta_vuelo;
    }

    @Override
    public String toString() {
        return "Vuelos{" + "idVuelo=" + idVuelo + ", ori=" + ori + ", desti=" + desti + ", cantPasa=" + cantPasa + ", ruta=" + ruta + ", ruta_vuelo=" + ruta_vuelo + '}';
    }

    
  

}
