package ReferenceData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

public class Serial {

	public static void main(String[] args) throws ClassNotFoundException {
		try( 
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(new File("C:/upload/Locanda.txt")));
				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File("C:/upload/Locanda.txt"))) ){
			//만든 DTO
			DataDTOB data = new DataDTOB();
			data.setHead("헤드야");
			data.setContent("콘텐츠야");
			output.writeObject(data);			
			
			//HashMap 
			HashMap<String, Object> map = new HashMap<>();
			map.put("lotion", "body");
			output.writeObject(map);
			
			Object obj = null;
			try {
			while((obj=input.readObject())!=null) {
				if(obj instanceof HashMap) {
					System.out.println(obj);
				}else if(obj instanceof DataDTOB) {
					System.out.println(obj);
				}
			}
			}catch(EOFException e) {
				System.out.println("reading over");
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
