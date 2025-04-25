package _ClassroomNotifier;


import classroom.notifier.entity.Discoverer;
import classroom.notifier.implement.Observer;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Set;

public class DiscoverTest {
    @Test
    void test() throws FileNotFoundException {
        String path = "src/test/java/resources/multi_ext";
        Discoverer dis = new Discoverer();
       Set<Observer> not = dis.discover(path);
        System.out.println(not.toString());
        //Discover Discoverer = new Discover(path, Observer.class);
        //Set<Observer> notificadores = Discoverer.InicializarExtensiones();
        //System.out.println(notificadores.toString());
    }
}
