package controleur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import modele.Bille;
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
	
	public static boolean arbre = false;
	private static ControleurIA instance = null;
	
	// Le controleur possede une partie virtuelle dont il recopie regulierement le plateau a partir de celui d'origine
	private ControleurIA(Controleur controleur) throws Exception {
		
		this.controleurPartie = controleur;
		this.controleurVirtuel = new Controleur(null);
		this.partieVirtuelle = new Partie(this.controleurVirtuel,null);
		

		this.init();
		this.construireArbre();
	}
	
	public static ControleurIA getInstance(Controleur newControleur) {
		
		if(ControleurIA.instance == null) {
			try {
				ControleurIA.instance =  new ControleurIA(newControleur);
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return ControleurIA.instance;
	}
	
	private void init() {
		try {
			this.partieVirtuelle.charger(this.controleurPartie.getPartie().toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
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
	}
	
	public void construireArbre() {
		
		ControleurIA.arbre = true;
		
		this.construireFils(this.getArbreCoups());
		
		for(int index = 0 ; index < this.getArbreCoups().getFils().size() ; index++) {
			this.construireFils(this.getArbreCoups().getFils(index));
		}
		
		this.getArbreCoups().afficher(0);
	}
	
	public void pousserArbre(ArbreCoups arb) {

		
		if (!arb.isFeuille()) { // Si ce n'est pas une feuille
			if (arb.getCoup() != null) // NULL si on est au premier noeud, donc on s'occupe des fils
				this.simulerCoup(arb.getCoup()); // On execute le coup virtuellement, pour que le plateau avance dans l'arbre
			for (int i=0; i<arb.getFils().size(); i++) // Puis, pour chaque fils ... 
				pousserArbre(arb.getFils(i));		   // ... on refait, cette procedure.
			
		}
		else {// Si c'est une feuille (aucune branche en dessous)
			construireFils(arb); // On genere d'autres coups
		}
		
	}
	
	private void construireFils(ArbreCoups noeud) {
		
		Joueur joueur;
		
		System.out.println("DBG noeud : " + noeud.getCoup());
		
		if(noeud.getCoup() != null) {
			
			this.simulerCoup(noeud.getCoup());
			
			joueur = this.getControleurVirtuel().getPartie().getJoueur(!noeud.getCoup().getJoueur().getCamps());
		}
		else
			joueur = this.getControleurVirtuel().getPartie().getJoueur(!this.getControleurVirtuel().getPartie().getJCourant().getCamps());
		
		ArrayList<Coup> coups = getControleurVirtuel().getCoupsPossibles(
        						getControleurVirtuel().getBillesJoueur(joueur));
		
		for (int i = 0 ; i < coups.size() ; i++)
        	noeud.addFils(coups.get(i));
		
		try {
			if(noeud.getCoup() != null) {
				this.getControleurVirtuel().getPartie().quickLoad();
			}
		}
		catch (IOException e) {}
	}
	
	public void simulerCoup(Coup coup) {
		// Application du coup dans la prtie virtuelle
		Bille bTemp;
		ArrayList<Bille> aSelectionner = new ArrayList<Bille>();
		if (coup == null)
			System.out.println("Coup NULLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
		for (int i=0; i < coup.getBilles().size(); i++) {
			bTemp = this.getControleurVirtuel().getPartie().getPlateau().getBille(
					(int) coup.getBilles().get(i).getX(), (int) coup.getBilles().get(i).getY());
			if (bTemp != null)
				aSelectionner.add(bTemp);
			else 
				System.out.println("DBG : NULLEUH "+coup.getBilles().get(i).getX()+","+coup.getBilles().get(i).getY());
		}
		
		//this.getControleurPartie().setSelectionnees(aSelectionner);
		
		this.getControleurVirtuel().setSelectionnees(aSelectionner);
		this.getControleurVirtuel().action(coup.getDirection());
		
	}
	
	public void jouer() {
		System.out.println(">> JE SUIS IA, et je JOOOOOOUUEE ! <<\n\n\n");
		this.pousserArbre(this.arbreCoups);
		
		int indexCoup = new Random().nextInt(this.getArbreCoups().getFils().size() - 1);
		Bille bTemp;
		System.out.println("\n\n\nDBG : "+indexCoup);
		ArrayList<Bille> aSelectionner = new ArrayList<Bille>();
		for (int i=0; i < this.getArbreCoups().getFils().get(indexCoup).getCoup().getBilles().size(); i++) {
			System.out.println("JEU : Selection de la Bille "+i);
			bTemp = this.getControleurPartie().getPartie().getPlateau().getBille(
					(int) this.getArbreCoups().getFils().get(indexCoup).getCoup().getBilles().get(i).getX(), (int) this.getArbreCoups().getFils().get(indexCoup).getCoup().getBilles().get(i).getY());
			if (bTemp != null)
				aSelectionner.add(bTemp);
			else 
				System.out.println("DBG : NULLEUH (jouer) "+this.getArbreCoups().getFils().get(indexCoup).getCoup().getBilles().get(i).getX()+","+this.getArbreCoups().getFils().get(indexCoup).getCoup().getBilles().get(i).getY());

		}
		this.getControleurPartie().setSelectionnees(aSelectionner);
		System.out.println("Direction "+this.getArbreCoups().getFils(indexCoup).getCoup().getDirection());
		this.getControleurPartie().action(this.getArbreCoups().getFils(indexCoup).getCoup().getDirection());
		
		init();
		// arbre = false;
	}
}
