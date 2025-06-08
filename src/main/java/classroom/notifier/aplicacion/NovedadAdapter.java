package classroom.notifier.aplicacion;

import java.util.Map;

public class NovedadAdapter {
    private Distribuidor distribuidor;
    public NovedadAdapter(Distribuidor distribuidor){
        this.distribuidor = distribuidor;
    }
    public void dispararMensaje(Map<String,String> novedad){
        if(!novedad.isEmpty())
            distribuidor.distribuir(crearMensaje(novedad));
    }
    String crearMensaje(Map<String,String> novedad){

        StringBuilder mensaje = new StringBuilder();
        novedad.forEach ( (materia,aula) ->{
            mensaje.append("Se modifico el aula de la materia ");
            mensaje.append(materia);
            mensaje.append(", la nueva aula es ");
            mensaje.append(aula);
            mensaje.append(" ");
        });
        return mensaje.toString();
    }
}