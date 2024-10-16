/*
 * This source file was generated by the Gradle 'init' task
 */
package classroom.notifier;

import classroom.notifier.entity.DataFromFile;
import classroom.notifier.entity.Timer;

public class ClassroomNotifier{

	private Notificador Notificador;
	private Adapter Adapter;
	private Timer Timer;
	private AdministradorMaterias AdministradorMaterias;
	private DataFromFile Database;

	
	public ClassroomNotifier(String[] args)
	{
		super();
	    inicilizar(args);
	}

	
	 private void inicilizar(String[] args)
	 {

		FactoryClassroom factoryClassroom = new FactoryClassroom(args);
		factoryClassroom.Inicialice(this);
	 }


	public classroom.notifier.Notificador getNotificador() {
		return Notificador;
	}

	public classroom.notifier.Adapter getAdapter() {
		return Adapter;
	}

	public classroom.notifier.entity.Timer getTimer() {
		return Timer;
	}

	public classroom.notifier.AdministradorMaterias getAdministradorMaterias() {
		return AdministradorMaterias;
	}

	public DataFromFile getDatabase() {
		return Database;
	}


	void setNotificador(classroom.notifier.Notificador notificador) {
		Notificador = notificador;
	}

	void setAdapter(classroom.notifier.Adapter adapter) {
		Adapter = adapter;
	}

	void setTimer(classroom.notifier.entity.Timer timer) {
		Timer = timer;
	}

	void setAdministradorMaterias(classroom.notifier.AdministradorMaterias administradorMaterias) {
		AdministradorMaterias = administradorMaterias;
	}

	void setDatabase(DataFromFile database) {
		Database = database;
	}
}
