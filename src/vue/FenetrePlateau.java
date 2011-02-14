package vue;

import java.awt.*;
import javax.swing.*;


import modele.Bille;


public class FenetrePlateau extends JPanel{

	private JPanel	plateau;
	protected FenetrePrincipale principale;
		
	public FenetrePlateau(FenetrePrincipale princ)
	{
		this.principale = princ;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        EcouteurSouris ecouteurSouris = new EcouteurSouris(this);
        this.addMouseListener(ecouteurSouris);   
        this.addMouseMotionListener(ecouteurSouris);
   	}
	
    public void paintComponent(Graphics g){
        //Vous pourrez voir cette phrase à chaque fois que la méthode est invoquée !
         System.out.println("Je suis exécutée ! ! !"); 
         int decalage = 0;
         Bille billeTemp;
         g.setColor(Color.YELLOW);
         g.fillRect(0, 0, 500, 500);
         
         for (int i = 0; i < 9; i++ ) {
        	 for (int j = 0; j < 9; j++) {
        		 decalage = (4-i)*23;
                 billeTemp = principale.getControleur().getPartie().getPlateau().getBille(i, j);
        		 if (billeTemp != null) {
        			 if (billeTemp.getJoueur().getCamps() == true)
        			 { 
        				 //g.setColor(Color.BLACK);
        				 int r = this.getPrincipale().getControleur().getPartie().getJ1().getR();
        				 int v = this.getPrincipale().getControleur().getPartie().getJ1().getG();
        				 int b = this.getPrincipale().getControleur().getPartie().getJ1().getB();

        				 Color tempColor = new Color(r, v, b);
        				 g.setColor(tempColor);
        			 }
        			 else if (billeTemp.getJoueur().getCamps() == false)
        			 {	
        				 //g.setColor(Color.WHITE);
        				 int r = this.getPrincipale().getControleur().getPartie().getJ2().getR();
        				 int v = this.getPrincipale().getControleur().getPartie().getJ2().getG();
        				 int b = this.getPrincipale().getControleur().getPartie().getJ2().getB();

        				 Color tempColor = new Color(r, v, b);
        				 g.setColor(tempColor);
        			 }
        			 else if ( !principale.getControleur().isOut(i, j))
        				 g.setColor(Color.gray);
        		 }
        		 else 
        			 g.setColor(Color.LIGHT_GRAY);
        		 
        		 if (!principale.getControleur().isOut(i, j))
        			 g.fillOval(decalage+j*45, i*40, 40, 40);
        		 
        		 if (principale.getControleur().isSelectionnee(principale.getControleur().getPartie().getPlateau().getBille(i,j))) {
        			 g.setColor(Color.BLUE);
        			 g.drawOval(decalage+j*45, i*40, 40, 40);
        		 }
        		 else if (principale.getControleur().nbNext(new Point(i,j)) > 0) {
         			g.setColor(Color.DARK_GRAY);
     			 	g.fillOval(decalage+j*45, i*40, 40, 40);
         		 }

        		 if (principale.getControleur().isVisee((principale.getControleur().getPartie().getPlateau().getBille(i,j)))) {
        			g.setColor(Color.RED);
    			 	g.drawOval(decalage+j*45, i*40, 40, 40);
        		 }
        		 if ( (principale.getControleur().getPointee().getY() == j) && (principale.getControleur().getPointee().getX() == i) ) {
           			g.setColor(Color.PINK);
      			 	g.drawOval(decalage+j*45, i*40, 40, 40);
         		 }
        		 if ( (principale.getControleur().isDeplacementVise(new Point(i,j))) ) {
            		g.setColor(Color.GREEN);
       			 	g.fillOval(decalage+j*45, i*40, 40, 40);
          		 }
        		 


        	 }
         }
  }   
	
    
    
    
	public FenetrePrincipale getPrincipale() {
		return principale;
	}

	public void setPrincipale(FenetrePrincipale principale) {
		this.principale = principale;
	}

	void donnerContrainte(GridBagConstraints gbc, int gx, int gy, int gw, int gh, int wx, int wy)
	{
		gbc.gridx=gx;
		gbc.gridy=gy;
		gbc.gridwidth=gw;
		gbc.gridheight=gh;
		gbc.weightx=wx;
		gbc.weighty=wy;
		gbc.fill=GridBagConstraints.BOTH;
	}
}
