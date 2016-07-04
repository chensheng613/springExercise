package com.mypro.mode;

public class MyBridgeTest {

	public static void main(String[] args) {
		 Bridge bridge = new MyBridge(); 
		 SourceSub1 source1 = new SourceSub1(); 
		 bridge.setSourceableI(source1);
	     bridge.method();
	}

}
