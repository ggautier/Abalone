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

public class FenetrePrincipale extends JFrame{

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

        //init du conteneur plateau
        plateau = new FenetrePlateau(this);
        
        //init de commande
        commande = new FenetreCommande();
                
        //init d'info, contenant les scores, tour en cours, ...
        info = new FenetreInfo();
        
        //On affecte une position au panel plateau, dans le contenant panel
        donnerContrainte(c,0,0,1,1,70,70);
        panel.add(plateau,c);
        
        donnerContrainte(c,1,0,1,1,30,70);
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
	
			
}
