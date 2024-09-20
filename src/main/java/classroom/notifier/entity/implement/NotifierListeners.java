package classroom.notifier.entity.implement;

import java.util.List;

public interface NotifierListeners {

    void enviarNotificaciones(String notificacion);
    void enviarRefrescarLista(List<String> datos);
}
