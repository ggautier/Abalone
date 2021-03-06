package modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Stack;
import java.util.StringTokenizer;

import Reseau.Connexion;

import controleur.Controleur;

/**
 * <b>Partie est la classe regroupant toutes les information du modele sur la partie en cours.</b>
 * <p>
 * Une partie est caracterisee par les informations suivantes :
 * <ul>
 * <li>Deux joueurs, dont un peut etre l'ordinateur lui-meme.</li>
 * <li>Un plateau, representant le plateau de jeu, et ses cases.</li>
 * <li>Des coups, qui representent la sequence de coup joues depuis le debut de la partie.</li>
 * </ul>
 * </p>
 * 
 * @see Joueur
 * @see Plateau
 * @see Coup
 * 
 * @author Lenogue Matthieu
 * @author Gautier Quentin
 * @author Gautier Gaetan
 * @author Ouary Maxime
 * 
 * @version 1.0
 */
public class Partie {
	/**
	 * le plateau de la partie
	 * 
	 */
	protected Plateau plateau;
	/**
	 * le controleur qui supervise la partie
	 * 
	 * @see Controleur
	 */
	protected Controleur controleur;
	/**
	 *  le joueur courant jouant la partie
	 *  
	 *  @see Joueur
	 */
	protected Joueur jCourant;
	/**
	 * 
	 */
	protected int online;
	
	protected Joueur joueurPhysique;
	
	protected Stack<String> actions;
	
	protected Joueur j1, j2;
	
	public final static int OFFLINE = 0;
	public final static int HOST = 2;
	public final static int CLIENT = 1;
	
	public Partie(Controleur newControleur, String fichierConfig, int online) throws IOException {
		
		this.setControleur(newControleur);
		this.actions = new Stack<String>();
		this.online = online;
		
		this.setJ1(new Joueur(controleur.getOptions().getNomJ1(), false, true));
		this.setJ2(new Joueur(controleur.getOptions().getNomJ2(), true, true));
		
		try {
			this.chargerParFichier(fichierConfig);
		}
		catch(NullPointerException npe) {
			System.out.println(npe.getMessage());
		}
		catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		
		if(this.getJ1().getCamps() == false)
			this.jCourant = this.getJ1();
		else
			this.jCourant = this.getJ2();
		
		this.jCourant.getTempsRestantGlobal().demarrer();
		this.jCourant.getTempsRestantCoup().demarrer();
		
		if (this.getOnlineMode() > 0) {
			if (this.getOnlineMode() == 1) {
				this.joueurPhysique = this.getJoueur(!this.jCourant.getCamps());
				this.controleur.setConnexion(new Connexion(this.getControleur(), this.getControleur().getOptions().getIP(), this.getControleur().getOptions().getPortEcoute()));
				
			}
			else if (this.getOnlineMode() == 2) {
				this.joueurPhysique = jCourant;
				this.controleur.setConnexion(new Connexion(this.getControleur(), "", this.getControleur().getOptions().getPortEcoute()));
			}
			
			this.controleur.getConnexion().start();

		}
		
	}

	public Joueur getJ1() {
		return j1;
	}

	public void setJ1(Joueur j1) {
		this.j1 = j1;
	}

	public Joueur getJ2() {
		return j2;
	}

	public void setJ2(Joueur j2) {
		this.j2 = j2;
	}
	
	public Joueur getJCourant() {
		return this.jCourant;
	}
	
	public void setJCourant(Joueur jCourant) {
		this.jCourant = jCourant;
	}
	
	public Joueur getJoueur(boolean camps) {
		if(this.getJ1().getCamps() == camps)
			return this.getJ1();
		else
			return this.getJ2();
	}
	
	public Controleur getControleur() {
		return controleur;
	}

	public void setControleur(Controleur controleur) {
		this.controleur = controleur;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	
	public void quickSave() {
		this.actions.push(this.toString());
		System.out.println("QSave");
	}
	
	public void quickLoad() throws IOException {
		System.out.println("Annulation...");
		if (actions.size() > 1) {
			this.actions.pop();
			this.charger(this.actions.pop());
		}
		else
			System.out.println("Pas de coup precedent");
		
		if(this.getControleur().getFenetrePrincipale() != null)
			this.getControleur().getFenetrePrincipale().rafraichir();
	}
	
	/**
	 * Charge une partie a partir d'un fichier de sauvegarde
	 * 
	 * @param fichierConf : le chemin du fichier d'initialisation de partie
	 * 
	 * @return
	 * 		<ul>
	 * 			<li>True si le chargement reussit,</li>
	 * 			<li>False sinon</li>
	 * 		</ul>
	 * 
	 * @throws IOException
	 */
	public boolean chargerParFichier(String fichierConf) throws IOException {
		
		BufferedReader buffer = new BufferedReader(new FileReader(fichierConf));
		String strTemp = "";
		String strTotal = "";
		
		
		do {
			strTemp = buffer.readLine();
			if (strTemp != null) {
				strTotal += strTemp;
				strTotal += "\n";
			}
		} while (strTemp != null);
		
		
		buffer.close();
		this.charger(strTotal);
		
		return true;
	}
	
	/**
	 * Charge une partie ulterieurement chargee depuis un fichier
	 * 
	 * @throws IOException
	 */
	public boolean charger(String donnees) throws IOException {		
		BufferedReader buffer = new BufferedReader(new StringReader(donnees));
		StringTokenizer tokenizer;
		String ligne;	
		//// JOUEURS
		// Chargement joueur 1
		// Les lignes vides sont ignorees
		do
		{
			ligne = buffer.readLine();
		}
		while((ligne != null) && (ligne.isEmpty()));
		
		// Echec si la fin du fichier est atteinte
		if(ligne == null)
			return false;
		
		tokenizer = new StringTokenizer(ligne, " ");
		
		// Extraction des differents elements du joueur
		
		if(tokenizer.hasMoreTokens()) {
			j1.setNom(tokenizer.nextToken());
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			j1.setR(Integer.parseInt(tokenizer.nextToken()));
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			j1.setG(Integer.parseInt(tokenizer.nextToken()));
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			j1.setB(Integer.parseInt(tokenizer.nextToken()));
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			j1.setScore(Integer.parseInt(tokenizer.nextToken()));
		}
		
		else
			return false;
		
		
		if (tokenizer.hasMoreTokens()) {
			if(tokenizer.nextToken().equals("true")) // JoueurHumain?
				j1.setHumain(true);
			else
				j1.setHumain(false);
		}
				
		// Chargement joueur 2
		
		// Les lignes vides sont ignorees
		do
		{
			ligne = buffer.readLine();
		}
		while((ligne != null) && (ligne.isEmpty()));
		
		// Echec si la fin du fichier est atteinte
		if(ligne == null)
			return false;
		
		tokenizer = new StringTokenizer(ligne, " ");
		
		// Extraction des differents elements du joueur
		
		if(tokenizer.hasMoreTokens()) {
			j2.setNom(tokenizer.nextToken());
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			j2.setR(Integer.parseInt(tokenizer.nextToken()));
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			j2.setG(Integer.parseInt(tokenizer.nextToken()));
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			j2.setB(Integer.parseInt(tokenizer.nextToken()));
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			j2.setScore(Integer.parseInt(tokenizer.nextToken()));
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			if(tokenizer.nextToken().equals("true")) // JoueurHumain?
				j2.setHumain(true);
			else
				j2.setHumain(false);
		}
		
		
		// Retour a la ligne
		ligne = buffer.readLine();
		
		// Echec si la fin du fichier est atteinte
		if(ligne == null)
			return false;
		
		// Chargement du plateau
		
		this.plateau = new Plateau();
		
		int numLigne = 0;
		
		while(((ligne = buffer.readLine()) != null) && (ligne.isEmpty() == false)) {
			
			if(ligne.length() != 9)
				return false;
			
			for(int numColonne = 0 ; numColonne < ligne.length() ; numColonne++) {
				
				if(ligne.charAt(numColonne) == '-')
					this.getPlateau().setBille(numLigne, numColonne, new Bille(numLigne, numColonne, this.getJ1()));
					
				else if(ligne.charAt(numColonne) == '+')
					this.getPlateau().setBille(numLigne, numColonne, new Bille(numLigne, numColonne, this.getJ2()));
				
				else
					this.getPlateau().setBille(numLigne, numColonne, null);
			}
			
			numLigne++;
		}
		
		// Extraction du joueur actif
		// Les lignes vides sont ignorees
		do
		{
			ligne = buffer.readLine();
		}
		while((ligne != null) && (ligne.isEmpty()));
		
		// Echec si la fin du fichier est atteinte
		if(ligne == null)
			return false;
		
		
		if(ligne.equals("0"))
			this.jCourant = this.getJ1();
		else
			this.jCourant = this.getJ2();	
		
		this.quickSave();
		return true;
	}
	
	/**
	 * 
	 * @return chaine de caracteres qui affiche les joueurs, le joueur courant , le plateau.
	 */
	public String toString() {
		String temp = "";
		//affichage du joueur 1 (Nom r g b score humain)
		temp += getJ1().getNom() + " ";
		temp += getJ1().getR() + " ";
		temp += getJ1().getG() + " ";
		temp += getJ1().getB() + " ";
		temp += getJ1().getScore() + " ";
		temp += getJ1().isHumain() + " ";

		temp += "\n";
		
		//affichage du joueur 2 (Nom r g b score humain)
		temp += getJ2().getNom() + " ";
		temp += getJ2().getR() + " ";
		temp += getJ2().getG() + " ";
		temp += getJ2().getB() + " ";
		temp += getJ2().getScore() + " ";
		temp += getJ2().isHumain() + " ";
		
		temp += "\n";
		temp += "\n";

		temp += getPlateau().toString();

		
		temp += "\n";
		
		temp += (getJCourant().getCamps() ? 1 : 0);
		
		return temp;
	}
	
	public int getOnlineMode() {
		return online;
	}

	public void setjCourant(Joueur jCourant) {
		this.jCourant = jCourant;
	}
	
	public Joueur getAdversaire(Joueur j) {
		if (j.equals(j1))
			return j2;
		else
			return j1;
	}
	
	public Joueur getJoueurPhysique() {
		return joueurPhysique;
	}

	public boolean aMonTour() {
		if (this.online > 0 && this.controleur.getFenetrePrincipale() != null)
			return (this.jCourant.equals(this.joueurPhysique));
		else return true;
		
	}
	
	
}
