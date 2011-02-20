package controleur;

import java.util.Vector;

import modele.Bille;
import modele.Coup;
import modele.Joueur;
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
	 * Le controleur associe a la partie virtuelle
	 * 
	 * @see Controleur
	 */
	private Controleur controleurVirtuel;
	
	
	private Partie partieVirtuelle; // Sera utilisee pour les simulations.
	
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
		this.partieVirtuelle.setjCourant(this.controleurPartie.getPartie().getJCourant());
		this.arbreCoups = new ArbreCoups(null);
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
        
		this.arbreCoups.afficher(0);
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
		this.partieVirtuelle.setPlateau(this.controleurPartie.getPartie().getPlateau().copy());
		
		this.arbreCoups = new ArbreCoups();
		
		this.construireFils(this.arbreCoups);
		
		for(int index = 0 ; index < this.arbreCoups.getFils().size() ; index++) {
			System.out.println("Construction fils " + index);
			this.construireFils(this.arbreCoups.getFils(index));
		}
		
		this.arbreCoups.afficher(0);
	}
	
	public void construireFils(ArbreCoups noeud) {
		
		Joueur joueur;
		
		// Recuperation du joueur adverse au joueur du noeud pere
		
		if(noeud.getCoup() == null)
			joueur = this.partieVirtuelle.getJoueur(!this.partieVirtuelle.getJCourant().getCamps());
		else
			joueur = this.partieVirtuelle.getJoueur(!noeud.getCoup().getJoueur().getCamps());
		
		Vector<Coup> coups = getControleurPartie().getCoupsPossibles(
        						getControleurPartie().getBillesJoueur(joueur));
		
		for (int i = 0 ; i < coups.size() ; i++)
        	noeud.addFils(coups.get(i));
	}
}
