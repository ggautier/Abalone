package modele;

import java.util.ArrayList;
import java.util.Vector;

import vue.FenetrePrincipale;
import controleur.ControleurIA;

/**
 * <b>Coup est la classe qui regroupe toute la sequence de coup joues depuis le debut de la partie.</b>
 * <p>
 * Un Coup est caracterise par les informations suivantes :
 * <ul>
 * <li>Un joueur, qui va jouer le coup.</li>
 * <li>Une partie, dans laquelle le coup est joue.</li>
 * </ul>
 * </p>
 * 
 * @see Joueur
 * @see Partie
 * 
 * @author Lenogue Matthieu
 * @author Gautier Quentin
 * @author Gautier Gaetan
 * @author Ouary Maxime
 * 
 * @version 1.0
 */
public class Coup {

	private ArrayList<Bille> billes;	// Contient toutes les Billes actuellement selectionnees.
	private int	direction;				// Contient le "deplacement" pointe par la Souris.
	private Joueur joueur;
	
	public Coup() {}
	
	public Coup(ArrayList<Bille> billes, int dir, Joueur newJoueur) {
		this.billes = (ArrayList<Bille>) billes.clone();
		this.direction = dir;
		this.joueur = newJoueur;
	}
	
	public ArrayList<Bille> getBilles() {
		return this.billes;
	}
	
	public int getDirection() {
		return this.direction;
	}
	
	public Joueur getJoueur() {
		return this.joueur;
	}
	
	public int nbBilles() {
		return this.billes.size();
	}
	
	public boolean equals(Coup coup2) {
		boolean memesBilles = false;
		if (billes.equals(coup2.billes))
			memesBilles = true;
		
		return (this.direction == coup2.direction && memesBilles);
	}
	
	public String toString() {
		String str = new String();
		
		for(int i = 0 ; i < this.billes.size() ; i++)
			str += this.billes.get(i) + " ";
		
		str += ": " + this.direction;
		
		return str;
	}
}

/*
 * Pour les coups, plusieurs encodages possibles : 
 * Billes sources + cases cibles
 * Billes sources + direction
 *    Je prefere la seconde solution, car cela permettrait d'indiquer plus precisement les eventuelles Billes ennemies que l'on poussera
 * 
 */
