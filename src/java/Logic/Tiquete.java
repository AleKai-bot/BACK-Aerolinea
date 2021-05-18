/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author Alejandro-PC
 */
public class Tiquete {

    private int idTiquete;
    private int idVuelo;// 
    private float precio;
    private String descrip;
    private String asientos;
    private int disponible;

   
    public Tiquete() {
        idTiquete = 0;
        idVuelo = 0;
        precio = 0;
        descrip = "";
        asientos= "";
        disponible = 0;
    }

    public int getIdTiquete() {
        return idTiquete;
    }

    public void setIdTiquete(int idTiquete) {
        this.idTiquete = idTiquete;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getAsientos() {
        return asientos;
    }

    public void setAsientos(String asientos) {
        this.asientos = asientos;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Tiquete{" + "idTiquete=" + idTiquete + ", idVuelo=" + idVuelo + ", precio=" + precio + ", descrip=" + descrip + ", asientos=" + asientos + ", disponible=" + disponible + '}';
    }


}
