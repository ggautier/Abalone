package modele;

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

	protected Vector<Bille>		billes;  // Contient toutes les Billes actuellement selectionnees.
	protected int		    	direction;// Contient le "deplacement" pointe par la Souris.
	
	public Coup() {
		
	}
	
	public Coup(Vector<Bille> b, int dir) {
		this.billes = b;
		this.direction = dir;
	}
	
	public boolean equals(Coup c) {
		boolean memesBilles = false;
		if (billes.equals(c.billes))
			memesBilles = true;
		
		return (this.direction == c.direction && memesBilles);
	}
	
	
}

/*
 * Pour les coups, plusieurs encodages possibles : 
 * Billes sources + cases cibles
 * Billes sources + direction
 *    Je prefere la seconde solution, car cela permettrait d'indiquer plus precisement les eventuelles Billes ennemies que l'on poussera
 * 
 */
