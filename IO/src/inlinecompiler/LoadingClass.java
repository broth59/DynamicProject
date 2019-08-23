package inlinecompiler;

public class LoadingClass {

	public static void main(String[] args) {
		try {
			ClassLoader loader = new ClassLoader() {};
			Class<?> klass1 = LoadingClass.class.getClassLoader().loadClass("classloading.Kulunga");
			Class<?> klass2 = loader.loadClass("classloading.Kulunga");
			Class<?> klass3 = Class.forName("classloading.Kulunga");
			
			System.out.println(klass1.getDeclaredConstructor(Integer.class).newInstance());
			System.out.println(klass2);
			System.out.println(klass3);
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
