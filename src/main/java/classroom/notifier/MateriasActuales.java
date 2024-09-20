package classroom.notifier;

import java.util.*;
import java.util.stream.Collectors;

import classroom.notifier.entity.Factory;
import classroom.notifier.entity.Observable;
import classroom.notifier.entity.implement.LecturaDatos;
import classroom.notifier.entity.implement.MedioNotificacion;
import classroom.notifier.entity.implement.NotifierListeners;
import classroom.notifier.entity.implement.Observer;

public class MateriasActuales extends Observable implements NotifierListeners {

    private Map<String, String> Asignacion;
    private Set<String> AsignacionNotificar;
    private Factory<MedioNotificacion> Factory;
    //private LecturaDatos Database;

    MateriasActuales(LecturaDatos Database) {
        this.Asignacion = new HashMap<>();
        this.AsignacionNotificar = new HashSet<>();
        this.Factory = new Factory<>(System.getProperty("user.dir"));
        //this.Database = Database;
        Asignacion = Database.ListarMateriasAulas();
        AsignacionNotificar = Database.ListarMateriasInscriptas();
    }

    protected void ComprobarNovedades(Map<String, String> AsignacionNueva) {
        //Asignacion.put("001", "7000");
        //AsignacionNotificar.add("001");

        Map<String, String> diferencia = AsignacionNueva.entrySet().stream().filter(nuevo -> this.AsignacionNotificar.contains(nuevo.getKey()))
                .filter(nuevo ->
                        !this.Asignacion.getOrDefault(nuevo.getKey(), "").equals(nuevo.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        if (!diferencia.isEmpty()) {
            try {
                Set<MedioNotificacion> Notificadores = this.Factory.ListarImplementaciones(MedioNotificacion.class);
                if (Notificadores.size() > 0) {
                    Notificadores.forEach(notificador ->
                    {
                        notificador.AddListener(this);
                        notificador.Notificar(diferencia);
                    });

                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
        }
    }

    @Override
    public void enviarNotificaciones(String notificacion) {
        setChanged();
        notifyObservers(notificacion);
    }

    @Override
    public void enviarRefrescarLista(List<String> datos) {
        setChanged();
        notifyObservers(datos);
    }
}
