package com.mypro.mode;

public interface Subject {

	public void accept(VisitorI visit);
	
	public String getSubject();
}

