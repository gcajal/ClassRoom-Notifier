package classroom.notifier.entity;

public class Alumno {
    private String Nombre;
    private String Email;
    private String Telefono;
    private String Documento;

    public Alumno(String nombre, String email, String telefono, String documento) {
        this.Nombre = nombre;
        this.Email = email;
        this.Telefono = telefono;
        this.Documento = documento;
    }
}
