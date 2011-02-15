package vue;
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

import modele.Joueur;


import controleur.Controleur;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class FenetrePrincipale extends JFrame implements ActionListener{

	private JPanel 			panel;
	private FenetrePlateau	plateau;
	private FenetreInfo		info;
	private FenetreCommande	commande;
	
	private JMenuBar 		menuBar;

	private JMenu			fichierMenu;
	
	private Controleur	controleur;
	
	private Color colorJ1, colorJ2;
	
	public FenetrePrincipale(String titre) throws Exception 
	{
		//Héritage du builder de la super classe JFrame
		super(titre);
		

		
		this.controleur = new Controleur(this);
			
		//Rendre la fenetre fermable et re-dimensionnable
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		////// Pas redimensionnable
		this.setResizable(false);
		this.setSize(800, 600);
		this.setLocation((getToolkit().getScreenSize().width-this.getWidth())/2,(getToolkit().getScreenSize().height-this.getHeight())/2);
		////////////////////////
        
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
        plateau.setSize(300, 200);
        
        //init de commande
        commande = new FenetreCommande(this);
                
        //init d'info, contenant les scores, tour en cours, ...
        info = new FenetreInfo(this);
        
        //On affecte une position au panel plateau, dans le contenant panel
        donnerContrainte(c,0,0,1,1,90,70);
        panel.add(plateau,c);
        
        donnerContrainte(c,1,0,1,1,10,70);
        panel.add(commande,c);
        
        donnerContrainte(c,0,1,2,1,100,30);
        panel.add(info,c);
        
        this.add(panel);
	}
	
	public FenetrePlateau getPlateau() {
		return plateau;
	}

	public void setPlateau(FenetrePlateau plateau) {
		this.plateau = plateau;
	}

	void refreshPlateau(int[][] tab)
	{
		
	}
	
	public void rafraichir() {
		this.getInfo().getTourDeJeu().setText(this.getControleur().getPartie().getJCourant().getNom());
		this.getInfo().getTourDeJeu().repaint();
		this.getInfo().getTourDeJeu().revalidate();
		this.getInfo().getTourDeJeu().repaint();
		
		this.commande.repaint();
		this.commande.revalidate();
		this.commande.repaint();
		
		this.repaint();
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
		Object source = e.getActionCommand().toString();
		
		if (source == "Nouveau")
		{}
		if (source == "Sauvegarder")
		{ 
			//FenetreSauvegarder fenetreSav = new FenetreSauvegarder("Sauvegarder");
			JFileChooser filechoose = new JFileChooser();
			// Créer un JFileChooser
			filechoose.setCurrentDirectory(new File(".")); 
			// Le répertoire source du JFileChooser est le répertoire d'où est lancé notre programme
			String approve = new String("Enregistrer");
			// Le bouton pour valider l'enregistrement portera la mention Enregistrer
			int resultatEnregistrer = filechoose.showDialog(filechoose, approve); 
			// Pour afficher le JFileChooser
			if (resultatEnregistrer == JFileChooser.APPROVE_OPTION) 
			// Si l'utilisateur clique sur le bouton Enregistrer
			{ 
				String monFichier= new String(filechoose.getSelectedFile().toString());
				// Récupérer le nom du fichier qu'il a spécifié
				if(monFichier.endsWith(".txt") || monFichier.endsWith(".TXT")) 
				{;}
				// Si ce nom de fichier finit par .txt ou .TXT, ne rien faire et passer à la suite
				else 
				{
					monFichier = monFichier + ".txt" ;
				}
				// Sinon renommer le fichier pour qu'il porte l'extension .txt
				{ 
					try
					{ 
						FileWriter lu = new FileWriter(monFichier);
						// Créer un objet java.io.FileWriter avec comme argument le mon du fichier dans lequel enregsitrer
						BufferedWriter out = new BufferedWriter(lu);
						// Mettre le flux en tampon (en cache)
						
						// affichage du plateau (toString())
						out.write(this.getControleur().getPartie().getPlateau().toString()); 
						out.write("\n");
						//affichage du joueur actif
						int i = (this.getControleur().getPartie().getJCourant().getCamps() ? 1 : 0) ;
						String temp = new String(""+i);
						out.write(temp);
						out.write("\n");
						//affichage du joueur 1 (Nom r g b score humain)
						temp = this.getControleur().getPartie().getJ1().getNom() + " ";
						temp += this.getControleur().getPartie().getJ1().getR() + " ";
						temp += this.getControleur().getPartie().getJ1().getG() + " ";
						temp += this.getControleur().getPartie().getJ1().getB() + " ";
						temp += this.getControleur().getPartie().getJ1().getScore() + " ";
						temp += this.getControleur().getPartie().getJ1().isHumain() + " ";

						out.write(temp);
						out.write("\n");
						
						//affichage du joueur 2 (Nom r g b score humain)
						temp = this.getControleur().getPartie().getJ2().getNom() + " ";
						temp += this.getControleur().getPartie().getJ2().getR() + " ";
						temp += this.getControleur().getPartie().getJ2().getG() + " ";
						temp += this.getControleur().getPartie().getJ2().getB() + " ";
						temp += this.getControleur().getPartie().getJ2().getScore() + " ";
						temp += this.getControleur().getPartie().getJ2().isHumain() + " ";

						out.write(temp);
						out.write("\n");

						// Balancer dans le flux le contenu de la zone de texte
						out.close(); 
						// Fermer le flux (c'est toujours mieux de le fermer explicitement)
					} 
					catch (IOException er) 
					{;}
				}
			}
		}
		if (source == "Charger")
		{
			JFileChooser filechoose = new JFileChooser();
			// Créer un JFileChooser
			filechoose.setCurrentDirectory(new File("."));
			// Le répertoire source du JFileChooser est le répertoire d'où est lancé notre programme
			String approve = new String("Charger");
			// Le bouton pour valider l'enregistrement portera la mention Charger
			String monFichier= null; 
			// On ne sait pas pour l'instant quel sera le fichier à ouvrir
			int resultatOuvrir = filechoose.showDialog(filechoose, approve); 
			// Pour afficher le JFileChooser
			if(resultatOuvrir == filechoose.APPROVE_OPTION)
			// Si l'utilisateur clique sur le bouton OUVRIR
			{
			  	monFichier = filechoose.getSelectedFile().toString();
			  	// Récupérer le nom du fichier qu'il a spécifié

			  	try
			  	{ 
			  		/*FileInputStream fis = new FileInputStream(monFichier);
			  		// Créer un flux d'entrée avec comme paramètre le nom du fichier à ouvrir
			  		int n = fis.available();
			  		while(n > 0) // tant qu'il y a des données dans le flux
			  		{ 
			  			byte[] b = new byte[n]; 
			  			// récupérer le byte à l'endroit n et le stocker dans un tableau de bytes
			  			int result = fis.read(b); // lire ce tableau de byte à l'endroit désiré
			  			if (result == -1) 
			  				break; // si le byte est -1, c'est que le flux est arrivé à sa fin (par définition)
			  			String s = new String(b);
			  			// assembler les bytes pour former une chaîne
			  			
			  			*/// Set le plateau avec la partie lue ...
			  			this.getControleur().getPartie().getPlateau().init(monFichier, this.getControleur().getPartie().getJ1(), this.getControleur().getPartie().getJ2());
			  		//}
			  	} 
			  	catch (Exception err) 
			  	{;}
		   	}
		}
		if (source == "Options")
		{ FenetreOption fenetreOpt = new FenetreOption("Options", this); }
		if (source == "Quitter")
		{ System.exit(0); }

		
	}

	public FenetreInfo getInfo() {
		return info;
	}

	public void setInfo(FenetreInfo info) {
		this.info = info;
	}
	
	

}
