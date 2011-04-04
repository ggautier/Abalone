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


public class FenetreConnexion extends JDialog implements ActionListener{
	
	private FenetrePrincipale		fenetre;
	private JDialog					dialog;
	private JPanel 					panel, sousPanIpPort, sousPanPort , sousPanProxy, panelBoutons;
	private JComboBox				choixCouleur1, choixCouleur2;
	private TitledBorder			title;
	private JTextField 				textFieldIp, textFieldPort1, TextFieldPort2, TextFieldProxy, TextFieldPort3;
	protected JLabel				port, ip, proxy, labelTempsCoupJ1, labelTempsGlobalJ1, labelTempsCoupJ2, labelTempsGlobalJ2;
	
	public FenetreConnexion(String titre, FenetrePrincipale fenetre)
	{	
		this.fenetre = fenetre;
		dialog = new JDialog();
		dialog.setSize(350, 275);
		dialog.setTitle(titre);
		dialog.setVisible(true);
		dialog.setAlwaysOnTop(true);
		
		dialog.setContentPane(buildContentPane());
	

	}
	

	private JPanel buildContentPane(){
		
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    
        this.sousPanIpPort = new JPanel();
        this.sousPanIpPort.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder("Connexion");
        this.sousPanIpPort.setBorder(title);
        
        
        this.sousPanPort = new JPanel();
        this.sousPanPort.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder("Connexion locale");
        this.sousPanPort.setBorder(title);
        
        this.sousPanProxy = new JPanel();
        this.sousPanProxy.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder("Connexion Proxy");
        this.sousPanProxy.setBorder(title);
        
        this.panelBoutons = new JPanel();
        this.panelBoutons.setLayout(new GridBagLayout());

    	ip = new JLabel("IP :");
    	port = new JLabel("Port :");
    	proxy = new JLabel("Proxy :");
        
        /*
         * Placement des éléments dans le panel Connexion
         * 
         */
    	donnerContrainte(c,0,0,1,1,100,100);
    	sousPanIpPort.add(ip,c);
    	
    	donnerContrainte(c,1,0,1,1,0,0);
		textFieldIp = new JTextField();
		textFieldIp.setColumns(15);
		sousPanIpPort.add(textFieldIp,c);
				
		donnerContrainte(c,0,1,1,1,0,0);
		sousPanIpPort.add(port,c);
		
		donnerContrainte(c,1,1,1,1,0,0);
		textFieldPort1 = new JTextField();
		textFieldPort1.setColumns(10);
		sousPanIpPort.add(textFieldPort1,c);
		
		
        /*
         * Placement des éléments dans le panel Connexion locale
         * 
         */
		
		donnerContrainte(c,0,0,1,1,0,0);
		sousPanPort.add(port,c);
		
		donnerContrainte(c,1,0,1,1,0,0);
		TextFieldPort2 = new JTextField();
		TextFieldPort2.setColumns(10);
		sousPanPort.add(TextFieldPort2,c);
		
		/*
         * Placement des éléments dans le panel Connexion Proxy
         * 
         */
		
		donnerContrainte(c,0,0,1,1,0,0);
    	sousPanProxy.add(proxy,c);
    	
    	donnerContrainte(c,1,0,1,1,0,0);
    	TextFieldProxy = new JTextField();
    	TextFieldProxy.setColumns(15);
		sousPanProxy.add(TextFieldProxy,c);
				
		donnerContrainte(c,0,1,1,1,0,0);
		sousPanProxy.add(port,c);
		
		donnerContrainte(c,1,1,1,1,0,0);
		TextFieldPort3 = new JTextField();
		TextFieldPort3.setColumns(10);
		sousPanProxy.add(TextFieldPort3,c);
		
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
		panel.add(sousPanPort,c);
		donnerContrainte(c,0,2,1,1,0,0);
		panel.add(sousPanProxy,c);
		donnerContrainte(c,0,3,1,1,0,0);
		panel.add(panelBoutons,c);


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

	// Action effectué au clique du boutton annuler
	// le bouton OK a son propre listener qui prend la fenetre en param
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getActionCommand();
		
		if(source == "OK")
		{
			
			this.dialog.dispose();
		}
		if(source == "Annuler")
		{
			this.dialog.dispose();

		}
	}
}
