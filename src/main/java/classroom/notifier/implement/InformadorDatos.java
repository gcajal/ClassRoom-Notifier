package classroom.notifier.implement;

import classroom.notifier.entity.Observable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class  InformadorDatos extends Observable {

    public abstract Map<String,String> Leer();

}
