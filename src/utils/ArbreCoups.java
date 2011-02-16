package utils;

import java.util.List;

import modele.Coup;

public class ArbreCoups {

	/**
	 * Le coup associe au noeud
	 * 
	 * @see Coup
	 */
	private Coup coup;
	
	/**
	 * Le "score" du coup associe au noeud
	 * Calculé comme suit :
	 *  +1 si joueur courant tue une bille adverse
	 *  -1 si joueur courant perd un bille
	 *  0 sinon
	 * 
	 * @see ArbreCoups#coup
	 */
	private int scoreCoup;
	
	/**
	 * Les fils du noeud
	 * 
	 * @see List
	 */
	private List<ArbreCoups> fils;
	
	/**
	 * Constructeur de la classe ArbreCoups
	 * 
	 * @param newCoup : le coup associe au noeud
	 */
	public ArbreCoups(Coup newCoup) {
		
	}
	
	/**
	 * Constructeur de la classe ArbreCoups
	 * 
	 * @param newCoup : le coup associe au noeud
	 * @param newFils : les fils du noeud
	 */
	public ArbreCoups(Coup newCoup, List<ArbreCoups> newFils) {
		
	}
	
	/**
	 * Retourne le coup associe au noeud
	 * 
	 * @return Le coup associe au noeud
	 */
	public Coup getCoup() {
		return this.coup;
	}
	
	/**
	 * Retourne le score du coup associe au noeud
	 * 
	 * @see ArbreCoups#scoreCoup
	 */
	public int getScoreCoup() {
		return this.scoreCoup;
	}
	
	/**
	 * Retourne les fils du noeud
	 * 
	 * @return Les fils du noeud
	 */
	public List<ArbreCoups> getFils() {
		return this.fils;
	}
	
	/**
	 * Modifie le coup associe au noeud
	 * 
	 * @param newCoup : le nouveau coup
	 */
	public void setCoup(Coup newCoup) {
		this.coup = newCoup;
	}
	
	/**
	 * Modifie le score du coup associe au noeud
	 * 
	 * @param newScoreCoup : le nouveau score
	 * 
	 * @see ArbreCoups#scoreCoup
	 */
	public void setScoreCoup(int newScoreCoup) {
		this.scoreCoup = newScoreCoup;
	}
	
	/**
	 * Modifie l'ensemble des fils du noeuds
	 * 
	 * @param newFils : Une liste contenant les nouveaux fils du noeud
	 */
	public void setFils(List<ArbreCoups> newFils) {
		this.fils = newFils;
	}
}
