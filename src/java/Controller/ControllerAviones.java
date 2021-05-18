package Controller;

import Logic.Avion;
import Model.ModelAvion;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.POST;

/**
 *
 * @author orell
 */
@Path("/aviones")
public class ControllerAviones {

    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void registrar(Avion user) {
        try {
            ModelAvion.agregarAvion(user);

        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/edit/{idUser}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Avion get(@PathParam("idUser") int idUsuario) {
        Avion u = new Avion();
        try {
            u = ModelAvion.buscarAvion(idUsuario);
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
           ModelAvion.eliminarAvion(idUser);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Path("/actualizar")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Avion user) {
        try {
            System.out.println(user);
            ModelAvion.modificarAvion(user);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/list")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Avion> search() {
        List<Avion> ps = ModelAvion.listarAviones();
        return ps;
    }

}
