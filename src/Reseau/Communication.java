package Reseau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import controleur.Controleur;

public class Communication {
	
	protected boolean heberge;
	
	protected String ip_partenaire;
	
	protected int port;
	
	protected Controleur controleur;
	
	protected boolean contact_etabli;

	
	public Communication(Controleur cont, String ip, int port) throws IOException {
		
		this.controleur = cont;
		
		if (ip.equals("") || ip == null)
			this.heberge = true;
		else {
			this.heberge = false;
			this.ip_partenaire = ip;
		}
		
		this.contact_etabli = false;
		this.port = port;
		
		this.etablir_contact();
		
		
	}
	
	public boolean etablir_contact() throws IOException {
		
		if (heberge) {
			ServerSocket server = new ServerSocket(this.port);
			Socket client = server.accept();
			
			BufferedWriter writerS = new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream()));
			BufferedReader readerS = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			
			String line;
			String str = "";
			while ( (line = readerS.readLine()) != null) {
				System.out.println("Recu Serveur : "+line);
				if (line.equals("C_CONTACT")) {
					writerS.write("OK\n");
					writerS.flush();
					System.out.println("S : Reception d'une requete");
					this.contact_etabli = true;
				} else if (this.contact_etabli) {
					this.ip_partenaire = line;
					System.out.println("S : IP acceptee : "+line);
				}
			}
			
			
			readerS.close();
			writerS.close();
			client.close();
			server.close();
		}
		else {
			Socket s = new Socket(this.ip_partenaire,this.port);
			
			s.getOutputStream().write(("C_CONTACT\n"+InetAddress.getLocalHost().getHostAddress()+"\n").getBytes());
			System.out.println("C : Envoi d'une requete a "+this.ip_partenaire+" depuis "+InetAddress.getLocalHost().getHostAddress());
			
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
				if (ligne.equals("OK")) {
					this.contact_etabli = true;
					System.out.println("C : Confirmation de "+this.ip_partenaire);
				}
			
			}
			
			writerC.close();
			readerC.close();
			s.close();
			
			return true;
		}
		
		return true;
		
	}
	
	public String attendre_coup() throws IOException {
		
		ServerSocket server = new ServerSocket(this.port);
		Socket client = server.accept();
		
		BufferedWriter writerS = new BufferedWriter(new OutputStreamWriter(
				client.getOutputStream()));
		BufferedReader readerS = new BufferedReader(new InputStreamReader(
				client.getInputStream()));
		
		String line;
		String str = "";
		while ( (line = readerS.readLine()) != null) {
			System.out.println("Recu Serveur : "+line);
			if (line.equals("Coup")) {
				writerS.write("OK !\n");
				writerS.flush();
			}
		}
		
		
		readerS.close();
		writerS.close();
		client.close();
		server.close();
		
		return str;

	}
		
	public boolean envoyer_coup() throws UnknownHostException, IOException {
		Socket s = new Socket(this.ip_partenaire,this.port);
		
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
		
		return true;
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