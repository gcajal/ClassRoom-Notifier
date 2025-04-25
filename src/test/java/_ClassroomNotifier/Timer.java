package _ClassroomNotifier;

public class Timer implements Runnable {
    private int frecuencia;
    private Runnable _runnable;

    public Timer(int frecuencia,Runnable runnable){
        this.frecuencia = frecuencia;
        this._runnable = runnable;

    }

   /* private void inicilizar(){
        javax.swing.Timer timer = new javax.swing.Timer(frecuencia, e -> {
            //changesDetectedVisible = !changesDetectedVisible;
            this.dataFromFile.Leer();
        });
        timer.setRepeats(true);
        timer.start();
    }*/

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(this.frecuencia);
                _runnable.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
