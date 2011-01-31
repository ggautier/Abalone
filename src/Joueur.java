/**
 * <b>Joueur est la classe représentant un joueur actif lors d'une partie.</b>
 * <p>
 * Un Joueur est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un plateau, sur lequel joue le joueur.</li>
 * <li>Une partie, dans laquel le joueur intéragit.</li>
 * <li>Des billes, que le joueur peut déplacer.</li>
 * </ul>
 * </p>
 * 
 * @see Plateau
 * @see Partie
 * @see Bille
 * 
 * @author Lenogue Matthieu
 * @author Gautier Quentin
 * @author Gautier Gaetan
 * @author Ouary Maxime
 * 
 * @version 1.0
 */
public class Joueur {

	/**
	 * <p>Le nom du joueur contenue dans une chaine de caractère.</p> 
	 */
	private String nom;
	/**
	 * <p>La "couleur" du joueur. Etant donné qu'Abalone se joue à 
	 * deux joueurs, on peut stocker cette information dans une 
	 * variable booleenne.</p>
	 */
	private boolean	couleur;
	/**
	 * <p>Le score personnel du joueur.</p> 
	 */
	private int score;
	/**
	 * <p>La variable humain permet de gérer si un coup doit être joué 
	 * par un autre joueur, ou joué immédiatement par la machine.</p> 
	 */
	private boolean humain;
	/**
	 * <p>L'instance de partie auquelle le joueur est rattaché.</p> 
	 */
	private Partie 	partie;
	/**
	 * <p>L'instance de plateau rattaché au joueur.</p>
	 */
	private Plateau plateau;
	
	
	// Section Getters-Setters
	/**
	 * Retourne le nom du joueur dans une chaine de caracteres.
     * @return Le nom du joueur. 
     */
	public String getNom() {
		return nom;
	}
	/**
     * Change le nom du joueur.
     * @param nom
     *            Le nouveau nom du joueur.
     */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * Retourne la valeur binaire du joueur.
     * @return Le camp du joueur. 
     */
	public boolean isCouleur() {
		return couleur;
	}
	/**
     * Change la valeur binaire du camp du joueur.
     * @param couleur
     *            Le nouveau camp du joueur.
     */
	public void setCouleur(boolean couleur) {
		this.couleur = couleur;
	}
	/**
	 * Retourne la valeur du score du joueur.
     * @return Le score du joueur. 
     */
	public int getScore() {
		return score;
	}
	/**
     * Impose une nouvelle valeur du score pour le joueur.
     * @param score
     *            Le nouveau score du joueur.
     */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * Retourne une valeur indiaquant si le joueur est humain, ou non.
     * @return Le booleen indiquant un joueur humain, ou non. 
     */
	public boolean isHumain() {
		return humain;
	}
	/**
     * Change le degré "d'humanité" d'un joueur.
     * @param humain
     *            Le nouveau niveau "d'humanité" du joueur.
     */
	public void setHumain(boolean humain) {
		this.humain = humain;
	}
	/**
	 * Retourne l'instance de la partie en cours.
     * @return La partie correspondante au joueur. 
     */
	public Partie getPartie() {
		return partie;
	}
	/**
     * Change l'instance de Partie, que le joueur joue.
     * @param partie
     *            Le nouvelle partie que le joueur joue.
     */
	public void setPartie(Partie partie) {
		this.partie = partie;
	}
	/**
	 * Retourne l'instance de plateau sur laquelle le joueur joue.
     * @return Le score du joueur. 
     */
	public Plateau getPlateau() {
		return plateau;
	}
	/**
     * Impose une nouvelle instance de plateau sur laquelle le joueur joue.
     * @param score
     *            Le nouveau score du joueur.
     */
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	// Fin de Section Getters-Setters
}
