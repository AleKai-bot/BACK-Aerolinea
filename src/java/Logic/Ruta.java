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
public class Ruta {

    private int idRut;
    private String descripcion;
    private int duracion;
    private int idAvion;
    private int idHorario;
    private Avion avion;
    private Horario horario;
    private String ori;
    private String des;

    public Ruta() {
        this.idRut = 0;
        this.descripcion = "";
        this.duracion =0;
        this.idAvion = 0;
        this.idHorario = 0;
        this.avion = new Avion();
        this.horario = new Horario();
    }

    public Ruta(int idRut, String nomRuta, int duracion, int idA, int idH) {
        this.idRut = idRut;
        this.descripcion = nomRuta;
        this.duracion = duracion;
        this.idAvion = idA;
        this.idHorario = idH;
        this.avion = new Avion();
        this.horario = new Horario();
    }

    public String getOri() {
        return ori;
    }

    public void setOri(String ori) {
        this.ori = ori;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public int getIdRut() {
        return idRut;
    }

    public void setIdRut(int idRut) {
        this.idRut = idRut;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int uracion) {
        this.duracion = duracion;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    @Override
    public String toString() {
        return "Ruta{" + "idRut=" + idRut + ", descripcion=" + descripcion + ", duracion=" + duracion + ", idAvion=" + idAvion + ", idHorario=" + idHorario + ", avion=" + avion + ", horario=" + horario + ", ori=" + ori + ", des=" + des + '}';
    }

   

}
