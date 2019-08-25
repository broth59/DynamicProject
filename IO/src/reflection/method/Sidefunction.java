package reflection.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Sidefunction implements SidefunctionEmplementer{

	@Override
	public Object invoke(Object obj, Method method, Object[] args) {
		Object result = null;
		try {
			//before
			result = method.invoke(obj, args);
			//after
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			result = null;
		}
		
		return result;
	}
	
}
