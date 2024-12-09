package classroom.notifier.entity;

import classroom.notifier.implement.Observer;

import java.util.*;


public interface Observable {
     void addObserver(Observer o) ;
     void addCurrentObservers(String name);

}
