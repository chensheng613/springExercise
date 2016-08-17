package com.mypro.mode;

import java.util.Enumeration;
import java.util.Vector;

public class AbstractSubject implements SubjectI {

	public Vector<ObserverI> observers = new Vector<ObserverI>();
	
	public void add(ObserverI observer) {
		observers.add(observer);

	}

	public void del(ObserverI observer) {
		observers.remove(observer);

	}

	public void notifyObservers() {
		Enumeration<ObserverI> enumer = observers.elements();
		while(enumer.hasMoreElements()){
			enumer.nextElement().update();
		}

	}

	public void operation() {

	}

}
