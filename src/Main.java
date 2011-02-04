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

}
