package classroom.notifier.entity;

import classroom.notifier.implement.Observer;

import java.util.HashSet;
import java.util.Set;

public abstract class Observable<T> {
    Set<Observer<T>> observers;
    private boolean changed;

    public Observable(){
        this.observers = new HashSet<>();
        this.changed = false;
    }

    public synchronized void addObserver(Observer<T> o) {
        if (o == null)
            throw new NullPointerException();
        
        observers.add(o);
    }

    protected synchronized void setChanged() {
        changed = true;
    }

    protected synchronized void clearChanged() {
        changed = false;
    }

    /**
     * Deletes an observer from the set of observers of this object.
     * Passing {@code null} to this method will have no effect.
     * @param   o   the observer to be deleted.
     */
    public synchronized void deleteObserver(Observer<T> o) {
        observers.remove(o);
    }

    public synchronized  void notifyObservers(T arg){

        //if (!changed) return;

        Object[] arrLocal = observers.toArray();

        for (int i = arrLocal.length-1; i>=0; i--)
            ((Observer<T>)arrLocal[i]).update(this, arg);

        clearChanged();
    }

}
