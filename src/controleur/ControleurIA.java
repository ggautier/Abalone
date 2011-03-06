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
	public int nbBranchements = 0;
	public int profondeur = 0;
	private static ControleurIA instance = null;
	
	// Le controleur possede une partie virtuelle dont il recopie regulierement le plateau a partir de celui d'origine
	private ControleurIA(Controleur controleur) throws Exception {
		
		this.controleurPartie = controleur;
		this.controleurVirtuel = new Controleur(null);
		this.partieVirtuelle = new Partie(this.controleurVirtuel,null,false);
		
		try {
			this.getControleurVirtuel().getPartie().charger(this.controleurPartie.getPartie().toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
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
	
	public void construireArbre() throws IOException {
		
		ControleurIA.arbre = true;
		this.arbreCoups = new ArbreCoups();
		
		this.construireFils(this.getArbreCoups());
		
		for(int index = 0 ; index < this.getArbreCoups().getFils().size() ; index++) {
			this.construireFils(this.getArbreCoups().getFils(index));
		}
		
		this.getArbreCoups().afficher(0);
	}
	
	public void pousserArbre(ArbreCoups arb) throws IOException {

		this.profondeur++;
		System.out.println("PROF "+this.profondeur);
		if (!arb.isFeuille()) { // Si ce n'est pas une feuille
			if (arb.getCoup() != null) // NULL si on est au premier noeud, donc on s'occupe des fils
				this.simulerCoup(arb.getCoup()); // On execute le coup virtuellement, pour que le plateau avance dans l'arbre
			for (int i=0; i<arb.getFils().size(); i++) {// Puis, pour chaque fils ... 
				pousserArbre(arb.getFils(i));		   // ... on refait, cette procedure.

			}
			//// Annuler coup ici ?
		}
		else {// Si c'est une feuille (aucune branche en dessous)
			construireFils(arb); // On genere d'autres coups
			nbBranchements++;
			System.out.println("POUSSER "+nbBranchements);
		}
		
		
		this.profondeur--;

		
	}
	
	private void construireFils(ArbreCoups noeud) throws IOException {
				
		System.out.println("DBG noeud : " + noeud.getCoup());
		
		if(noeud.getCoup() != null)
			this.simulerCoup(noeud.getCoup());
		
		
		ArrayList<Coup> coups = getControleurVirtuel().getCoupsPossibles(
        							getControleurVirtuel().getBillesJoueur(
        									this.getControleurVirtuel().getPartie().getJCourant()));
		
		for (int i = 0 ; i < coups.size() ; i++)
        	noeud.addFils(coups.get(i));
		
		try {
			if(noeud.getCoup() != null) {
				this.getControleurVirtuel().getPartie().quickLoad();
			}
		}
		catch (IOException e) {}
	}
	
	public void simulerCoup(Coup coup) throws IOException {
		// Application du coup dans la prtie virtuelle
		Bille bTemp;
		ArrayList<Bille> aSelectionner = new ArrayList<Bille>();

		if (coup == null)
			System.out.println("Coup NUL");
		
		for (int i=0; i < coup.getBilles().size(); i++) {
			
			bTemp = this.getControleurVirtuel().getPartie().getPlateau().getBille(
					(int) coup.getBilles().get(i).getX(), (int) coup.getBilles().get(i).getY());
			
			
			if (bTemp != null)
				aSelectionner.add(bTemp);
			else 
				System.out.println("DBG : NUL "+coup.getBilles().get(i).getX()+","+coup.getBilles().get(i).getY());
		}
		
		//this.getControleurPartie().setSelectionnees(aSelectionner);
		
		this.getControleurVirtuel().setSelectionnees(aSelectionner);
		this.getControleurVirtuel().action(coup.getDirection());
		
	}
	
	public void jouer() throws IOException {
		this.nbBranchements = 0;
		this.profondeur = 0;
		System.out.println(">> JE SUIS IA, et je JOOOOOOUUEE ! <<\n\n\n");
		
		try {
			this.getControleurVirtuel().getPartie().charger(this.controleurPartie.getPartie().toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		this.pousserArbre(this.arbreCoups);
		this.pousserArbre(this.arbreCoups);

		if(this.getArbreCoups().getFils().size() > 0) {
			
			int indexCoup = new Random().nextInt(this.getArbreCoups().getFils().size() - 1);
			Bille bTemp;
			ArrayList<Bille> aSelectionner = new ArrayList<Bille>();
			
			//System.out.println("IA selectionne : "+ aSelectionner);
			
			System.out.println("\n\n\nDBG indexCoup: "+indexCoup);
			System.out.println("Nombre de coups possibles : " + this.getArbreCoups().getFils().size());
			
			for (int indexBille = 0 ; indexBille < this.getArbreCoups().getFils().get(indexCoup).getCoup().getBilles().size() ; indexBille++) {
				
				//System.out.println("JEU : Selection de la Bille " + indexBille);
				
				bTemp = this.getControleurPartie().getPartie().getPlateau().getBille(
						(int) this.getArbreCoups().getFils().get(indexCoup).getCoup().getBilles().get(indexBille).getX(), (int) this.getArbreCoups().getFils().get(indexCoup).getCoup().getBilles().get(indexBille).getY());
				
				if (bTemp != null) {
					if (bTemp.getJoueur().equals(this.getControleurPartie().getPartie().getJCourant()))
						aSelectionner.add(bTemp);
				}
				else 
					System.out.println("DBG : NULLEUH (jouer) "+this.getArbreCoups().getFils().get(indexCoup).getCoup().getBilles().get(indexBille).getX()+","+this.getArbreCoups().getFils().get(indexCoup).getCoup().getBilles().get(indexBille).getY());
	
			}
			
			this.getControleurPartie().setSelectionnees(aSelectionner);
			System.out.println("Direction "+this.getArbreCoups().getFils(indexCoup).getCoup().getDirection());
			this.getControleurPartie().action(this.getArbreCoups().getFils(indexCoup).getCoup().getDirection());
			
			//this.arbreCoups = new ArbreCoups(this.getArbreCoups().getFils().get(indexCoup));
			this.construireArbre();
			
			
		}
		// arbre = false;
	}
}
