package com.mypro.mode;

public class MyBridge extends Bridge {

	public void method() {
		System.out.println("this is mybridge object");
		this.getSourceableI().method();
	}
}
