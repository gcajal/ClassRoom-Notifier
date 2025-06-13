package it1;


import classroom.notifier.aplicacion.ClassroomMateriaAula;
import classroom.notifier.aplicacion.Diferenciador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    public void cambio_aula_satisfactorio() {
        Map<String, String> _novedad = new HashMap<>();
        _novedad.put("PP2","7010");
        _novedad.put("P1","7013");
        _novedad.put("SOR","7011");

        ClassroomMateriaAula cls_novedad = new ClassroomMateriaAula(_novedad);

        //System.out.println(cls_novedad.getMateriasActuales());
        //System.out.println(this.cls_asig_actuales.getMateriasCambiadas(cls_novedad));

        Map<String, String> ret = new HashMap<>();
        ret.put("PP2","7010");

        //Assertions.assertEquals(ret,cls_novedad.getMateriasCambiadas(this.cls_asig_actuales));
        Assertions.assertEquals(ret,this.cls_asig_actuales.getMateriasCambiadas(cls_novedad));

    }

    @Test
    public void materias_sin_cambio_de_aula() {
        Map<String, String> asignaciones_sin_cambios = new HashMap<>();
        asignaciones_sin_cambios.put("PP2","7012");
        asignaciones_sin_cambios.put("P1","7013");
        asignaciones_sin_cambios.put("SOR","7011");

        ClassroomMateriaAula cls_novedad_sin_cambios = new ClassroomMateriaAula(asignaciones_sin_cambios);
        Assertions.assertTrue(this.cls_asig_actuales.getMateriasCambiadas(cls_novedad_sin_cambios).isEmpty());

    }
}
