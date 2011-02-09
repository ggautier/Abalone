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
	protected int coordX;
	/**
	 * La colonne du plateau sur laquelle se trouve la bille.
	 */
	protected int coordY;
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
	 * @param newY : la colonne du plateau sur laquelle se trouve la bille
	 * @param player : le joueur auquel appartient la bille
	 * 
	 */
	Bille(int newX, int newY, Joueur player) {
		this.coordX = newX;
		this.coordY = newY;
		this.joueur = player;
	}

	/**
	 * Renvoie la ligne du plateau sur laquelle se trouve la bille
	 * 
	 * @return La ligne du plateau sur laquelle se trouve la bille
	 */
	public int getX() {
		return coordX;
	}

	/**
	 * Modifie la ligne du plateau sur laquelle se trouve la bille
	 * 
	 * @param coordX : la nouvelle ligne sur laquelle se trouve de la bille
	 */
	public void setX(int coordX) {
		this.coordX = coordX;
	}

	/**
	 * Renvoie la colonne du plateau sur laquelle se trouve la bille
	 * 
	 * @return La colonne du plateau sur laquelle se trouve la bille
	 */
	public int getY() {
		return coordY;
	}

	/**
	 * Modifie la colonne du plateau sur laquelle se trouve la bille
	 * 
	 * @param coordY : la nouvelle colonne sur laquelle se trouve de la bille
	 */
	public void setY(int coordY) {
		this.coordY = coordY;
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
	
	/*
	public String toString() {
		String str = "o";
		if (joueur.getCouleur())
			str = "+";
		if (!joueur.getCouleur())
			str = "-";
		
		return str;
	}
	*/
	
	/**
	 * Test d'egalite avec une autre bille.
	 * 
	 * @param bille : la bille a comparer
	 */
	public boolean equals(Bille bille) {
		boolean retour = false;
		if (bille != null)
		{ retour = this.getX() == bille.getX() && this.getY() == bille.getY() && this.getJoueur().equals(bille.getJoueur()); }
		return retour;
	}
}
