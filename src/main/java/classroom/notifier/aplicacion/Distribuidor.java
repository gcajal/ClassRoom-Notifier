package classroom.notifier.aplicacion;

public class Distribuidor {

    private ClassroomNotifier classroomNotifier;

    public Distribuidor(ClassroomNotifier classroomNotifier) {
        this.classroomNotifier = classroomNotifier;
    }

    public void distribuir(String mensaje) {
        classroomNotifier.getCurrentObservers().forEach(observer -> observer.update(mensaje));
    }
}
