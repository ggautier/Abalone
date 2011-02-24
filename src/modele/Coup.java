package modele;

import java.awt.Point;
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

	private ArrayList<Point> billes;	// Contient toutes les Billes actuellement selectionnees.
	private int	direction;				// Contient le "deplacement" pointe par la Souris.
	private Joueur joueur;
	
	public Coup() {
		this.billes = new ArrayList<Point>();
	}
	
	public Coup(ArrayList<Bille> billes, int dir, Joueur newJoueur) {
		
		this.direction = dir;
		this.joueur = newJoueur;
		this.billes = new ArrayList<Point>();
		
		for (int i=0; i < billes.size(); i++)
			this.billes.add(new Point(billes.get(i).getLigne(),billes.get(i).getColonne()));
	}
	
	public ArrayList<Point> getBilles() {
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
			str += (int) this.billes.get(i).getX() + "," + (int) this.billes.get(i).getY() + "   ";
		
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
