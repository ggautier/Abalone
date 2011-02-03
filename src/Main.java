import java.awt.Dimension;

public class Main {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
