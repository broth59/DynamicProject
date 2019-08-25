package reflection.method;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import packageEx.ClazzLoader;

public class reflection_method {
	
	public static void main(String[] args) {
		try {
			Doto doto = (Doto)ProxyProvider.getInstance(new Doto(), new Sidefunction());
			doto.setHead("head");
			System.out.println("get: "+doto.getHead());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	
	}
}





