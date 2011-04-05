package controleur;

import java.io.IOException;

import vue.FenetreOver;

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
			
			if (this.controleur.getPartie().getOnlineMode() == 1)
				try {
					this.controleur.getConnexion().envoyer_keepalive();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			if (controleur.partie.getJCourant().getTempsRestantCoup().timeout() 
				|| controleur.partie.getJCourant().getTempsRestantGlobal().timeout()) {
				
				controleur.getPartie().getJCourant().getTempsRestantCoup().stopper();
				controleur.getPartie().getJCourant().getTempsRestantCoup().reset(0, 0);
				controleur.getPartie().getJCourant().getTempsRestantGlobal().stopper();
				controleur.getPartie().getJCourant().getTempsRestantGlobal().reset(0, 0);
				
				new FenetreOver("Temps ecoule : Victoire de "+controleur.getPartie().getAdversaire(controleur.getPartie().getJCourant()).getNom()
						, controleur.getFenetrePrincipale());
			}
			
		}
		
		
	}
}
