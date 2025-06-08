package classroom.notifier.aplicacion;

import java.util.Map;
import java.util.stream.Collectors;

public class Comparador {
    NovedadAdapter novedadAdapter;
    public Comparador(NovedadAdapter _novedadAdapter){
        this.novedadAdapter = _novedadAdapter;
    }
    public void comparar(ClassroomMateriaAula materiasNovedad,ClassroomMateriaAula materiasActuales) {
        if (materiasNovedad != null && materiasActuales != null && !materiasActuales.equals(materiasNovedad)) {
            novedadAdapter.dispararMensaje(getDiferencia(materiasNovedad, materiasActuales));
        }
    }

    private Map<String, String> getDiferencia(ClassroomMateriaAula materiasNovedad, ClassroomMateriaAula materiasActuales) {
        return materiasActuales.getMateriasCambiadas(materiasNovedad);
    }
}
