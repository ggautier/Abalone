/**
 * <b>Partie est la classe regroupant toutes les information du modele sur la partie en cours.</b>
 * <p>
 * Une partie est caracterisee par les informations suivantes :
 * <ul>
 * <li>Deux joueurs dont un qui peut etre l'ordinateur lui-meme.</li>
 * <li>Un plateau representant le plateau de jeu et ses cases.</li>
 * <li>Des coups qui representent la sequence de coups joues depuis le debut de la partie.</li>
 * </ul>
 * </p>
 * 
 * @see Joueur
 * @see Plateau
 * @see Coup
 * 
 * @author Lenogue Matthieu
 * @author Gautier Quentin
 * @author Gautier Gaetan
 * @author Ouary Maxime
 * 
 * @version 1.0
 */
public class Partie {
	/**
	 * Le plateau associe a la partie.
	 * 
	 * @see Plateau
	 */
	private Plateau plateau;
	/**
	 * Les joueurs de la partie
	 * 
	 * @see Joueur
	 */
	private Joueur[] joueurs;

	/**
	 * Constructeur de la classe Partie
	 */
	public Partie() {
		Joueur j1 = new Joueur("J1", false, true);
		Joueur j2 = new Joueur("J2", true, true);
		
		this.joueurs = new Joueur[2];
		this.joueurs[0] = j1;
		this.joueurs[1] = j2;
		
		this.plateau = new Plateau("./data/plateau/defaut.pl", j1, j2);

	}
	
	/**
	 * Renvoie le plateau associe a la partie
	 * 
	 * @return Le plateau associe a la partie
	 * @see Plateau
	 */
	public Plateau getPlateau() {
		return plateau;
	}
	
	/**
	 * Renvoie un tableau des 2 joueurs de la partie
	 * 
	 * @return Les 2 joueurs de la partie dans un tableau
	 * @see Joueur
	 */
	public Joueur[] getJoueurs() {
		return joueurs;
	}
	
	/**
	 * Modifie le plateau associe a la partie
	 * 
	 * @param plateau : le nouveau plateau
	 * @see Plateau
	 */
	private void setPlateau(Plateau newPlateau) {
		this.plateau = newPlateau;
	}
	
	/**
	 * Modifie les joueurs de la partie
	 * 
	 * @param newJoueur1
	 * @param newJoueur2
	 * 
	 * @see Joueur
	 */
	private void setJoueurs(Joueur newJoueur1, Joueur newJoueur2) {
		this.joueurs[0] = newJoueur1;
		this.joueurs[1] = newJoueur2;
	}
}
