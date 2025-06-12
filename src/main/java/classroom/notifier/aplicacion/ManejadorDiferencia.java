package classroom.notifier.aplicacion;

import java.util.Map;
import java.util.Objects;

public class ManejadorDiferencia {
    private NovedadAdapter novedadAdapter;
    public ManejadorDiferencia(NovedadAdapter novedadAdapter) {
        this.novedadAdapter = novedadAdapter;
    }

    public void manejarDiferencia(Object o){
        if (o != null){
            novedadAdapter.dispararMensaje((Map<String, String>) o);
        }
    }

}
