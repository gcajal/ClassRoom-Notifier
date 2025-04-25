package _ClassroomNotifier;

import classroom.notifier.entity.Discover;
import classroom.notifier.implement.Observer;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class DiscoverTest {
    @Test
    void test(){
        String path = "build/libs";
        Discover Discoverer = new Discover(path, Observer.class);
        Set<Observer> notificadores = Discoverer.InicializarExtensiones();
        System.out.println(notificadores.toString());
    }
}
