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
				 ( i==0 || i == 8) && ( (j < 3)  ) ||
				 ( i==1 || i == 7) && ( (j < 2)  ) ||
				 ( i==2 || i == 6) && ( (j < 1)  ) ||
				 ( i==3 || i == 5) && ( (j < 0)  ) ||
				 ( i==4  && j < 3				 ) || 
				 (j > 8) 
				 )
				;
	} // (Au passage, desole de n'avoir rien fait aujourd'hui. J'ai reinstalle tout le systeme, et fait quelques exo (rare)
	  // Je considere le projet comme officiellement entame, donc je m'y mets demain  
}
