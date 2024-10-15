package classroom.notifier.entity;

import java.util.Map;
import java.util.stream.Collectors;

public class Comparador {

    protected Map<String,String> comparar(Map<String,String> valor1,Map<String,String> valor2){
        Map<String, String> diferencia = valor1.entrySet().stream()
                .filter(nuevo ->
                        !valor2.getOrDefault(nuevo.getKey(), "").equals(nuevo.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return diferencia;
    }
}
