package classroom.notifier.entity;

import classroom.notifier.implement.Filter;

import java.util.ArrayList;
import java.util.List;

public class Timer {
    private int frecuencia;
    List<Filter> filters;

    public Timer(int frecuencia){
        this.frecuencia = frecuencia;
        this.filters = new ArrayList<Filter>();

        this.inicilizar();
    }

    private void inicilizar(){
        javax.swing.Timer timer = new javax.swing.Timer(2000, e -> {
            //changesDetectedVisible = !changesDetectedVisible;
            this.notificarFilter();
        });

        timer.setRepeats(false);
        timer.start();
    }

    private void notificarFilter(){
        this.filters.forEach((i) -> i.execute(null));
    }

    public void addFilter(Filter filter){
        this.filters.add(filter);
    }


}
