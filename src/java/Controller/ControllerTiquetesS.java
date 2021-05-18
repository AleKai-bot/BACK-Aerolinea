/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Logic.Tiquete;
import Logic.Vuelos;
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
@ServerEndpoint(value = "/user")
public class ControllerTiquetesS {

    private static final Map<String, Session> connections = new HashMap<String, Session>();
    private final Gson jsonProcessor = new Gson();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        float cant = (float) 0;
        session.getUserProperties().put("total", cant);
        session.getUserProperties().put("cantidad", 0);
        session.getUserProperties().put("compra", new Tiquete());
        session.getUserProperties().put("vuelo", 0);
        connections.put(session.getId(), session);
        System.out.println("Sesion " + session.getId() + " ha iniciado");
    }

    @OnMessage
    public void onMessage(String arrMsg, Session session) throws IOException {

        ArrayList<String> array = jsonProcessor.fromJson(arrMsg, ArrayList.class);
        String path = array.get(0);
        switch (path) {
            case "conexion":
                this.conexion(array, session);
                break;
            case "comprar":

                break;
            case "addToCar":
                this.addToCar(array, session);
                break;
            case "remove":
                this.removeTicket(array, session);
                break;
            case "disconect":
                this.desconectar(array, session);
                break;
        }
    }

    public void conexion(ArrayList msgrray, Session session) throws IOException {
        String idTk = (String) msgrray.get(1);//idVuelo

        Tiquete tk = ModelTiquete.buscarTiqueteDeVuelo(Integer.parseInt(idTk));
        session.getUserProperties().remove("compra");
        session.getUserProperties().put("compra", tk);
        session.getUserProperties().put("vuelo", tk.getIdVuelo());
        this.sendDataAllUsers(session);

        /*String idTk = (String) msgrray.get(1);//idTiquete
        Tiquete tk = ModelTiquete.buscarTiquete(Integer.parseInt(idTk));
        session.getUserProperties().remove("compra");
        session.getUserProperties().put("compra", tk);
        session.getUserProperties().put("vuelo",tk.getIdVuelo());
        this.sendDataAllUsers(session);*/
    }

    public void desconectar(ArrayList msgrray, Session session) throws IOException {
        this.acutalizarSalida(session.getId());
        ArrayList list = new ArrayList<>();
        list.add("Desconectando");//0
        this.onClose(session);
        System.out.println("Onclose");
        //session.getBasicRemote().sendText(jsonProcessor.toJson(list));
    }

    public void sendConexion(ArrayList msgrray, Session session, int disp) throws IOException {

        ArrayList list = new ArrayList<>();
        list.add("conexion");//0
        list.add(msgrray);//1
        list.add(disp);//2
        session.getBasicRemote().sendText(jsonProcessor.toJson(list));

    }

    public void addToCar(ArrayList msgrray, Session session) throws IOException {
        String idTk = (String) msgrray.get(1);//idTik
        String cant = (String) msgrray.get(2);//cant
        int catidad = Integer.parseInt(cant);
        Tiquete tk = ModelTiquete.buscarTiquete(Integer.parseInt(idTk));

        int disponible = this.acutalizarDisponible(tk.getIdVuelo(), catidad);
        tk.setDisponible(disponible);
        ArrayList<Tiquete> listaCompra = new ArrayList();
        listaCompra.add(tk);
        session.getUserProperties().put("listaCompra", listaCompra);
        session.getUserProperties().remove("compra");
        session.getUserProperties().put("compra", tk);

        float precioXtiquete = getTotalCompra(session, catidad);
        float totalCompra = (float) session.getUserProperties().remove("total");
        int canttemp = (Integer) session.getUserProperties().remove("cantidad");

        session.getUserProperties().put("cantidad", (catidad + canttemp));
        session.getUserProperties().put("vuelo",tk.getIdVuelo());
        session.getUserProperties().put("total", (totalCompra + precioXtiquete));

        //this.sendVuelosDisponibles(session);
        this.sendDataAllUsers(session);
    }

    public void realizarCompra(ArrayList msgrray, Session session) throws IOException {

    }

    public void removeTicket(ArrayList msgrray, Session session) throws IOException {
        String selected = (String) msgrray.get(1);//id
        String cant = (String) msgrray.get(2);

        int idTk = Integer.parseInt(selected);
        Tiquete tk = ModelTiquete.buscarTiquete(idTk);
        Vuelos v = ModelVuelo.buscarVuelos(tk.getIdVuelo());

        this.acutalizarDisponible(v.getIdVuelo(), -Integer.parseInt(cant));

        this.clearSession(session);
        this.sendDataAllUsers(session);
    }

    public void sendDataAllUsers(Session session) throws IOException {

        for (Session s : connections.values()) {
            ArrayList list = new ArrayList<>();
            ArrayList compra = new ArrayList<>();
            compra.add(s.getUserProperties().get("compra"));
            list.add(compra);//0 tiquete compra
            list.add(s.getUserProperties().get("total"));//1
            list.add(s.getUserProperties().get("cantidad"));//2
            list.add(ModelVuelo.buscarVuelos((int) s.getUserProperties().get("vuelo")).getCantPasa());//3 disponible
            s.getBasicRemote().sendText(jsonProcessor.toJson(list));
        }
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

    @OnClose
    public void onClose(Session session) throws IOException { //este metodo siempre se crea
        connections.remove(session.getId());
    }

    @OnError
    public void onError(Throwable e) {
        System.out.println(e.getMessage());
    }
}
