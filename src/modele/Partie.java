package modele;

import controleur.Controleur;

/**
 * <b>Partie est la classe regroupant toutes les information du modele sur la partie en cours.</b>
 * <p>
 * Une partie est caracterisee par les informations suivantes :
 * <ul>
 * <li>Deux joueurs, dont un peut etre l'ordinateur lui-meme.</li>
 * <li>Un plateau, representant le plateau de jeu, et ses cases.</li>
 * <li>Des coups, qui representent la sequence de coup joues depuis le debut de la partie.</li>
 * </ul>
 * </p>
 * 
 * @see Joueur
 * @see Plateau
 * @see Coup
 * 
 * @author Lenogue Matthieu
 * @author Gautier Quentin
 * @author Gautier Gaetan
 * @author Ouary Maxime
 * 
 * @version 1.0
 */
public class Partie {
	
	protected Plateau plateau;
	protected Controleur controleur;
	protected Joueur jCourant;
	
	protected Joueur j1, j2;

	public Partie(Joueur j1, Joueur j2, Plateau plateau) {
		this.j1 = j1;
		this.j2 = j2;
		
		if(this.getJ1().getCamps() == false)
			this.jCourant = this.getJ1();
		else
			this.jCourant = this.getJ2();
		
		this.plateau = plateau;
	}
	
	public Joueur getJ1() {
		return j1;
	}

	// Change de joueur
	public void nextTurn() {
		if(this.jCourant.equals(j1))
			this.jCourant = this.j2;
		else 
			this.jCourant = this.j1;
		
		this.getControleur().getFenetrePrincipale().getInfo();
	}
	
	public Joueur getJCourant() {
		return this.jCourant;
	}

	public void setJ1(Joueur j1) {
		this.j1 = j1;
	}

	public Joueur getJ2() {
		return j2;
	}

	public void setJ2(Joueur j2) {
		this.j2 = j2;
	}

	public Partie() throws Exception {
		this.j1 = new Joueur("J1", false, true);
		this.j2 = new Joueur("J2", true, true);
		this.plateau = new Plateau("./data/plateau/defaut.pl", j1, j2);
	}
	
	public Controleur getControleur() {
		return controleur;
	}

	public void setControleur(Controleur controleur) {
		this.controleur = controleur;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
}
