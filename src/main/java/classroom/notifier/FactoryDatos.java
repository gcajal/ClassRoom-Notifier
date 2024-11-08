package classroom.notifier;

import classroom.notifier.entity.DataFromFile;

public class FactoryDatos {

    static DataFromFile inicializarData(String[] args){
        if(args.length > 1)
            return new DataFromFile(args[0],args[1]);
        else
            return new DataFromFile("alumnosMateria.json","stockActual.json");
    }
}
