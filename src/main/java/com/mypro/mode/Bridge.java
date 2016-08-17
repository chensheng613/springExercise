package com.mypro.mode;

public abstract class Bridge {

	private SourceableI sourceableI;

	public SourceableI getSourceableI() {
		return sourceableI;
	}

	public void setSourceableI(SourceableI sourceableI) {
		this.sourceableI = sourceableI;
	}
	
	public void method(){
		sourceableI.method();
	}
}
