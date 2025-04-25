package classroom.notifier.entity;

import classroom.notifier.AdministradorMaterias;
import classroom.notifier.implement.Observer;

import java.util.Map;

public class DatosListener implements Observer {
    AdministradorMaterias administradorMaterias;
    public DatosListener(AdministradorMaterias _administradorMaterias){
        this.administradorMaterias = _administradorMaterias;
    }
    @Override
    public void update( Object data) {
        this.administradorMaterias.recibirActualizacion((Map<String, String>) data);
    }
}
