package classroom.notifier;

import java.util.*;

import classroom.notifier.entity.Alumno;
import classroom.notifier.implement.MedioComunicacion;

public class Notificador {

    private Set<MedioComunicacion> notificadores;


    Notificador(Set<MedioComunicacion> notificadores) {
        this.notificadores = notificadores;
    }

    protected void Notificar(Map<String, String> AsignacionNueva, Map<String, List<Alumno>> destinatario) {


        //Map<String, String> diferencia = destinatario.entrySet().stream().filter(nuevo -> this.AsignacionNotificar.contains(nuevo.getKey()))
        //        .filter(nuevo ->
        //                !this.Asignacion.getOrDefault(nuevo.getKey(), "").equals(nuevo.getValue()))
        //        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


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
