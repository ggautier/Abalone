
import java.awt.Dimension;
import java.util.Vector;

import vue.FenetrePrincipale;

import modele.Bille;

public class Main {


	/**
	 * @param args
	 * @throws Exception 
	 */
	/*
>>>>>>> branch 'refs/heads/master' of https://QuentinGautier@github.com/ggautier/Abalone.git
	public static void main(String[] args) {
<<<<<<< HEAD
=======
		
		Joueur joueur1 = new Joueur("Jean", false, true);
		Joueur joueur2 = new Joueur("Jacques", true, true);
		Plateau plateau = new Plateau("data/plateau/defaut.pl", joueur1, joueur2);
		
		FenetrePrincipale f = new FenetrePrincipale("Abalone - 1.00");
        f.setSize(new Dimension(950,725));
        f.setVisible(true);
	} 
	
	*/
	
	// Maxime : Main secondaire, que j'utilise pour faire des tests. Ne pas supprimer (sorry si j'ai oublie de commenter)
	
	public static void main(String[] args) throws Exception {
		/*
		Controleur controleur = new Controleur();
		Joueur j1 = new Joueur("J1", false, true);
		Joueur j2 = new Joueur("J2", true, true);
		Plateau plat = new Plateau("./data/plateau/defaut.pl", j1, j2);
		Partie part = new Partie();

		controleur.setPartie(part);
		controleur.getPartie().setPlateau(plat);
		controleur.getPartie().getPlateau().partie = controleur.getPartie();
		controleur.getPartie().setControleur(controleur);
		//controleur.selectionner(7,4);
		controleur.selectionner(6,5);
		controleur.selectionner(6,4);

		
		//System.out.println(controleur.isSelectionnee(controleur.getPartie().getPlateau().getBille(5, 5)));
		controleur.genererCoups();
		
		controleur.getPartie().getPlateau().afficher();
		*/
		
		
		
		FenetrePrincipale f = new FenetrePrincipale("Abalone - 1.00");
        f.setSize(new Dimension(950,725));
        
        f.setVisible(true);
        
        double note = 
        	15.0*2+9.65*2+7.43*2+11.15*2+8.75*2+12.58
        	;
        note/=11;
        System.out.println(note);
        //v.add(f.getControleur().getPartie().getPlateau().getBille(2, 4));


        /*
        public final static int GAUCHE = 10;
    	public final static int DROITE = 12;
    	public final static int BAS_GAUCHE = 01;
    	public final static int HAUT_DROITE = 21;
    	public final static int HAUT_GAUCHE = 00;
    	public final static int BAS_DROITE = 22;
		*/
	}
}
