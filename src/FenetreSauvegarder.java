/**
 *
 * @see FenetrePrincipale
 * 
 * @author Lenogue Matthieu
 * @author Gautier Quentin
 * @author Gautier Gaetan
 * @author Ouary Maxime
 * 
 * @version 1.0
 */

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;


public class FenetreSauvegarder extends JDialog {
	
	private JPanel 			panel;
	private JPanel			sousPan;
	private JButton			bouton1;
	private JTextField  	textFieldJ2 ;
	private JFileChooser	fileChooser;
	
	public FenetreSauvegarder(String titre)
	{	
	JDialog dialog = new JDialog();
	dialog.setSize(500, 350);
	dialog.setResizable(false);
	dialog.setTitle(titre);
	dialog.setVisible(true);
	dialog.setContentPane(buildContentPane());
	}
	
	private JPanel buildContentPane(){
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    JFileChooser filechoose = new JFileChooser();
		// Créer un JFileChooser
		filechoose.setCurrentDirectory(new File(".")); 
		// Le répertoire source du JFileChooser est le répertoire d’où est lancé notre programme
		String approve = new String("ENREGISTRER");
		// Le bouton pour valider l’enregistrement portera la mention ENREGSITRER
		int resultatEnregistrer = filechoose.showDialog(filechoose, approve); 
		// Pour afficher le JFileChooser…
		if (resultatEnregistrer == JFileChooser.APPROVE_OPTION) 
		// Si l’utilisateur clique sur le bouton ENREGSITRER
		{ 
			String monFichier= new String(filechoose.getSelectedFile().toString());
			// Récupérer le nom du fichier qu’il a spécifié
			if(monFichier.endsWith(".txt") || monFichier.endsWith(".TXT")) 
			{;}
			// Si ce nom de fichier finit par .txt ou .TXT, ne rien faire et passer à la suite
			else 
			{
				monFichier = monFichier + ".txt" ;
			}
			// Sinon renommer le fichier pour qu’il porte l’extension .txt
			{ 
				try
				{ 
					FileWriter lu = new FileWriter(monFichier);
					// Créer un objet java.io.FileWriter avec comme argument le mon du fichier dans lequel enregsitrer
					BufferedWriter out = new BufferedWriter(lu);
					// Mettre le flux en tampon (en cache)
					// Besoin de la représentation console pour un fichier texte.
					//out.write(textArea.getText()); 
					// Balancer dans le flux le contenu de la zone de texte
					out.close(); 
					// Fermer le flux (c’est toujours mieux de le fermer explicitement)
				} 
				catch (IOException er) 
				{;}
			}
		}
	    
	    donnerContrainte(c,0,0,1,1,100,100);
		JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
		panel.add(fileChooser,c);
	    
		return panel;
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
	
}