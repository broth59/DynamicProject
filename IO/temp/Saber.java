import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
public class Saber implements reflection.method.Donow,reflection.method.AbstractProxer{
  private reflection.method.Donow src;
  private reflection.method.SidefunctionEmplementer outer;
	public void printT(){
try{
		String method_name = "printT";
		Object[] args = new Object[]{};
		Class<?>[] args_class = new Class<?>[]{};
		Method method = src.getClass().getMethod(method_name, args_class);
		outer.invoke(src, method, args);
}catch(Exception e){
		};
	}
	public void printM(){
try{
		String method_name = "printM";
		Object[] args = new Object[]{};
		Class<?>[] args_class = new Class<?>[]{};
		Method method = src.getClass().getMethod(method_name, args_class);
		outer.invoke(src, method, args);
}catch(Exception e){
		};
	}
	public void setWrapped(Object obj){
		this.src = (reflection.method.Donow)obj;
	}
	public void setOuter(reflection.method.SidefunctionEmplementer outer){
		this.outer = outer;
	}
}
