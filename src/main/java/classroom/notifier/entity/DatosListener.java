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
    public void update(Observable observable, Object data) {
        this.administradorMaterias.recibirActualización((Map<String, String>) data);
    }
}
