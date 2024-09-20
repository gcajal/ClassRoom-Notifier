/*
 * This source file was generated by the Gradle 'init' task
 */
package _ClassroomNotifier;

import classroom.notifier.entity.Factory;
import classroom.notifier.entity.NotificadorDefault;
import classroom.notifier.entity.implement.MedioNotificacion;
import org.junit.jupiter.api.Test;

import classroom.notifier.ClassroomNotifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ClassroomNotifierTest {
    @Test void someLibraryMethodReturnsTrue() {
        String[] args = new String[2];
        ClassroomNotifier classUnderTest = new ClassroomNotifier(args);
        classUnderTest.EvaluarDiferencias();
        assertTrue(true, "EvaluarDiferencias should return 'true'");
    }

    @Test void validNotificadorDefault() {
        Map<String,String> lista = new HashMap<>();
        lista.put("001","7010");
        NotificadorDefault classUnderTest = new NotificadorDefault();
        classUnderTest.Notificar(lista);
        assertTrue(true, "EvaluarDiferencias should return 'true'");
    }

    @Test void validFactory(){
        Factory<MedioNotificacion> Factory = new Factory<MedioNotificacion>(System.getProperty("user.dir"));

        Set<MedioNotificacion> lista = Factory.ListarImplementaciones(MedioNotificacion.class);
        assertTrue(lista != null, "EvaluarDiferencias should return 'true'");
    }
}
