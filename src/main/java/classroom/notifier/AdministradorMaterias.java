package classroom.notifier;

import classroom.notifier.entity.Comparador;
import classroom.notifier.entity.Observable;
import classroom.notifier.implement.Filter;

import java.util.Map;

public class AdministradorMaterias extends Observable<Map<String,String>> implements Filter {

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

    static class Builder {
        private Comparador comparador;
        private Map<String,String> dataActual;

        public Builder agregarComparador(){
            this.comparador = new Comparador();
            return this;
        }
        public Builder agregarDatosActuales(Map<String,String> data){
            this.dataActual = data;
            return this;
        }

        public AdministradorMaterias build(){
            return new AdministradorMaterias(this.comparador,dataActual);
        }
    }
}
