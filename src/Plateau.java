import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * <b>Plateau est la classe repr�sentant la totalit� des cases du plateau de jeu.</b>
 * <p>
 * Un plateau est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Deux joueurs, dont un peut �tre l'ordinateur lui-m�me.</li>
 * <li>Une partie, repr�sentant une instance de jeu.</li>
 * <li>Des cases, qui rep�resente un emplacement physique n�cessaire au plateau.</li>
 * </ul>
 * </p>
 * 
 * @see Joueur
 * @see Partie
 * @see Case
 * 
 * @author Lenogue Matthieu
 * @author Gautier Quentin
 * @author Gautier Gaetan
 * @author Ouary Maxime
 * 
 * @version 1.0
 */

public class Plateau {

	private Bille[][] plateau;
	
	public Plateau(String nomFicConfig) {
		this.plateau = new Bille[9][9];
		this.remplir(nomFicConfig);
	}
	
	public Bille getBille(int ligne, int colonne) {
		return this.plateau[ligne][colonne];
	}
	
	private void setBille(int ligne, int colonne, Bille newBille) {
		this.plateau[ligne][colonne] = newBille;
	}
	
	public boolean remplir(String nomFicConfig) {
		
		/*
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(nomFicConfig));
		}
		
		catch (FileNotFoundException e) {
			System.out.println("Erreur : fichier de plateau \"" + nomFicConfig + "\" introuvable");
			return false;
		}
		*/
		
		return true;
	}
	
	public boolean caseVide(int ligne, int colonne) {
		return this.plateau[ligne][colonne] == null;
	}
}
