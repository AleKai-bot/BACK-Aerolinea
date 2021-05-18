package Controller;

import Logic.Horario;
import Logic.Ruta;
import Logic.Vuelos;
import Model.ModelHorario;
import Model.ModelRuta;
import Model.ModelVuelo;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;

/**
 *
 * @author orell
 */
@Path("/vuelos")
public class ControllerVuelos {

    //ControllerUsuario controller = new ControllerUsuario();
    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void registrar(Vuelos user) {
        try {
            ModelVuelo.agregarVuelo(user);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/edit/{idUser}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Vuelos get(@PathParam("idUser") int idUsuario) {
        Vuelos u = new Vuelos();
        try {
            u = ModelVuelo.buscarVuelos(idUsuario);
            return u;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("/delete/{idVuelo}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public void del(@PathParam("idVuelo") int idUser) {
        try {
            ModelVuelo.eliminarVuelo(idUser);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Path("/actualizar")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Vuelos user) {
        try {
            ModelVuelo.modificarVuelos(user);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/list")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Vuelos> search() {
        List<Vuelos> ps = ModelVuelo.listarVuelos();
        return ps;
    }

    @GET
    @Path("/horario/{idRuta}")
    @Produces({MediaType.APPLICATION_JSON})
    public Horario searcHorario(@PathParam("idRuta") int idRuta) {
        Ruta r = ModelRuta.buscarRuta(idRuta);
        Horario ps = ModelHorario.buscarHorario(r.getIdHorario());
        return ps;
    }
    @GET
    @Path("/ruta/{idRuta}")
    @Produces({MediaType.APPLICATION_JSON})
    public Ruta searchRuta(@PathParam("idRuta") int idRuta) {
        Ruta ps = ModelRuta.buscarRuta(idRuta);
        return ps;
    }
}
