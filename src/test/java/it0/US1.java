package it0;

import classroom.notifier.inicializacion.Discoverer;
import classroom.notifier.interfaces.Observer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class US1 {
    public static String path = "";
    public ReadProperties prop;
    public Discoverer dis;
    @BeforeEach
    public void init(){
        this.prop = ReadProperties.getInstance("src/test/java/resources/config-test.properties");
        this.dis = new Discoverer();
    }

    @Test
    public void empty_directory_test() throws FileNotFoundException {
        path = prop.get("emptyPath");
        Set<Observer> notificadores =  dis.discover(path);
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
    public void simple_extension_test() throws FileNotFoundException {
        path = prop.get("simpleExt");
        Set<Observer> notificadores =  dis.discover(path);
        String ext_expected = "notificadores.Telegram@";
        Assertions.assertEquals(1,notificadores.size());
        Assertions.assertTrue(notificadores.toString().contains(ext_expected));
    }

    @Test
    public void multi_extension_test() throws FileNotFoundException {
        path = prop.get("multiExtPath");
        Set<Observer> notificadores =  dis.discover(path);
        String f_ext_expected = "notificadores.Telegram@";
        String s_ext_expected = "notificadores.Whatsapp@";
        Assertions.assertEquals(2,notificadores.size());
        Assertions.assertTrue(notificadores.toString().contains(f_ext_expected));
        Assertions.assertTrue(notificadores.toString().contains(s_ext_expected));
    }

    @Test
    public void extension_not_found() throws FileNotFoundException {
        path = prop.get("noExt");
        Set<Observer> notificadores =  dis.discover(path);
        String ext_expected = "[]";
        Assumptions.assumeTrue(notificadores.size() == 0);
        Assertions.assertEquals(notificadores.toString(),ext_expected);
    }
}
