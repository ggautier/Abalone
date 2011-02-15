package vue;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class EcouteurSouris implements MouseListener, MouseMotionListener {

	
	FenetrePlateau fenetre;
	
	public EcouteurSouris(FenetrePlateau fen) {
		this.fenetre = fen;
	}
	
    public void mousePressed(MouseEvent e) {
     	// Ici, on balance la selection de la Bille cliquee
    	// Soit la fenetre n'est pas redimensionnable, donc on met des donnees brutes,
    	// sinon on met des variables.
    	 Point pointee = fenetre.getPrincipale().getControleur().getBillePointee(e.getPoint()); // Retourne la case Pointee par la souris.
    	 fenetre.getPrincipale().getControleur().selectionner(pointee); // Selectionne la Bille pointee par la souris
    	 
    	 if (fenetre.getPrincipale().getControleur().isDeplacementVise(pointee)) // Si la case pointee correspond a un deplacement
    		 fenetre.getPrincipale().getControleur().action(fenetre.getPrincipale().getControleur().getDeplacementVise());
    	 fenetre.repaint();
    }

     public void mouseReleased(MouseEvent e) {

     }
     
     public void mouseMoved(MouseEvent e) {
    	 Point pointee = fenetre.getPrincipale().getControleur().getBillePointee(e.getPoint());
    	 
    	 if (!fenetre.getPrincipale().getControleur().isOut((int)pointee.getX(), (int)pointee.getY())) // Si la case existe
    		 fenetre.getPrincipale().getControleur().setPointee(pointee); // On pointe
    	 
 		 this.fenetre.getPrincipale().getControleur().majDeplacementVise(pointee); // Mise a jour du deplacement pointe.

    	 fenetre.repaint();
      }


     public void mouseEntered(MouseEvent e) {

     }

     public void mouseExited(MouseEvent e) {

     }

     public void mouseClicked(MouseEvent e) {

     }

	public void mouseDragged(MouseEvent e) {

	}


}
