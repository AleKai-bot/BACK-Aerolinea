package Controller;

import java.util.Set;
import javax.ws.rs.core.Application;


/**
 *
 * @author orell
 */

@javax.ws.rs.ApplicationPath("/api")
public class ApplicationConfig extends Application{

   @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(Controller.ControllerAviones.class);
        resources.add(Controller.ControllerHorarios.class);
        resources.add(Controller.ControllerRutas.class);
        resources.add(Controller.ControllerTiquete.class);
        resources.add(Controller.ControllerUsuarios.class);
        resources.add(Controller.ControllerVuelos.class);
    }
    
}
