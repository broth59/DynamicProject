package inlinecompiler;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

public class SystemProperties {

	public static void main(String[] args) {
		Properties props = System.getProperties();
		Iterator<Object> ite = props.keySet().iterator();
		while(ite.hasNext()) {
			Object next = ite.next();
			System.out.println(next +":"+props.getProperty(next.toString()));
		}
		System.out.println(props.get("user.dir"));

	}

}
