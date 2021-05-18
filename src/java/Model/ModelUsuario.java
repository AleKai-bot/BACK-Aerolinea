/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Data.DaoUsuario;
import Logic.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Alejandro-PC
 */
public class ModelUsuario {

    private static DaoUsuario data = new DaoUsuario();

    private ModelUsuario() {

    }

    public static void agregarUsuario(Usuario u) {
        data.agregarUsuario(u.getNombre(), "admin", u.getApellidos(), u.getCorreo(), u.getFechaNaci(), u.getDirec(), u.getTelefono(),u.getRol());
    }

    public static Usuario buscarUsuario(int cod) {
        return data.buscarUsuario(cod);
    }

    public static Usuario buscarUsuarioCorreo(String cod) {
        return data.buscarUsuarioCorreo(cod);
    }

    public static void eliminarUsuario(int idUser) {
        data.eliminarUsuario(idUser);
    }

    public static ArrayList<Usuario> listarUs() {
        return data.listarUsers();
    }

    public static void modificarUsuario(Usuario u) {
        data.modificarUsuario(u.getIdUser(), u.getNombre(),u.getPass(), u.getApellidos(), u.getCorreo(), u.getFechaNaci(), u.getDirec(), u.getTelefono(),u.getRol());
    }

}
