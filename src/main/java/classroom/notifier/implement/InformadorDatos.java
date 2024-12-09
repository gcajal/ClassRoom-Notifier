package classroom.notifier.implement;

import classroom.notifier.entity.DatosListener;
import classroom.notifier.entity.Observable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface  InformadorDatos {

    public Map<String,String> Leer();
    public void agregarObservador(Object o);

}
