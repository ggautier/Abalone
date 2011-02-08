import java.util.Vector;

/**
 * <b>Controleur est la classe qui va mettre � jour les informations du mod�le, en respectant les r�gles du jeu.</b>
 * <p>
 * Un Controleur est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Une partie, qui va servir de point d'acc�s aux informations du mod�le.</li>
 * <li>Une fen�trePrincipale, qui va solliciter des changements.</li>
 * <li>Un controleurIA, qui peut g�n�rer le meilleur coup possible � l'instant.</li>
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
	protected Vector<Bille>		selectionnees;
	protected Vector<Vector<Bille>> visees;
	
	// Dir
	public final static int GAUCHE = 10;
	public final static int DROITE = 12;
	public final static int BAS_GAUCHE = 01;
	public final static int HAUT_DROITE = 21;
	public final static int HAUT_GAUCHE = 00;
	public final static int BAS_DROITE = 22;
	
	// Axe
	public final static int GD = 01;
	public final static int HG_BD = 11;
	public final static int HD_BG = 10;
	
	public Controleur()
	{
		this.partie = new Partie();
		this.selectionnees = new Vector<Bille>(3);
		this.visees = new Vector<Vector<Bille>>(2);
	}
	
	//test les coord d'une case, est renvoie  true si s'est une case de sortie.
	// Version de bourrin

	/*boolean isOut(int i, int j)
		{
		boolean good = false;
		if(i==0 || i==10)
		{ if(j==4 || j==6 || j==8 || j==10 || j==12 || j==14) {good=true;} }
		else 
		if(i==1 || i==9)
		{ if(j==3 || j==15) { good=true; } }
		else
		if(i==2 || i==8)
		{ if(j==2 || j==16) { good=true; } }
		else
		if(i==3 || i==7)
		{ if(j==1 || j==17) { good=true; } }
		else
		if(i==4 || i==6)
		{ if(j==0 || j==18) { good=true; } }
		 return good; 
	}
	*/

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public Vector<Bille> getSelectionnees() {
		return selectionnees;
	}

	public boolean selectionner(int i, int j) {
		if (partie.getPlateau().getBille(i, j) != null) {
			if (isSelectionnee(partie.getPlateau().getBille(i, j)))
				selectionnees.remove(partie.getPlateau().getBille(i,j));
			else
				selectionnees.add(partie.getPlateau().getBille(i,j));
			
		}
		else
			System.out.println("Pas de bille ici");
		
		return false;
	}
	
	
	public void setSelectionnees(Vector<Bille> selectionnees) {
		this.selectionnees = selectionnees;
	}

	// Version de programmeur (mais pas forcement plus intelligente, hein)
	public boolean isOut(int i, int j) {
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
	
	// "true" si la Bille est selectionnee.
	public boolean isSelectionnee(Bille b) {
		boolean retour = false;
		if (this.selectionnees.size() != 0)
			for (int i=0; i < this.selectionnees.size(); i++)
				if (selectionnees.get(i).equals(b))
					retour = true;
				
		return retour;
	}
	
	// "true" si la Bille est visee
	public boolean isVisee(Bille b) {
		boolean retour = false;
		if (this.visees.size() != 0)
			for (int i=0; i < this.visees.size(); i++)
				for (int j=0; j < this.visees.get(i).size(); i++)
					if (visees.get(i).get(j).equals(b))
						retour = true;
				
		return retour;
	}
		
	// Retourne un vecteur contenant les 6 billes au alentours de la Bille passee en entree
	public Vector<Bille> billeAlentours(Bille b) {
		Vector<Bille> vRetour = new Vector<Bille>(6);
		
		vRetour.addElement(partie.getPlateau().getBille(b.getX()+1,b.getY())); 	// A droite
		vRetour.addElement(partie.getPlateau().getBille(b.getX()-1,b.getY())); 	// A gauche
		vRetour.addElement(partie.getPlateau().getBille(b.getX(),b.getY()+1));	// En bas a gauche
		vRetour.addElement(partie.getPlateau().getBille(b.getX(),b.getY()-1)); 	// En haut a droite
		vRetour.addElement(partie.getPlateau().getBille(b.getX()-1,b.getY()-1));// En haut a gauche
		vRetour.addElement(partie.getPlateau().getBille(b.getX()+1,b.getY()+1));// En bas a droite

		
		return vRetour;
	
	}
	
	// Retourne un Vecteur contenant les Billes, parmi celles passees en entree, sont de la meme Couleur
	Vector<Bille> billeCouleur(Vector<Bille> v, Joueur j) {
		Vector<Bille> vRetour = new Vector<Bille>(6);
		
		for(int i = 0; i <= v.size(); i++)
			if(v.get(i).getJoueur() == j)
				vRetour.add(v.get(i));		// Si la Bille a la meme Couleur, on l'ajoute au Vecteur de retour.
				
		return vRetour;
	
	}
	
	public int getAxe(Bille b1, Bille b2) {
		int axe = 0;
		if (b1.getX() - b2.getX() != 0)  // Si Billes sur la meme ligne
			axe+=HD_BG;

		if (b1.getY() - b2.getY() != 0)  // Si Billes sur la meme colonne.
			axe+=GD;
		
		// J'ai fait en sorte que les deux puissent s'additionner de maniere logique (meme ligne + meme colonne)
		
		return axe;
	}
	
	// En gros chantier /!\
	public Vector<Bille> genererCoups(Vector<Bille> v) {
		visees.clear();
		int axe = 0;
		Bille billeTemp;
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

	public Vector<Bille> genererCoups() { // Celui la sera utilise
		visees.clear();
		int axe = 0;
		Bille billeTemp;
		if (selectionnees.size() >= 2) // Si au moins deux billes
			axe = getAxe(selectionnees.get(0),selectionnees.get(1));
		
		System.out.println("Axe : "+axe);
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
		
		return null;
	}
	
	public Vector<Bille> getAdversairesPoussables(Vector<Bille> v, int dir) { // Ici, on determine quelles rangees sont poussables
		int taille_rangee = v.size();
		Boolean vide = false;
		Bille billeTete = getTete(v,dir);
		Bille billeTemp;
		Vector<Bille> vTemp = new Vector<Bille>(2);
				
		for(int i=1; i <= 3; i++) {
			System.out.println("Tour "+i+" en recherche de cibles vers : "+dir+" pour la Bille en ("+billeTete.getX()+","+billeTete.getY()+")");
			billeTemp = voisine(billeTete,dir,i); // Bille voisinne d'i crans, suivant la direction
			// Pas encore clairement definie : On verifie si on a une Bille du joueur adverse.
			if (billeTemp != null) {
				if (!partie.getPlateau().caseVide(billeTemp.getX(),billeTemp.getY())) // Si on trouve une case vide, c'est qu'on a deja enregistre toutes les Billes ennemies
					i = 42; // Moyen bourrin de mettre fin a la boucle.
				else if (billeTemp.getJoueur() != billeTete.getJoueur())
					vTemp.add(billeTemp);
				else if (billeTemp.getJoueur() == billeTete.getJoueur()) // Si un trouve une Bille de notre couleur, alors foutu !
					vTemp.clear();
			}
			else
				i = 42;
		}
		
		if (v.size() < vTemp.size()) // Si il y a + de Billes ennemies que de Billes alliees, alors foutu aussi !
			vTemp.clear();
		
		visees.add(vTemp); // On ajoute a la liste des billes visees
		System.out.println("ajout de "+vTemp.size()+" Billes en cibles");
		
		return vTemp; // Pas encore clairement defini, mais l'idee est de retourner la liste de Billes ennemies qu'on pousserait.
		
	}
	
	
	public Bille voisine(Bille b, int dir, int dist) {
		int dirTemp = (dir - 11) / 10;
		int xAjoute = Math.round(dirTemp);
		int yAjoute = (dirTemp - xAjoute) * 10;
		
		Bille billeRetour = partie.getPlateau().getBille(b.getX() + xAjoute*dist, b.getY() + yAjoute*dist);

		// GAUCHE : x - 1;
		// Droite : x + 1
		// HAUT_DROITE : y - 1
		return billeRetour;
	}

	/* Memo :
		public final static int GAUCHE = 10;
		public final static int DROITE = 12;
		public final static int BAS_GAUCHE = 01;
		public final static int HAUT_DROITE = 21;
		public final static int HAUT_GAUCHE = 00;
		public final static int BAS_DROITE = 22;
	*/
	
	public Bille getTete(Vector<Bille> v, int dir) {
		Bille billeTemp = new Bille(-1, -1, null); // Comment declarer une Bille "nulle" ?
		Bille billeTest;
		System.out.print("cherche Tete en "+dir+" : ");

		if (v.size() > 0) {
			billeTemp = v.get(0);
			for (int i = 0; i < v.size(); i++) 
				for (int j = 1; j <= 2; j++) {
					billeTest = voisine(billeTemp,dir,j);
					if (billeTest != null) 
						if (billeTest.equals(v.get(i)))
							billeTemp = billeTest;
					
					}
				
			}
		
		return billeTemp;
	}
	                                               

	// (Au passage, desole de n'avoir rien fait aujourd'hui. J'ai reinstalle tout le systeme, et fait quelques exo (rare)
	  // Je considere le projet comme officiellement entame, donc je m'y mets demain  
	
	/*
	Si une Bille selectionnee : Que dalle a verifier.
	Si deux ou trois Billes selectionnees : 
	1. On identifie l'axe forme par ces Billes.
	2. a) A partir d'une des Billes a l'extremite, on regarde combien il y a de Billes ennemies, et si elles sont suivies d'un vide
	   b) Pareil avec l'autre extremite
    */
	 
}
