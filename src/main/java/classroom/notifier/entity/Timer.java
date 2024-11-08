package classroom.notifier.entity;

import classroom.notifier.implement.Filter;

import java.util.ArrayList;
import java.util.List;

public class Timer {
    private int frecuencia;
    List<Filter> filters;

    public Timer(String[] args){

        if(args.length > 2)
            this.frecuencia = Integer.decode(args[2]);
        else
            this.frecuencia = 10;

        this.filters = new ArrayList<Filter>();

        this.inicilizar();
    }

    private void inicilizar(){
        javax.swing.Timer timer = new javax.swing.Timer(frecuencia, e -> {
            //changesDetectedVisible = !changesDetectedVisible;
            this.notificarFilter();
        });

        timer.setRepeats(true);
        timer.start();
    }

    private void notificarFilter(){
        this.filters.forEach((i) -> i.execute(null));
    }

    public void addFilter(Filter filter){
        this.filters.add(filter);
    }


}
