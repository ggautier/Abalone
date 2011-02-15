package modele;

import controleur.Controleur;
import junit.framework.TestCase;

public class PartieTest extends TestCase {

	public void testGetJ1() {
		
		try {
			Partie partie = new Partie("./data/plateau/defaut.plt");
			assertEquals(partie.getJ1(), partie.j1);
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void testSetJ1() {
		
		Joueur joueur = new Joueur("Abdalah", true, true);
		
		try {
			Partie partie = new Partie("./data/plateau/defaut.plt");
			
			partie.setJ1(joueur);
			assertEquals(partie.getJ1(), joueur);
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void testGetJ2() {
		
		try {
			Partie partie = new Partie("./data/plateau/defaut.plt");
			assertEquals(partie.getJ2(), partie.j2);
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void testSetJ2() {
		
		Joueur joueur = new Joueur("Abdalah", true, true);
		
		try {
			Partie partie = new Partie("./data/plateau/defaut.plt");
			
			partie.setJ2(joueur);
			assertEquals(partie.getJ2(), joueur);
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void testGetPlateau() {
		
		try {
			Partie partie = new Partie("./data/plateau/defaut.plt");
			
			assertEquals(partie.getPlateau(), partie.plateau);
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void testSetPlateau() {
		
		Plateau plateau;
		
		try {
			plateau = new Plateau();
			Partie partie = new Partie("./data/plateau/defaut.plt");
			
			partie.setPlateau(plateau);
			
			assertEquals(partie.getPlateau(), plateau);
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
