package Reseau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Communication {

	public Communication() {
		
	}

	public static void testClient() throws UnknownHostException, IOException {
		Socket s = new Socket("localhost",300);
		
		s.getOutputStream().write(("Test803\n").getBytes());
		
		BufferedWriter writerC = new BufferedWriter(new OutputStreamWriter(
				s.getOutputStream()));
		BufferedReader readerC = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		
		String g = "TEST";
	
		writerC.write(g);
		writerC.flush();
		
		String ligne;
		while((ligne = readerC.readLine()) != null) {
					
			System.out.println("Recu Client : " + ligne);
		}
		
		writerC.close();
		readerC.close();
		s.close();
		
	}
	
	public static void testServeur() throws IOException {
		ServerSocket server = new ServerSocket(300);
		Socket client = server.accept();
		
		BufferedWriter writerS = new BufferedWriter(new OutputStreamWriter(
				client.getOutputStream()));
		BufferedReader readerS = new BufferedReader(new InputStreamReader(
				client.getInputStream()));
		
		String line;
		
		while ( (line = readerS.readLine()) != null) {
			System.out.println("Recu Serveur : "+line);
			if (line.equals("Test803")) {
				writerS.write("OK !\n");
				writerS.flush();
			}
		}
		
		
		readerS.close();
		writerS.close();
		client.close();
		server.close();
	}

}