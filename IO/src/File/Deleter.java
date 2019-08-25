package File;

import java.io.File;
import java.io.IOException;

public class Deleter {
	public static void main(String[] args) throws IOException {
		File file1 = new File("./file");
		file1.mkdir();
		file1.deleteOnExit();
		File file2 = new File("./file/sfsdf.txt");
		file2.createNewFile();
		file2.deleteOnExit();
		
		
		
	}
}
