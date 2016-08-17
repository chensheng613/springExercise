package com.mypro.mode;

public class Proxy implements SourceableI {

	private Source source;
	
	public Proxy() {
		super();
		this.source = new Source(); 
	}


	public void method() {
		System.out.println("begin proxy");
		source.method();
		System.out.println("end proxy");
	}
	
}
