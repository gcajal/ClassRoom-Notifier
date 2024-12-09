/*
 * This source file was generated by the Gradle 'init' task
 */
package classroom.notifier;

import classroom.notifier.entity.*;
import classroom.notifier.implement.Observer;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassroomNotifier implements Observable{
	private Map<String, Observer> _observers;
	private Map<String, Observer> currentObservers;
	private boolean changed;

	@Override
	public synchronized void addObserver(Observer o) {
		if (o == null)
			throw new NullPointerException();

		//observers.add(o);
		this._observers.put(o.getClass().getSimpleName(),o);
	}

	@Override
	public void addCurrentObservers(String name) {
		currentObservers.put(name, _observers.get(name));
	}

	public Set<String> getAllObserversNames() {
		return _observers.keySet();
	}


	public Set<Observer> getCurrentObservers() {
		return currentObservers.values().stream().collect(Collectors.toSet());
	}

	public void refreshObservers(String name) {
		Iterator<Map.Entry<String, Observer>> iterator = currentObservers.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, Observer> entry = iterator.next();

			// Si la clave no es la que queremos conservar, la eliminamos
			if (!entry.getKey().equals(name)) {
				iterator.remove();
			}
		}
	}


	public synchronized void notifyObservers(Object arg) {
		getCurrentObservers().forEach(observer -> observer.update(arg));
	}
}
