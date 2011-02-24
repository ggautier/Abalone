package controleur;

import java.awt.Point;
import java.util.ArrayList;

import vue.FenetrePrincipale;
import modele.Bille;
import modele.Joueur;
import modele.Partie;
import junit.framework.TestCase;

public class ControleurTest extends TestCase {

	public void testGetControleurIA() {
		
		try {
			Controleur controleur = new Controleur(null);
			ControleurIA ia = ControleurIA.getInstance(controleur);
			
			controleur.setControleurIA(ia);
			
			assertTrue(controleur.getControleurIA() == ia);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}

	public void testInitControleurIA() {
		
		try {
			Controleur controleur = new Controleur(null);
			controleur.initControleurIA();
			assertTrue(((controleur.getControleurIA() != null) || (controleur.getControleurIA() instanceof ControleurIA)));
		}
		
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}

	public void testSetControleurIA() {
		
		try {
			Controleur controleur = new Controleur(null);
			ControleurIA ia = ControleurIA.getInstance(controleur);
			
			controleur.setControleurIA(ia);
			
			assertTrue(controleur.getControleurIA() == ia);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}
	
	public void testGetPartie() {
		
		try {
			Controleur controleur = new Controleur(null);
			Partie partie = new Partie(controleur, "./data/plateau/defautDebug.plt");
			
			controleur.setPartie(partie);
			
			assertTrue(controleur.getPartie() == controleur.partie);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}

	public void testSetPartie() {
		
		try {
			Controleur controleur = new Controleur(null);
			Partie partie = new Partie(controleur, "./data/plateau/defautDebug.plt");
			
			controleur.setPartie(partie);
			
			assertTrue(controleur.getPartie() == partie);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}
	
	public void testGetFenetrePrincipale() {
		
		try {
			Controleur controleur = new Controleur(null);
			FenetrePrincipale fenetre = new FenetrePrincipale("test");
			
			controleur.setFenetrePrincipale(fenetre);
			
			assertTrue(controleur.getFenetrePrincipale() == controleur.fenetrePrincipale);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}

	public void testSetFenetrePrincipale() {

		try {
			Controleur controleur = new Controleur(null);
			FenetrePrincipale fenetre = new FenetrePrincipale("test");
			
			controleur.setFenetrePrincipale(fenetre);
			
			assertTrue(controleur.getFenetrePrincipale() == fenetre);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}
	
	public void testIsOut_bille_in() {
		
		try {
			Controleur controleur = new Controleur(null);
			
			assertFalse(controleur.isOut(3, 3));
		}
		
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}
	
	public void testIsOut_bille_out() {
		
		try {
			Controleur controleur = new Controleur(null);
			
			assertTrue(controleur.isOut(9, 3));
		}
		
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}
	
	public void testGetSelectionnees() {
		
		try {
			Controleur controleur = new Controleur(null);
			ArrayList<Bille> billes = new ArrayList<Bille>();
			billes.add(new Bille(0, 0, new Joueur("nom", true, true)));
			
			controleur.setSelectionnees(billes);
			assertTrue(controleur.selectionnees == billes);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}
	
	public void testSelectionnerIntInt() {
		
		try {
			Controleur controleur = new Controleur(new FenetrePrincipale("titre"));
			
			controleur.selectionner(7, 4);
			
			assertTrue(controleur.getSelectionnees().get(0) == controleur.getPartie().getPlateau().getBille(7, 4));
		}
		
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}
	
	public void testSelectionnerPoint() {
		
		try {
			Controleur controleur = new Controleur(new FenetrePrincipale("titre"));
			
			controleur.selectionner(new Point(7, 4));
			
			assertTrue(controleur.getSelectionnees().get(0) == controleur.getPartie().getPlateau().getBille(7, 4));
		}
		
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}
	
	public void testSetSelectionnees() {
		
		try {
			Controleur controleur = new Controleur(new FenetrePrincipale("titre"));
			ArrayList<Bille> billes = new ArrayList<Bille>();
			
			billes.add(new Bille(2, 2, new Joueur("nom", true, true)));
			
			controleur.setSelectionnees(billes);
			
			assertTrue(controleur.getSelectionnees() == billes);
		}
	
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}
	
	public void testDeselectionner() {
		
		try {
			Controleur controleur = new Controleur(new FenetrePrincipale("titre"));
			
			controleur.selectionner(7, 4);
			controleur.deselectionner();
			
			assertTrue(controleur.getSelectionnees().isEmpty());
		}
	
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}
	
	public void testIsSelectionnee() {
		
		try {
			Controleur controleur = new Controleur(new FenetrePrincipale("titre"));
			ArrayList<Bille> billes = new ArrayList<Bille>();
			Bille bille = new Bille(2, 2, new Joueur("nom", true, true));
			
			billes.add(bille);
			
			controleur.setSelectionnees(billes);
			
			assertTrue(controleur.isSelectionnee(bille));
		}
	
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}

	public void testGetVisees() {
		fail("Pas encore implémenté");
	}
	
	public void testNbNext() {
		fail("Pas encore implémenté");
	}

	public void testNextCoup() {
		fail("Pas encore implémenté");
	}

	public void testMajDeplacementVise() {
		fail("Pas encore implémenté");
	}

	public void testGetBillePointee() {
		fail("Pas encore implémenté");
	}

	public void testIsVisee() {
		fail("Pas encore implémenté");
	}

	public void testBilleAlentours() {
		fail("Pas encore implémenté");
	}

	public void testGetAxe() {
		fail("Pas encore implémenté");
	}

	public void testGenererCoupsArrayListOfBille() {
		fail("Pas encore implémenté");
	}

	public void testGenererCoups() {
		fail("Pas encore implémenté");
	}

	public void testGetAdversairesPoussables() {
		fail("Pas encore implémenté");
	}

	public void testVoisine() {
		fail("Pas encore implémenté");
	}

	public void testVoisineP() {
		fail("Pas encore implémenté");
	}

	public void testGetTete() {
		fail("Pas encore implémenté");
	}

	public void testDeplacementPossible() {
		fail("Pas encore implémenté");
	}

	public void testDir2axe() {
		fail("Pas encore implémenté");
	}

	public void testActionArrayListOfBilleInt() {
		fail("Pas encore implémenté");
	}

	public void testActionInt() {
		fail("Pas encore implémenté");
	}

	public void testDeplacerBille() {
		fail("Pas encore implémenté");
	}

	public void testGetPointee() {
		fail("Pas encore implémenté");
	}

	public void testSetPointeeBille() {
		fail("Pas encore implémenté");
	}

	public void testSetPointeePoint() {
		fail("Pas encore implémenté");
	}

	public void testIsDeplacementVise() {
		fail("Pas encore implémenté");
	}

	public void testGetDeplacementVise() {
		fail("Pas encore implémenté");
	}

	public void testSetDeplacementVise() {
		fail("Pas encore implémenté");
	}

	public void testGetBillesJoueur() {
		fail("Pas encore implémenté");
	}

	public void testGetCoupsPossibles() {
		fail("Pas encore implémenté");
	}

	public void testNextTurn() {
		fail("Pas encore implémenté");
	}

}
