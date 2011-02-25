package modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

import vue.FenetrePrincipale;
import controleur.ControleurIA;

/**
 * <b>Coup est la classe qui regroupe toute la sequence de coups joues depuis le debut de la partie.</b>
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

	/**
	 * les Billes actuellement selectionnees
	 */
	private ArrayList<Point> billes;
	/**
	 * la direction du coup
	 */
	private int	direction;
	/**
	 * le joueur qui a joué le coup
	 * 
	 * @see joueur
	 */
	private Joueur joueur;
	/**
	 *  Constructeur de la classe Coup
	 *  
	 */
	public Coup() {
		this.billes = new ArrayList<Point>();
	}
	/**
	 * Constructeur de la classe Coup
	 * 
	 * @param billes : les Billes actuellement selectionnees
	 * @param dir : la direction du coup
	 * @param newJoueur : le joueur qui a joué le coup
	 */
	public Coup(ArrayList<Bille> billes, int dir, Joueur newJoueur) {
		
		this.direction = dir;
		this.joueur = newJoueur;
		this.billes = new ArrayList<Point>();
		
		for (int i=0; i < billes.size(); i++)
			this.billes.add(new Point(billes.get(i).getLigne(),billes.get(i).getColonne()));
	}
	/**
	 * retourne la liste des billes selectionnees
	 * 
	 * @return : la liste des billes selectionnees 
	 */
	public ArrayList<Point> getBilles() {
		return this.billes;
	}
	/**
	 * retourne la direction du coup
	 * 
	 * @return : direction du coup
	 */
	public int getDirection() {
		return this.direction;
	}
	/**
	 * retourne le joueur qui a joue le coup 
	 * 
	 * @return : le joueur qui a joue le coup 
	 */
	public Joueur getJoueur() {
		return this.joueur;
	}
	/**
	 * retourne le nombre de bille joue dans le coup
	 * 
	 * @return : le nombre de bille joue dans le coup
	 */
	public int nbBilles() {
		return this.billes.size();
	}
	/**
	 * permet de compararer deux coups
	 * 
	 * @param coup2 : le coup a comparer 
	 * @return <ul><li>true si les deux coups sont égaux</li>
	 * 			   <li>false sinon</li></ul>
	 */
	public boolean equals(Coup coup2) {
		boolean memesBilles = false;
		if (billes.equals(coup2.billes))
			memesBilles = true;
		return (this.direction == coup2.direction && memesBilles);
	}
	/**
	 * Affiche la position initial des billes et leur direction.
	 * 
	 * @return : chaine de caracteres qui affiche la position initial des billes et leur direction.
	 */
	public String toString() {
		String str = new String();
		
		for(int i = 0 ; i < this.billes.size() ; i++)
			str += (int) this.billes.get(i).getX() + "," + (int) this.billes.get(i).getY() + "   ";
		
		str += ": " + this.direction;
		return str;
	}
}
