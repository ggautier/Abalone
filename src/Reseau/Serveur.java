package Reseau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Serveur extends Thread {

	protected String IPclient;
	
	protected int port;
	
	protected int statut;
	
	public final static int NOT_CONNECTED = 0;
	public final static int CONNECTED = 1;
	public final static int RECEPTIONCOUP = 11;
	public final static int ENVOICOUP = 12;
	public final static int RECEPTIONMSG = 14;
	public final static int ENVOIMSG = 15;
	public final static int DISCONNECTED = 2;
	
	public Serveur(int port) {
		this.port = port;
		this.statut = NOT_CONNECTED;
	}
	
	public void run() {
		BufferedWriter writerS  = null;
		BufferedReader readerS = null;
		ServerSocket server = null;
		Socket  client = null;
		try {
			server = new ServerSocket(this.port);
			System.out.println("S : En attente de requete");
			client = server.accept();
			client.setSoTimeout(300000);
			server.setSoTimeout(300000);
			
			writerS = new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream()));
			readerS = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
		}
		catch (IOException e) {}
			
			String ligne;
			String strCoup = "";
			String msg = "";
			
		
		try {
			while ( (ligne = readerS.readLine()) != null) {
				System.out.println("Recu Serveur : "+ligne);
				if (statut == NOT_CONNECTED) {
					if (ligne.equals("C_CONTACT")) {
						this.IPclient = readerS.readLine();
	
						writerS.write("OK_CONNECT\n");
						writerS.flush();
				
						System.out.println("S : Reception d'une requete");
						System.out.println("S : IP acceptee : "+this.IPclient+" - "+this.port);
						
						this.statut = CONNECTED;
						
					} 
				}
				else if (statut == CONNECTED) {
					if (ligne.equals("Coup")) {
						strCoup = "";
						statut = RECEPTIONCOUP;
					}

				}
				else if (statut == RECEPTIONCOUP) {
					strCoup += ligne+"\n";
					
					if (ligne.equals(";")) {
						System.out.println("||| "+strCoup+" |||");
						client.getOutputStream().write(("COUP_RECU\n").getBytes());
						statut = CONNECTED;
					}
				}
			}
		
		} 
		catch (SocketTimeoutException e) {
			System.err.println("Timeout");
		}
		catch (java.net.SocketException e) {
			System.err.println("Socket ferme");
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				server.close();
				client.close();
				readerS.close();
				writerS.close();
			}
			catch (IOException e) {}

		}
	}
}
