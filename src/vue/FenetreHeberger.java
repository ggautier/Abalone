package vue;
/**
 * <b>FenetreOption est la classe permettant l'affichage d'une fenetre subalterne de modification des options de jeu.</b>
 * <p>
 * Une FenetreOption est caractérisee par les informations suivantes :
 * <ul>
 * <li>Une fenetrePrincipale, qui va solliciter l'activation de la fenetre d'options.</li>
 * </ul>
 * </p>
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class FenetreHeberger extends JDialog implements ActionListener{
	
	private FenetrePrincipale		fenetre;
	private JDialog					dialog;
	private JPanel 					panel, sousPanIpPort, sousPanPort , sousPanProxy, panelBoutons;
	private TitledBorder			title;
	private JTextField 				textFieldPort;
	protected JLabel				port;
	
	public FenetreHeberger(String titre, FenetrePrincipale fenetre) {	
		this.fenetre = fenetre;
		dialog = new JDialog();
		dialog.setSize(300, 150);
		dialog.setTitle(titre);
		dialog.setVisible(true);
		dialog.setAlwaysOnTop(true);
		
		dialog.setContentPane(buildContentPane());
	
	}
	
	private JPanel buildContentPane() {
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    
        this.sousPanIpPort = new JPanel();
        this.sousPanIpPort.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder("Heberger une partie");
        this.sousPanIpPort.setBorder(title);

        
        this.panelBoutons = new JPanel();
        this.panelBoutons.setLayout(new GridBagLayout());


				
		donnerContrainte(c,0,0,1,1,0,0);
		sousPanIpPort.add(new JLabel("Port d'ecoute : "), c);
		
		donnerContrainte(c,1,0,1,1,0,0);
		textFieldPort = new JTextField();
		textFieldPort.setColumns(10);
		textFieldPort.setText(fenetre.getControleur().getOptions().getPortEcoute()+"");
		sousPanIpPort.add(textFieldPort,c);
		
		
	     /*
         * Placement des éléments dans le panel Boutons
         * 
         */
		
		donnerContrainte(c,0,0,1,1,0,0);
		JButton boutonAnnuler = new JButton();
		boutonAnnuler.addActionListener(this);
		boutonAnnuler.setText("Annuler");
		panelBoutons.add(boutonAnnuler,c);
		
		donnerContrainte(c,1,0,1,1,0,0);
		JButton boutonOk = new JButton();
		boutonOk.setText("OK");
		boutonOk.addActionListener(this);
		panelBoutons.add(boutonOk,c);

		
		
        donnerContrainte(c,0,0,1,1,0,0);
		panel.add(sousPanIpPort,c);
		donnerContrainte(c,0,1,1,1,0,0);
		panel.add(panelBoutons,c);


		return panel;
		
	}	

	void donnerContrainte(GridBagConstraints gbc, int gx, int gy, int gw, int gh, int wx, int wy) {
		gbc.gridx=gx;
		gbc.gridy=gy;
		gbc.gridwidth=gw;
		gbc.gridheight=gh;
		gbc.weightx=wx;
		gbc.weighty=wy;
		gbc.fill=GridBagConstraints.BOTH;
	}

	// Action effectué au clique du boutton annuler
	// le bouton OK a son propre listener qui prend la fenetre en param
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getActionCommand();
		
		if(source == "OK") {
			fenetre.getControleur().getOptions().setIP("localhost");
			fenetre.getControleur().getOptions().setPortEcoute(Integer.decode(textFieldPort.getText()));
			
			fenetre.getControleur().lancerPartie(2);
			this.dialog.dispose();
		}
		if(source == "Annuler") {
			this.dialog.dispose();
		}
	}
	
	
}
