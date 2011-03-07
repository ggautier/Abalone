package Reseau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import controleur.Controleur;

public class Connexion extends Thread {

	protected String IPcible;
	
	protected int port;
	
	protected int statut;
	
	protected boolean heberge;
	
	protected String buffer;
	
	protected String toSend = "";
	
	protected Controleur controleur;
	
	protected BufferedWriter writerC = null;
	
	public final static int NOT_CONNECTED = 0;
	public final static int CONNECTED = 1;
	public final static int RECEPTIONCOUP = 11;
	public final static int ENVOICOUP = 12;
	public final static int RECEPTIONMSG = 14;
	public final static int ENVOIMSG = 15;
	public final static int DISCONNECTED = 2;
	
	public Connexion(Controleur c, String ip, int portCible) {
		this.controleur = c;
		this.IPcible = ip;
		if (this.IPcible.equals(""))
			this.heberge = true;
		else
			this.heberge = false;
		
		this.port = portCible;
		this.statut = NOT_CONNECTED;
	}
	
	public void run() {
		
		BufferedReader readerC = null;
		Socket socketClient = null;
		ServerSocket socketServeur = null;
		
		try {
			if (!heberge) {
				socketClient = new Socket(this.IPcible, this.port);
				socketClient.setSoTimeout(10000);
				socketClient.getOutputStream().write(("R_CONNECT\n"+InetAddress.getLocalHost().getHostAddress()+"\n").getBytes());

			}
			else {
				socketServeur = new ServerSocket(this.port);
				socketClient = socketServeur.accept();
			}
						
			writerC = new BufferedWriter(new OutputStreamWriter(
					socketClient.getOutputStream()));
			readerC = new BufferedReader(new InputStreamReader(
					socketClient.getInputStream()));
		}
		catch (IOException e) {
			
		}
		
		String ligne;
		String strEnvoi = "";
		
		try {
			while((ligne = readerC.readLine()) != null) {
						
				if (this.statut == NOT_CONNECTED) {
				
					if (!heberge) {
						
						System.out.println("Recu depuis Serveur : " + ligne);
						if (ligne.equals("OK_CONNECT")) {
							this.statut = CONNECTED;
							socketClient.setSoTimeout(300000);
							System.out.println("C : Confirmation de "+this.IPcible+" - "+this.port);
						}
						
					}
					else {
						
						System.out.println("Recu depuis Client : " + ligne);
						
						if (ligne.equals("R_CONNECT")) { // Reception d'une requete de connexion
							this.IPcible = readerC.readLine();
		
							System.out.println("H : Reception d'une requete");
							System.out.println("H : IP acceptee : "+this.IPcible+" - "+this.port);
							
							writerC.write("OK_CONNECT\n");
							writerC.flush();
					
							this.statut = CONNECTED;
						} 
						
					}
				}
				else if (this.statut != DISCONNECTED) {
				
					strEnvoi = protocole(ligne);
					System.out.println("recu: "+ligne+" -- toSend :"+strEnvoi);
					if (!strEnvoi.equals(""))
						socketClient.getOutputStream().write((strEnvoi).getBytes());

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
				socketClient.close();
			}
			catch (IOException e) {}
		}
		
		System.out.println("FERMETURE");
		
	}
	
	public String protocole(String ligne) throws IOException {
		String retourStr = "";
		System.out.println("Recu :"+ligne);
		
		if (ligne.equals("Coup")) {
			System.out.println("Reception d'un coup");
			buffer = "";
			statut = RECEPTIONCOUP;
		}
		else if (statut == RECEPTIONCOUP) {
			buffer += ligne+"\n";
			
			if (ligne.equals(";")) {
				System.out.println("||| "+buffer+" |||");
				statut = CONNECTED;
				retourStr = "COUP_RECU\n";
				this.controleur.action_from_string(buffer);
			}
			
		}
		else if (statut == ENVOICOUP) {
			System.out.println("Je dois envoyer : #"+toSend+"#");
			System.out.println("Envoi d'un coup");
			retourStr = new String(toSend);
			toSend = "";
			statut = CONNECTED;
		}
			
		return retourStr;
	}
	
	public synchronized boolean envoyer_coup(String str) throws IOException {
		this.toSend = str;
		System.out.println("#Coup\n"+str+"#");
		writerC.write("Coup\n"+str+"\n");
		writerC.flush();
		
		return true;
	}
	
}
