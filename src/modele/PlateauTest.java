package modele;

import junit.framework.TestCase;

public class PlateauTest extends TestCase {

	public void testGetBille() {
		Joueur j1 = new Joueur("Marcel", true , true);
		Joueur j2 = new Joueur("Yves" , false, false);
		try{
			Plateau plato = new Plateau("Plateau", j1, j2);
			assertEquals(plato.getBille(12, 9),plato.getPlateau());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		

	}

	public void testSetBille() {
		fail("Not yet implemented");
	}

	public void testInit() {
		fail("Not yet implemented");
	}

	public void testRemplir() {
		fail("Not yet implemented");
	}

	public void testCaseVide() {
		fail("Not yet implemented");
	}

}
