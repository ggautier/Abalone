package controleur;

import java.io.IOException;
import java.util.ArrayList;

import modele.Coup;
import modele.Joueur;
import modele.Partie;
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
		this.partieVirtuelle.setJCourant(this.controleurPartie.getPartie().getJCourant());
		this.init();
	}
	
	private void init() {
		this.arbreCoups = new ArbreCoups();
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
	
	public Controleur getControleurVirtuel() {
		return this.controleurVirtuel;
	}
	
	public Partie getPartieVirtuelle() {
		return this.partieVirtuelle;
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
	
	public void setControleurVirtuel(Controleur newControleur) {
		this.controleurVirtuel = newControleur;
	}
	
	public void setPartieVirtuelle(Partie newPartie) {
		this.partieVirtuelle = newPartie;
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
	
	public void meilleurCoup() {
		this.getPartieVirtuelle().setPlateau(this.getControleurPartie().getPartie().getPlateau().copy());
		
		ArrayList<Coup> vCoups = new ArrayList<Coup>();
		int profondeur = 1;
		
		for (int p=0; p<profondeur; p++) {
			
	        vCoups = getControleurPartie().getCoupsPossibles(
	        		getControleurPartie().getBillesJoueur(
	        				getControleurPartie().getPartie().getJ2()
	        			)
	        		);
	        
	        for (int i=0; i<vCoups.size(); i++)
	        	this.getArbreCoups().addFils(vCoups.get(i));
		}
        
		this.getArbreCoups().afficher(0);
	}
	
	public void construireArbre() {
		this.getPartieVirtuelle().setPlateau(this.getControleurPartie().getPartie().getPlateau().copy());
		
		this.init();
		
		this.construireFils(this.getArbreCoups());
		
		for(int index = 0 ; index < this.getArbreCoups().getFils().size() ; index++) {
			this.construireFils(this.getArbreCoups().getFils(index));
		}
		
		this.getArbreCoups().afficher(0);
	}
	
	private void construireFils(ArbreCoups noeud) {
		
		Joueur joueur;
		
		if(noeud.getCoup() != null) {
			
			// Application du coup dans la prtie virtuelle
			//this.getControleurVirtuel().setSelectionnees(noeud.getCoup().getBilles());
			//this.getControleurVirtuel().action(noeud.getCoup().getDirection());
			
			System.out.println("DBG " + noeud.getCoup().getBilles());
			
			joueur = this.getPartieVirtuelle().getJoueur(!noeud.getCoup().getJoueur().getCamps());
		}
		
		else
			joueur = this.getPartieVirtuelle().getJoueur(!this.getPartieVirtuelle().getJCourant().getCamps());
		
		ArrayList<Coup> coups = getControleurPartie().getCoupsPossibles(
        						getControleurPartie().getBillesJoueur(joueur));
		
		for (int i = 0 ; i < coups.size() ; i++)
        	noeud.addFils(coups.get(i));
		
		/*
		try {
			if(noeud.getCoup() != null) {
				this.getPartieVirtuelle().quickLoad();
			}
		}
		
		catch (IOException e) {}
		*/
	}
}
