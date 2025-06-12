package classroom.notifier.aplicacion;

import java.util.Map;

public class NovedadAdapter {
    private Distribuidor distribuidor;
    private Mensaje mensaje;
    public NovedadAdapter(Distribuidor distribuidor, Mensaje mensaje){
        this.distribuidor = distribuidor;
        this.mensaje = mensaje;
    }
    public void dispararMensaje(Map<String,String> novedad){
        if(!novedad.isEmpty())
            distribuidor.distribuir(this.mensaje.crear(novedad));
    }

}