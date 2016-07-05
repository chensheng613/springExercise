package com.mypro.mode;

public class SubjectVisitorTest {

	public static void main(String[] args) {

		VisitorI visitorI = new MyVisitor();
		
		Subject subject = new MySubjectV();
		
		subject.accept(visitorI);
		
	}

}
