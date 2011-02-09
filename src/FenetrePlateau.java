import java.awt.*;
import javax.swing.*;

public class FenetrePlateau extends JPanel{

	private JPanel	plateau;
	protected FenetrePrincipale principale;
		
	public FenetrePlateau(FenetrePrincipale princ)
	{
		this.principale = princ;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.addMouseListener(new EcouteurSouris(this));
   	}
	
    public void paintComponent(Graphics g){
        //Vous pourrez voir cette phrase � chaque fois que la m�thode est invoqu�e !
         System.out.println("Je suis ex�cut�e ! ! !"); 
         int decalage = 0;
         Bille billeTemp;
         g.setColor(Color.YELLOW);
         g.fillRect(0, 0, 500, 500);
         
         for (int i = 0; i < 9; i++ ) {
        	 for (int j = 0; j < 9; j++) {
        		 decalage = (4-i)*23;
                 billeTemp = principale.getControleur().getPartie().getPlateau().getBille(i, j);
        		 if (billeTemp != null) {
        			 if (billeTemp.getJoueur().getCouleur() == true)
        				 g.setColor(Color.BLACK);
        			 else if (billeTemp.getJoueur().getCouleur() == false)
        				 g.setColor(Color.WHITE);
        			 else if ( !principale.getControleur().isOut(i, j))
        				 g.setColor(Color.gray);
        		 }
        		 else 
        			 g.setColor(Color.GREEN);
        		 
        		 if (!principale.getControleur().isOut(i, j))
        			 g.fillOval(decalage+j*45, i*40, 40, 40);
        		 
        		 if (principale.getControleur().isSelectionnee(principale.getControleur().getPartie().getPlateau().getBille(i,j))) {
        			 g.setColor(Color.BLUE);
        			 g.drawOval(decalage+j*45, i*40, 40, 40);
        		 }
        		 else if (principale.getControleur().isVisee((principale.getControleur().getPartie().getPlateau().getBille(i,j)))) {
        			g.setColor(Color.RED);
    			 	g.drawOval(decalage+j*45, i*40, 40, 40);
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
