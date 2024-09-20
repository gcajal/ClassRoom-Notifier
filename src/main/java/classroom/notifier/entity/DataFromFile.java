package classroom.notifier.entity;

import classroom.notifier.entity.implement.LecturaDatos;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DataFromFile implements LecturaDatos {
    private String archivosOrigenMaterias;
    private String archivosOrigenInscriptas;

    public DataFromFile(String archivosOrigenInscriptas, String archivosOrigenMaterias){
        this.archivosOrigenMaterias = archivosOrigenMaterias;
        this.archivosOrigenInscriptas = archivosOrigenInscriptas;
    }


    @Override
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

    @Override
    public Set<String> ListarMateriasInscriptas() {
        Set<String> resultado = new HashSet<String>();
        try{
            String content = ReadFile(archivosOrigenInscriptas);

            // Crear instancia de ObjectMapper
            JSONObject json = new JSONObject(content);
            JSONArray materias = json.getJSONArray("materias");
            // Recorrer los elementos del JSONArray
            for (int i = 0; i < materias.length(); i++) {
                String nombreMateria = materias.getString(i);

                resultado.add(nombreMateria);
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
}
