package classroom.notifier.aplicacion;

import classroom.notifier.interfaces.Observer;

public class AsignacionObserver implements Observer {
    OrquestadorCambioAula orquestadorCambioAula;
    public AsignacionObserver(OrquestadorCambioAula _orquestadorCambioAula){
        this.orquestadorCambioAula = _orquestadorCambioAula;
    }
    @Override
    public void update( Object data) {
        this.orquestadorCambioAula.actualizarMaterias((ClassroomMateriaAula) data);
    }
}
