package com.mypro.mode;

public class CaculatorTest {

	public static void main(String[] args) {
		AbstractCaculator abstractCaculator = new Caculator();
		String expression = "2+8";
		System.out.println(abstractCaculator.caculator(expression, "\\+"));
	}

}
