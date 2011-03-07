package Reseau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Client extends Thread {

	protected String IPserveur;
	
	protected int port;
	
	protected int statut;
	
	public final static int NOT_CONNECTED = 0;
	public final static int CONNECTED = 1;
	public final static int RECEPTIONCOUP = 11;
	public final static int ENVOICOUP = 12;
	public final static int RECEPTIONMSG = 14;
	public final static int ENVOIMSG = 15;
	public final static int DISCONNECTED = 2;
	
	public Client(String IPcible, int portCible) {
		this.IPserveur = IPcible;
		this.port = portCible;
		this.statut = NOT_CONNECTED;
	}
	
	public void run() {
		BufferedWriter writerC = null;
		BufferedReader readerC = null;
		Socket s = null;
		
		
		try {
			s = new Socket(this.IPserveur, this.port);
			s.setSoTimeout(10000);
			
			s.getOutputStream().write(("C_CONTACT\n"+InetAddress.getLocalHost().getHostAddress()+"\n").getBytes());
			System.out.println("C : Envoi d'une requete a "+this.IPserveur+" depuis "+InetAddress.getLocalHost().getHostAddress());
			
			writerC = new BufferedWriter(new OutputStreamWriter(
					s.getOutputStream()));
			readerC = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
		}
		catch (IOException e) {
			
		}
		

		
		String ligne;
		String strCoup = "";
		
		try {
			while((ligne = readerC.readLine()) != null) {
						
				if (statut == NOT_CONNECTED) {
				System.out.println("Recu Client : " + ligne);
					if (ligne.equals("OK_CONNECT")) {
						this.statut = CONNECTED;
						s.setSoTimeout(300000);
						System.out.println("C : Confirmation de "+this.IPserveur+" - "+this.port);
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
						s.getOutputStream().write(("COUP_RECU\n").getBytes());
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
		} 
		finally {
			try {
				writerC.close();
				readerC.close();
				s.close();
			}
			catch (IOException e) {}
		}
		
	}
	
	
}
