package classroom.notifier;

import java.util.Map;

public class NovedadAdapter {

    private ClassroomNotifier classroomNotifier;

    NovedadAdapter(ClassroomNotifier classroomNotifier){

        this.classroomNotifier = classroomNotifier;
    }

    public void dispararMensaje(Map<String,String> novedad){
        if(!novedad.isEmpty())
            classroomNotifier.notifyObservers(crearMensaje(novedad));

    }
    String crearMensaje(Map<String,String> novedad){

        StringBuilder mensaje = new StringBuilder();
        novedad.forEach ( (materia,aula) ->{
            mensaje.append("Se modifico el aula de la materia ");
            mensaje.append(materia);
            mensaje.append(", la nueva aula es ");
            mensaje.append(aula);
            mensaje.append("/n");
        });

        return mensaje.toString();
    }

}
