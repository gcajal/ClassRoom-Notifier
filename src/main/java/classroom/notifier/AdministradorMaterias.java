package classroom.notifier;

import classroom.notifier.entity.Comparador;
import classroom.notifier.entity.Observable;
import classroom.notifier.implement.Filter;

import java.util.Map;

public class AdministradorMaterias extends Observable implements Filter {

    private Comparador Comparador;
    Map<String,String> Materias;

    AdministradorMaterias(Comparador Comparador,Map<String,String> Materias){
        this.Comparador = Comparador;
        this.Materias = Materias;
    }
    @Override
    public void execute(Map<String, String> Novedad) {
        Map<String,String> diferencia = this.Comparador.comparar(this.Materias,Novedad);
        if(!diferencia.isEmpty()){
            Materias = Novedad;
            notifyObservers(diferencia);
        }
    }
}
