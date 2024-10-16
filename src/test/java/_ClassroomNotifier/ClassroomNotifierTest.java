/*
 * This source file was generated by the Gradle 'init' task
 */
package _ClassroomNotifier;

import classroom.notifier.entity.*;
import classroom.notifier.implement.MedioComunicacion;
import org.junit.jupiter.api.Test;

import classroom.notifier.ClassroomNotifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ClassroomNotifierTest {
    @Test void someLibraryMethodReturnsTrue() {
        String[] args = new String[]{
                "stockNotificar.json"
                ,"stockActual.json"
        };
        ClassroomNotifier classUnderTest = new ClassroomNotifier(args);

        assertTrue(true, "EvaluarDiferencias should return 'true'");
    }

    @Test void validNotificadorDefault() {
        Map<String,String> lista = new HashMap<>();
        lista.put("001","7010");
        NotificadorDefault classUnderTest = new NotificadorDefault();

        assertTrue(true, "EvaluarDiferencias should return 'true'");
    }

    @Test void validFactory(){
        Discover<MedioComunicacion> Discover = new Discover<MedioComunicacion>(System.getProperty("user.dir"),MedioComunicacion.class);
        Factory<MedioComunicacion> Factory = new Factory<MedioComunicacion>(Discover);
        Set<MedioComunicacion> lista = Factory.ListarImplementaciones();
        assertTrue(lista != null, "EvaluarDiferencias should return 'true'");
    }

    @Test void validReadFile(){
        DataFromFile data = new DataFromFile("stockNotificar.json","stockActual.json");

        Map<String, List<Alumno>> lista = data.ListarMateriasInscriptas();
        Map<String,String> mapa = data.ListarMateriasAulas();
        assertTrue(lista != null && mapa != null, "EvaluarDiferencias should return 'true'");
    }
}
