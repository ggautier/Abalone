package vue;
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
		//H�ritage du builder de la super classe JFrame
		super(titre);
		
		/*
		this.controleur = new Controleur(this);
		this.controleur.initControleurIA();
		*/
		
		//Rendre la fenetre fermable et re-dimensionnable
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		////// Pas redimensionnable
		this.setResizable(false);
		this.setSize(800, 600);
		this.setLocation((getToolkit().getScreenSize().width-this.getWidth())/3,(getToolkit().getScreenSize().height-this.getHeight())/3);
		////////////////////////
        
        //On cree un barre de menu (vide), puis on cree le 1er menu "Fichier",
        //dans lequel on ajoute l'item "Nouveau", ....
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        
        JMenu fichierMenu = new JMenu("Fichier");
        
        JMenuItem itemNouveau = new JMenuItem("Nouveau Jeu", 'N');
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
        JMenuItem itemJoin = new JMenuItem("Rejoindre une partie", 'R');
        itemJoin.addActionListener(this);
        fichierMenu.add(itemJoin);
        JMenuItem itemHost = new JMenuItem("Heberger une partie", 'H');
        itemHost.addActionListener(this);
        fichierMenu.add(itemHost);
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
        
		this.controleur = new Controleur(this);
		this.getControleur().initControleurIA();
		this.getControleur().getControleurIA().getControleurVirtuel().initControleurIA();
		
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
        
        this.rafraichir();
        

	}
	
	public FenetrePlateau getPlateau() {
		return plateau;
	}

	public void setPlateau(FenetrePlateau plateau) {
		this.plateau = plateau;
	}
	
	public void rafraichir() {
		this.getInfo().getTourDeJeu().setText(this.getControleur().getPartie().getJCourant().getNom()
				+ " - " + ((this.getControleur().getPartie().getJCourant().isHumain()) ? "humain" : "cpu"));
		
		if (this.getControleur().getPartie().getOnlineMode() > 0)
			this.commande.getPrevious().setEnabled(false);
		else
			this.info.getEnvoyerMSG().setEnabled(false);
		
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
		Object source = e.getActionCommand().toString();
		
		if (source == "Nouveau")
		{ FenetreOver fenetreOver = new FenetreOver("Fin de partie", this); }
		if (source == "Sauvegarder")
		{ 
			//FenetreSauvegarder fenetreSav = new FenetreSauvegarder("Sauvegarder");
			JFileChooser filechoose = new JFileChooser();
			// Cr�er un JFileChooser
			filechoose.setCurrentDirectory(new File(".")); 
			// Le r�pertoire source du JFileChooser est le r�pertoire d'o� est lanc� notre programme
			String approve = new String("Enregistrer");
			// Le bouton pour valider l'enregistrement portera la mention Enregistrer
			int resultatEnregistrer = filechoose.showDialog(filechoose, approve); 
			// Pour afficher le JFileChooser
			if (resultatEnregistrer == JFileChooser.APPROVE_OPTION) 
			// Si l'utilisateur clique sur le bouton Enregistrer
			{ 
				String monFichier= new String(filechoose.getSelectedFile().toString());
				// R�cup�rer le nom du fichier qu'il a sp�cifi�
				if(monFichier.endsWith(".txt") || monFichier.endsWith(".TXT")) 
				{;}
				// Si ce nom de fichier finit par .txt ou .TXT, ne rien faire et passer � la suite
				else 
				{
					monFichier = monFichier + ".txt" ;
				}
				// Sinon renommer le fichier pour qu'il porte l'extension .txt
				{ 
					try
					{ 
						FileWriter lu = new FileWriter(monFichier);
						// Cr�er un objet java.io.FileWriter avec comme argument le mon du fichier dans lequel enregsitrer
						BufferedWriter out = new BufferedWriter(lu);
						// Mettre le flux en tampon (en cache)
						
						String temp = this.getControleur().getPartie().toString();

						out.write(temp);

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
			// Cr�er un JFileChooser
			filechoose.setCurrentDirectory(new File("."));
			// Le r�pertoire source du JFileChooser est le r�pertoire d'o� est lanc� notre programme
			String approve = new String("Charger");
			// Le bouton pour valider l'enregistrement portera la mention Charger
			String monFichier= null; 
			// On ne sait pas pour l'instant quel sera le fichier � ouvrir
			int resultatOuvrir = filechoose.showDialog(filechoose, approve); 
			// Pour afficher le JFileChooser
			if(resultatOuvrir == filechoose.APPROVE_OPTION)
			// Si l'utilisateur clique sur le bouton OUVRIR
			{
			  	monFichier = filechoose.getSelectedFile().toString();
			  	// R�cup�rer le nom du fichier qu'il a sp�cifi�

			  	
			  	try
			  	{
		  			this.getControleur().getPartie().chargerParFichier(monFichier);
			  	} 
			  	catch (IOException ioe) 
			  	{
			  		System.out.println(ioe.getMessage());
			  	}
		   	}
		}
		if (source == "Options")
		{ 
			FenetreOption fenetreOpt = new FenetreOption("Options", this); 
		}
		if (source == "Rejoindre une partie")
		{ 
			FenetreConnexion fenetreConnex = new FenetreConnexion("Connexion", this); 
		}
		if (source == "Quitter")
		{ System.exit(0); }

		
	}

	public FenetreInfo getInfo() {
		return info;
	}

	public void setInfo(FenetreInfo info) {
		this.info = info;
	}

	public FenetreCommande getCommande() {
		return commande;
	}

	public void setCommande(FenetreCommande commande) {
		this.commande = commande;
	}
	
	
	

}
