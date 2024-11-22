package classroom.notifier.implement;

import classroom.notifier.entity.Observable;

public interface Observer {
    public void update(Observable observable, Object data);
}
