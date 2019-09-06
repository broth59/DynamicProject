package reflection.method;

import packageEx.ClazzLoader;

public class reflection_method {
	
	public static void main(String[] args) {
		try {
			Doto doto = (Doto)ProxyProvider.getInstance(new Doto(), new Sidefunction());
			doto.setHead("head");
			System.out.println(ClazzLoader.load(""));
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
	}
}





