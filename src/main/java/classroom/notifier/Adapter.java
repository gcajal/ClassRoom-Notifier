package classroom.notifier;

import classroom.notifier.entity.Alumno;
import classroom.notifier.entity.Observable;
import classroom.notifier.implement.Observer;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Adapter extends Observable implements Observer {

    private Map<String, List<Alumno>> Inscriptos;
    private Notificador notificador;

    Adapter(Map<String, List<Alumno>> Inscriptos, Notificador notificador){
        super();
        this.Inscriptos = Inscriptos;
        this.notificador = notificador;
    }

    @Override
    public void update(Observable observable, Object arg) {
        if( arg instanceof Map<?,?>){
            notificador.Notificar((Map<String, String>) arg,Inscriptos);
        }
        else
            notifyObservers(arg);

    }
}
