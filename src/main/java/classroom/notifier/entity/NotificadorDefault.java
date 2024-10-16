package classroom.notifier.entity;

import classroom.notifier.implement.MedioComunicacion;

import java.util.*;
import java.util.stream.Collectors;

public class NotificadorDefault extends Observable implements MedioComunicacion {


	public NotificadorDefault(){
		super();
	}

	@Override
	public void Notificar(String Materia,String Aula, List<Alumno> destinatarios) {
		String result = Materia + ": " + Aula;
		notifyObservers(result);
	}

	@Override
	public String getMedio() {
		return "View";
	}
}
