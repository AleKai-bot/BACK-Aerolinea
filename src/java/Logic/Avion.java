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
public class Avion {

    private int idAvion;
    private int anno;
    private String model;
    private String marca;
    
    private int cantFila;
    private int cantAsci;
    
    //private ArrayList<Asieto> asiento;
    //50;
    //private int asientosRes;
    
    
    // Sin este constructor los objetos json no funcionan
    public Avion() {
        this.idAvion = 0;
        this.anno = 0;
        this.model = "";
        this.marca = "";
        this.cantFila = 0;
        this.cantAsci = 0;
    }

    public Avion(int idAvion, int anno, String model, String marca, int cantFila, int cantAsci) {
        this.idAvion = idAvion;
        this.anno = anno;
        this.model = model;
        this.marca = marca;
        this.cantFila = cantFila;
        this.cantAsci = cantAsci;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCantFila() {
        return cantFila;
    }

    public void setCantFila(int cantFila) {
        this.cantFila = cantFila;
    }

    public int getCantAsci() {
        return cantAsci;
    }

    public void setCantAsci(int cantAsci) {
        this.cantAsci = cantAsci;
    }
    
    
    
}
