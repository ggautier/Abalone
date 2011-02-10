import java.awt.Dimension;
import java.util.Vector;

public class Main {

	/**
	 * @param args
	 */
	/*
	public static void main(String[] args) {
		
		Joueur joueur1 = new Joueur("Jean", false, true);
		Joueur joueur2 = new Joueur("Jacques", true, true);
		Plateau plateau = new Plateau("data/plateau/defaut.pl", joueur1, joueur2);
		
		FenetrePrincipale f = new FenetrePrincipale("Abalone - 1.00");
        f.setSize(new Dimension(950,725));
        f.setVisible(true);
	} 
	
	*/
	
	// Maxime : Main secondaire, que j'utilise pour faire des tests. Ne pas supprimer (sorry si j'ai oublie de commenter)
	
	public static void main(String[] args) {
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
        //f.getControleur().selectionner(0, 3);
        f.getControleur().selectionner(6, 6);
        f.getControleur().selectionner(6, 5);
        f.getControleur().selectionner(6, 4);
        f.getControleur().action(12);
        
        f.setVisible(true);
        
        Vector<Bille> v = new Vector<Bille>(6);
        System.out.println();
        System.out.println();
        v.add(f.getControleur().getPartie().getPlateau().getBille(2, 2));        
        v.add(f.getControleur().getPartie().getPlateau().getBille(2, 3));
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
