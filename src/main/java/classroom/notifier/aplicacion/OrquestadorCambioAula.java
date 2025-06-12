package classroom.notifier.aplicacion;

public class OrquestadorCambioAula {
    private Diferenciador diferenciador;
    private ClassroomMateriaAula asignacionMateriaAula;
    private ManejadorDiferencia manejadorDiferencia;


    public OrquestadorCambioAula(Diferenciador diferenciador, ManejadorDiferencia manejadorDiferencia) {
        this.diferenciador = diferenciador;
        this.manejadorDiferencia = manejadorDiferencia;
    }

    public void actualizarMaterias(ClassroomMateriaAula novedad) {
        Object diferencia = this.diferenciador.obtenerDiferencias(novedad,this.asignacionMateriaAula);
        asignacionMateriaAula = novedad;
        this.manejadorDiferencia.manejarDiferencia(diferencia);
    }
}
