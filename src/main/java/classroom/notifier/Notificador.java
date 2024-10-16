package classroom.notifier;

import java.util.*;

import classroom.notifier.entity.Alumno;
import classroom.notifier.implement.MedioComunicacion;

public class Notificador {

    private Set<MedioComunicacion> notificadores;


    Notificador(Set<MedioComunicacion> notificadores) {
        this.notificadores = notificadores;
    }

    void Notificar(Map<String, String> AsignacionNueva, Map<String, List<Alumno>> destinatario) {

        if (!AsignacionNueva.isEmpty() && !destinatario.isEmpty()) {
            try {
                if (!this.notificadores.isEmpty()) {
                    this.notificadores.forEach(notificador ->
                    {
                        AsignacionNueva.forEach((materia,aula) -> {
                            List<Alumno> Alumnos = destinatario.getOrDefault(materia,new ArrayList<Alumno>());
                            if(!Alumnos.isEmpty())
                            notificador.Notificar(materia,aula,Alumnos);
                        });



                    });

                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
        }
    }
}
