package controleur;

import modele.Partie;
import modele.Plateau;
import utils.ArbreCoups;

/**
 * <b>ControleurIA est la classe qui va generer le .</b>
 * <p>
 * Un Controleur est caracterise par les informations suivantes :
 * <ul>
 * <li>Un controleur qui va solliciter le calcul du meilleur coup suivant la configuration de la partie.</li>
 * </ul>
 * </p>
 * 
 * @see Controleur
 * 
 * @author Lenogue Matthieu
 * @author Gautier Quentin
 * @author Gautier Gaetan
 * @author Ouary Maxime
 * 
 * @version 1.0
 */
public class ControleurIA {

	/**
	 * Le controleur associe au controleur de l'IA
	 * 
	 * @see Controleur
	 */
	private Controleur controleurPartie;
	
	/**
	 * L'arbre des coups possibles
	 * 
	 * @see ArbreCoups
	 */
	private ArbreCoups arbreCoups;
	
	public ControleurIA(Controleur newControleur) {
		this.setControleurPartie(newControleur);
	}
	
	public void test() {
		Plateau pTemp = this.getControleurPartie().getPartie().getPlateau().copy();
		
		
	}
	
	/**
	 * Retourne le controleur associe au controleur de l'IA
	 * 
	 * @return Le controleur associe au controleur de l'IA
	 * 
	 * @see Controleur
	 */
	public Controleur getControleurPartie() {
		return this.controleurPartie;
	}
	
	/**
	 * Retourne l'arbre des coups possibles
	 * 
	 * @return L'arbre des coups possibles
	 * 
	 * @see ArbreCoups
	 */
	public ArbreCoups getArbreCoups() {
		return this.arbreCoups;
	}
	
	/**
	 * Modifie le controleur associe au controleur de l'IA
	 * 
	 * @param newControleur : le nouveau controleur
	 * 
	 * @see Controleur
	 */
	public void setControleurPartie(Controleur newControleur) {
		this.controleurPartie = newControleur;
	}
	
	/**
	 * Modifie l'arbre des coups possibles
	 * 
	 * @param newArbre : le nouvel arbre des coups possibles
	 * 
	 * @see ArbreCoups
	 */
	public void setArbreCoups(ArbreCoups newArbre) {
		this.arbreCoups = newArbre;
	}
	
	public void construireArbre() {
		
	}
	
	
}
