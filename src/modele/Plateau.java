package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import controleur.Controleur;

/**
 * <b>Plateau est la classe representant la totalite des cases du plateau de jeu.</b>
 * <p>
 * Un plateau est caracterise par les billes qu'il contient.
 * </ul>
 * </p>
 * 
 * @see Bille
 * 
 * @author Lenogue Matthieu
 * @author Gautier Quentin
 * @author Gautier Gaetan
 * @author Ouary Maxime
 * 
 * @version 1.0
 */

public class Plateau {
	
	/**
	 * Le plateau en tant que tel.
	 */
	protected Bille[][] plateau;
	/**
	 * Constructeur de la classe PLateau
	 * 
	 * @param nomFicConfig Le fichier d'initialisation du plateau
	 * @param joueur1 : le premier joueur de la partie
	 * @param joueur2 : le second joueur de la partie
	 * @throws Exception 
	 */
	public Plateau() {
		this.plateau = new Bille[9][9];
	}
	
	/**
	 * Renvoie une bille placee sur le plateau.
	 * 
	 * @param ligne La ligne sur laquelle se trouve la bille a recuperer
	 * @param colonne La colonne sur laquelle se trouve la bille a recuperer
	 * 
	 * @return Une bille du plateau
	 * 
	 * @see Bille
	 */
	public Bille getBille(int ligne, int colonne) {
		if (!Controleur.isOut(ligne, colonne))
			return this.plateau[ligne][colonne];
		else 
			return null;
	}
	
	/**
	 * Modifie une bille du plateau.
	 * 
	 * @param ligne La ligne sur laquelle se trouve la bille a modifier.
	 * @param colonne La colonne sur laquelle se trouve la bille a modifier.
	 * @param newBille La nouvelle bille.
	 * 
	 * @see Bille
	 */
	public void setBille(int ligne, int colonne, Bille newBille) {
		this.plateau[ligne][colonne] = newBille;
		if (newBille != null) {
			newBille.setLigne(ligne);
			newBille.setColonne(colonne);
		}
	}
	
	/**
	 *	Affiche l'etat du plateau
	 *
	 *  @see Bille
	 */
	public String toString() {
		String str = "";
		for(int i=0; i < 9; i++) {
			for(int j=0; j < 9; j++)
				if (caseVide(i,j))
					str+="o";
				else
					str+= getBille(i,j).toString();
			str+="\n";
		}
		return str;
	}
	
	/**
	 * Teste si une case est vide.
	 * 
	 * @param ligne : la ligne de la case a tester
	 * @param colonne : la colonne de la classe a tester
	 * 
	 * @return
	 * 		<ul>
	 * 			<li>True si la case est vide,</li>
	 * 			<li>False sinon.</li>
	 * 		</ul>
	 */
	public boolean caseVide(int ligne, int colonne) {
		try {
			return this.plateau[ligne][colonne] == null;
		}
		
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Erreur lors du test de vacuite : coordonnees hors plateau");
			return false;
		}
	}
	public Bille[][] getBilles() {
		return plateau;
	}

	public void setPlateau(Bille[][] plateau) {
		this.plateau = plateau;
	}

}
