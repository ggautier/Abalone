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


	private JPanel 			panel;
	private FenetrePlateau	plateau;
	private FenetreInfo		info;
	private FenetreCommande	commande;
	
	private JMenuBar 		menuBar;
	private JMenu			fichierMenu;
	
	private Controleur	controleur;
	
	public FenetrePrincipale(String titre)
	{
		//Héritage du builder de la super classe JFrame
		super(titre);
	
		//Rendre la fenetre fermable et re-dimensionnable
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        
        //On cree un barre de menu (vide), puis on cree le 1er menu "Fichier",
        //dans lequel on ajoute l'item "Nouveau", ....
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

        //init du conteneur plateau
        plateau = new FenetrePlateau();
        
        //init de commande
        commande = new FenetreCommande();
                
        //init d'info, contenant les scores, tour en cours, ...
        info = new FenetreInfo();
        
        //On affecte une position au panel plateau, dans le contenant panel
        donnerContrainte(c,0,0,1,1,50,50);
        panel.add(plateau,c);
        
        donnerContrainte(c,1,0,1,1,50,50);
        panel.add(commande,c);
        
        donnerContrainte(c,0,1,2,1,0,0);
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
