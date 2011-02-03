/**
 * <b>FenetrePrincipale est la classe principale de la vue, qui permet l'affichage de la fenêtre de jeu.</b>
 * <p>
 * Une FenetrePrincipale est caractérisée par les informations suivantes :
 * <ul>
 * <li>Un controleur, vers lequel la fenêtre va envoyer des demandes de modification.</li>
 * <li>Une partie, que la fenêtre va solliciter pour renouveler l'affichage après une modification.</li>
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FenetrePrincipale extends JFrame implements ActionListener{

	private JPanel 		panel;
	private JMenuBar 	menuBar;
	private JMenu		fichierMenu;
	private JPanel		plateau;
	private JPanel 		info;
	private JPanel		commande;
	private JMenuItem	itemOptions;
	private Controleur	controleur;
	
	private int[][]		tabjeu;
	private int[]		tabCase;
	private int[]		tabCaseAlt;

	public FenetrePrincipale(String titre)
	{
		//Héritage du builder de la super classe JFrame
		super(titre);
	
		tabjeu = new int[11][19];
		
		//Variable propre à la fenetre ("fermable" et redimensionable)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        
        //On cree un barre de menu (vide), puis on cree le 1er menu "Fichier", dans lequel on ajoute l'item "Nouveau", ....
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        
        JMenu fichierMenu = new JMenu("Fichier");
        
        JMenuItem itemNouveau = new JMenuItem("Nouveau", 'N');
        //item.addActionListener(afficherMenuListener);
        fichierMenu.add(itemNouveau);
        fichierMenu.add(new JSeparator());
        JMenuItem itemSave = new JMenuItem("Sauvegarder", 'S');
        //item.addActionListener(afficherMenuListener);
        fichierMenu.add(itemSave);
        JMenuItem itemLoad = new JMenuItem("Charger", 'C');
        //item.addActionListener(afficherMenuListener);
        fichierMenu.add(itemLoad);
        fichierMenu.add(new JSeparator());
        JMenuItem itemOptions = new JMenuItem("Options", 'O');
        itemOptions.addActionListener(this);
        fichierMenu.add(itemOptions);
        fichierMenu.add(new JSeparator());
        JMenuItem itemQuitter = new JMenuItem("Quitter", 'Q');
        //item.addActionListener(afficherMenuListener);
        fichierMenu.add(itemQuitter);
        menuBar.add(fichierMenu);
        
        //init de panel (globale)
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //init du conteneur plateau - un grid de 19*11
        plateau = new JPanel();
        plateau.setLayout(new GridBagLayout());

       /* for (int i=0;i<11;i++){//on parcours tout le tableau
        	for (int j=0;j<19;j++){
                JToggleButton jb = new JToggleButton();//on crée un JToggleButton
                jb.setName(String.valueOf(i) + String.valueOf(j));//on lui donne un nom en fonction de sa position (ce sera ses coordonnées
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
        }*/
		tabCase = new int[9];
		tabCaseAlt = new int[9];
		
		tabCase[0] = 5;
		tabCaseAlt[0] = 4;
		tabCase[1] = 6;
		tabCaseAlt[1] = 3;
		tabCase[2] = 7;
		tabCaseAlt[2] = 2;
		tabCase[3] = 8;
		tabCaseAlt[3] = 1;
		tabCase[4] = 9;
		tabCaseAlt[4] = 0;
		tabCase[5] = 8;
		tabCaseAlt[5] = 1;
		tabCase[6] = 7;
		tabCaseAlt[6] = 2;
		tabCase[7] = 6;
		tabCaseAlt[7] = 3;
		tabCase[8] = 5;
		tabCaseAlt[8] = 4;

        
        for (int i=0; i<9; i++)
        {
        	for (int j=0; j<tabCase[i]; j++)
        	{	
        		JToggleButton jb = new JToggleButton();//on crée un JToggleButton
        		jb.setName(String.valueOf(i) + String.valueOf(j));
        		jb.setText(jb.getName());
        		
        		donnerContrainte(c,tabCaseAlt[i]+(2*j),i,2,1,0,0);
        		plateau.add(jb,c);
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
	boolean isOut(int i, int j) {
		return  (
				( (i==0 || i==10) && (j==4 || j==6 || j==8 || j==10 || j==12 || j==14) ) ||
				( (i==1 || i==9) && (j==3 || j==15) ) ||
				( (i==2 || i==8) && (j==2 || j==16) ) ||
				( (i==3 || i==7) && (j==1 || j==17) ) ||
				( (i==4 || i==6) && (j==0 || j==18) )
				)
				;
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
	
	public void actionPerformed(ActionEvent e) {
		FenetreOption fOp = new FenetreOption("Options");
	}
	
		
}
