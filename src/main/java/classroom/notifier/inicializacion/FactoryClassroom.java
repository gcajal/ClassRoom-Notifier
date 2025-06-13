package classroom.notifier.inicializacion;


import classroom.notifier.aplicacion.*;
import classroom.notifier.interfaces.InformadorDatos;
import classroom.notifier.interfaces.Observer;

import java.io.FileNotFoundException;
import java.util.Set;

public class FactoryClassroom {
    public ClassroomNotifier crear(InformadorDatos informadorDatos, String path) throws FileNotFoundException {

        ClassroomNotifier classroomNotifier = new ClassroomNotifier();
        Discoverer discoverer = new Discoverer();
        Set<Observer> notificadores =  discoverer.discover(path);
        notificadores.forEach(classroomNotifier::addObserver);
        NovedadAdapter NovedadAdapter = new NovedadAdapter(new Distribuidor(classroomNotifier), new Mensaje());
        Diferenciador Diferenciador = new Diferenciador();
        OrquestadorCambioAula OrquestadorCambioAula = new OrquestadorCambioAula(Diferenciador, new ManejadorDiferencia(NovedadAdapter));

        AsignacionObserver asignacionObserver = new AsignacionObserver(OrquestadorCambioAula);

        if(informadorDatos != null)
            informadorDatos.suscribir(asignacionObserver);

        return classroomNotifier;
    }
}
