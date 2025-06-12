package classroom.notifier.aplicacion;

import java.util.Map;

public class Mensaje {

    public String crear(Map<String,String> novedad){

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
