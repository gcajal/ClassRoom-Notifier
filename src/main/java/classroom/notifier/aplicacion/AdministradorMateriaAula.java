package classroom.notifier.aplicacion;

import java.util.HashMap;
import java.util.Map;

public class AdministradorMateriaAula {
    private Comparador Comparador;
    private ClassroomMateriaAula materiasActuales;

    public AdministradorMateriaAula(Comparador comparador) {
        Comparador = comparador;
    }

    public void actualizarMaterias(ClassroomMateriaAula novedad) {
        this.Comparador.comparar(novedad,this.materiasActuales);
        materiasActuales = novedad;
    }
}
