package com.mypro.mode;

public abstract class AbstractCaculator {

	public final int caculator(String exp,String opt){
		int array[] = this.split(exp, opt);
		return caculator(array[0],array[1]);
	}
	
	public int[] split(String exp,String opt){  
        String array[] = exp.split(opt);  
        int arrayInt[] = new int[2];  
        arrayInt[0] = Integer.parseInt(array[0]);  
        arrayInt[1] = Integer.parseInt(array[1]);  
        return arrayInt;  
    } 
	
	abstract public int caculator(int x,int y);
}
