package annotation.surpress;

import reflection.method.Donow;

public class SurpressTester {
	
	@SuppressWarnings("all")
	public static void main(String[] args) {
		System.out.println("Y");
		Object obj = new Object();
		Donow now = (Donow)obj;
	}

}
