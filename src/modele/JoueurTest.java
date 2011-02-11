package modele;

import junit.framework.TestCase;

public class JoueurTest extends TestCase {

	public void testGetR() {
		Joueur j1 = new Joueur("Ouary", true , true);
		assertEquals(j1.getR(),j1.r);
	}

	public void testSetR() {
		Joueur j1 = new Joueur("Ouary", true , true);
		j1.setR(255);
		assertEquals(255,j1.getR());
	}

	public void testGetG() {
		Joueur j1 = new Joueur("Ouary", true , true);
		assertEquals(j1.getG(),j1.g);
	}

	public void testSetG() {
		Joueur j1 = new Joueur("Ouary", true , true);
		j1.setG(255);
		assertEquals(255,j1.getG());
	}

	public void testGetB() {
		Joueur j1 = new Joueur("Ouary", true , true);
		assertEquals(j1.getB(),j1.b);
	}

	public void testSetB() {
		Joueur j1 = new Joueur("Ouary", true , true);
		j1.setB(255);
		assertEquals(255,j1.getB());
	}

	public void testGetNom() {
		Joueur j1 = new Joueur("Ouary", true , true);
		assertEquals(j1.getNom(),j1.nom);
	}

	public void testSetNom() {
		Joueur j1 = new Joueur("Ouary", true , true);
		j1.setNom("Sam");
		assertEquals("Sam",j1.getNom());
	}

	public void testGetCamps() {
		Joueur j1 = new Joueur("Ouary", true , true);
		assertEquals(j1.getCamps(),j1.camps);
	}

	public void testSetCamps() {
		Joueur j1 = new Joueur("Ouary", true , true);
		j1.setCamps(false);
		assertEquals(false,j1.getCamps());
	}

	public void testGetScore() {
		Joueur j1 = new Joueur("Ouary", true , true);
		assertEquals(j1.getScore(),j1.score);
	}

	public void testSetScore() {
		Joueur j1 = new Joueur("Ouary", true , true);
		j1.setScore(12);
		assertEquals(12,j1.getScore());
	}

	public void testIsHumain() {
		Joueur j1 = new Joueur("Ouary", true , true);
		assertEquals(j1.isHumain(),j1.humain);
	}

	public void testSetHumain() {
		Joueur j1 = new Joueur("Ouary", true , true);
		j1.setHumain(false);
		assertEquals(false,j1.isHumain());
	}

	public void testEqualsJoueur_joueurs_egaux() {
		Joueur j1 = new Joueur("Ouary", true , true);
		Joueur j2 = new Joueur("Ouary", true , true);
		
		assertTrue(j1.equals(j2));
	}
	
	public void testEqualsJoueur_joueurs_inegaux() {
		Joueur j1 = new Joueur("Ouary", true , true);
		Joueur j2 = new Joueur("Ouary", false , true);
		
		assertTrue(!j1.equals(j2));
	}
/*
	public void testEqualsJoueur_nulle() {
		Joueur j1 = new Joueur("Ouary", true , true);
		
		assertTrue(j1.equals(null));
	}*/
}
