package packageEx;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClazzLoader {

	private static List<Class<?>> findClasses(File file, String package_name) throws ClassNotFoundException {
		List<Class<?>> class_list = new ArrayList<Class<?>>();
		File[] files = file.listFiles();
		
		String integrated = package_name+".";
		if(package_name.length()==0)integrated="";

		for (File one : files) {
			String file_name = one.getName();
			if (one.isDirectory()) {
				class_list.addAll(findClasses(one, integrated+ file_name));
			} else if (file_name.endsWith(".class")) {
				class_list.add(Class.forName(integrated+file_name.substring(0, file_name.length() - 6)));
			}
		}
		return class_list;
	}

	public static List<Class<?>> load(String base_package) {
		String path = base_package.replace(".", "/");

		ClassLoader class_loader = Thread.currentThread().getContextClassLoader();
		ArrayList<Class<?>> class_list = null;
		
		try {

			Enumeration<URL> urls = class_loader.getResources(path);
			ArrayList<File> dirs = new ArrayList<File>();

			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				dirs.add(new File(url.getFile()));
			}
			class_list = new ArrayList<Class<?>>();
			for (File dir : dirs) {
				class_list.addAll(findClasses(dir, base_package));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return class_list;
	}
}