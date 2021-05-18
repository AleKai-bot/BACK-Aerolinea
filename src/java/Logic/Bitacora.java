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
public class Bitacora {

    private int idBitacora;
    private int idUser;
    private String correo;
    private int idVuelo;
    private String asientos;
    private Date fecha;
    private float total;
    private Vuelos vuelo;

    public Bitacora() {
        idBitacora = 0;
        idUser = 0;
        correo = "";
        idVuelo = 0;
        asientos = "";
        fecha = new Date();
        total= (float) 0.0;
        this.vuelo= new Vuelos();
    }

    public int getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(int idBitacora) {
        this.idBitacora = idBitacora;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getAsientos() {
        return asientos;
    }

    public void setAsientos(String asientos) {
        this.asientos = asientos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Vuelos getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelos vuelo) {
        this.vuelo = vuelo;
    }

    @Override
    public String toString() {
        return "Bitacora{" + "idBitacora=" + idBitacora + ", idUser=" + idUser + ", correo=" + correo + ", idVuelo=" + idVuelo + ", asientos=" + asientos + ", fecha=" + fecha + ", total=" + total + ", vuelo=" + vuelo + '}';
    }

}
