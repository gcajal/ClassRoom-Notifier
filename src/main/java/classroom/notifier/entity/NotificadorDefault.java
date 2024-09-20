package classroom.notifier.entity;

import classroom.notifier.entity.implement.MedioNotificacion;
import classroom.notifier.entity.implement.NotifierListeners;

import java.util.*;

class NotificadorDefault implements MedioNotificacion {
	
	Set<NotifierListeners> listeners;

	public NotificadorDefault(){
		this.listeners = new HashSet<>();
	}

	//Se puede agregar datos del destinatario
	public void Notificar(Map<String, String> cambiosANotificar) {

		List<String> materias = cambiosANotificar
					.entrySet()
					.stream()
					.map( e -> e.getKey() + " " + e.getValue()).toList();
		listeners.forEach(  listener -> listener.enviarRefrescarLista(materias));

	}

	@Override
	public void AddListener(NotifierListeners listener) {
		if(listener != null) this.listeners.add(listener);
	}
}
