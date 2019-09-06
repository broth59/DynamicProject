package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class PathTester {

	public static void main(String[] args)  {
		Path path = Paths.get("C:/upload/gray_round.png");
		Path path2 = Paths.get("C:/upload/gray_round2.png");
		try {
			path.toFile().createNewFile();
	
		System.out.println(path.getFileName());
		FileChannel chan = FileChannel.open(path2, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
		FileChannel chan2 = FileChannel.open(path, StandardOpenOption.READ);
		ByteBuffer bob = ByteBuffer.allocate(20);
		
		int read = -1;
		while((read = chan2.read(bob))>0) {
			bob.flip();
			chan.write(bob);
			bob.clear();
		}
		chan2.read(bob);
		chan.write(bob);
		
		chan.close();
		chan2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
