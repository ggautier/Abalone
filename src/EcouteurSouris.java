import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class EcouteurSouris implements MouseListener {

	
	FenetrePlateau fenetre;
	
	public EcouteurSouris(FenetrePlateau fen) {
		this.fenetre = fen;
	}
	
    public void mousePressed(MouseEvent e) {
     	// Ici, on balance la selection de la Bille cliquee
    	// Soit la fenetre n'est pas redimensionnable, donc on met des donnees brutes,
    	// sinon on met des variables.
    	 System.out.println(fenetre.getPrincipale().getControleur().getBillePointee(e.getPoint()));
    	 fenetre.getPrincipale().getControleur().selectionner(fenetre.getPrincipale().getControleur().getBillePointee(e.getPoint()));
    	 fenetre.repaint();
    }

     public void mouseReleased(MouseEvent e) {

     }

     public void mouseEntered(MouseEvent e) {
    	 System.out.println(fenetre.getPrincipale().getControleur().getBillePointee(e.getPoint()));

     }

     public void mouseExited(MouseEvent e) {

     }

     public void mouseClicked(MouseEvent e) {


     }


}
