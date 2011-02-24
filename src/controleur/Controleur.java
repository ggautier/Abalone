package controleur;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;
import vue.FenetreOver;
import vue.FenetrePlateau;
import vue.FenetrePrincipale;

import modele.Bille;
import modele.Coup;
import modele.Joueur;
import modele.Partie;
import modele.Plateau;


/**
 * <b>Controleur est la classe qui va mettre a jour les informations du modele, en respectant les regles du jeu.</b>
 * <p>
 * Un Controleur est caracterise par les informations suivantes :
 * <ul>
 * <li>Une partie, qui va servir de point d'acces aux informations du modele.</li>
 * <li>Une fenetrePrincipale, qui va solliciter des changements.</li>
 * <li>Un controleurIA, qui peut generer le meilleur coup possible a l'instant.</li>
 * </ul>
 * </p>
 * 
 * @see Partie
 * @see FenetrePrincipale
 * @see ControleurIA
 * 
 * @author Lenogue Matthieu
 * @author Gautier Quentin
 * @author Gautier Gaetan
 * @author Ouary Maxime
 * 
 * @version 1.0
 */
public class Controleur {

	protected ControleurIA		controleurIA;
	protected FenetrePrincipale	fenetrePrincipale;
	protected Partie			partie; 
	protected ArrayList<Bille>		selectionnees;  // Contient toutes les Billes actuellement selectionnees.
	protected ArrayList<ArrayList<Bille>> visees;		// Contient les Billes poussables par les Billes selectionnees.
	protected ArrayList<Integer> 		coups;		// Contient les directions vers lesquelles les Billes selectionnees peuvent aller.
	protected Bille				pointee;		// Contient la Bille actuellement pointee par la Souris.
	protected int		    	deplacementVise;// Contient le "deplacement" pointe par la Souris.
	


	// Directions, sous la forme lignecolonne : 1=pas de mouvement; 0=mouvement descendant; 2=mouvement montant
	public final static int GAUCHE = 10;
	public final static int DROITE = 12;
	public final static int BAS_GAUCHE = 21;
	public final static int HAUT_DROITE = 01;
	public final static int HAUT_GAUCHE = 00;
	public final static int BAS_DROITE = 22;
	
	public final static int[] tabDir = {GAUCHE, DROITE, BAS_GAUCHE, HAUT_DROITE, HAUT_GAUCHE, BAS_DROITE};
	
	// Axes, sous la forme lignecolonne : 1=mouvement; 0=pas de mouvement
	public final static int GD = 01;
	public final static int HG_BD = 11;
	public final static int HD_BG = 10;
	
	public Controleur(FenetrePrincipale fen) throws Exception
	{
		this.fenetrePrincipale = fen;
		this.partie = new Partie(this, "./data/plateau/defautDebug.plt");
		this.selectionnees = new ArrayList<Bille>(3);
		this.visees = new ArrayList<ArrayList<Bille>>(2);
		this.coups = new ArrayList<Integer>(6);
		this.deplacementVise = -1;
	}
	
	public ControleurIA getControleurIA() {
		return this.controleurIA;
	}
	
	public void initControleurIA() throws Exception {
		this.controleurIA = ControleurIA.getInstance(this);
	}
	

	// Retourne le nombre de coups pour lesquels une Case est cible.
	public int nbNext(Point p) {
		int dir = -1;
		int retour = 0;
		
		for (int j=0; j < coups.size(); j++) {
			dir = coups.get(j);
			for (int i=0; i < selectionnees.size(); i++)
				if (voisineP(selectionnees.get(i),dir,1).equals(p))
						retour++;
					
		}
		
		return retour;
	}
	
	// Normalement inutile
	public int nextCoup(Point p) {
		int dir = -1;
		int retour = -1;
		
		for (int j=0; j < coups.size(); j++) {
			dir = coups.get(j);
			for (int i=0; i < selectionnees.size(); i++)
				if (voisineP(selectionnees.get(i),dir,1).equals(p))
						retour = coups.get(j);
					;
		}
		
		return retour;
	}
	
	public void deselectionner() {
		this.selectionnees.clear();
		this.visees.clear();
		this.coups.clear();
		
		this.fenetrePrincipale.rafraichir();
	}
	
	public void majDeplacementVise(Point p) {
		this.deplacementVise = -1;
		int dir = -1;
		
		for (int j=0; j < coups.size(); j++) {
			dir = coups.get(j);
			for (int i=0; i < selectionnees.size(); i++)
				if (nbNext(new Point(selectionnees.get(i).getColonne(),selectionnees.get(i).getLigne())) >= 0) {
					if (voisineP(selectionnees.get(i),dir,1).equals(p)) {
						this.deplacementVise = coups.get(j);
					} 
				}			
		}
		
	}
	
	/**
	 * Retourne la partie associee au controleur
	 * 
	 * @return La partie jouee actuellement et associee au controleur.
	 */
	public Partie getPartie() 
	{
		return partie;
	}

	/**
	 * Modifie la partie associee au controleur
	 * 
	 * @param partie : la nouvelle parie associee au controleur
	 */
	public void setPartie(Partie partie) 
	{
		this.partie = partie;
	}
	
	/**
	 * Retourne l'ensemble des billes selectionnees
	 * 
	 * @return Un vecteur contenant les billes actuellement selectionnees
	 */
	public ArrayList<Bille> getSelectionnees() 
	{
		return selectionnees;
	}

	/**
	 * Selectionne une bille
	 * 
	 * @param i : la ligne sur laquelle se trouve la bille a selectionner
	 * @param j : la  colonne sur laquelle se trouve la bille a selectionner
	 * 
	 * @return
	 * 		<ul>
	 * 			<li>True si la selection reussit,</li>
	 * 			<li>False sinon.</li>
	 * 		</ul>
	 */
	public boolean selectionner(int i, int j) {
		Bille billeTemp = partie.getPlateau().getBille(i, j); // Recuperation de la bille pointee.
		
		// La selection ne peut se faire que s'il y a une bille aux coordonnees indiquees
		if (billeTemp != null) {
			// Une bille adverse ne peut etre selectionnee
			if (billeTemp.getJoueur().equals(this.partie.getJCourant())) {
				if (isSelectionnee(billeTemp)) { // Si ele est selectionnees ...
					if (selectionnees.size() > 2) { // ... et qu'il y en a 2 autres qui le sont.
						boolean milieu = false;
						int axe = getAxe(this.selectionnees.get(0),this.selectionnees.get(1)); // On recupere l'axe
	
						///// La on determine si la bille est au milieu de la file de selection
						switch (axe) {
						case GD:
							milieu = (!billeTemp.equals(getTete(selectionnees,GAUCHE)) &&
									 !billeTemp.equals(getTete(selectionnees,DROITE)))
							;
							break;
						case HG_BD:
							milieu = (!billeTemp.equals(getTete(selectionnees,HAUT_GAUCHE)) &&
									 !billeTemp.equals(getTete(selectionnees,BAS_DROITE)))
							;
							break;
						
						case HD_BG:
							milieu = (!billeTemp.equals(getTete(selectionnees,HAUT_DROITE))) &&
									 !billeTemp.equals(getTete(selectionnees,BAS_GAUCHE))
							;
						////
							
						default:
							break;
						}
						
						if (!milieu) // Si la Bille n'est pas au milieu et qu'elle est deja selectionnee ..
							selectionnees.remove(billeTemp); // .. alors on la deselectionne
					}
					else // Si moins de 3 Billes sont selectionnes, alors on deselectionne la Bille si elle est deja selectionnee.
						selectionnees.remove(billeTemp);
	
	
				} 
				else { // SI LA BILLE N'EST PAS DEJA SELECTIONNEE
					if (this.selectionnees.size() == 1) { // S'il y a deja une Bille selectionnee
						ArrayList<Bille> voisines = billeAlentours(billeTemp); // On regarde si la Bille qu'on veut selectionner est a cote.
						boolean aCote = false;
						for (int z = 0; z<voisines.size(); z++)
							if (isSelectionnee(voisines.get(z)))
								aCote = true;
						
						if (!aCote) // Si la Bille qu'on veut selectionner n'est pas a cote d'une Bille deja selectionnee ...
							selectionnees = new ArrayList<Bille>(3); // ... alors on deselectionne toutes les Billes avant de selectionner la nouvelle
									
					}
					else if (this.selectionnees.size() >= 2) { // Si deux Billes sont deja selectionnees.
						int axe = getAxe(this.selectionnees.get(0),this.selectionnees.get(1));
						 // ... meme principe, sauf qu'on veut que la Bille qu'on selectionne soit dans le meme axe que celle deja selectionnees
						boolean inAxe = false;
						
						switch (axe) {
						case GD:
							inAxe = (billeTemp.equals(voisine(getTete(selectionnees,GAUCHE),GAUCHE,1)) ||
									 billeTemp.equals(voisine(getTete(selectionnees,DROITE),DROITE,1)))
							;
							break;
						case HG_BD:
							inAxe = (billeTemp.equals(voisine(getTete(selectionnees,HAUT_GAUCHE),HAUT_GAUCHE,1)) ||
									 billeTemp.equals(voisine(getTete(selectionnees,BAS_DROITE),BAS_DROITE,1)))
							;
							break;
						
						case HD_BG:
							inAxe = (billeTemp.equals(voisine(getTete(selectionnees,HAUT_DROITE),HAUT_DROITE,1)) ||
									 billeTemp.equals(voisine(getTete(selectionnees,BAS_GAUCHE),BAS_GAUCHE,1)))
							;
							break;
							
						default:
							break;
						}
						
						if(!inAxe)
							selectionnees = new ArrayList<Bille>(3);
									
					}
					if ( (selectionnees.size() < 3))  // Enfin, on selectionne la Bille choisie (sauf si 3 sont deja selectionnees.
						selectionnees.add(billeTemp);
					
				}
				///// On remet a jour les coups possibles etc ...
				visees.clear();
				coups.clear();
				this.genererCoups();
				///////
			}

		}
		
		PointerInfo pointer = MouseInfo.getPointerInfo();
		Point location = pointer.getLocation();
		location.setLocation(location.getX()-this.fenetrePrincipale.getLocation().getX(),location.getY()-this.fenetrePrincipale.getLocation().getY()); 
		
		return true;
	}

	// Selectionne une Bille a partir de coordonnees
	public void selectionner(Point p) {
		this.selectionner((int) p.getX(), (int) p.getY());
	}
	
	public void setSelectionnees(ArrayList<Bille> selectionnees) {
		this.selectionnees = selectionnees;
	}

	
	// Determine si les coordonnees en entree sont hors-plateau
	public boolean isOut(int i, int j) 
	{
		return  (
				 (i==0) && ( (j > 4)  ) ||
				 (i==1) && ( (j > 5)  ) ||
				 (i==2) && ( (j > 6)  ) ||
				 (i==3) && ( (j > 7)  ) ||
				 (i==4) && ( (j > 8)  ) || 
				 (i > 4) &&  (j < i-4)  ||
				 (j < 0) || (j > 8) || (i < 0) || (i > 8)
				 )
				;
	}
	
	// Retourne une Bille a partir d'un point de coordonnes (utilitaire)
	public Point getBillePointee(Point p) {
		FenetrePlateau fen = this.fenetrePrincipale.getPlateau();

		Point pRetour = new Point();
		double iBille = (p.getY()-this.fenetrePrincipale.getPlateau().getLongueur()/2)
				/fen.getLongueur();
		
		int i = (int) Math.round(iBille);
		//int decalage = (int) (4-iBille)*23;
		double jBille = ((p.getX()-fen.getLongueur()/2)-(4-i)*
				(int) (fen.getWidth()*0.042)-fen.getOffsetL())
				/fen.getLargeur();
		int j = (int) Math.round(jBille);
		
		pRetour.setLocation(i,j);

		return pRetour;
	} 

	// "true" si la Bille est selectionnee.
	public boolean isSelectionnee(Bille b)  {	
		boolean retour = false;
		if (b != null) {
			if (this.selectionnees.size() != 0)
				 for (int i=0; i < this.selectionnees.size(); i++) 
					 if (selectionnees.get(i).equals(b))
						 retour = true;
		}	
				
		return retour;
	}
	
	// "true" si la Bille est visee
	public boolean isVisee(Bille b) {	
		boolean retour = false;
		if (this.visees.size() != 0) {
			for (int i=0; i < this.visees.size(); i++) {	
				for (int j=0; j < this.visees.get(i).size(); j++) {	
					if (visees.get(i).get(j).equals(b)) { 
						retour = true; 
					}
				}
			}
		}		
		return retour;
	}
		
	// Retourne un vecteur contenant les 6 billes au alentours de la Bille passee en entree
	public ArrayList<Bille> billeAlentours(Bille b) 
	{	ArrayList<Bille> vRetour = new ArrayList<Bille>(6);
		
		if (!isOut(b.getLigne()+1,b.getColonne()))
			vRetour.add(partie.getPlateau().getBille(b.getLigne()+1,b.getColonne())); 	// A droite
		if (!isOut(b.getLigne()-1,b.getColonne()))
			vRetour.add(partie.getPlateau().getBille(b.getLigne()-1,b.getColonne())); 	// A gauche
		if (!isOut(b.getLigne(),b.getColonne()+1))
			vRetour.add(partie.getPlateau().getBille(b.getLigne(),b.getColonne()+1));	// En bas a gauche
		if (!isOut(b.getLigne(),b.getColonne()-1))
			vRetour.add(partie.getPlateau().getBille(b.getLigne(),b.getColonne()-1)); 	// En haut a droite
		if (!isOut(b.getLigne()-1,b.getColonne()-1))
			vRetour.add(partie.getPlateau().getBille(b.getLigne()-1,b.getColonne()-1));// En haut a gauche
		if (!isOut(b.getLigne()+1,b.getColonne()+1))
			vRetour.add(partie.getPlateau().getBille(b.getLigne()+1,b.getColonne()+1));// En bas a droite
		
		return vRetour;
	
	}
	
	// Retourne un Vecteur contenant les Billes, parmi celles passees en entree, sont de la meme Couleur
	ArrayList<Bille> billeCouleur(ArrayList<Bille> v, Joueur j) {
		ArrayList<Bille> vRetour = new ArrayList<Bille>(6);
		
		for(int i = 0; i <= v.size(); i++)
			if(v.get(i).getJoueur() == j)
				vRetour.add(v.get(i));		// Si la Bille a la meme Couleur, on l'ajoute au Vecteur de retour.
				
		return vRetour;
	
	}
	
	// Recupere l'axe forme par les deux Billes passees en parametre.
	public int getAxe(Bille b1, Bille b2) {
		int axe = 0;
		if (b1.getLigne() - b2.getLigne() != 0)  // Si Billes sur la meme ligne
			axe+=HD_BG;

		if (b1.getColonne() - b2.getColonne() != 0)  // Si Billes sur la meme colonne.
			axe+=GD;
		
		// J'ai fait en sorte que les deux puissent s'additionner de maniere logique (meme ligne + meme colonne)
		
		return axe;
	}
	
	// Permet de mettre a jour dynamiquement :
	//  - Deplacements possibles
	//  - Billes adverses qui seront poussees suite au deplacement
	// (normalement inutile, voir la version plus bas)
	public ArrayList<Bille> genererCoups(ArrayList<Bille> v) {
		visees.clear();
		int axe = -1;
		if (v.size() > 2) // Si au moins deux billes
			axe = getAxe(v.get(0),v.get(1));
		
		switch (axe) {
		case GD:
			getAdversairesPoussables(v,GAUCHE);
			getAdversairesPoussables(v,DROITE);
			break;
		case HG_BD:
			getAdversairesPoussables(v,HAUT_GAUCHE);
			getAdversairesPoussables(v,BAS_DROITE);
			break;
		
		case HD_BG:
			getAdversairesPoussables(v,HAUT_DROITE);
			getAdversairesPoussables(v,BAS_GAUCHE);
			break;
			
		default:
			break;
		}
		
		return null;
	}

	// Permet de mettre a jour dynamiquement :
	//  - Deplacements possibles
	//  - Billes adverses qui seront poussees suite au deplacement
	public ArrayList<Bille> genererCoups() { // Celui la sera utilise
		visees.clear();
		int axe = -1;
		//Bille billeTemp;
		if (selectionnees.size() >= 2) {// Si au moins deux billes
			axe = getAxe(selectionnees.get(0),selectionnees.get(1));
		
			switch (axe) {
			case GD:
				getAdversairesPoussables(selectionnees,GAUCHE);
				getAdversairesPoussables(selectionnees,DROITE);
				break;
			case HG_BD:
				getAdversairesPoussables(selectionnees,HAUT_GAUCHE);
				getAdversairesPoussables(selectionnees,BAS_DROITE);
				break;
			
			case HD_BG:
				getAdversairesPoussables(selectionnees,HAUT_DROITE);
				getAdversairesPoussables(selectionnees,BAS_GAUCHE);
				break;
				
			default:
				break;
			}
		}
		
		deplacementPossible(selectionnees,GAUCHE);
		deplacementPossible(selectionnees,DROITE);
		deplacementPossible(selectionnees,HAUT_GAUCHE);
		deplacementPossible(selectionnees,BAS_DROITE);
		deplacementPossible(selectionnees,HAUT_DROITE);
		deplacementPossible(selectionnees,BAS_GAUCHE);
		
		return null;
	}
	
	// Recupere les adversaires qui seront pousses si on va dans une direction donnee
	public ArrayList<Bille> getAdversairesPoussables(ArrayList<Bille> v, int dir) { // Ici, on determine quelles rangees sont poussables

		Bille billeTete = getTete(v,dir);
		Bille billeTemp;
		ArrayList<Bille> vTemp = new ArrayList<Bille>(2);
		
		for(int i=1; i <= 3; i++) {
			billeTemp = voisine(billeTete,dir,i); // Bille voisinne d'i crans, suivant la direction
			// Pas encore clairement definie : On verifie si on a une Bille du joueur adverse.
			if (billeTemp != null) {
				if (partie.getPlateau().caseVide(billeTemp.getLigne(),billeTemp.getColonne())) // Si on trouve une case vide, c'est qu'on a deja enregistre toutes les Billes ennemies
					i = 42; // Moyen bourrin de mettre fin a la boucle.
				else if (billeTemp.getJoueur() != billeTete.getJoueur())
					vTemp.add(billeTemp);
				else if (billeTemp.getJoueur().equals(billeTete.getJoueur())) { // Si un trouve une Bille de notre couleur, alors foutu !
					vTemp.clear();
					i = 42;
				}
			}
			else
				i = 42;
		}
		
		if (v.size() <= vTemp.size()) // Si il y a + de Billes ennemies que de Billes alliees, alors foutu aussi !
			vTemp.clear();
		
		visees.add(vTemp); // On ajoute a la liste des billes visees
		
		return vTemp; // Pas encore clairement defini, mais l'idee est de retourner la liste de Billes ennemies qu'on pousserait.
	}
	
	// Retourne la Bille voisine de la Bille passee en parametres

	public Bille voisine(Bille b, int dir, int dist) {
		Bille billeRetour = null;
		double dirTemp = (dir - 11) / 10.0;
		int xAjoute = (int) Math.round(dirTemp);
		double yAjoute = (dirTemp - xAjoute) * 10;
				
		if (!isOut(b.getLigne() + (int) xAjoute*dist, b.getColonne() + (int) yAjoute*dist)) {
			billeRetour = partie.getPlateau().getBille(b.getLigne() + (int) xAjoute*dist, b.getColonne() + (int) yAjoute*dist);
		}

		// GAUCHE : x - 1;
		// Droite : x + 1
		// HAUT_DROITE : y - 1
		return billeRetour;
	}
	

	/*
	public Bille voisine(Bille b, int dir, int dist) {
		switch(dir) {
		
			// GAUCHE
			case GAUCHE :
				if((b.getColonne() > 0) && (this.getPartie().getPlateau().getBille(b.getLigne(), b.getColonne() - 1) != null)) {
					return this.getPartie().getPlateau().getBille(b.getLigne(), b.getColonne() - 1);
				}
<<<<<<< HEAD
				
				/*
				else
					System.out.println();
				
=======
>>>>>>> branch 'refs/heads/master' of https://Latoof@github.com/ggautier/Abalone.git
				
				break;
			
			// DROITE
			case DROITE :
				if((b.getColonne() < 8) && (this.getPartie().getPlateau().getBille(b.getLigne(), b.getColonne() + 1) != null)) {
					return this.getPartie().getPlateau().getBille(b.getLigne(), b.getColonne() + 1);
				}
<<<<<<< HEAD
				
				/*
				else
					System.out.println();
				
=======
>>>>>>> branch 'refs/heads/master' of https://Latoof@github.com/ggautier/Abalone.git
				
				break;
			
			// HAUT GAUCHE
			case HAUT_GAUCHE :
				if((b.getLigne() > 0) && (b.getColonne() > 0) && (this.getPartie().getPlateau().getBille(b.getLigne() - 1, b.getColonne() - 1) != null)) {
					return this.getPartie().getPlateau().getBille(b.getLigne() - 1, b.getColonne() - 1);
				}
<<<<<<< HEAD
				
				/*
				else
					System.out.println();
				
=======
>>>>>>> branch 'refs/heads/master' of https://Latoof@github.com/ggautier/Abalone.git
				
				break;
			
			// HAUT DROITE
			case HAUT_DROITE :
				if((b.getLigne() > 0) && (this.getPartie().getPlateau().getBille(b.getLigne() - 1, b.getColonne()) != null)) {
					return this.getPartie().getPlateau().getBille(b.getLigne() - 1, b.getColonne());
				}
<<<<<<< HEAD
				
				/*
				else
					System.out.println();
				
=======
>>>>>>> branch 'refs/heads/master' of https://Latoof@github.com/ggautier/Abalone.git
				
				break;
				
			// BAS GAUCHE
			case BAS_GAUCHE :
				if((b.getLigne() < 8) && (b.getColonne() > 0) && (this.getPartie().getPlateau().getBille(b.getLigne() + 1, b.getColonne()) != null)) {
					return this.getPartie().getPlateau().getBille(b.getLigne() + 1, b.getColonne());
				}
<<<<<<< HEAD
				
				/*
				else
					System.out.println();
				
=======
>>>>>>> branch 'refs/heads/master' of https://Latoof@github.com/ggautier/Abalone.git
				
				break;
				
			// BAS DROITE
			case BAS_DROITE :
				if((b.getLigne() < 8) && (b.getColonne() < 8) && (this.getPartie().getPlateau().getBille(b.getLigne() + 1, b.getColonne() + 1) != null)) {
					return this.getPartie().getPlateau().getBille(b.getLigne() + 1, b.getColonne() + 1);
				}
<<<<<<< HEAD
				
				/*
				else
					System.out.println();
				
=======
>>>>>>> branch 'refs/heads/master' of https://Latoof@github.com/ggautier/Abalone.git
				
				break;
		}
		
		return null;
	}
	*/
	

	// Retourne les coordonnees voisines de la Bille passee en parametres
	public Point voisineP(Bille b, int dir, int dist) {
		Point retour = new Point(-1,-1);
		
		if (dir >= 0) {
			double dirTemp = (dir - 11) / 10.0;
			int xAjoute = (int) Math.round(dirTemp);
			double yAjoute = (dirTemp - xAjoute) * 10;
			if (!isOut(b.getLigne() + (int) xAjoute*dist, b.getColonne() + (int) yAjoute*dist))
				retour = new Point(b.getLigne() + (int) xAjoute*dist, b.getColonne() + (int) yAjoute*dist);
		}

		// GAUCHE : x - 1;
		// Droite : x + 1
		// HAUT_DROITE : y - 1
		return retour;
	}

	
	// Recupere la Bille en "tete" de file, pour une direction donnee.
	public Bille getTete(ArrayList<Bille> v, int dir) {
		Bille billeTemp = new Bille(-1, -1, null); // Comment declarer une Bille "nulle" ?
		Bille billeTest;

		if (v.size() > 0) {
			billeTemp = v.get(0);
			for (int i = 1; i < v.size(); i++) 
				for (int j = 1; j <= 2; j++) {
					billeTest = voisine(billeTemp,dir,j);
					if (billeTest != null) 
						if (billeTest.equals(v.get(i)))
							billeTemp = billeTest;
					
					}
				
		}
		
		return billeTemp;
	}
	
	// Determine si un deplacement lateral ou en ligne et possible dans une direction donnee.
	public boolean deplacementPossible(ArrayList<Bille> v, int dir)  {
		int axe = -1;
		boolean possible = false;
		int proxyVide = 0;
		Bille b; // DBG
		
		if (!v.isEmpty()) { // Si le vecteur n'est pas vide
			
			if (v.size() > 1) {	// Si plus d'une bille est selectionnee
				
				axe = getAxe(v.get(0),v.get(1));    		// On recupere l'axe forme par les Billes selectionnees
				
				if (axe != dir2axe(dir)) {	// Si deplacement lateral
					
					for(int i = 0; i < v.size(); i++)  		// Pour chaque Bille selectionnee
						if(voisine(v.get(i), dir, 1) == null) // On regarde chaque case a cote, vers la direction en parametre
						{
							switch(dir) {
							
								case GAUCHE :
									if(!isOut(v.get(i).getLigne(), v.get(i).getColonne() - 1))
										proxyVide++;
									
									break;
								
								case DROITE :
									if(!isOut(v.get(i).getLigne(), v.get(i).getColonne() + 1))
										proxyVide++;
									
									break;
								
								case HAUT_GAUCHE :
									if(!isOut(v.get(i).getLigne() - 1, v.get(i).getColonne() - 1))
										proxyVide++;
									
									break;
	
								case HAUT_DROITE :
									if(!isOut(v.get(i).getLigne() - 1, v.get(i).getColonne()))
										proxyVide++;
									
									break;
								
								case BAS_GAUCHE :
									if(!isOut(v.get(0).getLigne() + 1, v.get(0).getColonne()))
										proxyVide++;
									
									break;
									
								case BAS_DROITE :
									if(!isOut(v.get(0).getLigne() + 1, v.get(0).getColonne() + 1))
										proxyVide++;
									
									break;
							}
						}
					if (proxyVide == v.size())	// Toutes les billes selectionnees peuvent etre deplacees
						possible = true;
				}
				
				// Pour les deplacements en ligne, seule la Bille "au bout" nous interesse
				else if ((voisine(getTete(v,dir),dir,1) == null || isVisee(voisine(getTete(v,dir),dir,1))) 
							&& (!isOut((int)voisineP(getTete(v,dir),dir,1).getX(),(int)voisineP(getTete(v,dir),dir,1).getY()))) 
					possible = true;
	
			}
			
			else if(voisine(v.get(0),dir,1) == null) {
				// System.out.println("DBG deplacementPossible(" + v.get(0) + "," + dir + ")");	// DBG
				
				switch(dir) {
					
					case GAUCHE :
						if(!isOut(v.get(0).getLigne(), v.get(0).getColonne() - 1))
							possible = true;
						
						break;
					
					case DROITE :
						if(!isOut(v.get(0).getLigne(), v.get(0).getColonne() + 1))
							possible = true;
						
						break;
						
					case HAUT_GAUCHE :
						if(!isOut(v.get(0).getLigne() - 1, v.get(0).getColonne() - 1))
							possible = true;
						
						break;
					
					case HAUT_DROITE :
						if(!isOut(v.get(0).getLigne() - 1, v.get(0).getColonne()))
							possible = true;
						
						break;
						
					case BAS_GAUCHE :
						if(!isOut(v.get(0).getLigne() + 1, v.get(0).getColonne()))
							possible = true;
						
						break;
						
					case BAS_DROITE :
						if(!isOut(v.get(0).getLigne() + 1, v.get(0).getColonne() + 1))
							possible = true;
						
						break;
				}
			}
		}
		
		if (possible)
			coups.add(dir);
		
		return possible;

	}
	
	// On a 6 directions, et trois axes possibles. Methode utilitaire permettant de convertir direction en axe.
	public int dir2axe(int dir) {
		int axe = -1;
		
		switch(dir) {
		case 10:
		case 12:
			axe = 01;
			break;
		case 01:
		case 21:
			axe = 10;
			break;
		case 00:
		case 22:
			axe = 11;
			break;
		default:
			break;
			
		}
		
		return axe;
	}
	
	
	
	public boolean action(ArrayList<Bille> v, int dir) {
		if (deplacementPossible(v,dir)) {
			for(int i=0; i < visees.size(); i++)
				deplacerBille(visees.get(0).get(i),dir);
			for(int j=0; j < v.size(); j++)
				deplacerBille(v.get(j),dir);
			
			this.nextTurn(false);
		}
		return true;
	}
	
	// Deplacement des Billes selectionnees : Si le deplacement vers la direction "dir" est possible, elle s'effectue.
	public boolean action(int dir) {
		Bille billeTemp;
		boolean expulsee = false;
		boolean deplacement = deplacementPossible(selectionnees,dir);
		if (deplacement) {
			ArrayList<Bille> ennemies = getVisees(dir);
			while(!ennemies.isEmpty())  {
				billeTemp = getTete(ennemies,dir);
				expulsee = deplacerBille(billeTemp,dir);
				ennemies.remove(billeTemp);
			}
			
			while(!selectionnees.isEmpty())  {
				billeTemp = getTete(selectionnees,dir);
				deplacerBille(billeTemp,dir);
				selectionnees.remove(billeTemp);
			}		
			
		}
		this.selectionnees.clear();
		this.visees.clear();
		this.coups.clear();
		this.deplacementVise = -1;
		
		if (this.getFenetrePrincipale() != null) {
			this.getFenetrePrincipale().rafraichir();
	        
	        if (expulsee) {
	        	if (this.getPartie().getJ1().getScore() > 5)
	        		new FenetreOver("Victoire de "+this.getPartie().getJ1().getNom(),this.getFenetrePrincipale());
	        	else if (this.getPartie().getJ2().getScore() > 5)
	        		new FenetreOver("Victoire de "+this.getPartie().getJ2().getNom(),this.getFenetrePrincipale());
	
	        }
		}
        
        if (deplacement) {
        	System.out.println("DBG action : Changement de joueur " + (this.getFenetrePrincipale() != null ? "REEL" : "VIRTUEL"));
        	this.nextTurn(false);
        }
        	
		
		return expulsee;
	}
	
	public ArrayList<Bille> getVisees(int dir) {
		ArrayList<Bille> retour = new ArrayList<Bille>(2);
		for (int i=0; i < this.visees.size(); i++)
			for (int j=0; j < this.visees.get(i).size(); j++)
				if (visees.get(i).get(j).equals(voisine(getTete(selectionnees,dir),dir,1)))
					retour = visees.get(i);
			
		return retour;
	}
	
	// Methode utilisee pour deplacer la Bille. Ne pas appeller directement (passer par "action")
	public boolean deplacerBille(Bille b, int dir) {
		Bille billeTemp = b;
		boolean out = false;
		Point pOld = new Point(b.getLigne(),b.getColonne()); // Coordonnees de la Bille avant deplacement
		Point pTemp = voisineP(b, dir, 1); // Coordonnees apres deplacement

		if (billeTemp != null) {	// Si la case n'est pas vide
			if (!isOut((int) pTemp.getX(), (int) pTemp.getY())) {
				partie.getPlateau().setBille((int) pTemp.getX(), (int) pTemp.getY(), billeTemp);
				partie.getPlateau().setBille((int) pOld.getX() , (int) pOld.getY() , null);

			}
			else { // Si la Bille en question est sortie ...
				out = true;
				if (!b.getJoueur().equals(partie.getJCourant()))
					partie.getJCourant().setScore(partie.getJCourant().getScore()+1); // Le joueur courant augmente son score
				else
					partie.getJCourant().setScore(partie.getJCourant().getScore()-1); // Normalement inutile (penalite de suicide)
				
			}
	
		}
		
		return out;
	}
	
	// Retourne la Bille pointee par la souris.
	public Bille getPointee() {
		if (this.pointee == null) 
			this.pointee = new Bille(-1,-1,null);
		
		return pointee;
	}

	// Met a jour la Bille pointee par la souris
	public void setPointee(Bille pointee) {
		this.pointee = null;
		
		if(pointee != null && selectionnees.size() < 3) {
			if(pointee.getJoueur().equals(partie.getJCourant()))
				this.pointee = pointee;

		}
	}

	public void setPointee(Point p) {
		this.setPointee(this.partie.getPlateau().getBille((int)p.getX(), (int)p.getY()));
	}
	
	// Determine si un deplacement est pointe par la souris.
	public boolean isDeplacementVise(Point p) {
		boolean retour = false;

			//int deplacement = this.nextCoup(p);

		
		for (int i=0; i < selectionnees.size(); i++)
			if (voisineP(selectionnees.get(i),this.deplacementVise,1).equals(p))
					retour = true;
		
					
		return retour;
	}

	public int getDeplacementVise() {
		return deplacementVise;
	}

	public void setDeplacementVise(int deplacementVise) {
		this.deplacementVise = deplacementVise;
	}

	public FenetrePrincipale getFenetrePrincipale() {
		return fenetrePrincipale;
	}

	public void setFenetrePrincipale(FenetrePrincipale fenetrePrincipale) {
		this.fenetrePrincipale = fenetrePrincipale;
	}
	
	public ArrayList<Bille> getBillesJoueur(Joueur joueur) {
		
		ArrayList<Bille> billesJoueur = new ArrayList<Bille>();
		Plateau plateau = this.getPartie().getPlateau();
		
		for(int ligne = 0 ; ligne < plateau.getBilles().length ; ligne++) {
			for(int colonne = 0 ; colonne < plateau.getBilles()[ligne].length ; colonne++) {
				if(plateau.getBille(ligne,colonne) != null)
					if(plateau.getBille(ligne,colonne).getJoueur().equals(joueur))
						billesJoueur.add(plateau.getBilles()[ligne][colonne]);
			}
		}
		
		return billesJoueur;
	}
	
	/**
	 * Retourne les coups possibles avec un ensemble de billes
	 * 
	 * @param billesJoueur
	 * @return
	 */
	public ArrayList<Coup> getCoupsPossibles(ArrayList<Bille> billesJoueur) {
		
		ArrayList<Coup> coups = new ArrayList<Coup>();
		ArrayList<Bille> billesTestees = new ArrayList<Bille>(3);
		Bille bTemp, bTemp2;
		int dirTestee = -1;

		
		
		// Pour une bille
		
		for (int i = 0 ; i < billesJoueur.size() ; i++) {
			billesTestees.add(billesJoueur.get(i));
			
			for (int t = 0 ; t < 6 ; t++) {
				
				dirTestee = tabDir[t];
				
				if (deplacementPossible(billesTestees,dirTestee))
					coups.add(new Coup((ArrayList<Bille>) billesTestees.clone(),dirTestee, billesTestees.get(0).getJoueur()));
			}
			
			billesTestees.clear();
		}
		
		// Pour deux billes
		
		for (int i=0; i<billesJoueur.size(); i++)
		{
			billesTestees.add(billesJoueur.get(i));
			
			for (int v=0; v<6; v++)
			{
				bTemp = voisine(billesTestees.get(0),tabDir[v],1);
				if (bTemp != null)
				{
					if (bTemp.getJoueur().equals(billesTestees.get(0).getJoueur()))
					{
						billesTestees.add(bTemp);
						
						int axe = getAxe(billesTestees.get(0),billesTestees.get(1));
						
						switch (axe) {
							case GD:
								/*
								if (deplacementPossible(billesTestees,GAUCHE))
									coups.add(new Coup((ArrayList<Bille>) billesTestees.clone(),GAUCHE, billesTestees.get(0).getJoueur()));
								*/
								if (deplacementPossible(billesTestees,DROITE))
									coups.add(new Coup((ArrayList<Bille>) billesTestees.clone(),DROITE, billesTestees.get(0).getJoueur()));
										
								break;
							case HG_BD:
								/*
								if (deplacementPossible(billesTestees,HAUT_GAUCHE))
									coups.add(new Coup((ArrayList<Bille>) billesTestees.clone(),HAUT_GAUCHE, billesTestees.get(0).getJoueur()));
								*/
								if (deplacementPossible(billesTestees,BAS_DROITE))
									coups.add(new Coup((ArrayList<Bille>) billesTestees.clone(),BAS_DROITE, billesTestees.get(0).getJoueur()));	
								break;
							
							case HD_BG:
								if (deplacementPossible(billesTestees,HAUT_DROITE))
									coups.add(new Coup((ArrayList<Bille>) billesTestees.clone(),HAUT_DROITE, billesTestees.get(0).getJoueur()));
								/*
								if (deplacementPossible(billesTestees,BAS_GAUCHE))
									coups.add(new Coup((ArrayList<Bille>) billesTestees.clone(),BAS_GAUCHE, billesTestees.get(0).getJoueur()));	
								*/
								break;
								
							default:
								break;
						}
						
						billesTestees.remove(bTemp);
					}
				}
			}
			
			billesTestees.clear();
		}
		
		// Pour trois billesArrayList
		
		for (int i=0; i<billesJoueur.size(); i++) {
			
			billesTestees.add(billesJoueur.get(i));
			
			for (int v=0; v<6; v++) {
				bTemp = voisine(billesTestees.get(0),tabDir[v],1);
				bTemp2 = voisine(billesTestees.get(0),tabDir[v],2);
				if (bTemp != null && bTemp2 != null)
					if (bTemp.getJoueur().equals(billesTestees.get(0).getJoueur())
						&& bTemp2.getJoueur().equals(billesTestees.get(0).getJoueur())) {
						
						billesTestees.add(bTemp);
						billesTestees.add(bTemp2);
						int axe = getAxe(billesTestees.get(0),billesTestees.get(1));
						
						switch (axe) {
							case GD:
								if (deplacementPossible(billesTestees,GAUCHE))
									coups.add(new Coup((ArrayList<Bille>) billesTestees.clone(),GAUCHE, billesTestees.get(0).getJoueur()));
								if (deplacementPossible(billesTestees,DROITE))
									coups.add(new Coup((ArrayList<Bille>) billesTestees.clone(),DROITE, billesTestees.get(0).getJoueur()));	
								break;
							case HG_BD:
								if (deplacementPossible(billesTestees,HAUT_GAUCHE))
									coups.add(new Coup((ArrayList<Bille>) billesTestees.clone(),HAUT_GAUCHE, billesTestees.get(0).getJoueur()));
								if (deplacementPossible(billesTestees,BAS_DROITE))
									coups.add(new Coup((ArrayList<Bille>) billesTestees.clone(),BAS_DROITE, billesTestees.get(0).getJoueur()));	
								break;
							
							case HD_BG:
								if (deplacementPossible(billesTestees,HAUT_DROITE))
									coups.add(new Coup((ArrayList<Bille>) billesTestees.clone(),HAUT_DROITE, billesTestees.get(0).getJoueur()));
								if (deplacementPossible(billesTestees,BAS_GAUCHE))
									coups.add(new Coup((ArrayList<Bille>) billesTestees.clone(),BAS_GAUCHE, billesTestees.get(0).getJoueur()));	
								break;
								
							default:
								break;
						}
						
						billesTestees.remove(bTemp);
						billesTestees.remove(bTemp2);
					}
			}
			
			billesTestees.clear();
		}
		
		return coups;
	}
	
	// Change de joueur
	public void nextTurn(boolean virtual) {

		
		if (this.partie.getJCourant().equals(partie.getJ1()))
			this.getPartie().setJCourant(partie.getJ2());
		else 
			this.getPartie().setJCourant(partie.getJ1());
		
		this.getPartie().quickSave();
		
		if ((this.getFenetrePrincipale() != null) && (!this.getPartie().getJCourant().isHumain())) {
			this.controleurIA.jouer();
		}
		
		if(this.getFenetrePrincipale() != null) 
			this.getFenetrePrincipale().rafraichir();

	}
	
	/*
	Si une Bille selectionnee : Rien a verifier.
	Si deux ou trois Billes selectionnees : 
	1. On identifie l'axe forme par ces Billes.
	2. a) A partir d'une des Billes a l'extremite, on regarde combien il y a de Billes ennemies, et si elles sont suivies d'un vide
	   b) Pareil avec l'autre extremite
    */
	 
}
