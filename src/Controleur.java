import java.util.Vector;

/**
 * <b>Controleur est la classe qui va mettre à jour les informations du modèle, en respectant les règles du jeu.</b>
 * <p>
 * Un Controleur est caractérisé par les informations suivantes :
 * <ul>
 * <li>Une partie, qui va servir de point d'accès aux informations du modèle.</li>
 * <li>Une fenêtrePrincipale, qui va solliciter des changements.</li>
 * <li>Un controleurIA, qui peut générer le meilleur coup possible à l'instant.</li>
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
	
	public static int GAUCHE = 01;
	public static int DROITE = 21;
	public static int BAS_GAUCHE = 12;
	public static int HAUT_DROITE = 10;
	public static int HAUT_GAUCHE = 00;
	public static int BAS_DROITE = 22;
	
	public static int GD = 10;
	public static int HG_BD = 11;
	public static int HD_BG = 01;
	
	public Controleur()
	{

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

	// Version de programmeur (mais pas forcement plus intelligente, hein)
	public boolean isOut(int i, int j) {
		return  (
				 ( i==0 || i == 8) && ( (j > 4)  ) ||
				 ( i==1 || i == 7) && ( (j > 5)  ) ||
				 ( i==2 || i == 6) && ( (j > 6)  ) ||
				 ( i==3 || i == 5) && ( (j > 7)  ) ||
				 ( i==4  && j < 3				 ) || 
				 (j < 0) || (j > 8) || (i < 0) || (i > 8)
				 )
				;
	}
		
	// Retourne un vecteur contenant les 6 billes au alentours de la Bille passee en entree
	public Vector<Bille> billeAlentours(Bille b) {
		Vector<Bille> vRetour = new Vector<Bille>(6);
		Bille bTemp;
		
		vRetour.addElement(plateau.getBille(b.getX()+1,b.getY())); 	// A droite
		vRetour.addElement(plateau.getBille(b.getX()-1,b.getY())); 	// A gauche
		vRetour.addElement(plateau.getBille(b.getX(),b.getY()+1));	// En bas a gauche
		vRetour.addElement(plateau.getBille(b.getX(),b.getY()-1)); 	// En haut a droite
		vRetour.addElement(plateau.getBille(b.getX()-1,b.getY()-1));// En haut a gauche
		vRetour.addElement(plateau.getBille(b.getX()+1,b.getY()+1));// En bas a droite

		
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
		if (b1.getX() - b2.getX() != 0) 
			axe+=GD;

		if (b1.getY() - b2.getY() != 0) 
			axe+=HD_BG;
		
		return axe;
	}
	
	// En gros chantier
	public Vector<Bille> trucBidule(Vector<Bille> v) {
		int axe = 0;
		Bille billeTemp;
		if (v.size() > 2) // Si au moins deux billes
			axe = getAxe(v.get(0),v.get(1));
		
		switch (axe) {
		case GD:
			getNbAdversaires(v,GAUCHE);
			getNbAdversaires(v,DROITE);
			break;
		case HG_BD:
			getNbAdversaires(v,HAUT_GAUCHE);
			getNbAdversaires(v,BAS_DROITE);
			break;
		
		case HD_BG:
			getNbAdversaires(v,HAUT_DROITE);
			getNbAdversaires(v,BAS_GAUCHE);
			break;
			
		default:
			break;
		}
		
		
	}
	
	public Vector<Bille> getNbAdversaires(Vector<Bille> v, int dir) {
		
		
		
		
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
