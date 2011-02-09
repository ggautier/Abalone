/**
 * <b>Joueur est la classe representant un joueur actif lors d'une partie.</b>
 * <p>
 * Un Joueur est caracterise par les informations suivantes :
 * <ul>
 * <li>Un nom, la chaine de caractares le nommant.</li>
 * <li>Une couleur, correspondant a une valeur binaire indiquant son camps.</li>
 * <li>Un score, qui se modifie durant la partie.</li>
 * <li>Un flag humain, indiquant s'il est un joueur humain, ou gere par la machine.</li>
 * </ul>
 * </p>
 * 
 * 
 * @author Lenogue Matthieu
 * @author Gautier Quentin
 * @author Gautier Gaetan
 * @author Ouairy Maxime
 * 
 * @version 1.0
 */
public class Joueur {

	/**
	 * Le nom du joueur contenu dans une chaine de caracteres.
	 */
	private String nom;
	
	/**
	 * La "couleur" du joueur.<br>
	 * Etant donne qu'Abalone se joue a deux joueurs,
	 * cette information est stockee dans une variable booleenne.
	 */
	private boolean	couleur;
	
	/**
	 * Le score personnel du joueur.
	 */
	private int score;
	
	/**
	 * Indique si le joueur est humain ou non.
	 */
	private boolean humain;
	
	/**
	 * Constructeur de la classe Joueur
	 * 
	 * @param newNom : le nom du joueur
	 * @param newCouleur : la couleur du joueur (couleur 1 ou 2)
	 * @param newHumain : un booleen indiquant si le joueur est humain
	 */
	public Joueur(String newNom, boolean newCouleur, boolean newHumain) {
		this.setNom(newNom);
		this.setCouleur(newCouleur);
		this.setHumain(newHumain);
	}
	
	/**
	 * Renvoie le nom du joueur.
	 * 
     * @return Le nom du joueur. 
     */
	public String getNom() {
		return nom;
	}
	
	/**
     * Modifie le nom du joueur.
     * 
     * @param nom : le nouveau nom du joueur.
     */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Renvoie la couleur du joueur.
	 * 
     * @return Le couleur du joueur. 
     */
	public boolean getCouleur() {
		return couleur;
	}
	
	/**
     * Modifie la couleur du joueur.
     * 
     * @param couleur : la nouvelle couleur du joueur.
     */
	public void setCouleur(boolean couleur) {
		this.couleur = couleur;
	}
	
	/**
	 * Renvoie le score du joueur.
	 * 
     * @return Le score du joueur. 
     */
	public int getScore() {
		return score;
	}
	
	/**
     * Modifie le score du joueur.
     * 
     * @param score : le nouveau score du joueur.
     */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Indique si le joueur est humain ou non.
	 * 
     * @return Le booleen indiquant si le joueur est  humain ou non. 
     */
	public boolean isHumain() {
		return humain;
	}
	
	/**
     * Modifie le marqueur de gestion du joueur.
     * @param humain : le nouveau marqueur de gestion du joueur.<br>True si le joueur est humain.
     */
	public void setHumain(boolean humain) {
		this.humain = humain;
	}
	
	/**
	 * Test d'egalite entre deux joueurs.
	 * 
	 * @param joueur
	 * @return
	 * 		<ul>
	 * 			<li>True si les 2 joueurs sont egaux,</li>
	 * 			<li>False sinon.</li>
	 * 		</ul>
	 */
	public boolean equals(Joueur joueur) {
		return (this.couleur == joueur.couleur);
	}
	// Fin de Section Getters-Setters
}
