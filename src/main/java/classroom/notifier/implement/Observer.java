package classroom.notifier.implement;

import classroom.notifier.entity.Observable;

public interface Observer<T> {
    public void update(Observable<T> observable, T arg);
}
