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
public class Horario {

    private int idHora;
    private String fechaSali;
    private String fechaLlega;
    private String hora_salida;

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public int getIdHora() {
        return idHora;
    }

    public void setIdHora(int idHora) {
        this.idHora = idHora;
    }

    public String getFechaSali() {
        return fechaSali;
    }

    public void setFechaSali(String fechaSali) {
        this.fechaSali = fechaSali;
    }

    public String getFechaLlega() {
        return fechaLlega;
    }

    public void setFechaLlega(String fechaLlega) {
        this.fechaLlega = fechaLlega;
    }

    @Override
    public String toString() {
        return "Horario{" + "idHora=" + idHora + ", fechaSali=" + fechaSali + ", fechaLlega=" + fechaLlega + ", hora_salida=" + hora_salida + '}';
    }

    

}
