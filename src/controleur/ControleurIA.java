package controleur;

import java.util.Vector;

import modele.Coup;
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
	
	protected Controleur controleurVirtuel;
	
	
	protected Partie partieVirtuelle; // Sera utilisee pour les simulations.
	
	/**
	 * L'arbre des coups possibles
	 * 
	 * @see ArbreCoups
	 */
	private ArbreCoups arbreCoups;
	
	// Le controleur possede une partie virtuelle dont il recopie regulierement le plateau a partir de celui d'origine
	public ControleurIA(Controleur controleur) throws Exception {
		
		this.controleurPartie = controleur;
		this.controleurVirtuel = new Controleur(null);
		this.partieVirtuelle = new Partie(this.controleurVirtuel,null);
		
		this.partieVirtuelle.setPlateau(this.controleurPartie.getPartie().getPlateau().copy());
		this.partieVirtuelle.setJ1(this.controleurPartie.getPartie().getJ1());
		this.partieVirtuelle.setJ2(this.controleurPartie.getPartie().getJ2());
	}
	
	public void meilleurCoup() {
		this.partieVirtuelle.setPlateau(this.controleurPartie.getPartie().getPlateau().copy());
		
		Vector<Coup> vCoups = new Vector<Coup>();
		int profondeur = 1;
		
		for (int p=0; p<profondeur; p++) {
			
	        vCoups = getControleurPartie().getCoupsPossibles(
	        		getControleurPartie().getBillesJoueur(
	        				getControleurPartie().getPartie().getJ2()
	        			)
	        		);
	        for (int i=0; i<vCoups.size(); i++)
	        	this.arbreCoups.addFils(vCoups.get(i));
	         
		}
        
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
