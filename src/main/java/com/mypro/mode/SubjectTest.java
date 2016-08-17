package com.mypro.mode;

public class SubjectTest {

	public static void main(String[] args) {
		MySubject subject = new MySubject();
		ObserverI ob1 = new Observer1();
		ObserverI ob2 = new Observer2();
		subject.add(ob1);
		subject.add(ob2);
		subject.operation();

	}

}
