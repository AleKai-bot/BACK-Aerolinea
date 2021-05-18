/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.ArrayList;

/**
 *
 * @author Alejandro-PC
 */
public class Usuario {

    private int idUser;
    private String nombre;
    private String pass;
    private String apellidos;
    private String correo;
    private String fechaNaci;
    private String direc;
    private String telefono;
    private String rol;
    private ArrayList<Vuelos> vuelos;
    
// Sin este constructor los objetos json no funcionan
    public Usuario() {
        this.idUser = 0;
        this.nombre = "";
        this.pass = "";
        this.apellidos = "";
        this.correo = "";
        this.fechaNaci = "";
        this.direc = "";
        this.telefono = "";
        this.rol="cliente";
        this.vuelos = new ArrayList();
    }

    public Usuario(String nombre, String pass, String apellidos, String correo, String fechaNaci, String direc, String telefono, ArrayList<Vuelos> vuelos) {
        this.nombre = nombre;
        this.pass = pass;
        this.apellidos = apellidos;
        this.correo = correo;
        this.fechaNaci = fechaNaci;
        this.direc = direc;
        this.telefono = telefono;
        this.vuelos = vuelos;
        this.rol="cliente";
    }

    public Usuario(String nombre, String pass, String apellidos, String correo, String fechaNaci, String direc, String telefono) {
        this.nombre = nombre;
        this.pass = pass;
        this.apellidos = apellidos;
        this.correo = correo;
        this.fechaNaci = fechaNaci;
        this.direc = direc;
        this.telefono = telefono;
        this.rol="cliente";
    }

    public ArrayList<Vuelos> getVuelos() {
        return vuelos;
    }

    public void setVuelos(ArrayList<Vuelos> vuelos) {
        this.vuelos = vuelos;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFechaNaci() {
        return fechaNaci;
    }

    public void setFechaNaci(String fechaNaci) {
        this.fechaNaci = fechaNaci;
    }

    public String getDirec() {
        return direc;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUser=" + idUser + ", nombre=" + nombre + ", pass=" + pass + ", apellidos=" + apellidos + ", correo=" + correo + ", fechaNaci=" + fechaNaci + ", direc=" + direc + ", telefono=" + telefono + ", rol=" + rol + ", vuelos=" + vuelos + '}';
    }

}
