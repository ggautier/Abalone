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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FenetrePrincipale extends JFrame{

	private JPanel 		panel;
	private JMenuBar 	menuBar;
	private JMenu		fichierMenu;

	public FenetrePrincipale(String titre)
	{
		//H�ritage du builder de la super classe JFrame
		super(titre);
		
		//Variable propre � la fenetre ("fermable" et redimensionable)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        
        //On cree un barre de menu (vide), puis on cree le 1er menu "Fichier", dans lequel on ajoute l'item "Nouveau", ....
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        
        JMenu fichierMenu = new JMenu("Fichier");
        
        JMenuItem item = new JMenuItem("Nouveau");
        fichierMenu.add(item);
        item = new JMenuItem("Sauvegarder");
        fichierMenu.add(item);
        item = new JMenuItem("Charger");
        fichierMenu.add(item);
        item = new JMenuItem("Options");
        fichierMenu.add(item);
        item = new JMenuItem("Quitter");
        fichierMenu.add(item);
        menuBar.add(fichierMenu);
        
        
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

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
