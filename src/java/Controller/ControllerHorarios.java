package Controller;

import Logic.Horario;
import Model.ModelHorario;
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
@Path("/horarios")
public class ControllerHorarios {
    
    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void registrar(Horario user) {
        try {
            ModelHorario.agregarHorario(user);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/edit/{idUser}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Horario get(@PathParam("idUser") int idUsuario) {
        Horario u = new Horario();
        try {
            u = ModelHorario.buscarHorario(idUsuario);
            return u;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("/delete/{idHora}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public void del(@PathParam("idHora") int idUser) {
        try {
          ModelHorario.eliminarHorario(idUser);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    @PUT
    @Path("/actualizar")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Horario user) {
        try {
            System.out.println(user);
            ModelHorario.modificarHorario(user);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    @GET
    @Path("/list")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Horario> search() {
        List<Horario> ps = ModelHorario.listarHorarios();
        return ps;
    }

}   