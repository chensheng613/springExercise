package com.mypro.mode;

public class MyVisitor implements VisitorI {

	public void visit(Subject sub) {
		System.out.println(sub.getSubject());
	}

}
