package classroom.notifier;

import classroom.notifier.entity.*;
import classroom.notifier.implement.MedioComunicacion;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FactoryClassroom {

    String[] args;

    public FactoryClassroom(String[] args){
        this.args = args;
    }

    protected void Inicialice(ClassroomNotifier classroomNotifier){
        Timer timer = IniTimer();
        DataFromFile data = IniData();
        Set<MedioComunicacion> notificadores = IniMedioComunicacion(IniDiscover());
        Set<Observable> observables = ListMedioComunicacionObservable(notificadores);
        Notificador notificador = new Notificador(notificadores);
        Adapter adapter = IniAdapter(data.ListarMateriasInscriptas(),notificador);
        AdministradorMaterias administradorMaterias = IniAdministradorMateria(data.ListarMateriasAulas());
        //Add lista
        administradorMaterias.addObserver(adapter);
        data.addFilter(administradorMaterias);
        timer.addFilter(data);
        observables.forEach((obs) ->{
            obs.addObserver(adapter);
        });

        classroomNotifier.setTimer(timer);
        classroomNotifier.setDatabase(data);
        classroomNotifier.setNotificador(notificador);
        classroomNotifier.setAdapter(adapter);
        classroomNotifier.setAdministradorMaterias(administradorMaterias);

    }

    private Timer IniTimer(){
        if(args.length > 2)
            return new Timer(Integer.decode(args[2]));
        else
            return new Timer(10);
    }

    private Discover IniDiscover(){
        Discover<MedioComunicacion> Discover;
        if(args.length > 3)
            Discover = new Discover<MedioComunicacion>(args[4],MedioComunicacion.class);
        else
            Discover = new Discover<MedioComunicacion>("",MedioComunicacion.class);

        return Discover;
    }


    private Set<MedioComunicacion> IniMedioComunicacion(Discover discover){
        Factory<MedioComunicacion> Factory = new Factory<>(discover);
        return Factory.ListarImplementaciones();
    }

    private Set<Observable> ListMedioComunicacionObservable(Set<MedioComunicacion> lista){

        Set<Observable> observables = new HashSet<Observable>();
        lista.forEach((e) ->{
            if(e instanceof Observable)
                observables.add((Observable) e);
        });

        return observables;
    }

    private DataFromFile IniData(){
        if(args.length > 1)
            return new DataFromFile(args[0],args[1]);
        else
            return new DataFromFile("alumnosMateria.json","stockActual.json");
    }

    private AdministradorMaterias IniAdministradorMateria(Map<String,String> materias ){
        Comparador comparador = new Comparador();
        return new AdministradorMaterias(comparador, materias);
    }
    private Adapter IniAdapter(Map<String, List<Alumno>> alumnos, Notificador notificador){
        return new Adapter(alumnos,notificador);
    }



}
