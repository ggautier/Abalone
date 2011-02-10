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
	 * <p>Le nom du joueur contenue dans une chaine de caractere.</p> 
	 */
	private String nom;
	
	/**
	 * <p>La "couleur" du joueur. Etant donne qu'Abalone se joue a 
	 * deux joueurs, on peut stocker cette information dans une 
	 * variable booleenne.</p>
	 */
	private boolean	camps;
	
	/**
	 * <p>Le score personnel du joueur.</p> 
	 */
	private int score;
	
	/**
	 * <p>La variable humain permet de gerer si un coup doit etre joue 
	 * par un autre joueur, ou joue immediatement par la machine.</p> 
	 */
	private boolean humain;

	private int r, g, b;
	
	public Joueur(String newNom, boolean newCamps, boolean newHumain) {
		this.setNom(newNom);
		this.setCamps(newCamps);
		this.setHumain(newHumain);
		
		if(this.getCamps())
		{
			this.setR(240);
			this.setG(10);
			this.setB(10);
		}
		else
		{
			this.setR(255);
			this.setG(255);
			this.setB(255);
		}
	}
	
	// Section Getters-Setters
	
	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
	
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
     * @return Le camps du joueur. 
     */
	public boolean getCamps() {
		return camps;
	}
	
	/**
     * Change la valeur binaire du camp du joueur.
     * @param couleur
     *            Le nouveau camp du joueur.
     */
	public void setCamps(boolean camps) {
		this.camps = camps;
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
     * Change le degre "d'humanite" d'un joueur.
     * @param humain
     *            Le nouveau niveau "d'humanite" du joueur.
     */
	public void setHumain(boolean humain) {
		this.humain = humain;
	}
	
	public boolean equals(Joueur j) {
		return ((this.camps == j.camps) && this.nom.equals(j.nom) && this.humain == j.humain);
	}
	// Fin de Section Getters-Setters
}
