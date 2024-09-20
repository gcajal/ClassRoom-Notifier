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
            String content = new String(Files.readAllBytes(Paths.get(archivosOrigenMaterias)));

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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public Set<String> ListarMateriasInscriptas() {
        Set<String> resultado = new HashSet<String>();
        try{
            String content = new String(Files.readAllBytes(Paths.get(archivosOrigenInscriptas)));

            // Crear instancia de ObjectMapper
            JSONObject json = new JSONObject(content);
            JSONArray materias = json.getJSONArray("materias");
            // Recorrer los elementos del JSONArray
            for (int i = 0; i < materias.length(); i++) {
                JSONObject amigo = materias.getJSONObject(i);
                String nombreMateria = amigo.getString(Integer.toString(i));

                resultado.add(nombreMateria);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultado;
    }
}
