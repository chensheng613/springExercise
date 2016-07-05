package com.mypro.mode;

public class MySubjectV implements Subject {
	

	public void accept(VisitorI visit) {
		visit.visit(this);
	}

	public String getSubject() {
		return "this is subject";
	}
}
