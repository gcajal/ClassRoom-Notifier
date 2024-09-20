package classroom.notifier.entity;

import java.util.Map;

class NotificadorDefault extends Observable implements MedioNotificacion {
	
	public NotificadorDefault() {
		
	}
	//Se puede agregar datos del destinatario
	public void Notificar(Map<String, String> cambiosANotificar) {
		setChanged();
		notifyObservers(cambiosANotificar);
	}
}
