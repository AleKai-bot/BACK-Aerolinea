/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Logic.Ruta;
import Model.ModelRuta;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Alejandro-PC
 */
@Path("/rutas")
public class ControllerRutas {

    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void registrar(Ruta user) {
        try {
            ModelRuta.agregarRuta(user);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    public static Ruta buscarRuta(int cod) {
        return ModelRuta.buscarRuta(cod);
    }

    @GET
    @Path("/list")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Ruta> search() {
        List<Ruta> ps = ModelRuta.listarRutas();
        return ps;
    }

    @DELETE
    @Path("/delete/{idUser}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public void del(@PathParam("idUser") int idUser) {
        try {
            ModelRuta.eliminarRuta(idUser);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/byOriDes/{ori}/{des}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Ruta> byOriDes(@PathParam("ori") String ori, @PathParam("des") String des) {
        try {
           List<Ruta> px =  ModelRuta.byOriDes(ori, des);
           return px;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Path("/actualizar")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Ruta user) {
        try {
            System.out.println(user);
            ModelRuta.modificarRuta(user);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

}
