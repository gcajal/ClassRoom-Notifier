/*
 * This source file was generated by the Gradle 'init' task
 */
package classroom.notifier;

import classroom.notifier.entity.*;
import classroom.notifier.implement.MedioComunicacion;

import java.util.Set;

public class ClassroomNotifier{

	private Notificador _Notificador;
	private Timer _Timer;
	private AdministradorMaterias _AdministradorMaterias;
	//private Adapter Adapter;

	//private DataFromFile Database;
	private static ClassroomNotifier instance = null;

	public static synchronized  ClassroomNotifier getInstance(String[] args) {
		if (instance == null) {
			System.out.println("Ini Classroom");
			instance = new ClassroomNotifier(args);
		}
		return instance;
	}

	
	public ClassroomNotifier(String[] args)
	{
		super();
	    inicilizar(args);
		this.instance = this;
	}

	
	 private void inicilizar(String[] args)
	 {
	 	this._Timer = new Timer(args);
		DataFromFile datos = FactoryDatos.inicializarData(args);

	 	this._AdministradorMaterias = new AdministradorMaterias.Builder()
				 .agregarComparador()
				 .agregarDatosActuales(datos.ListarMateriasAulas())
				 .build();

		 this._Notificador = new Notificador.Builder()
				 	.agregarNotificadores(args)
				 	.build();
		 Adapter _Adapter = new Adapter(datos.ListarMateriasInscriptas(),this._Notificador);

		 _Timer.addFilter(datos);
		 datos.addFilter(this._AdministradorMaterias);
		 this._AdministradorMaterias.addObserver(_Adapter);

		 Observable<String> notifificadorDefault = (Observable<String>) this._Notificador.getNotificadorPorNombre(this._Notificador.getTipoNotificacion());
		 if(notifificadorDefault != null)
		 	notifificadorDefault.addObserver(this._Notificador);

	 }


	public classroom.notifier.Notificador getNotificador() {
		return this._Notificador;
	}


	public classroom.notifier.AdministradorMaterias getAdministradorMaterias() {
		return this._AdministradorMaterias;
	}




}
