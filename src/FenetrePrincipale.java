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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		this.controleur = new Controleur();
	
		//Rendre la fenetre fermable et re-dimensionnable
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        
        //On cree un barre de menu (vide), puis on cree le 1er menu "Fichier",
        //dans lequel on ajoute l'item "Nouveau", ....
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        
     JMenu fichierMenu = new JMenu("Fichier");
        
        JMenuItem itemNouveau = new JMenuItem("Nouveau", 'N');
        itemNouveau.addActionListener(this);
        fichierMenu.add(itemNouveau);
        fichierMenu.add(new JSeparator());
        JMenuItem itemSave = new JMenuItem("Sauvegarder", 'S');
        itemSave.addActionListener(this);
        fichierMenu.add(itemSave);
        JMenuItem itemLoad = new JMenuItem("Charger", 'C');
        itemLoad.addActionListener(this);
        fichierMenu.add(itemLoad);
        fichierMenu.add(new JSeparator());
        JMenuItem itemOptions = new JMenuItem("Options", 'O');
        itemOptions.addActionListener(this);
        fichierMenu.add(itemOptions);
        fichierMenu.add(new JSeparator());
        JMenuItem itemQuitter = new JMenuItem("Quitter", 'Q');
        itemQuitter.addActionListener(this);
        fichierMenu.add(itemQuitter);
        menuBar.add(fichierMenu);
        
        //init de panel (globale)
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //init du conteneur plateau
        plateau = new FenetrePlateau(this);
        
        //init de commande
        commande = new FenetreCommande();
                
        //init d'info, contenant les scores, tour en cours, ...
        info = new FenetreInfo();
        
        //On affecte une position au panel plateau, dans le contenant panel
        donnerContrainte(c,0,0,1,1,90,70);
        panel.add(plateau,c);
        
        donnerContrainte(c,1,0,1,1,10,70);
        panel.add(commande,c);
        
        donnerContrainte(c,0,1,2,1,100,30);
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
	
	public Controleur getControleur() {
		return controleur;
	}

	public void setControleur(Controleur controleur) {
		this.controleur = controleur;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		
		System.out.println(source.toString());
		
	}

	
	public void actionPerformed(ActionEvent e) {
		FenetreOption fOp = new FenetreOption("Options");
	}
}
