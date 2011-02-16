package modele;

import junit.framework.TestCase;

public class PlateauTest extends TestCase {

	public void testGetBille() {
		Joueur j1 = new Joueur("Marcel", true , true);
		Joueur j2 = new Joueur("Yves" , false, false);
		try{
			Plateau plato = new Plateau();
			assertEquals(plato.getBille(12, 9),plato.getPlateau());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public void testSetBille() {
		Joueur j1 = new Joueur("Marcel", true , true);
		Joueur j2 = new Joueur("Yves" , false, false);
		try{
			Plateau plato = new Plateau();
			Bille bille1 = new Bille(2, 7, j1);
			plato.setBille(7,3,bille1);
			assertEquals(plato.getBille(12, 9),bille1);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
/* TODO latter
	public void testInit() {
	
	}
*/

	public void testCaseVide() {
		Joueur j1 = new Joueur("Marcel", true , true);
		Joueur j2 = new Joueur("Yves" , false, false);
		try{
			Plateau plato = new Plateau();
			assertTrue(!plato.caseVide(0, 0));
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public void testCaseVide_True() {
		Joueur j1 = new Joueur("Marcel", true , true);
		Joueur j2 = new Joueur("Yves" , false, false);
		try{
			Plateau plato = new Plateau();
			assertTrue(plato.caseVide(5, 5));
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
