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
import java.net.UnknownHostException;

import controleur.Controleur;

public class Communication {
	
	public static boolean ouverte = false;
	
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
		
		Communication.ouverte = true;
		
	}
	
	public boolean etablir_contact() throws IOException {
		
		if (heberge) {
			ServerSocket server = new ServerSocket(this.port);
			System.out.println("S : En attente de requete");
			Socket client = server.accept();
			client.setSoTimeout(300000);
			server.setSoTimeout(300000);
			
			BufferedWriter writerS = new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream()));
			BufferedReader readerS = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			
			String ligne;
			String str = "";
			
			
			try {
				while ( (ligne = readerS.readLine()) != null) {
					System.out.println("Recu Serveur : "+ligne);
					if (ligne.equals("C_CONTACT")) {
						this.ip_partenaire = readerS.readLine();

						writerS.write("OK\n");
						writerS.flush();
				
						server.close();
						client.close();
						System.out.println("S : Reception d'une requete");
						System.out.println("S : IP acceptee : "+this.ip_partenaire);
						
						this.contact_etabli = true;
					} 
					else if (this.contact_etabli) {
	

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
				server.close();
				client.close();
				readerS.close();
				writerS.close();

			}

		}
		else {
			Socket s = new Socket(this.ip_partenaire, this.port);
			s.setSoTimeout(10000);
			
			s.getOutputStream().write(("C_CONTACT\n"+InetAddress.getLocalHost().getHostAddress()+"\n").getBytes());
			System.out.println("C : Envoi d'une requete a "+this.ip_partenaire+" depuis "+InetAddress.getLocalHost().getHostAddress());
			
			BufferedWriter writerC = new BufferedWriter(new OutputStreamWriter(
					s.getOutputStream()));
			BufferedReader readerC = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			
	
			
			String ligne;
			try {
				while((ligne = readerC.readLine()) != null) {
							
					System.out.println("Recu Client : " + ligne);
					if (ligne.equals("OK")) {
						s.close();
						this.contact_etabli = true;
						System.out.println("C : Confirmation de "+this.ip_partenaire);
						
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
				writerC.close();
				readerC.close();
				s.close();
			}
			
		
			
			
		}
		
		return false;
		
	}
	
	public String attendre_coup() throws IOException {
		System.out.println("Attente d'un coup");
		boolean coupTransmis = false;
		boolean coupDetecte = false;
		
		ServerSocket server = new ServerSocket(this.port);
		Socket client = server.accept();
		
		BufferedWriter writerS = new BufferedWriter(new OutputStreamWriter(
				client.getOutputStream()));
		BufferedReader readerS = new BufferedReader(new InputStreamReader(
				client.getInputStream()));
		
		String line;
		String str = "";
		try {
			while ( (line = readerS.readLine()) != null) {
				System.out.println("Recu Serveur : "+line);
				if (line.equals("Coup")) {
					System.out.println("Coup recu");
					coupDetecte = true;
				}
				else if (coupDetecte) {
						str += line+"\n";
						
						if (line.equals(";")) {
							System.out.println("||| "+str+" |||");
							this.controleur.action_from_string(str);
							client.getOutputStream().write(("RECU\n").getBytes());
							server.close();
							client.close();
							coupTransmis = true;
							
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
			writerS.close();
			readerS.close();
			server.close();
			client.close();
		}
		
		return str;

	}
		
	public boolean envoyer_coup(String str) throws UnknownHostException, IOException {
		Socket s = new Socket(this.ip_partenaire,this.port);
		System.out.println("Envoi du coup : "+str);
		s.getOutputStream().write(("Coup\n"+str+"\n").getBytes());
		
		
		BufferedWriter writerC = new BufferedWriter(new OutputStreamWriter(
				s.getOutputStream()));
		BufferedReader readerC = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		
		String ligne;
		
		try {
			while((ligne = readerC.readLine()) != null) {
						
				System.out.println("Recu Client : " + ligne);
				if (ligne.equals("RECU")) {
					System.out.println("Acquitement OK");
					s.close();
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
			writerC.close();
			readerC.close();
			s.close();
		}
		
		
		
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