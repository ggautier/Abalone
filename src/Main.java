import java.awt.Dimension;

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
		Controleur controleur = new Controleur();
		Joueur j1 = new Joueur("J1", false, true);
		Joueur j2 = new Joueur("J2", true, true);
		Plateau plat = new Plateau("./data/plateau/defaut.pl", j1, j2);
		Partie part = new Partie();

		controleur.setPartie(part);
		controleur.getPartie().setPlateau(plat);
		controleur.getPartie().getPlateau().partie = controleur.getPartie();
		controleur.getPartie().setControleur(controleur);
		controleur.selectionner(2,4);
		controleur.selectionner(2,3);
		//System.out.println(controleur.isSelectionnee(controleur.getPartie().getPlateau().getBille(5, 5)));
		
		controleur.getPartie().getPlateau().afficher();
		
		FenetrePrincipale f = new FenetrePrincipale("Abalone - 1.00");
        f.setSize(new Dimension(950,725));
        f.setVisible(true);
	}
	
}
