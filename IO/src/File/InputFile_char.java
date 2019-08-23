package File;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class InputFile_char {

	public static void main(String[] args) {
		try(InputStreamReader input = new InputStreamReader(new FileInputStream(new File("C:/upload/Kranka.txt")), "EUC-KR"  );
			   PrintWriter output = new PrintWriter(new FileWriter(new File("C:/upload/Maktto.txt"))) ){

			int readed =-1;
			char[] cs = new char[2];
			while((readed=input.read(cs))>0) {
				output.println(Arrays.toString(cs));
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
