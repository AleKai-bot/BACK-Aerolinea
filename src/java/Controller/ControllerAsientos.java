/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Logic.Bitacora;
import Logic.Tiquete;
import Logic.Vuelos;
import Model.ModelBitacora;
import Model.ModelTiquete;
import Model.ModelVuelo;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author orell
 */
@ServerEndpoint(value = "/asientos")
public class ControllerAsientos {

    private static final Map<String, Session> connections = new HashMap<String, Session>();
    private final Gson jsonProcessor = new Gson();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        session.getUserProperties().put("idTk", 0);
        session.getUserProperties().put("vuelo", 0);
        session.getUserProperties().put("asientos", new ArrayList());
        connections.put(session.getId(), session);
        System.out.println("Sesion " + session.getId() + " ha iniciado");
    }

    @OnMessage
    public void onMessage(String arrMsg, Session session) throws IOException {

        ArrayList<String> array = jsonProcessor.fromJson(arrMsg, ArrayList.class);
        String path = array.get(0);
        String bit = array.get(1);
        
        switch (path) {
            case "conexion":
                this.conexion(array, session);
                break;
            case "confirmado":
                String cant = array.get(2);
                this.realizarCompra(bit, session,cant);
                break;
            case "ocupar":
                this.seleccionAsiento(array, session);
                break;
            case "liberar":
                this.removeAsiento(array, session);
                break;
            case "reallizarCompra":

                break;
            case "disconect":
                this.desconectar(array, session);
                break;
        }
    }

    public void conexion(ArrayList msgrray, Session session) throws IOException {

        String idV = (String) msgrray.get(1);//idVuelo
        Tiquete tk = ModelTiquete.buscarTiqueteDeVuelo(Integer.parseInt(idV));//Tk de vuelo elegido
        session.getUserProperties().remove("idTk");
        session.getUserProperties().put("idTk", tk.getIdTiquete());
        session.getUserProperties().remove("vuelo");
        session.getUserProperties().put("vuelo", tk.getIdVuelo());

        this.sendDataAllUsers("conexion", session);
    }

    public boolean existe(String[] arr, String idA) {
        for (String a : arr) {
            return a.equals(idA);
        }
        return false;
    }

    public void seleccionAsiento(ArrayList msgrray, Session session) throws IOException {
        String idAs = (String) msgrray.get(1);//idAsiento
        String idV = (String) msgrray.get(2);//idVuelo
        String arrAsientos = (String) msgrray.get(3);//arr asientos

        String[] arrA = arrAsientos.split(",");
        ArrayList as = new ArrayList();
        for (String a : arrA) {
            as.add(a);
            System.out.println("arrAsientos  : " + a);
        }
        session.getUserProperties().remove("asientos");
        session.getUserProperties().put("asientos", as);

        Tiquete tk = ModelTiquete.buscarTiqueteDeVuelo(Integer.parseInt(idV));
        String[] sss = tk.getAsientos().split(",");
        String newAsientos = "";
        for (String a : sss) {
            newAsientos += a + ",";
        }
        tk.setAsientos(newAsientos + idAs);
        ModelTiquete.modificarTiquete(tk);
        session.getUserProperties().put("idTk", tk.getIdTiquete());
        
        this.sendDataAllUsers("ocupar", session);
    }

    public void removeAsiento(ArrayList msgrray, Session session) throws IOException {
        String idAs = (String) msgrray.get(1);//idAsiento
        String idV = (String) msgrray.get(2);//idVuelo
        String arrAsientos = (String) msgrray.get(3);//arr asientos
        Tiquete tk = ModelTiquete.buscarTiqueteDeVuelo(Integer.parseInt(idV));

        String[] sss = tk.getAsientos().split(",");
        String newAsientos = "";
        System.out.println("sin modificar  : " + tk.getAsientos());
        for (String a : sss) {//elimino el asiento
            if (!a.equals(idAs)) {
                newAsientos += a + ",";
            }
        }
        tk.setAsientos(newAsientos);
        System.out.println("modificado  : " + newAsientos);
        ModelTiquete.modificarTiquete(tk);
        session.getUserProperties().put("idTk", tk.getIdTiquete());

        String[] arrA = arrAsientos.split(",");
        ArrayList as = new ArrayList();
        for (String a : arrA) {
            as.add(a);
        }
        session.getUserProperties().remove("asientos");
        session.getUserProperties().put("asientos", as);

        this.sendDataAllUsers("liberar", session);
    }

    public void sendDataAllUsers(String method, Session session) throws IOException {

        for (Session s : connections.values()) {
            ArrayList list = new ArrayList<>();
            Tiquete t = (Tiquete) ModelTiquete.buscarTiqueteDeVuelo((int)s.getUserProperties().get("idTk"));
            list.add(method);//0
            list.add(t.getAsientos());//1 asientos
            list.add(s.getUserProperties().get("asientos"));//2 asientos seleccionados

            s.getBasicRemote().sendText(jsonProcessor.toJson(list));
        }
    }

    public void realizarCompra(String msgrray, Session session, String cant) throws IOException {
        Bitacora registro = jsonProcessor.fromJson(msgrray, Bitacora.class);
        System.out.println(registro+"cant : "+ cant);
        ModelBitacora.agregarBitacora(registro);
        Tiquete tk = ModelTiquete.buscarTiqueteDeVuelo(registro.getIdVuelo());//Tk de vuelo elegido
        tk.setAsientos(tk.getAsientos() + "," + registro.getAsientos());
        acutalizarDisponible((int)session.getUserProperties().get("vuelo"),Integer.parseInt(cant));
        ModelTiquete.modificarTiquete(tk);
    }

    public int removeAll(Session session) throws IOException {
        Tiquete tk = (Tiquete) session.getUserProperties().get("compra");
        int idV = tk.getIdVuelo();
        Vuelos v = ModelVuelo.buscarVuelos(idV);
        this.acutalizarDisponible(v.getIdVuelo(), -(int) session.getUserProperties().get("cantidad"));
        this.clearSession(session);
        return idV;
    }

    public int acutalizarDisponible(int idVuelo, int cant) throws IOException {

        Vuelos edited = ModelVuelo.buscarVuelos(idVuelo);
        edited.setCantPasa((edited.getCantPasa() - cant));
        ModelVuelo.modificarVuelos(edited);
        int disp = edited.getCantPasa();
        return disp;
    }

    public void acutalizarSalida(String idS) throws IOException {
        Session session = connections.get(idS);
        Tiquete tk = (Tiquete) session.getUserProperties().get("compra");
        int idV = tk.getIdVuelo();
        Vuelos v = ModelVuelo.buscarVuelos(idV);
        this.acutalizarDisponible(v.getIdVuelo(), -(int) session.getUserProperties().get("cantidad"));
        System.out.println("acutalizarSalida " + session.getId() + tk.toString() + v.toString());
        connections.remove(idS);
    }

    public void sendVuelosDisponibles(Session session) throws IOException {
        System.out.println("cant Conx : " + connections.size());
        for (Session s : connections.values()) {
            System.out.println("Id conexion:  " + s.getId());
        }
        // this.sendDataAllUsers(session);
    }

    public void clearSession(Session session) throws IOException {
        float total = (float) 0;
        session.getUserProperties().remove("listaCompra");
        session.getUserProperties().remove("cantidad");
        session.getUserProperties().remove("total");

        session.getUserProperties().put("cantidad", 0);
        session.getUserProperties().put("total", total);
    }

    public float getTotalCompra(Session session, int cant) throws IOException {
        float total = 0;
        for (Tiquete t : (ArrayList<Tiquete>) session.getUserProperties().get("listaCompra")) {
            total += t.getPrecio();
        }
        return total * cant;
    }

    public void desconectar(ArrayList msgrray, Session session) throws IOException {
        this.acutalizarSalida(session.getId());
        ArrayList list = new ArrayList<>();
        list.add("Desconectando");//0
        this.onClose(session);
        System.out.println("Onclose");
        //session.getBasicRemote().sendText(jsonProcessor.toJson(list));
    }

    @OnClose
    public void onClose(Session session) throws IOException { //este metodo siempre se crea
        connections.remove(session.getId());
    }

    @OnError
    public void onError(Throwable e) {
        System.out.println(e.getMessage());
    }
}
