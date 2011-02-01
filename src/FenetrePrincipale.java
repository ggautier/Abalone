/**
 * <b>FenetrePrincipale est la classe principale de la vue, qui permet l'affichage de la fen�tre de jeu.</b>
 * <p>
 * Une FenetrePrincipale est caract�ris�e par les informations suivantes :
 * <ul>
 * <li>Un controleur, vers lequel la fen�tre va envoyer des demandes de modification.</li>
 * <li>Une partie, que la fen�tre va solliciter pour renouveler l'affichage apr�s une modification.</li>
 * </ul>
 * </p>
 * 
 * @see Controleur
 * @see Partie
 * 
 * @author Lenogue Matthieu
 * @author Gautier Quentin
 * @author Gautier Gaetan
 * @author Ouary Maxime
 * 
 * @version 1.0
 */

import java.awt.*;
import javax.swing.*;

public class FenetrePrincipale extends JFrame{

	private JPanel 		panel;
	private JMenuBar 	menuBar;
	private JMenu		fichierMenu;
	private JPanel		plateau;
	private JPanel 		info;
	private JPanel		commande;
	
	private Controleur	controleur;
	
	private int[][]		tabjeu;

	public FenetrePrincipale(String titre)
	{
		//H�ritage du builder de la super classe JFrame
		super(titre);
	
		tabjeu = new int[11][19];
		
		//Variable propre � la fenetre ("fermable" et redimensionable)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        
        //On cree un barre de menu (vide), puis on cree le 1er menu "Fichier", dans lequel on ajoute l'item "Nouveau", ....
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        
        JMenu fichierMenu = new JMenu("Fichier");
        
        JMenuItem item = new JMenuItem("Nouveau", 'N');
        //item.addActionListener(afficherMenuListener);
        fichierMenu.add(item);
        fichierMenu.add(new JSeparator());
        item = new JMenuItem("Sauvegarder", 'S');
        //item.addActionListener(afficherMenuListener);
        fichierMenu.add(item);
        item = new JMenuItem("Charger", 'C');
        //item.addActionListener(afficherMenuListener);
        fichierMenu.add(item);
        fichierMenu.add(new JSeparator());
        item = new JMenuItem("Options", 'O');
        //item.addActionListener(afficherMenuListener);
        fichierMenu.add(item);
        fichierMenu.add(new JSeparator());
        item = new JMenuItem("Quitter", 'Q');
        //item.addActionListener(afficherMenuListener);
        fichierMenu.add(item);
        menuBar.add(fichierMenu);
        
        //init de panel (globale)
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //init du conteneur plateau - un grid de 19*11
        plateau = new JPanel();
        plateau.setLayout(new GridLayout(11,19));
        plateau.setMinimumSize(new java.awt.Dimension(600, 600));
        plateau.setPreferredSize(new java.awt.Dimension(600, 600));


        
        for (int i=0;i<11;i++){//on parcours tout le tableau
        	for (int j=0;j<19;j++){
                JToggleButton jb = new JToggleButton();//on cr�e un JToggleButton
                jb.setName(String.valueOf(i+String.valueOf(j)));//on lui donne un nom en fonction de sa position (ce sera ses coordonn�es
                jb.setText(jb.getName());
                if (isOut(i,j))
                { 
                	tabjeu[i][j]=8;
                	jb.setEnabled(false);
                }
                else 
                if (trou(i,j))
                {
                	tabjeu[i][j]=0;
                	jb.setBackground(java.awt.Color.LIGHT_GRAY);
                	jb.setEnabled(false);
                }
                else if(noire(i,j))
                {
                	tabjeu[i][j]=1;
                	jb.setBackground(java.awt.Color.BLACK);
                }
                else if(rouge(i,j))
                {
                	tabjeu[i][j]=2;
                	jb.setBackground(java.awt.Color.RED);
                }
                else
                {
                	tabjeu[i][j]=9;
                	jb.setBackground(java.awt.Color.GRAY);
                	jb.setEnabled(false);
                } 
                plateau.add(jb);
        	}
        }
        
        
        //init de commande
        commande = new JPanel();
        commande.setBackground(Color.BLACK);
        
        //init d'info, contenant les scores, tour en cours, ...
        info = new JPanel();
        info.setBackground((Color.BLUE));
        
        //On affecte une position au panel plateau, dans le contenant panel
        donnerContrainte(c,0,0,1,1,100,100);
        panel.add(plateau,c);
        
        donnerContrainte(c,1,0,1,1,0,0);
        panel.add(commande,c);
        
        donnerContrainte(c,0,1,2,1,100,100);
        panel.add(info,c);
        
        this.add(panel);
	}
	
	void refreshPlateau(int[][] tab)
	{
		
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
	
	//Fonction test du controleur avant implentation dans ce dernier OK !
	boolean isOut(int i, int j)
	{
		boolean good = false;
		if(i==0 || i==10)
		{ if(j==4 || j==6 || j==8 || j==10 || j==12 || j==14) {good=true;} }
		else 
		if(i==1 || i==9)
		{ if(j==3 || j==15) { good=true; } }
		else
		if(i==2 || i==8)
		{ if(j==2 || j==16) { good=true; } }
		else
		if(i==3 || i==7)
		{ if(j==1 || j==17) { good=true; } }
		else
		if(i==4 || i==6)
		{ if(j==0 || j==18) { good=true; } }
		 return good; 
	}
	
	//indique la place des trous dans une config init.
	public boolean trou(int i,int j)
	{
		boolean good=false;
		if (i==3 || i==7)
		{ if(j==3 || j==5 || j==13 || j==15) { good=true; } }
		else
		if (i==4|| i==6)
		{ if (j!=0 && j!=18) { if (j%2==0) { good=true; } } }
		else
		if (i==5)
		{ if (j%2==1){good=true;} }
		return good;
	}
	
	public boolean rouge(int i,int j)
	{
		boolean good=false;
		if(i==7)
		{ if(j==7 || j==9 || j==11) { good=true; } }
		else 
		if(i==8)
		{ if(j==4 || j==6 || j==8 || j==10 || j==12 || j==14) { good=true; } }
		else 
		if(i==9)
		{ if(j==5 || j==7 || j==9 || j==11 || j==13) { good=true; }	}
		return good;
	}
	
	public boolean noire(int i,int j)
	{
		boolean good=false;
		if(i==3)
		{ if(j==7 || j==9 || j==11) { good=true; } }
		else 
		if(i==2)
		{ if(j==4 || j==6 || j==8 || j==10 || j==12 || j==14) { good=true; } }
		else 
		if(i==1)
		{ if(j==5 || j==7 || j==9 || j==11 || j==13) { good=true; }	}
		return good;
	} 
		
}
