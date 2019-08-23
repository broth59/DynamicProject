package Keyborad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Arrays;

public class InputSystem_buffered {

	public static void main(String[] args)  {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
		System.out.println("hi");
		ByteBuffer bbuff = ByteBuffer.allocate(1024);
		CharBuffer cbuff = bbuff.asCharBuffer();
		
		int readed = reader.read(cbuff);
		cbuff.put('C');
		OutputStreamWriter out = new OutputStreamWriter(System.out);
		System.out.println(cbuff);

		}catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
