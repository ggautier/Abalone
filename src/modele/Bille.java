package modele;

/**
 * <b>Bille est la classe qui represente une bille durant une partie.</b>
 * <p>Une Bille est caracterisee par :
 * <ul>
 * 	<li>un joueur qui va la posseder,</li>
 * 	<li>des coordonnees entieres.</li>
 * </ul></p>
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
	 * La ligne du plateau sur laquelle se trouve la bille.
	 */
	protected int ligne;
	/**
	 * La colonne du plateau sur laquelle se trouve la bille.
	 */
	protected int colonne;
	/**
	 * Le joueur auquel appartient la bille.
	 * 
	 * @see Joueur
	 */
	protected Joueur joueur;
	
	/**
	 * Constructeur de la classe Bille
	 * 
	 * @param newX : la ligne du plateau sur laquelle se trouve la bille
	 * @param newColonne : la colonne du plateau sur laquelle se trouve la bille
	 * @param player : le joueur auquel appartient la bille
	 * 
	 */
	public Bille(int newLigne, int newColonne, Joueur player) {
		this.ligne = newLigne;
		this.colonne = newColonne;
		this.joueur = player;
	}

	/**
	 * Renvoie la ligne du plateau sur laquelle se trouve la bille
	 * 
	 * @return La ligne du plateau sur laquelle se trouve la bille
	 */
	public int getLigne() {
		return ligne;
	}

	/**
	 * Modifie la ligne du plateau sur laquelle se trouve la bille
	 * 
	 * @param newLigne : la nouvelle ligne sur laquelle se trouve de la bille
	 */
	public void setLigne(int newLigne) {
		this.ligne = newLigne;
	}

	/**
	 * Renvoie la colonne du plateau sur laquelle se trouve la bille
	 * 
	 * @return La colonne du plateau sur laquelle se trouve la bille
	 */
	public int getColonne() {
		return colonne;
	}

	/**
	 * Modifie la colonne du plateau sur laquelle se trouve la bille
	 * 
	 * @param newColonne : la nouvelle colonne sur laquelle se trouve de la bille
	 */
	public void setColonne(int newColonne) {
		this.colonne = newColonne;
	}

	/**
	 * Renvoie le joueur auquel appartient la bille
	 * 
	 * @return Le joueur auquel appartient la bille
	 * @see Joueur
	 */
	public Joueur getJoueur() {
		return joueur;
	}

	/**
	 * Modifie le joueur auquel appartient la bille
	 * 
	 * @param joueur : le nouveau jour proprietaire de la bille
	 * @see Joueur
	 */
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	public String toString() {
		String str = "o";
		if (joueur.getCamps())
			str = "+";
		if (!joueur.getCamps())
			str = "-";
		
		return str;
	}
	
	// Version utilisee pour le developpement de l'IA

	public String afficher() {
		String str = new String();
		
		str = "(" + this.getLigne() + "," + this.getColonne() + ")";
		
		if (joueur.getCamps())
			str += " J2";
		else
			str += " J1";
		
		return str;
	}

	
	/**
	 * Test d'egalite avec une autre bille.
	 * 
	 * @param bille : la bille a comparer
	 */
	public boolean equals(Bille bille) {
		boolean retour = false;
		if (bille != null)
		{ retour = this.getLigne() == bille.getLigne() && this.getColonne() == bille.getColonne() && this.getJoueur().equals(bille.getJoueur()); }
		return retour;
	}
}
