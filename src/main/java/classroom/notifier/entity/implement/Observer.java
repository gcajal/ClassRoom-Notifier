package classroom.notifier.entity.implement;

import classroom.notifier.entity.Observable;

public interface Observer {
    public void update(Observable observable, Object arg);
}
