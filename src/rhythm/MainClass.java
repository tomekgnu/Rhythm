package rhythm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.sun.corba.se.impl.ior.ByteBuffer;
@SuppressWarnings("restriction")
public class MainClass {

	static BufferedReader br;
	static FileInputStream is;
	static FileOutputStream fos;
	public static void mainFunction(String[] args) {
		
		
		try {
			br = new BufferedReader(new FileReader("C:\\Users\\Tomek\\eclipse-workspace\\Rhythm\\src\\rhythm\\blues.in"));
			fos = new FileOutputStream("C:\\Users\\Tomek\\eclipse-workspace\\Rhythm\\src\\rhythm\\plik.bin");
			String line;
			ByteBuffer buffer = new ByteBuffer();
			while((line = br.readLine()) != null) {				
				String[] beatBar = line.split("\\s+");
				if(beatBar.length == 2) {
					short beats = Short.parseShort(beatBar[0]);
					short bar = Short.parseShort(beatBar[1]);
					
					buffer.append((byte)beats);
					buffer.append((byte)(beats >> 8));
					buffer.append((byte)bar);
					buffer.append((byte)(bar >> 8));	
				}
				else {
					
					for(int i = 0; i < beatBar.length; i++) {
						if(beatBar[i].equals("|") == false) {
							byte num = Byte.parseByte(beatBar[i]);
							buffer.append(num);							
						}
						else {
							byte num = Byte.parseByte("0");
							buffer.append(num);	
						}
					}
				}
			}
			buffer.trimToSize();
			fos.write(buffer.toArray());					
			fos.flush();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		
	}
}
