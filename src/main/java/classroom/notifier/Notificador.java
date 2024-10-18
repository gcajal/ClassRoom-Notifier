package classroom.notifier;

import java.util.*;
import java.util.stream.Collectors;

import classroom.notifier.entity.Alumno;
import classroom.notifier.implement.MedioComunicacion;

public class Notificador {

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
                        if(this.tipoNotificacion.equals("Default") || this.tipoNotificacion.equals(notificador.getMedio())) {
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

    public Set<String> getNotificadores(){
        return this.notificadores.stream().map(MedioComunicacion::getMedio).collect(Collectors.toSet());
    }
}
