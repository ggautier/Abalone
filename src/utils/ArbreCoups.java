package utils;

import java.util.LinkedList;

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
	 * Calcul� comme suit :
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
	private LinkedList<ArbreCoups> fils;
	
	public ArbreCoups() {
		this.fils = new LinkedList<ArbreCoups>();
	}
	
	/**
	 * Constructeur de la classe ArbreCoups
	 * 
	 * @param newCoup : le coup associe au noeud
	 */
	public ArbreCoups(Coup newCoup) {
		this.setCoup(newCoup);
		this.fils = new LinkedList<ArbreCoups>();
	}
	
	/**
	 * Constructeur de la classe ArbreCoups
	 * 
	 * @param newCoup : le coup associe au noeud
	 * @param newFils : les fils du noeud
	 */
	public ArbreCoups(Coup newCoup, LinkedList<ArbreCoups> newFils) {
		
		this.setCoup(newCoup);
		this.setFils(newFils);
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
		
		int score = 0;
		
		if(this.isFeuille() == false) {
			
			score = this.getFils().size();
			
			for(int i = 0 ; i < this.getFils().size() ; i++)
				score += this.getFils().get(i).getScoreCoup();
		}
		
		return this.scoreCoup;
	}
	
	public int getScoreMaxCoup() {
		
		int score = 0;
		
		if (this.isFeuille())
			score = this.scoreCoup;
		
		else {
			for (int i=0; i<fils.size(); i++)
				if (fils.get(i).getScoreCoup() > score)
					score = fils.get(i).getScoreCoup();
		}
		
		return this.scoreCoup;
	}
	
	/**
	 * Retourne les fils du noeud
	 * 
	 * @return Les fils du noeud
	 */
	public LinkedList<ArbreCoups> getFils() {
		return this.fils;
	}
	
	/**
	 * Retourne un fils du noeud
	 * 
	 * @param index : l'index du fils à retourner
	 * 
	 * @return Le fils du noeud à l'index demande dans la liste des fils
	 */
	public ArbreCoups getFils(int index) {
		return this.fils.get(index);
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
	public void setFils(LinkedList<ArbreCoups> newFils) {
		this.fils = (LinkedList<ArbreCoups>) newFils.clone();
	}
	
	public boolean isFeuille() {
		return (fils.isEmpty());
	}
	
	public void addFils(Coup fils) {
		this.fils.add(new ArbreCoups(fils));
	}
	
	public void afficher(int indentation) {
		
		for(int i = 0 ; i < indentation ; i++)
			System.out.print("   ");
		
		System.out.println(this.getCoup() + " (" + this.getFils().size() +")");
		
		for(int index = 0 ; index < this.getFils().size() ; index++) {
			this.getFils(index).afficher(indentation + 1);
		}
	}
}
