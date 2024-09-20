package classroom.notifier.entity;

import classroom.notifier.entity.implement.Observer;

import java.util.HashSet;
import java.util.Set;

public abstract class Observable {
    Set<Observer> observers;
    private boolean changed;

    public Observable(){
        this.observers = new HashSet<Observer>();
        this.changed = false;
    }

    public synchronized void addObserver(Observer o) {
        if (o == null)
            throw new NullPointerException();
        if (!observers.contains(o)) {
            observers.add(o);
        }
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
    public synchronized void deleteObserver(Observer o) {
        observers.remove(o);
    }

    public synchronized  void notifyObservers(Object arg){

        if (!changed) return;

        Object[] arrLocal = observers.toArray();

        for (int i = arrLocal.length-1; i>=0; i--)
            ((Observer)arrLocal[i]).update(this, arg);

        clearChanged();
    }

}
