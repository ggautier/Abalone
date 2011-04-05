package modele;

import java.util.Timer;
import java.util.TimerTask;

import controleur.CompteRebours;

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
	protected String nom;
	
	/**
	 * <p>La "couleur" du joueur. Etant donne qu'Abalone se joue a 
	 * deux joueurs, on peut stocker cette information dans une 
	 * variable booleenne.</p>
	 */
	protected boolean camps;
	
	/**
	 * <p>Le score personnel du joueur.</p> 
	 */
	protected int score;
	
	/**
	 * <p>La variable humain permet de gerer si un coup doit etre joue 
	 * par un autre joueur, ou joue immediatement par la machine.</p> 
	 */
	protected boolean humain;
	
	protected CompteRebours tempsRestantCoup;
	
	protected CompteRebours tempsRestantGlobal;

	protected int r, g, b;
	
	public Joueur(String newNom, boolean newCamps, boolean newHumain) {
		this.setNom(newNom);
		this.setCamps(newCamps);
		this.setHumain(newHumain);

		
		if(this.getCamps())
		{
			this.setR(255);
			this.setG(255);
			this.setB(255);
		}
		else
		{
			this.setR(0);
			this.setG(0);
			this.setB(0);
		}
		
		this.setScore(0);
		
		this.tempsRestantGlobal = new CompteRebours(20,00);
		this.tempsRestantGlobal.start();
		this.tempsRestantGlobal.demarrer();

	}
	
	/**
	 * Constructeur de la classe Joueur
	 * 
	 * @param newNom : le nom du joueur contenue dans une chaine de caractere
	 * @param newCamps : le camps du joueur dans un booleen 
	 * @param newHumain : le joueur adverse humain ou CPU
	 * @param newScore : le score personnel du joueur
	 * @param couleurR : couleur du joueur rouge, comprise entre 0 et 255 
	 * @param couleurG : couleur du joueur verte, comprise entre 0 et 255 
	 * @param couleurB : couleur du joueur bleue, comprise entre 0 et 255 
	 */
	public Joueur(String newNom, boolean newCamps , boolean newHumain, int newScore, int couleurR, int couleurG, int couleurB) {
		this.setNom(newNom);
		this.setCamps(newCamps);
		this.setScore(newScore);
		this.setHumain(newHumain);
		this.setR(couleurR);
		this.setG(couleurG);
		this.setB(couleurB);
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
	// Fin de Section Getters-Setters
	
	
	public boolean equals(Joueur j) {
		if (j != null)
			return ((this.camps == j.camps) && (this.nom.equals(j.nom)) && (this.humain == j.humain));
		else
			return false;
	}

	public String getTempsRestantCoup() {
		return tempsRestantCoup.toString();
	}

	public void setTempsRestantCoup(CompteRebours tempsRestantCoup) {
		this.tempsRestantCoup = tempsRestantCoup;
	}

	public String getTempsRestantGlobal() {
		return tempsRestantGlobal.toString();
	}

	public void setTempsRestantGlobal(CompteRebours tempsRestantGlobal) {
		this.tempsRestantGlobal = tempsRestantGlobal;
	}
	
	
}