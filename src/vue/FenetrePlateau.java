package vue;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;


import javax.imageio.ImageIO;
import javax.swing.*;


import modele.Bille;


public class FenetrePlateau extends JPanel{

	private JPanel	plateau;
	protected FenetrePrincipale principale;
	protected BufferedImage imgBilleBlanche, imgBilleNoire, imgVide, imgPlateau;
	
	protected int decalage;
	protected int offsetL;
	protected int offsetH;
	
	protected int largeur;
	protected int longueur;
	
		
	@SuppressWarnings("deprecation")
	public FenetrePlateau(FenetrePrincipale princ)
	{
		
		this.principale = princ;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        EcouteurSouris ecouteurSouris = new EcouteurSouris(this);
      
        this.addMouseListener(ecouteurSouris);   
        this.addMouseMotionListener(ecouteurSouris);
        try {
        	imgBilleBlanche = ImageIO.read(new File("./data/img/blanche.png"));
        } 
        catch (IOException e) {
        }
        try {
        	imgBilleNoire = ImageIO.read(new File("./data/img/noire.png"));
        } 
        catch (IOException e) {
        }
        try {
        	imgVide = ImageIO.read(new File("./data/img/vide.png"));
        } 
        catch (IOException e) {
        }
        try {
        	imgPlateau = ImageIO.read(new File("./data/img/plateau.png"));
        } 
        catch (IOException e) {
        }
        
   	}
	
    public void paintComponent(Graphics g){
		 this.offsetL = (int) (this.getWidth()*0.13);
		 this.offsetH = (int) (this.getHeight()*0.015);
		 this.longueur = 55;
		 this.largeur = 60;
		 
		 System.out.println(getSize());
		 BufferedImage image1 = null;

        //Vous pourrez voir cette phrase à chaque fois que la méthode est invoquée !
         System.out.println("Je suis exécutée ! ! !"); 
         Bille billeTemp;
         g.setColor(Color.WHITE);
         /* Test avec variables */
         g.fillRect(0, 0, this.getWidth(), this.getHeight());
         g.drawImage(imgPlateau,(int) (this.getWidth()*0.085), 0, (int) (this.getWidth()*0.85), this.getHeight(), this);
         //
         
         for (int i = 0; i < 9; i++ ) {
        	 for (int j = 0; j < 9; j++) {

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
        				 image1 = imgBilleBlanche;
        			 }
        			 else if (billeTemp.getJoueur().getCamps() == false)
        			 {	
        				 //g.setColor(Color.WHITE);
        				 int r = this.getPrincipale().getControleur().getPartie().getJ2().getR();
        				 int v = this.getPrincipale().getControleur().getPartie().getJ2().getG();
        				 int b = this.getPrincipale().getControleur().getPartie().getJ2().getB();

        				 Color tempColor = new Color(r, v, b);
        				 g.setColor(tempColor);
        				 image1 = imgBilleNoire;
        			 }
        			 else if ( !principale.getControleur().isOut(i, j)) {
        				 g.setColor(Color.gray);
        				 image1 = imgVide;
        			 }
        		 }
        		 else {
        			 g.setColor(Color.LIGHT_GRAY);
        			 image1 = imgVide;
        		 }

        		 this.decalage = (4-i)*(int) (this.getWidth()*0.042);

        		 if (!principale.getControleur().isOut(i, j))
        			 g.drawImage(image1,this.offsetL+this.decalage+j*largeur, this.offsetH+i*longueur, longueur, longueur, this);
        			 //g.fillOval(decalage+j*45, i*40, 40, 40);
        		 
        		 if (principale.getControleur().isSelectionnee(principale.getControleur().getPartie().getPlateau().getBille(i,j))) {
        			 g.setColor(new Color(0,100,255,90));
        			 g.fillOval(this.offsetL+this.decalage+j*largeur, this.offsetH+i*longueur, longueur, longueur);
        		 }
        		 else if (principale.getControleur().nbNext(new Point(i,j)) > 0) {
         			g.setColor(new Color(220,220,220,130));
     			 	g.fillOval(this.offsetL+this.decalage+j*largeur, this.offsetH+i*longueur, longueur, longueur);
         		 }
        		 else if ( (principale.getControleur().getPointee().getY() == j) && (principale.getControleur().getPointee().getX() == i) ) {
        			 g.setColor(new Color(150,150,220,128));
       			 	g.fillOval(this.offsetL+this.decalage+j*largeur, this.offsetH+i*longueur, longueur, longueur);
          		 }

        		 if (principale.getControleur().isVisee((principale.getControleur().getPartie().getPlateau().getBille(i,j)))) {
        			 g.setColor(new Color(255,0,0,50));
    			 	g.fillOval(this.offsetL+this.decalage+j*largeur, this.offsetH+i*longueur, longueur, longueur);
        		 }

        		 if ( (principale.getControleur().isDeplacementVise(new Point(i,j))) ) {
            		g.setColor(new Color(0,255,0,108));
       			 	g.fillOval(this.offsetL+this.decalage+j*largeur, this.offsetH+i*longueur, longueur, longueur);
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

	public int getDecalage() {
		return decalage;
	}

	public void setDecalage(int decalage) {
		this.decalage = decalage;
	}

	public int getOffsetL() {
		return offsetL;
	}

	public void setOffsetL(int offsetL) {
		this.offsetL = offsetL;
	}

	public int getOffsetH() {
		return offsetH;
	}

	public void setOffsetH(int offsetH) {
		this.offsetH = offsetH;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	
	
}
