package classroom.notifier.implement;

import classroom.notifier.entity.Alumno;

import java.util.List;
import java.util.Map;

public interface MedioComunicacion {

	void Notificar(String Materia, String Aula, List<Alumno> destinatarios);
	String getMedio();
}
