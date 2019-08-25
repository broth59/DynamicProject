package reflection.method;

import java.lang.reflect.Method;

public interface SidefunctionEmplementer {
	public abstract Object invoke(Object obj, Method method, Object[] args );
}
