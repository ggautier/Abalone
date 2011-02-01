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

	private JPanel 		panel;
	private JMenuBar 	menuBar;
	private JMenu		fichierMenu;
	private JPanel		plateau;
	private JPanel 		info;
	private JPanel		commande;

	public FenetrePrincipale(String titre)
	{
		//Héritage du builder de la super classe JFrame
		super(titre);
		
		//Variable propre à la fenetre ("fermable" et redimensionable)
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
                JToggleButton jb = new JToggleButton();//on crée un JToggleButton
                jb.setName(String.valueOf(i*19+j));//on lui donne un nom en fonction de sa position (ce sera ses coordonnées
                jb.setText(jb.getName());
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
	
	void donnerContrainte(GridBagConstraints gbc, int gx, int gy, int gw, int gh, int
			wx, int wy)
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
