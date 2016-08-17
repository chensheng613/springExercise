package com.mypro.mode;

public class MySubject extends AbstractSubject {

	@Override
	public void operation() {
		System.out.println("MySubject has run");
		this.notifyObservers();
	}
}
