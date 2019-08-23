package PrimaryData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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

public class Data {

	public static void main(String[] args) {
		try( DataInputStream input = new DataInputStream(new FileInputStream(new File("C:/upload/Kranka.txt")));
				DataOutputStream output = new DataOutputStream(new FileOutputStream(new File("C:/upload/Kranka.txt"))) ){
			
			output.writeUTF("랑이");
			output.writeInt(5);
			output.writeBoolean(true);
			
			String name = input.readUTF();
			System.out.println(name);
		}catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
