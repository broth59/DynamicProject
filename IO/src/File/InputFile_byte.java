package File;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InputFile_byte {

	public static void main(String[] args) {
		try(FileInputStream input = new FileInputStream(new File("C:/upload/Kranka.txt"));
			   FileOutputStream output = new FileOutputStream(new File("C:/upload/Maktto.txt"), true) ){
			int readed =-1;
			while((readed=input.read())!=-1) {
				output.write(readed);
			}
			output.flush();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
