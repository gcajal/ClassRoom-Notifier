package classroom.notifier;

import classroom.notifier.entity.Comparador;
import classroom.notifier.entity.DatosListener;
import classroom.notifier.entity.Discover;
import classroom.notifier.entity.Observable;
import classroom.notifier.implement.InformadorDatos;
import classroom.notifier.implement.Notificador;
import classroom.notifier.implement.Observer;

import java.util.Set;

public class FactoryClassroom {


    public ClassroomNotifier Inicializar(InformadorDatos informadorDatos, String path){

        ClassroomNotifier classroomNotifier = new ClassroomNotifier();
        Discover Discoverer = new Discover(path);
        Set<Observer> notificadores = Discoverer.InicializarExtensiones();
        notificadores.forEach(classroomNotifier::addObserver);

        NovedadAdapter NovedadAdapter = new NovedadAdapter(classroomNotifier);
        Comparador Comparador = new Comparador(NovedadAdapter);
        AdministradorMaterias AdministradorMaterias = new AdministradorMaterias(Comparador);

        DatosListener DatosListener = new DatosListener(AdministradorMaterias);

        if(informadorDatos != null)
            informadorDatos.addObserver(DatosListener);

        return classroomNotifier;
    }
}
