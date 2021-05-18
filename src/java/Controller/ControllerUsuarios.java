package Controller;

import Logic.Bitacora;
import Model.ModelUsuario;
import Logic.Usuario;
import Model.ModelBitacora;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import javax.ws.rs.core.Context;

/**
 *
 * @author orell
 */
@Path("/users")
public class ControllerUsuarios {

    @Context
    HttpServletRequest request;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuario login(Usuario user) {

        try {
            Usuario usuario = new Usuario();

            usuario = ModelUsuario.buscarUsuarioCorreo(user.getCorreo());

            if (usuario == null) {
                return null;
            } else {
                if (usuario.getPass().equals(user.getPass())) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("logged", usuario);

                    return usuario;
                } else {
                    throw new NotFoundException();
                }
            }
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @POST
    @Path("/registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void registrar(Usuario user) {
        try {
            ModelUsuario.agregarUsuario(user);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/edit/{idUser}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Usuario get(@PathParam("idUser") int idUsuario) {
        Usuario u = new Usuario();
        try {
            u = ModelUsuario.buscarUsuario(idUsuario);
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

            ModelUsuario.eliminarUsuario(idUser);

        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/list")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Usuario> search() {
        List<Usuario> ps = ModelUsuario.listarUs();
        return ps;
    }

    @GET
    @Path("/misvuelos/{idUser}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Bitacora> searchMisVuelos(@PathParam("idUser") int idUser) {
        List<Bitacora> ps = ModelBitacora.listarBitacoraUser(idUser);
        return ps;
    }

    @PUT
    @Path("/actualizar")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Usuario user) {
        try {
            System.out.println(user);
            ModelUsuario.modificarUsuario(user);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
