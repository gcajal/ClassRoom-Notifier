package classroom.notifier.aplicacion;

import classroom.notifier.interfaces.Observer;

import java.util.Map;

public class DatosListener implements Observer {
    AdministradorMateriaAula administradorMateriaAula;
    public DatosListener(AdministradorMateriaAula _administradorMateriaAula){
        this.administradorMateriaAula = _administradorMateriaAula;
    }
    @Override
    public void update( Object data) {
        this.administradorMateriaAula.actualizarMaterias((ClassroomMateriaAula) data);
    }
}
