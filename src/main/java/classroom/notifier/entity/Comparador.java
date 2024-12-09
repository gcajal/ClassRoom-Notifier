package classroom.notifier.entity;

import classroom.notifier.NovedadAdapter;

import java.util.Map;
import java.util.stream.Collectors;

public class Comparador {
    NovedadAdapter novedadAdapter;
    public Comparador(NovedadAdapter _novedadAdapter){
        this.novedadAdapter = _novedadAdapter;
    }
    public void comparar(Map<String,String> valor1,Map<String,String> valor2){
        Map<String, String> diferencia = valor1.entrySet().stream()
                .filter(nuevo ->
                        !valor2.getOrDefault(nuevo.getKey(), "").equals(nuevo.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if(!diferencia.isEmpty()){
            novedadAdapter.dispararMensaje(diferencia);
        }
    }
}
