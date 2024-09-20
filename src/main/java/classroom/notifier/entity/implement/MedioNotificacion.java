package classroom.notifier.entity.implement;

import java.util.Map;

public interface MedioNotificacion {

	void Notificar(Map<String, String> cambiosANotificar);
	void AddListener(NotifierListeners listener);
}
