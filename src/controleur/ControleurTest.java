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
			Partie partie = new Partie(controleur, "./data/plateau/defautDebug.plt",0);
			
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
			Partie partie = new Partie(controleur, "./data/plateau/defautDebug.plt",0);
			
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
	
	public void testIsSelectionnee_true() {
		
		try {
			Controleur controleur = new Controleur(new FenetrePrincipale("titre"));
			ArrayList<Bille> billes = new ArrayList<Bille>();
			Bille bille = new Bille(2, 2, new Joueur("nom", true, true));
			Bille bille2 = new Bille(3, 5, new Joueur("nom2", false, true));
			
			billes.add(bille);
			billes.add(bille2);
			
			controleur.setSelectionnees(billes);
			
			assertTrue((controleur.isSelectionnee(bille)) && (controleur.isSelectionnee(bille2)));
		}
		
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}
		
		public void testIsSelectionnee_false() {
			
			try {
				Controleur controleur = new Controleur(new FenetrePrincipale("titre"));
				ArrayList<Bille> billes = new ArrayList<Bille>();
				Bille bille = new Bille(2, 2, new Joueur("nom", true, true));
				Bille bille2 = new Bille(3, 5, new Joueur("nom2", false, true));
				
				billes.add(bille);
				
				controleur.setSelectionnees(billes);
				
				assertFalse(controleur.isSelectionnee(bille2));
			}
	
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}

	public void testSetVisees() {
		
		try {
			Controleur controleur = new Controleur(new FenetrePrincipale("titre"));
			
			ArrayList<ArrayList<Bille>> viseesTest = new ArrayList<ArrayList<Bille>>();
			
			ArrayList<Bille> billes1 = new ArrayList<Bille>();
			ArrayList<Bille> billes2 = new ArrayList<Bille>();
			
			Bille bille1 = new Bille(2, 2, new Joueur("nom", true, true));
			Bille bille2 = new Bille(3, 5, new Joueur("nom2", false, true));
			Bille bille3 = new Bille(6, 6, new Joueur("nom", true, true));
			Bille bille4 = new Bille(7, 3, new Joueur("nom2", false, true));
			
			billes1.add(bille1);
			billes1.add(bille2);
			billes2.add(bille3);
			billes2.add(bille4);
			
			viseesTest.add(billes1);
			viseesTest.add(billes2);
			
			controleur.setVisees(viseesTest);
			
			assertFalse((controleur.isVisee(bille1))
							& (controleur.isVisee(bille2))
							& (controleur.isVisee(bille3))
							& (controleur.isVisee(bille4)));
		}

		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}
	
	public void testIsVisee() {
		fail("Pas encore implemente");
	}
	
	public void testGetVisees() {
		
		try {
			Controleur controleur = new Controleur(new FenetrePrincipale("titre"));
			
			controleur.selectionner(2, 2);
			controleur.selectionner(3, 2);
			
			ArrayList<Bille> billes = new ArrayList<Bille>();
			ArrayList<ArrayList<Bille>> viseesTest = new ArrayList<ArrayList<Bille>>();
			
			billes.add(controleur.getPartie().getPlateau().getBille(4, 2));
			viseesTest.add(billes);
			
			controleur.setVisees(viseesTest);
			
			System.out.println(controleur.visees);
			assertFalse(controleur.getVisees(21).isEmpty()) ;
		}

		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}
	
	public void testVoisine() {
		
		try {
			Controleur controleur = new Controleur(new FenetrePrincipale("titre"));
			
			assertTrue((controleur.voisine(controleur.getPartie().getPlateau().getBille(2, 2), 21, 1).getLigne() == 3)
				&& (controleur.voisine(controleur.getPartie().getPlateau().getBille(2, 2), 21, 1).getColonne() == 2));
		}
		
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception levee");
		}
	}
	
	public void testNbNext() {
		fail("Pas encore implemente");
	}

	public void testNextCoup() {
		fail("Pas encore implemente");
	}

	public void testMajDeplacementVise() {
		fail("Pas encore implemente");
	}

	public void testGetBillePointee() {
		fail("Pas encore implemente");
	}

	public void testBilleAlentours() {
		fail("Pas encore implemente");
	}

	public void testGetAxe() {
		fail("Pas encore implemente");
	}

	public void testGenererCoupsArrayListOfBille() {
		fail("Pas encore implemente");
	}

	public void testGenererCoups() {
		fail("Pas encore implemente");
	}

	public void testGetAdversairesPoussables() {
		fail("Pas encore implemente");
	}

	public void testVoisineP() {
		fail("Pas encore implemente");
	}

	public void testGetTete() {
		fail("Pas encore implemente");
	}

	public void testDeplacementPossible() {
		fail("Pas encore implemente");
	}

	public void testDir2axe() {
		fail("Pas encore implemente");
	}

	public void testActionArrayListOfBilleInt() {
		fail("Pas encore implemente");
	}

	public void testActionInt() {
		fail("Pas encore implemente");
	}

	public void testDeplacerBille() {
		fail("Pas encore implemente");
	}

	public void testGetPointee() {
		fail("Pas encore implemente");
	}

	public void testSetPointeeBille() {
		fail("Pas encore implemente");
	}

	public void testSetPointeePoint() {
		fail("Pas encore implemente");
	}

	public void testIsDeplacementVise() {
		fail("Pas encore implemente");
	}

	public void testGetDeplacementVise() {
		fail("Pas encore implemente");
	}

	public void testSetDeplacementVise() {
		fail("Pas encore implemente");
	}

	public void testGetBillesJoueur() {
		fail("Pas encore implemente");
	}

	public void testGetCoupsPossibles() {
		fail("Pas encore implemente");
	}

	public void testNextTurn() {
		fail("Pas encore implemente");
	}

}
