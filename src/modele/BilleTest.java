package modele;

import junit.framework.TestCase;

public class BilleTest extends TestCase {

	public void testGetX() {
		Joueur joueur = new Joueur("Jose", false, false);
		Bille bille = new Bille(4, 5, joueur);
		
		assertEquals(bille.getX(), bille.coordX);
	}

	public void testSetX() {
		Joueur joueur = new Joueur("Jose", false, false);
		Bille bille = new Bille(4, 5, joueur);
		
		bille.setX(12);
		assertEquals(12, bille.coordX);
	}

	public void testGetY() {
		Joueur joueur = new Joueur("Jose", false, false);
		Bille bille = new Bille(4, 5, joueur);
		
		assertEquals(bille.getY(), bille.coordY);
	}

	public void testSetY() {
		Joueur joueur = new Joueur("Jose", false, false);
		Bille bille = new Bille(4, 5, joueur);
		
		bille.setY(12);
		assertEquals(12, bille.coordY);
	}

	public void testGetJoueur() {
		Joueur joueur = new Joueur("Jose", false, false);
		Bille bille = new Bille(4, 5, joueur);
		
		assertEquals(bille.getJoueur(), joueur);
	}

	public void testSetJoueur() {
		Joueur joueur1 = new Joueur("Jose", false, false);
		Joueur joueur2 = new Joueur("Marcel", true, true);
		Bille bille = new Bille(4, 5, joueur1);
		
		bille.setJoueur(joueur2);
		assertEquals(bille.getJoueur(), joueur2);
	}

	public void testEqualsBille_billes_egales() {
		Joueur joueur1 = new Joueur("Jose", false, false);
		Bille bille1 = new Bille(2, 7, joueur1);
		Bille bille2 = new Bille(2, 7, joueur1);
		
		assertTrue(bille1.equals(bille2));
	}
	
	public void testEqualsBille_billes_differentes() {
		Joueur joueur1 = new Joueur("Jose", false, false);
		Bille bille1 = new Bille(2, 7, joueur1);
		
		Joueur joueur2 = new Joueur("Marcel", true, true);
		Bille bille2 = new Bille(4, 5, joueur2);
		
		assertTrue(!bille1.equals(bille2));
	}

	public void testEqualsBille_bille_nulle() {
		Joueur joueur1 = new Joueur("Jose", false, false);
		Bille bille1 = new Bille(2, 7, joueur1);
		
		assertTrue(!bille1.equals(null));
	}
}
