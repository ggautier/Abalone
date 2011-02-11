package modele;

import junit.framework.TestCase;

public class PartieTest extends TestCase {

	public void testGetJ1() {
		Joueur joueur1 = new Joueur("Rachid", true, false);
		Joueur joueur2 = new Joueur("Mouloud", false, false);
		Plateau plateau;
		
		try {
			plateau = new Plateau("./data/plateau/defaut.plt", joueur1, joueur2);
			Partie partie = new Partie(joueur1, joueur2, plateau);
			assertEquals(partie.getJ1(), partie.j1);
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void testSetJ1() {
		Joueur joueur1 = new Joueur("Rachid", true, false);
		Joueur joueur2 = new Joueur("Mouloud", false, false);
		Joueur joueur3 = new Joueur("Abdalah", true, true);
		Plateau plateau;
		
		try {
			plateau = new Plateau("./data/plateau/defaut.plt", joueur1, joueur2);
			Partie partie = new Partie(joueur1, joueur2, plateau);
			
			partie.setJ1(joueur3);
			assertEquals(partie.getJ1(), joueur3);
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void testGetJ2() {
		Joueur joueur1 = new Joueur("Rachid", true, false);
		Joueur joueur2 = new Joueur("Mouloud", false, false);
		Plateau plateau;
		
		try {
			plateau = new Plateau("./data/plateau/defaut.plt", joueur1, joueur2);
			Partie partie = new Partie(joueur1, joueur2, plateau);
			assertEquals(partie.getJ2(), partie.j2);
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void testSetJ2() {
		Joueur joueur1 = new Joueur("Rachid", true, false);
		Joueur joueur2 = new Joueur("Mouloud", false, false);
		Joueur joueur3 = new Joueur("Abdalah", true, true);
		Plateau plateau;
		
		try {
			plateau = new Plateau("./data/plateau/defaut.plt", joueur1, joueur2);
			Partie partie = new Partie(joueur1, joueur2, plateau);
			
			partie.setJ2(joueur3);
			assertEquals(partie.getJ2(), joueur3);
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void testGetPlateau() {
		Joueur joueur1 = new Joueur("Rachid", true, false);
		Joueur joueur2 = new Joueur("Mouloud", false, false);
		Plateau plateau;
		
		try {
			plateau = new Plateau("./data/plateau/defaut.plt", joueur1, joueur2);
			Partie partie = new Partie(joueur1, joueur2, plateau);
			
			
			assertEquals(partie.getPlateau(), plateau);
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void testSetPlateau() {
		Joueur joueur1 = new Joueur("Rachid", true, false);
		Joueur joueur2 = new Joueur("Mouloud", false, false);
		Plateau plateau;
		Plateau plateau2;
		
		try {
			plateau = new Plateau("./data/plateau/defaut.plt", joueur1, joueur2);
			plateau2 = new Plateau("bidule", joueur1, joueur2);
			Partie partie = new Partie(joueur1, joueur2, plateau);
			
			partie.setPlateau(plateau2);
			
			assertEquals(partie.getPlateau(), plateau2);
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
