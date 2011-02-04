// TODO Fixer le cas des billes mortes
//		-> supprimer l'objet ?
//		-> l'indiquer dans un booléen ?
//		-> quid de la case ?

/**
 * <b>Bille est la classe qui represente une bille durant une partie.</b>
 * <p>
 * Une Bille est caracterisee par un joueur qui va la posseder.
 * </p>
 * 
 * @see Joueur
 * 
 * @author Lenogue Matthieu
 * @author Gautier Quentin
 * @author Gautier Gaetan
 * @author Ouary Maxime
 * 
 * @version 1.0
 */
public class Bille {
	
	/**
	 * Le joueur proprietaire objet.
	 * 
	 * @see Joueur
	 */
	private Joueur joueur;
	
	public Bille(int ligne, int colonne, Joueur newJoueur)
	{
		this.joueur = newJoueur;
	}
	
	/**
	 * Accesseur du joueur
	 * 
	 * @return Le joueur proprietaire de la bille.
	 * 
	 * @see Joueur
	 */
	public Joueur getJoueur() {
		return this.joueur;
	}
	
	public String toString() {
		String string;
		
		if(this.joueur.getCouleur() == true)
			string = "0";
		else
			string = "1";
		
		return string;
	}
}
