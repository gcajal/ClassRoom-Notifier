package classroom.notifier.entity;

import java.io.File;
import java.io.IOException;
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

    private Set<T> elementos;
    private String configuratorPath;

    public Factory(String path) {
        this.configuratorPath = path;
    }


    public Set<T> ListarImplementaciones(Class<T> interfaceClass) {
        Set<T> listaImplementaciones = new HashSet<T>();

        try {
            Path path = Paths.get(this.configuratorPath);  // Obtener el path de configurator
            File root = new File(path.toString());
            //File[] files = directory.listFiles((dir, name) -> name.endsWith(".jar"));
            List<File> files = Files.walk(path)
                    .filter(Files::isRegularFile)  // Filtrar solo archivos
                    .filter(file -> file.toString().endsWith(".jar"))  // Filtrar archivos que terminan en .class
                    .map(Path::toFile)  // Convertir Path a File
                    .toList();  // Recopilar en una lista


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

                                        if (interfaceClass.isAssignableFrom(clazz) && !clazz.isInterface()) {
                                            // Crea una instancia de la clase y la agrega a la lista
                                            @SuppressWarnings("unchecked")
                                            T instance = (T) clazz.getDeclaredConstructor().newInstance();
                                            listaImplementaciones.add(instance);
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


            //Desde archivo .class
/*
            //files = directory.listFiles((dir, name) -> name.endsWith(".class"));
            List<File> classFiles = Files.walk(path)
                    .filter(Files::isRegularFile)  // Filtrar solo archivos
                    .filter(file -> file.toString().endsWith(".class"))  // Filtrar archivos que terminan en .class
                    .map(Path::toFile)  // Convertir Path a File
                    .toList();  // Recopilar en una lista

            // Convertir la lista a un array
            File[] files = classFiles.toArray(new File[0]);
//            if (files != null) {
                for (File file : files) {
                    URL[] urls = {file.toURI().toURL()};
                    URLClassLoader cl = URLClassLoader.newInstance(urls);


                    try {
                        String className = entry.getName().replace("/", ".").replace(".class", "");
                        Class<?> cls = Class.forName(className);
                        //Class<?> clazz = cl.loadClass(cl.getName());

                        if (interfaceClass.isAssignableFrom(cls) && !cls.isInterface()) {
                            // Crea una instancia de la clase y la agrega a la lista
                            @SuppressWarnings("unchecked")
                            T instance = (T) cls.getDeclaredConstructor().newInstance();
                            listaImplementaciones.add(instance);
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            //}
*/

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaImplementaciones;
    }


}
