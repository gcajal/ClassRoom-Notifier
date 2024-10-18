package classroom.notifier.entity;

import classroom.notifier.implement.Filter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DataFromFile  implements Filter {
    private String archivosOrigenMaterias;
    private String archivosOrigenInscriptas;
    List<Filter> filters;

    public DataFromFile(String archivosOrigenInscriptas, String archivosOrigenMaterias){
        this.archivosOrigenMaterias = archivosOrigenMaterias;
        this.archivosOrigenInscriptas = archivosOrigenInscriptas;
        this.filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter filter){
        this.filters.add(filter);
    }



    public Map<String, String> ListarMateriasAulas() {

        Map<String, String> resultado = new HashMap<String,String>();
        try{
            String content = ReadFile(archivosOrigenMaterias);

        // Crear instancia de ObjectMapper
            JSONObject json = new JSONObject(content);
            JSONArray materias = json.getJSONArray("materias");
            // Recorrer los elementos del JSONArray
            for (int i = 0; i < materias.length(); i++) {
                JSONObject amigo = materias.getJSONObject(i);
                String nombre = amigo.getString("nombre");
                String aula = amigo.getString("aula");
                resultado.put(nombre,aula);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }


    public Map<String, List<Alumno>> ListarMateriasInscriptas() {
        Map<String, List<Alumno>> resultado = new HashMap<String, List<Alumno>>();
        try{
            String content = ReadFile(archivosOrigenInscriptas);

            // Crear instancia de ObjectMapper
            JSONObject json = new JSONObject(content);
            JSONArray asignacion = json.getJSONArray("asignacion");
            // Recorrer los elementos del JSONArray
            for (int i = 0; i < asignacion.length(); i++) {
                JSONObject asignacionMateria = asignacion.getJSONObject(i);
                String nombreMateria = asignacionMateria.getString("materia");
                JSONArray alumnosArr = asignacionMateria.getJSONArray("alumnos");
                List<Alumno> alumnos = new ArrayList<Alumno>();
                for (int j = 0; j < alumnosArr.length(); j++) {
                    JSONObject alumno = alumnosArr.getJSONObject(j);
                    String nombre = alumno.getString("Nombre");
                    String email = alumno.getString("Email");
                    String telefono = alumno.getString("Telefono");;
                    String documento = alumno.getString("Documento");;
                    alumnos.add(new Alumno(nombre,email,telefono,documento));
                }

                resultado.put(nombreMateria,alumnos);
                //resultado.add(nombreMateria);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    private String ReadFile(String file){
        String content = "";
        try{
            content = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/"+file)));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return content;

    }

    @Override
    public void execute(Object obj) {
        Map<String,String> datos = this.ListarMateriasAulas();
        notificarFilter(datos);
    }

    private void notificarFilter(Object obj){
        this.filters.forEach((i) -> i.execute(obj));
    }
}
