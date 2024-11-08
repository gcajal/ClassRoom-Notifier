package classroom.notifier;

import java.util.*;
import java.util.stream.Collectors;

import classroom.notifier.entity.*;
import classroom.notifier.entity.Observable;
import classroom.notifier.implement.MedioComunicacion;
import classroom.notifier.implement.Observer;

public class Notificador extends Observable<String> implements Observer<String> {

    private Set<MedioComunicacion> notificadores;
    private String tipoNotificacion;

    Notificador(Set<MedioComunicacion> notificadores) {
        this.notificadores = notificadores;
        this.tipoNotificacion = "Default";
    }

    void Notificar(Map<String, String> AsignacionNueva, Map<String, List<Alumno>> destinatario) {

        if (!AsignacionNueva.isEmpty() && !destinatario.isEmpty()) {
            try {
                if (!this.notificadores.isEmpty()) {
                    this.notificadores.forEach(notificador ->
                    {
                        if(this.tipoNotificacion.equals(notificador.getMedio())) {
                            AsignacionNueva.forEach((materia, aula) -> {
                                List<Alumno> Alumnos = destinatario.getOrDefault(materia, new ArrayList<Alumno>());
                                if (!Alumnos.isEmpty()) {
                                    notificador.Notificar(materia, aula, Alumnos);
                                }
                            });
                        }
                    });

                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
        }
    }

    public boolean setNotificador(String notificador){
        if(!this.getNotificadores().contains(notificador))
            return false;

        this.tipoNotificacion = notificador;
        return true;
    }

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public Set<String> getNotificadores(){
        return this.notificadores.stream().map(MedioComunicacion::getMedio).collect(Collectors.toSet());
    }

    public MedioComunicacion getNotificadorPorNombre(String nombre){
        List<MedioComunicacion> medios = this.notificadores.stream().filter((e) -> e.getMedio().equals(nombre)).collect(Collectors.toList());
        if(medios.isEmpty())
            return null;

        return  medios.get(0);
    }

    @Override
    public void update(Observable<String> observable, String arg) {
        this.notifyObservers(arg);

    }

    static class Builder {
        private Set<MedioComunicacion> notificadores;

        public Notificador.Builder agregarNotificadores(String[] args){
            notificadores = IniMedioComunicacion(IniDiscover(args));
            return this;
        }

        public Notificador build(){
            return new Notificador(notificadores);
        }

        private Set<MedioComunicacion> IniMedioComunicacion(Discover discover){
            Factory<MedioComunicacion> Factory = new Factory<>(discover);
            return Factory.ListarImplementaciones();
        }

        private Discover IniDiscover(String[] args){
            Discover<MedioComunicacion> Discover;
            if(args.length > 3)
                Discover = new Discover<MedioComunicacion>(args[4],MedioComunicacion.class);
            else
                Discover = new Discover<MedioComunicacion>("",MedioComunicacion.class);

            return Discover;
        }
    }
}
