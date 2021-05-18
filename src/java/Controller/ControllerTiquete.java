/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Logic.Tiquete;
import Model.ModelTiquete;
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
@Path("/ticket")
public class ControllerTiquete {

    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void registrar(Tiquete user) {
        try {
            ModelTiquete.agregarTiquete(user);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    @GET
    @Path("/edit/{idUser}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Tiquete get(@PathParam("idUser") int id) {
        Tiquete u = new Tiquete();
        try {
            u = ModelTiquete.buscarTiquete(id);
            return u;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("/delete/{idUser}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public void del(@PathParam("idUser") int idUser) {
        try {

            ModelTiquete.eliminarTiquete(idUser);

        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/list")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Tiquete> search() {
        List<Tiquete> ps = ModelTiquete.listarTiquetes();
        return ps;
    }

    @PUT
    @Path("/actualizar")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Tiquete user) {
        try {
            System.out.println(user);
            ModelTiquete.modificarTiquete(user);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

}
