package _ClassroomNotifier;

import classroom.notifier.implement.InformadorDatos;
import classroom.notifier.implement.Observer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DataFromFile  extends InformadorDatos {
    private String archivosOrigenMaterias;

    public DataFromFile(String archivosOrigenInscriptas, String archivosOrigenMaterias){
        this.archivosOrigenMaterias = archivosOrigenMaterias;
    }

    @Override
    public Map<String, String> Leer() {

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

        if(!resultado.isEmpty()) this.notifyObservers(resultado);

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
