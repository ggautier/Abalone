/**
 * <b>Bille est la classe qui représente une bille durant une partie.</b>
 * <p>
 * Un Bille est caractérisée par les informations suivantes :
 * <ul>
 * <li>Un joueur, qui va posséder la bille.</li>
 * <li>Une case, que la bille va occuper à un instant.</li>
 * </ul>
 * </p>
 * 
 * @see Joueur
 * @see Case
 * 
 * @author Lenogue Matthieu
 * @author Gautier Quentin
 * @author Gautier Gaetan
 * @author Ouary Maxime
 * 
 * @version 1.0
 */
public class Bille {
	protected int coordX;
	protected int coordY;
	protected Joueur joueur;
	
	Bille(int i, int j, Joueur player) {
		this.coordX = i;
		this.coordY = j;
		this.joueur = player;
		
	}

	public int getX() {
		return coordX;
	}

	public void setX(int coordX) {
		this.coordX = coordX;
	}

	public int getY() {
		return coordY;
	}

	public void setY(int coordY) {
		this.coordY = coordY;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	public String toString() {
		String str = "+";
		
		return str;
	}
	
	
	
	
	
	
	
	
}
