package tests.it0;

import classroom.notifier.entity.Discover;
import classroom.notifier.implement.Observer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import tests.ReadProperties;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class US1 {
    public static String path = "";


    @Test
    public void empty_directory_test(){
        ReadProperties prop = ReadProperties.getInstance("src/test/java/resources/config-test.properties");
        path = prop.get("emptyPath");
        Discover Discoverer = new Discover(path, Observer.class);
        Set<Observer> notificadores = Discoverer.InicializarExtensiones();
        String ext_expected = "[]";
        Assumptions.assumeTrue(notificadores.size() == 0);
        Assertions.assertEquals(notificadores.toString(),ext_expected);
        Path dirPath = Path.of(path);
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(dirPath)) {
            boolean isEmpty = !dirStream.iterator().hasNext();
            Assertions.assertTrue(isEmpty);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void simple_extension_test(){
        ReadProperties prop = ReadProperties.getInstance("src/test/java/resources/config-test.properties");
        path = prop.get("simpleExt");
        Discover Discoverer = new Discover(path, Observer.class);
        Set<Observer> notificadores = Discoverer.InicializarExtensiones();
        String ext_expected = "notificadores.Telegram@";
        Assertions.assertEquals(1,notificadores.size());
        Assertions.assertTrue(notificadores.toString().contains(ext_expected));
    }

    @Test
    public void multi_extension_test(){
        ReadProperties prop = ReadProperties.getInstance("src/test/java/resources/config-test.properties");
        path = prop.get("multiExtPath");
        Discover Discoverer = new Discover(path, Observer.class);
        Set<Observer> notificadores = Discoverer.InicializarExtensiones();
        System.out.println(notificadores.toString());
        String f_ext_expected = "notificadores.Telegram@";
        String s_ext_expected = "email.Email@";
        Assertions.assertEquals(2,notificadores.size());
        Assertions.assertTrue(notificadores.toString().contains(f_ext_expected));
        Assertions.assertTrue(notificadores.toString().contains(s_ext_expected));
    }

    @Test
    public void extension_not_found(){
        ReadProperties prop = ReadProperties.getInstance("src/test/java/resources/config-test.properties");
        path = prop.get("noExt");
        Discover Discoverer = new Discover(path, Observer.class);
        Set<Observer> notificadores = Discoverer.InicializarExtensiones();
        String ext_expected = "[]";
        Assumptions.assumeTrue(notificadores.size() == 0);
        Assertions.assertEquals(notificadores.toString(),ext_expected);
        //
        // System.out.println(path = prop.get("noExt"));
    }
}
