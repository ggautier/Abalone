package Reseau;

import java.awt.Point;
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
import java.util.concurrent.TimeoutException;

import javax.swing.JDialog;
import javax.swing.JLabel;

import controleur.CompteRebours;
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
		
	protected CompteRebours delaiContact;
	
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
		this.delaiContact = new CompteRebours(0,10);
		if (this.IPcible.equals(""))
			this.heberge = true;
		else
			this.heberge = false;
		
		this.port = portCible;
		this.statut = NOT_CONNECTED;
		
		if (controleur.getOptions().isuProxy()) {
			System.setProperty("http.proxyHost", controleur.getOptions().getProxy());
			System.setProperty("http.proxyPort", controleur.getOptions().getPortProxy()+"");
		}
		else {
			System.setProperty("http.proxyHost", "");
			System.setProperty("http.proxyPort", "");
		}
		
	}
	
	public void run() {
		
		JDialog dialogAttente = new JDialog(controleur.getFenetrePrincipale());
		JLabel labelConnexion = new JLabel("  En attente de connexion ...");
		dialogAttente.setTitle("Connexion ...");
		dialogAttente.add(labelConnexion);
		dialogAttente.setSize(200,100);
		dialogAttente.setLocationRelativeTo(dialogAttente.getParent());
		dialogAttente.setLocation(new Point(dialogAttente.getParent().getSize().width/2,dialogAttente.getParent().getSize().height/2));
		dialogAttente.setVisible(true);
		
		// On initialise les Buffer et Socket
		BufferedReader readerC = null;
		Socket socketClient = null;
		ServerSocket socketServeur = null;
		
		try {
			if (!heberge) {
				// Si on est client
				socketClient = new Socket(this.IPcible, this.port);
				socketClient.setSoTimeout(3000);
				socketClient.getOutputStream().write(("R_CONNECT\n"+InetAddress.getLocalHost().getHostAddress()+"\n").getBytes());
				// On initialise le Socket, puis on envoie une requete de connexion au serveur
				
			}
			else {
				// Si on est serveur
				socketServeur = new ServerSocket(this.port);
				socketServeur.setSoTimeout(60000);
				
				socketClient = socketServeur.accept();
				
				// On ecoute au port demande.
			}
						
			writerC = new BufferedWriter(new OutputStreamWriter(
					socketClient.getOutputStream()));
			readerC = new BufferedReader(new InputStreamReader(
					socketClient.getInputStream()));
		}
		catch (SocketTimeoutException e) {
			synchronized(this) {
				if (heberge)
					labelConnexion.setText("Aucune connexion trouvee.");
				
				dialogAttente.repaint();
				try {
					this.wait((long) 1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				dialogAttente.dispose();
				
				try {
					socketServeur.close();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
				this.controleur.lancerPartie(0);
				this.interrupt();
			}
		}
		catch (IOException e) {
			
		}
		
		String ligne;
		String strEnvoi = "";
		if (readerC != null) {
			
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
								
								// On ferme la fenetre de connexion
								dialogAttente.dispose();
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
								
								delaiContact.demarrer();
						
								System.out.println("H : Reception d'une requete");
								System.out.println("H : IP acceptee : "+this.IPcible+" - "+this.port);
								
								// On ferme la fenetre de connexion
								dialogAttente.dispose();
								
							} 
							
						}
					}
					// Pour le reste, si on est toujours connecte, on se refere au protocole de communication (voir methode "protocole()"
					else if (this.statut != DISCONNECTED) {
					
						strEnvoi = protocole(ligne);
						if (!strEnvoi.equals(""))
							socketClient.getOutputStream().write((strEnvoi).getBytes());
	
					}
				
					if (delaiContact.timeout()) {
						erreur();
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
		
		if (dialogAttente.isVisible()) {
			synchronized(this) {
				if (!heberge)
					labelConnexion.setText("Echec de la connexion au Serveur");
				
				dialogAttente.repaint();
				try {
					this.wait((long) 1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				dialogAttente.dispose();
				
				this.controleur.lancerPartie(0);
				this.interrupt();
			}
		}
		}
		
	}
	
	public String protocole(String ligne) throws IOException {
		String retourStr = "";
		System.out.println("Recu :"+ligne);
		
		if (ligne.equals("KEEPALIVE"))
			delaiContact.reset(0, 10);
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
		
		try {
			writerC.write("COUP\n"
					+str
					+"\n");
			writerC.flush();
		}
		catch (IOException e) {
			this.erreur();
		}
		
		return true;
	}
	
	public synchronized boolean envoyer_msg(String str) throws IOException {
		System.out.println("#MSG"+str+"#");
		try {
			writerC.write("MSG\n"
					+str+"\n"
					+";\n");
			writerC.flush();
		}
		catch (IOException e) {
			this.erreur();
		}
		
			return true;
		}
	
	public synchronized boolean envoyer_keepalive() throws IOException {
		if (statut != NOT_CONNECTED)
			try {
				writerC.write("KEEPALIVE\n");
				writerC.flush();
			}
			catch (IOException e) {
				this.erreur();
			}
			
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
	
	public synchronized boolean erreur() {
		JDialog dialogErreur = new JDialog(controleur.getFenetrePrincipale());
		dialogErreur.setTitle("Erreur");
		dialogErreur.add(new JLabel("Connexion coupee"));
		dialogErreur.setSize(200,100);
		dialogErreur.setLocationRelativeTo(dialogErreur.getParent());
		dialogErreur.setLocation(new Point(dialogErreur.getParent().getSize().width/2,dialogErreur.getParent().getSize().height/2));
		dialogErreur.setVisible(true);
		
		try {
			this.wait((long) 2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		dialogErreur.dispose();
		
		this.controleur.lancerPartie(0);
		this.interrupt();
		
		return true;
		
	
	}

	
}
