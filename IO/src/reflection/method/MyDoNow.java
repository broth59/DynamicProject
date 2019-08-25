package reflection.method;

import anno.Stuff;

@Stuff
public class MyDoNow implements Donow {

	@Override
	public void printM() {
		System.out.println("M");
	}

	@Override
	public void printT() {
		System.out.println("T");
	}
	
}
