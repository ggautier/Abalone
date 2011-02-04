import java.awt.Dimension;

public class Main {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		Joueur joueur1 = new Joueur("Jean", false, true);
		Joueur joueur2 = new Joueur("Jacques", true, true);
		Plateau plateau = new Plateau("data/plateau/defaut.pl", joueur1, joueur2);
		
		FenetrePrincipale f = new FenetrePrincipale("Abalone - 1.00");
        f.setSize(new Dimension(950,725));
        f.setVisible(true);
	} 
	
	
	
	// Maxime : Main secondaire, que j'utilise pour faire des tests. Ne pas supprimer (sorry si j'ai oublie de commenter)
	/*	
	public static void main(String[] args) {
		Controleur controleur = new Controleur();
		controleur.setPartie(new Partie());
		controleur.getPartie().setPlateau(new Plateau());
		
		System.out.println(controleur.getPartie().getPlateau().toString());
	}
	*/
}
