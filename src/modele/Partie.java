package modele;

import java.util.Vector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
	
	protected Plateau plateau;
	protected Controleur controleur;
	protected Joueur jCourant;
	
	protected Vector<String> actions;
	
	protected Joueur j1, j2;
	
	public Partie(String fichierConfig) {
		try {
			System.out.println(this.charger(fichierConfig));
		}
		catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		if(this.getJ1().getCamps() == false)
			this.jCourant = this.getJ1();
		else
			this.jCourant = this.getJ2();
		
		this.plateau = plateau;
		this.controleur = controleur;
		this.actions = new Vector<String>();

	}
	
	public Joueur getJ1() {
		return j1;
	}

	// Change de joueur
	public void nextTurn() {
		if (this.jCourant.equals(j1))
			this.jCourant = this.j2;
		else 
			this.jCourant = this.j1;
		
		this.getControleur().getFenetrePrincipale().rafraichir();
	}
	
	public Joueur getJCourant() {
		return this.jCourant;
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
		
	}
	
	public void quickLoad() {
		
	}
	
	public String toString() {
		String temp = "";

		/*
		out.write(temp);
		out.write("\n");
		*/
		
		
		
		temp += getPlateau().toString();
		temp += "\n";
		temp += (getJCourant().getCamps() ? 1 : 0);
		
		temp += "\n";
		temp += getJ1().getNom() + " ";
		temp += getJ1().getR() + " ";
		temp += getJ1().getG() + " ";
		temp += getJ1().getB() + " ";
		temp += getJ1().getScore() + " ";
		temp += getJ1().isHumain() + " ";

		temp += "\n";
		/*
		out.write(temp);
		out.write("\n");
		*/
		
		//affichage du joueur 2 (Nom r g b score humain)
		temp += getJ2().getNom() + " ";
		temp += getJ2().getR() + " ";
		temp += getJ2().getG() + " ";
		temp += getJ2().getB() + " ";
		temp += getJ2().getScore() + " ";
		temp += getJ2().isHumain() + " ";
		
		temp += "\n";
		
		/*
		// affichage du plateau (toString())
		out.write(this.getControleur().getPartie().getPlateau().toString()); 
		out.write("\n");
		//affichage du joueur actif
		int i = (this.getControleur().getPartie().getJCourant().getCamps() ? 1 : 0) ;
		temp = ""+i;
		out.write(temp);
		out.write("\n");
		*/
		
		
		return temp;
	}
	/**
	 * 
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
	public boolean charger(String fichierConf) throws IOException {
		
		BufferedReader buffer=new BufferedReader(new FileReader(fichierConf));
		StringTokenizer tokenizer;
		String ligne;
		
		int joueurR;
		int joueurG;
		int joueurB;
		int scoreJoueur;
		String nomJoueur;
		String joueurHumain;
		
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
			nomJoueur = tokenizer.nextToken();
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			joueurR = Integer.parseInt(tokenizer.nextToken());
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			joueurG = Integer.parseInt(tokenizer.nextToken());
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			joueurB = Integer.parseInt(tokenizer.nextToken());
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			scoreJoueur = Integer.parseInt(tokenizer.nextToken());
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			joueurHumain = tokenizer.nextToken();
		}
		
		else
			return false;
		
		this.j1 = new Joueur(nomJoueur, false,(joueurHumain.equals("true")), scoreJoueur, joueurR, joueurG, joueurB);
		
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
			nomJoueur = tokenizer.nextToken();
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			joueurR = Integer.parseInt(tokenizer.nextToken());
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			joueurG = Integer.parseInt(tokenizer.nextToken());
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			joueurB = Integer.parseInt(tokenizer.nextToken());
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			scoreJoueur = Integer.parseInt(tokenizer.nextToken());
		}
		
		else
			return false;
		
		if (tokenizer.hasMoreTokens()) {
			joueurHumain = tokenizer.nextToken();
		}
		
		else
			return false;
		
		this.j2 = new Joueur(nomJoueur, true , (joueurHumain.equals("true")), scoreJoueur, joueurR, joueurG, joueurB);
		
		// Chargement du plateau
		
		this.plateau = new Plateau();
		
		buffer.readLine();
		
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
		
		return true;
	}
}
