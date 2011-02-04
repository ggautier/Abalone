import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * <b>Plateau est la classe représentant la totalité des cases du plateau de jeu.</b>
 * <p>
 * Un plateau est caractérisé par les informations suivantes :
 * <ul>
 * <li>Deux joueurs, dont un peut être l'ordinateur lui-même.</li>
 * <li>Une partie, représentant une instance de jeu.</li>
 * <li>Des cases, qui repéresente un emplacement physique nécessaire au plateau.</li>
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
	
	// On mettra une config par defaut, quand aucun parametre en entree (vive la surcharge)
	public Plateau() {
		this.plateau = new Bille[9][9];
		this.remplir();
	}
	
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
	
	// Temporaire, pour les tests (desole de foutre la zone)
	public boolean remplir() {
		for(int i=0; i < 9; i++)
			for(int j=0; j < 9; j++)
				setBille(i,j,new Bille(i, j, null));
		
		
		return true;
	}
	
	public String toString() {
		String str = "";
		for(int i=0; i < 9; i++) {
			for(int j=0; j < 9; j++)
				str+= getBille(i,j).toString();
			str+="\n";
		}
		return str;
	}
	
	public boolean caseVide(int ligne, int colonne) {
		return this.plateau[ligne][colonne] == null;
	}
}
