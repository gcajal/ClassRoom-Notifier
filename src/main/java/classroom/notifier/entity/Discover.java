package classroom.notifier.entity;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class Discover<T> {
    private String configuratorPath;
    private Class<?> cls;

    public Discover(String path,Class<T> cls){
        this.configuratorPath = path;
        this.cls = cls;
    }

    protected  List<Class> buscarImplementaciones(){
        List<Class> clases = new ArrayList<Class>();
        try {
            Path path = Paths.get(this.configuratorPath);  // Obtener el path de configurator
            File root = new File(path.toString());
            //File[] files = directory.listFiles((dir, name) -> name.endsWith(".jar"));
            List<File> files = Files.walk(path)
                    .filter(Files::isRegularFile)  // Filtrar solo archivos
                    .filter(file -> file.toString().endsWith(".jar"))  // Filtrar archivos que terminan en .class
                    .map(Path::toFile)  // Convertir Path a File
                    .collect(Collectors.toList());  // Recopilar en una lista


            if (files != null) {
                for (File file : files) {
                    try (JarFile jarFile = new JarFile(file)) {
                        URL[] urls = { file.toURI().toURL() };//{new URL("jar:file:" + file.getAbsolutePath() + "!/")};
                        URLClassLoader cl = URLClassLoader.newInstance(urls);

                        jarFile.stream()
                                .filter(e -> e.getName().endsWith(".class"))
                                .forEach(entry -> {
                                    String className = entry.getName().replace("/", ".").replace(".class", "");
                                    try {
                                        Class<?> clazz = cl.loadClass(className);

                                        if (cls.isAssignableFrom(clazz) && !clazz.isInterface()) {
                                            // Crea una instancia de la clase y la agrega a la lista

                                            clases.add(clazz);
                                            //@SuppressWarnings("unchecked")
                                            //T instance = (T) clazz.getDeclaredConstructor().newInstance();
                                            //listaImplementaciones.add(instance);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clases;
    }
}
