package Keyborad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Arrays;

public class InputSystem_in {

	public static void main(String[] args)  {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
		System.out.println("hi");
		byte[] bits = new byte[1024];
//		int readed = reader.(bits);
		OutputStreamWriter out = new OutputStreamWriter(System.out);
//		out.write(new String(bits, 0, readed));
		out.append("sdr");
		
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
