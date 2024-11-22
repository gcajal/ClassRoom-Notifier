package _ClassroomNotifier;

import classroom.notifier.entity.DatosListener;

import java.util.ArrayList;
import java.util.List;

public class Timer {
    private int frecuencia;
    private DataFromFile DataFromFile;

    public Timer(int frecuencia,DataFromFile _DataFromFile){
        this.frecuencia = frecuencia;
        this.DataFromFile = _DataFromFile;
        this.inicilizar();
    }

    private void inicilizar(){
        javax.swing.Timer timer = new javax.swing.Timer(frecuencia, e -> {
            //changesDetectedVisible = !changesDetectedVisible;
            this.DataFromFile.Leer();
        });
        timer.setRepeats(true);
        timer.start();
    }


}
