package classroom.notifier;

import classroom.notifier.entity.Comparador;

import java.util.HashMap;
import java.util.Map;

public class AdministradorMaterias {
    private Comparador Comparador;
    Map<String,String> Materias;

    AdministradorMaterias(Comparador Comparador){
        this.Comparador = Comparador;
        this.Materias = new HashMap<>();
    }
    public void recibirActualizacion(Map<String, String> Novedad) {
        if(!Materias.isEmpty())
            this.Comparador.comparar(Novedad,this.Materias);
        Materias = Novedad;
    }
}
