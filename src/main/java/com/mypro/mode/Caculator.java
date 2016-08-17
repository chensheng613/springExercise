package com.mypro.mode;

public class Caculator extends AbstractCaculator {

	@Override
	public int caculator(int x, int y) {
		System.out.println("抽象类调用此类方法");
		return x+y;
	}

}
