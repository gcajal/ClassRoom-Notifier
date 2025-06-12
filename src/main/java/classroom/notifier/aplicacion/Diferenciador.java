package classroom.notifier.aplicacion;

import java.util.Map;


public class Diferenciador {

    public Map<String, String> obtenerDiferencias(ClassroomMateriaAula materiasNovedad,ClassroomMateriaAula materiasActuales) {
        if (materiasNovedad != null && materiasActuales != null && !materiasActuales.equals(materiasNovedad)) {
            return getDiferencia(materiasNovedad, materiasActuales);
        }
        return null;
    }

    private Map<String, String> getDiferencia(ClassroomMateriaAula materiasNovedad, ClassroomMateriaAula materiasActuales) {
        return materiasActuales.getMateriasCambiadas(materiasNovedad);
    }
}
