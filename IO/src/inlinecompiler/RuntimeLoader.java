package inlinecompiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class RuntimeLoader {
	@SuppressWarnings("all")
	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InterruptedException {
		Process process = null;
		try {
			System.out.println(System.getProperty("user.dir"));
			process = Runtime.getRuntime().exec("javac "+"-cp "+System.getProperty("user.dir")+"/bin"
												+" -d "+System.getProperty("user.dir")+"/bin "+"C:/upload/Child.java");
			
		
			BufferedReader buff  = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String mesg = "";
			while((mesg = buff.readLine()) != null) {
				System.out.println(mesg);
			}
		}finally {
			process.waitFor();
			
			Class<?> klass = Class.forName("classloading.Child");
			System.out.println(klass.getSuperclass());
			Object obj = klass.newInstance();
			Parent p = (Parent)obj;
			System.out.println(p);
		}

	}

}
