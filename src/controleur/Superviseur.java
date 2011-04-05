package controleur;

// Le superviseur est une partie de notre controleur : Il s'agit d'un Thread charge de surveiller
//   si tout se passe bien dans le programme, et de mettre a jour regulierement la fenetre.
//     c'est egalement lui qui est charge d'envoyer les paquets "keepalive", dans le mode reseau.
public class Superviseur extends Thread {

	protected Controleur controleur;
	
	protected boolean en_cours;
	
	public Superviseur(Controleur controleur) {
		this.controleur = controleur;
		en_cours = true;
		this.start();
	}
	
	public void run() {
		
		
		while (en_cours) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.controleur.getFenetrePrincipale().rafraichir();
			System.out.println("MAJ : "+controleur.getPartie().getJ1().getTempsRestantGlobal());
			
			
		}
		
		
	}
}
