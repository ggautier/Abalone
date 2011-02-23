package modele;

import junit.framework.TestCase;

public class BilleTest extends TestCase {

	public void testGetLigne() {
		Joueur joueur = new Joueur("Jose", false, false);
		Bille bille = new Bille(4, 5, joueur);
		
		assertEquals(bille.getLigne(), 4);
	}

	public void testSetLigne() {
		Joueur joueur = new Joueur("Jose", false, false);
		Bille bille = new Bille(4, 5, joueur);
		
		bille.setLigne(12);
		assertEquals(bille.getLigne(), 12);
	}

	public void testGetColonne() {
		Joueur joueur = new Joueur("Jose", false, false);
		Bille bille = new Bille(4, 5, joueur);
		
		assertEquals(bille.getColonne(), 5);
	}

	public void testSetColonne() {
		Joueur joueur = new Joueur("Jose", false, false);
		Bille bille = new Bille(4, 5, joueur);
		
		bille.setColonne(12);
		assertEquals(12, bille.getColonne());
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
