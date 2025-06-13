package classroom.notifier.aplicacion;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClassroomMateriaAula {

    private Map<String,String> materiasActuales;

    public ClassroomMateriaAula(Map<String, String> materias) {
        this.materiasActuales = materias;
    }

    public Map<String, String> getMateriasActuales() {
        return materiasActuales;
    }


    @Override
    public boolean equals(Object nuevaMateria) {
        if (this == nuevaMateria) return true;
        if (nuevaMateria == null || getClass() != nuevaMateria.getClass()) return true;
        ClassroomMateriaAula nuevoClassroomMaterias = (ClassroomMateriaAula) nuevaMateria;
        return Objects.equals(materiasActuales,nuevoClassroomMaterias.materiasActuales);
    }

    public Map<String, String> getMateriasCambiadas(ClassroomMateriaAula nuevaMaterias) {
        return nuevaMaterias.getMateriasActuales().entrySet().stream()
                .filter(nuevo ->
                        !materiasActuales.getOrDefault(nuevo.getKey(), "").equals(nuevo.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}