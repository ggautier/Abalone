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

	private ControleurIA		controleurIA;
	private FenetrePrincipale	fenetrePrincipale;
	private Partie				partie;
	
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
	boolean isOut(int i, int j) {
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
	Vector billeAlentours(Bille b) {
		Vector vRetour = new Vector(6);
		Bille bTemp;
		
		vRetour.addElement(plateau.getBille(b.getX()+1,b.getY())); 	// A droite
		vRetour.addElement(plateau.getBille(b.getX()-1,b.getY())); 	// A gauche
		vRetour.addElement(plateau.getBille(b.getX(),b.getY()+1));	// En bas a gauche
		vRetour.addElement(plateau.getBille(b.getX(),b.getY()-1)); 	// En haut a droite
		vRetour.addElement(plateau.getBille(b.getX()-1,b.getY()-1));// En haut a gauche
		vRetour.addElement(plateau.getBille(b.getX()+1,b.getY()+1));// En haut a droite

		
		return vRetour;
	
	}
	
	// Retourne un Vecteur contenant les Billes, parmi celles passees en entree, sont de la meme Couleur
	Vector billeCouleur(Vector v, Couleur c) {
		Vector vRetour = new Vector(6);
		
		for(int i = 0; i <= v.size(); i++)
			if(v.get(i).getCouleur() == c)
				vRetour.add(v.get(i));		// Si la Bille a la meme Couleur, on l'ajoute au Vecteur de retour.
				
		return vRetour;
	}
	
	// En gros chantier
	Vector trucBidule(Vector v) {
		int axe = 0;
		Bille billeTemp;
		if (v.size() > 2) // Si au moins deux billes
			getAxe(v.get(0),v.get(1));
		
		switch (axe) {
		case 1:
			getNbAdversaires(plusLoin(v, 11), 00);
			getNbAdversaires(plusLoin(v, 11), 02);

		break;
			
		default:
			break;
		}
	}
	// (Au passage, desole de n'avoir rien fait aujourd'hui. J'ai reinstalle tout le systeme, et fait quelques exo (rare)
	  // Je considere le projet comme officiellement entame, donc je m'y mets demain  
}
