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
    public void execute(Object obj) {
        if(obj instanceof Map<?,?>) {
            Map<String, String> Novedad = (Map<String, String>)obj;
            Map<String, String> diferencia = this.Comparador.comparar(Novedad,this.Materias);
            if (!diferencia.isEmpty()) {
                Materias = Novedad;
                setChanged();
                notifyObservers(diferencia);
            }
        }
    }
}
