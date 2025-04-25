package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    private static ReadProperties instance = null;
    private Properties properties;

    private ReadProperties(String path){
        properties = new Properties();
        try{
            properties.load(new FileInputStream(new File(path)));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public static ReadProperties getInstance(String path){
        if (instance == null){
            synchronized (ReadProperties.class){
                if(instance == null){
                    instance = new ReadProperties(path);
                }
            }
        }
        return instance;
    }

    public String get(String key){
        return properties.getProperty(key);
    }
}
