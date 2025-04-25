package classroom.notifier;


import classroom.notifier.entity.Comparador;
import classroom.notifier.entity.DatosListener;
import classroom.notifier.entity.Discoverer;
import classroom.notifier.implement.InformadorDatos;
import classroom.notifier.implement.Notificador;
import classroom.notifier.implement.Observer;

import java.io.FileNotFoundException;
import java.util.Set;

public class FactoryClassroom {
    public ClassroomNotifier Inicializar(InformadorDatos informadorDatos, String path) throws FileNotFoundException {

        ClassroomNotifier classroomNotifier = new ClassroomNotifier();
        //Discover Discoverer = new Discover(path, Object.class.getClass());
        Discoverer discoverer = new Discoverer();
        //Discover Discoverer = new Discover(path, Observer.class);
        Set<Observer> notificadores =  discoverer.discover(path);
        //Set<Observer> notificadores = Discoverer.InicializarExtensiones();
        notificadores.forEach(classroomNotifier::addObserver);

        NovedadAdapter NovedadAdapter = new NovedadAdapter(classroomNotifier);
        Comparador Comparador = new Comparador(NovedadAdapter);
        AdministradorMaterias AdministradorMaterias = new AdministradorMaterias(Comparador);

        DatosListener datosListener = new DatosListener(AdministradorMaterias);

        if(informadorDatos != null)
            informadorDatos.agregarObservador(datosListener);

        return classroomNotifier;
    }
}
