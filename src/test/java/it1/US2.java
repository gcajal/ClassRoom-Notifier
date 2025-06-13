package it1;


import classroom.notifier.aplicacion.ClassroomMateriaAula;
import classroom.notifier.aplicacion.Diferenciador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class US2 {

    public ClassroomMateriaAula cls_asig_actuales;
    public Map<String, String> _asignaciones_actuales;

    @BeforeEach
    public void init(){
        _asignaciones_actuales = new HashMap<>();
        _asignaciones_actuales.put("PP2","7012");
        _asignaciones_actuales.put("P1","7013");
        _asignaciones_actuales.put("SOR","7011");
         this.cls_asig_actuales = new ClassroomMateriaAula(_asignaciones_actuales);
    }

    @Test
    public void test_1() {
        Map<String, String> _novedad = new HashMap<>();
        _novedad.put("PP2","7010");
        _novedad.put("P1","7013");
        _novedad.put("SOR","7011");

        ClassroomMateriaAula cls_novedad = new ClassroomMateriaAula(_novedad);

        System.out.println(cls_novedad.getMateriasActuales());
        System.out.println(cls_novedad.getMateriasCambiadas(cls_asig_actuales));

        Map<String, String> ret = new HashMap<>();
        ret.put("PP2","7012");

        Assertions.assertEquals(ret,cls_novedad.getMateriasCambiadas(this.cls_asig_actuales));

    }

    @Test
    public void test_2() {
        Map<String, String> asignaciones_sin_cambios = new HashMap<>();
        asignaciones_sin_cambios.put("PP2","7012");
        asignaciones_sin_cambios.put("P1","7013");
        asignaciones_sin_cambios.put("SOR","7011");

        ClassroomMateriaAula cls_novedad_sin_cambios = new ClassroomMateriaAula(asignaciones_sin_cambios);

        Assertions.assertNull(new Diferenciador().obtenerDiferencias(this.cls_asig_actuales, cls_novedad_sin_cambios));

    }
}
