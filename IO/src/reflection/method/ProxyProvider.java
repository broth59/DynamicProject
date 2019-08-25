package reflection.method;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProxyProvider {

	private static Object target;
	private static Class<?> target_clazz;

	private static StringBuffer sb;
	private static String separator = System.getProperty("line.separator");

	private static Object wrapped;
	private static SidefunctionEmplementer outer;

	private static void set(Object target, SidefunctionEmplementer outer) {
		ProxyProvider.target = target;
		ProxyProvider.target_clazz = target.getClass();

		ProxyProvider.outer = outer;
	}

	private static void line(String line) {
		sb.append(line + separator);
	}

	private static void def(String line) {
		sb.append("\t\t" + line + ";" + separator);
	}

	private static void block(String line) {
		sb.append("\t\t" + line + separator);
	}

	private static Object cheat() throws Exception {
		System.out.println(target_clazz.getName());

		// 스태틱 멤버 셋팅
		ProxyProvider.set(target, outer);
		String parent = target_clazz.getName();
		Class<?>[] interfaces = target.getClass().getInterfaces();

		// 자바 파일 생성
		String prop = "$$" + String.valueOf(System.currentTimeMillis());

		File temp_src = new File("./src/" + prop);
		if (!temp_src.exists())temp_src.mkdirs();
		File temp_bin = new File("./bin/" + prop);
		if (!temp_bin.exists())temp_bin.mkdirs();

		BufferedWriter out = new BufferedWriter(new FileWriter("./src/" + prop + "/Saber.java"));
		sb = new StringBuffer();

		// 패키지 작성
		line("package " + prop + ";");

		// 임포트 작성
		line("import java.lang.reflect.Method;");
		line("import java.lang.reflect.Parameter;");

		// 클래스명 작성
		line("public class Saber extends " + parent + " implements reflection.method.AbstractProxer" + "{");

		// 멤버 생성
		line("  " + "private " + parent + " src;");
		line("  " + "private " + "reflection.method.SidefunctionEmplementer" + " outer;");

		//메소드 문자열 처리 패턴
		String name_regex = "\\s[a-zA-z]{1,30}[.]\\S{0,100}[(]";
		Pattern name_pattern = Pattern.compile(name_regex);
		Pattern without_pattern = Pattern.compile("[a-zA-z]{1,50}[(]");
		Pattern param_pattern = Pattern.compile("[(]\\S{1,100}[)]");

		//메소드 생성
		Method[] methodes = target_clazz.getDeclaredMethods();
		for (Method method : methodes) {
			// 어노테이션 처리


			// 접근 제어자 처리
			String modifier = Modifier.toString(method.getModifiers());
			// 반환 타입 처리
			Class<?> return_type = method.getReturnType();
			String return_class = return_type.getName();
			String result_statement = "";
			String return_statement = "";
			if(!return_class.equals("void")) {
				result_statement = "result = ";
				return_statement = "return ("+return_class+")result";
			}
			// 함수명 처리
			String method_name = method.getName();
			// 파라미터 처리
			Parameter[] parameters = method.getParameters();
			int param_count = method.getParameterCount();
			String params = "";
			String params_class = "";
			String param_statement = "(";
			for (Parameter param : parameters) {
				params += param.getName() + ",";
				params_class += param.getType().getName() + ".class,";
				param_statement += param.getType().getName()+" "+param.getName()+",";
				System.out.println(param_statement);
			}
			if(param_count!=0) {
				params = params.substring(0, params.length()-1);
				params_class = params_class.substring(0, params_class.length()-1);				
				param_statement = param_statement.substring(0, param_statement.length()-1);									
			}
			param_statement += ")";
			
			// 메소드 시그니쳐 처리
			String full_name = method.toGenericString();

				//함수명 처리
			Matcher matcher = name_pattern.matcher(full_name);
			StringBuffer strb = new StringBuffer();
			while (matcher.find()) {
				String finded = full_name.substring(matcher.start(), matcher.end());
				Matcher without_matcher = without_pattern.matcher(finded);
				without_matcher.find();
				String inner = finded.substring(without_matcher.start(), without_matcher.end());
				matcher.appendReplacement(strb, " " + inner);
			}
			matcher.appendTail(strb);

				//파라미터 처리
			String temp_method = strb.toString();
			Matcher param_matcher = param_pattern.matcher(temp_method);
			strb = new StringBuffer();
			while(param_matcher.find()) {
				param_matcher.appendReplacement(strb, param_statement);	
			}
			param_matcher.appendTail(strb);
			sb.append("\t"+strb.toString());

			// 선언문 처리
			line("{");
			
			def("Object result = null");
			block("try{");
			def("String method_name = \"" + method_name + "\"");
			def("Object[] args = new Object[]{" + params + "}");
			def("Class<?>[] args_class = new Class<?>[]{" + params_class + "}");

			def("Method method = src.getClass().getMethod(method_name, args_class)");
			def(result_statement+"outer.invoke(src, method, args)");
			block("}catch(Exception e){");
			def("e.printStackTrace()");
			block("}");
			def(return_statement);

			line("\t" + "}");

		}

		// 세터 처리
		line("\t" + "public void setWrapped(Object obj){");
		line("\t\t" + "this.src = (" + parent + ")obj;");
		line("\t" + "}");

		line("\t" + "public void setOuter(reflection.method.SidefunctionEmplementer outer){");
		line("\t\t" + "this.outer = outer;");
		line("\t" + "}");

		line("}");
		System.out.println(sb);

		out.write(sb.toString());
		out.flush();
		out.close();

		// 컴파일
		String context = System.getProperty("user.dir");
		Process process = null;
		try {

			process = Runtime.getRuntime().exec("javac -cp " + context + "/bin " + "-d " + context + "/bin " + context
					+ "/src/" + prop + "/Saber.java");

		} finally {
			process.waitFor();

			BufferedReader cmd_input = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String readed_line = null;
			while ((readed_line = cmd_input.readLine()) != null) {
				System.out.println("cmd: " + readed_line);
			}
		}

		// 객체생성
		Class<?> klazz = Class.forName(prop + ".Saber");
		wrapped = klazz.getDeclaredConstructor(new Class<?>[] {}).newInstance(new Object[] {});
		// target 셋팅
		AbstractProxer proxer = (AbstractProxer) wrapped;
		proxer.setWrapped(target);
		proxer.setOuter(outer);

		// 폴더 삭제
		temp_bin.deleteOnExit();
		temp_src.deleteOnExit();
		new File("./src/" + prop + "/Saber.java").deleteOnExit();
		new File("./bin/" + prop + "/Saber.class").deleteOnExit();

		return wrapped;
	}

	public static Object getInstance(Object target, SidefunctionEmplementer outer) throws Exception {
		set(target, outer);
		return cheat();
	}
}
