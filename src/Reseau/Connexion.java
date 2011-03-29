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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import controleur.Controleur;

public class Connexion extends Thread {
	
	public static boolean effectuee = false;
	// Pour verifier si la connexion est deja ouverte
	
	protected Controleur controleur;


	protected String IPcible;
	// Adresse IP du partenaire
	
	protected int port;
	// Numero de port d'ecoute
	
	protected boolean heberge;
	// Determine si on est le client ou le serveur
	
	protected String buffer;
		
	
	protected BufferedWriter writerC = null;
	
	/////////
	protected int statut;
	
	public final static int NOT_CONNECTED = 0;
	public final static int CONNECTED = 1;
	public final static int RECEPTIONCOUP = 11;
	public final static int ENVOICOUP = 12;
	public final static int RECEPTIONMSG = 14;
	public final static int ENVOIMSG = 15;
	public final static int DISCONNECTED = 2;
	///////////////////////////
	
	public Connexion(Controleur c, String ip, int portCible) {
		Connexion.effectuee = true;
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
		
		// On initialise les Buffer et Socket
		BufferedReader readerC = null;
		Socket socketClient = null;
		ServerSocket socketServeur = null;
		
		try {
			if (!heberge) {
				// Si on est client
				socketClient = new Socket(this.IPcible, this.port);
				socketClient.setSoTimeout(10000);
				socketClient.getOutputStream().write(("R_CONNECT\n"+InetAddress.getLocalHost().getHostAddress()+"\n").getBytes());
				// On initialise le Socket, puis on envoie une requete de connexion au serveur
				
			}
			else {
				// Si on est serveur
				socketServeur = new ServerSocket(this.port);
				socketClient = socketServeur.accept();
				// On ecoute au port demande.
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
			// Tant que le Socket est ouvert ...
			while((ligne = readerC.readLine()) != null) {
						
				if (this.statut == NOT_CONNECTED) {
				
					if (!heberge) {
						// On est client
						System.out.println("Recu depuis Serveur : " + ligne);
						if (ligne.equals("OK_CONNECT")) {
							// Si le Serveur a accepte notre requete de connexion ...
							this.statut = CONNECTED; // ... alors nous sommes connectes
							socketClient.setSoTimeout(300000);
							System.out.println("C : Confirmation de "+this.IPcible+" - "+this.port);
						}
						
					}
					else {
						// Si on est serveur
						System.out.println("Recu depuis Client : " + ligne);
						
						if (ligne.equals("R_CONNECT")) { // Quand on recoit une requete de connexion ...
							this.IPcible = readerC.readLine(); // .. On recupere l'@IP entrante
							
				
							writerC.write("OK_CONNECT\n"); // .. On informe le client qu'on a bien recu et qu'on accepte
							writerC.flush();
							this.statut = CONNECTED;

					
							System.out.println("H : Reception d'une requete");
							System.out.println("H : IP acceptee : "+this.IPcible+" - "+this.port);
							
						} 
						
					}
				}
				// Pour le reste, si on est toujours connecte, on se refere au protocole de communication (voir methode "protocole()"
				else if (this.statut != DISCONNECTED) {
				
					strEnvoi = protocole(ligne);
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
		// Fermeture des Sockets et des buffer
		System.out.println("FERMETURE");
		
	}
	
	public String protocole(String ligne) throws IOException {
		String retourStr = "";
		System.out.println("Recu :"+ligne);
		
		if (ligne.equals("COUP")) {
			System.out.println("Reception d'un coup");
			buffer = "";
			statut = RECEPTIONCOUP;
		}
		if (ligne.equals("MSG")) {
			System.out.println("Reception d'un message");
			buffer = "";
			statut = RECEPTIONMSG;
		}
		else if (statut == RECEPTIONCOUP) {
			buffer += ligne+"\n";
			
			if (ligne.equals(";")) {
				System.out.println("Fin de la description du coup");
				System.out.println("||| "+buffer+" |||");
				statut = CONNECTED;
				retourStr = "COUP_RECU\n";
				this.controleur.action_from_string(buffer);
			}
			
		}
		else if (statut == RECEPTIONMSG) {
			
			if (ligne.equals(";")) {
				System.out.println("Fin de la description du message");
				System.out.println("||| "+buffer+" |||");
				statut = CONNECTED;
				retourStr = "MSG_RECU\n";
				this.controleur.getFenetrePrincipale().getInfo().message(
					buffer);
				
			}
			else
				buffer += ligne;

			
		}
			
		return retourStr;
	}
	
	public synchronized boolean envoyer_coup(String str) throws IOException {
		System.out.println("#COUP\n"+str+"#");
		writerC.write("COUP\n"
				+str
				+"\n");
		writerC.flush();
		
		return true;
	}
	
	public synchronized boolean envoyer_msg(String str) throws IOException {
		System.out.println("#MSG"+str+"#");
		writerC.write("MSG\n"
				+str+"\n"
				+";\n");
		writerC.flush();
		
		return true;
	}
	
	// En cas d'erreur, on resynchronise la partie
	public synchronized boolean envoyer_partie(String str) throws IOException {
		System.out.println("#GAME"+str+"#");
		writerC.write("GAME"
				+str+"\n"
				+";\n");
		writerC.flush();
		
		return true;
	}
	
}
