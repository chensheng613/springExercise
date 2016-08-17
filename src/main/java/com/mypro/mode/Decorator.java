package com.mypro.mode;

public class Decorator implements SourceableI {

	private SourceableI source;
	
	public Decorator() {
		super();
	}

	public Decorator(SourceableI source) {
		super();
		this.source = source;
	}


	public void method() {
		System.out.println("begin decorator method");
		source.method();
		System.out.println("end decorator method");
	}

}
