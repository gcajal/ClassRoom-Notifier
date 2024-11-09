package classroom.notifier.entity;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class Factory<T> {

    private Discover<T> discover;

    public Factory(Discover<T> discover) {
        this.discover = discover;
    }


    public Set<T> ListarImplementaciones() {
        Set<T> listaImplementaciones = new HashSet<T>();
        List<Class> clases = discover.buscarImplementaciones();
        clases.forEach((clazz) ->{
            T instance = null;
            try {
                instance = (T) clazz.getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            listaImplementaciones.add(instance);
        });

        return listaImplementaciones;
    }


}
