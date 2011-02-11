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


public class FenetreOption extends JDialog implements ActionListener{
	
	
	private JPanel 					panel, sousPanJ1, sousPanJ2 , panelBoutons;
	private JButton					boutonOk;
	private JTextField  			textFieldJ2 ;
	private DefaultComboBoxModel	comboModel1, comboModel2;
	private JComboBox				choixCouleur1, choixCouleur2;
	private TitledBorder			title;
	private FenetrePrincipale		fenetre;
	
	
	public FenetreOption(String titre, JFrame fenetre)
	{	
	JDialog dialog = new JDialog();
	dialog.setSize(300, 200);
	dialog.setTitle(titre);
	dialog.setVisible(true);
	dialog.setAlwaysOnTop(true);
	dialog.setContentPane(buildContentPane());
	
	this.fenetre = (FenetrePrincipale)fenetre;

	}
	

	private JPanel buildContentPane(){
		
		comboModel1 = new DefaultComboBoxModel();
		comboModel1.addElement("Rouge");
		comboModel1.addElement("Bleu");
		comboModel1.addElement("Blanc");
		comboModel1.addElement("Noir");
		
		comboModel2 = new DefaultComboBoxModel();
		comboModel2.addElement("Rouge");
		comboModel2.addElement("Bleu");
		comboModel2.addElement("Blanc");
		comboModel2.addElement("Noir");
		
		choixCouleur1 = new JComboBox(comboModel1);
		choixCouleur1.setName("choixCouleur1");
		choixCouleur1.addActionListener(this);
		choixCouleur2 = new JComboBox(comboModel2);
		choixCouleur2.setName("choixCouleur2");
		choixCouleur2.addActionListener(this);
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    
	    donnerContrainte(c,0,0,1,1,0,0);
        this.sousPanJ1 = new JPanel();
        this.sousPanJ1.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder("Joueur1");
        this.sousPanJ1.setBorder(title);
        
        
        this.sousPanJ2 = new JPanel();
        this.sousPanJ2.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder("Joueur2");
        this.sousPanJ2.setBorder(title);
        
        this.panelBoutons = new JPanel();
        this.panelBoutons.setLayout(new GridBagLayout());

        /*
         * Placement des éléments dans le panel Joueur 1
         * 
         */
    	donnerContrainte(c,0,0,1,1,0,0);
		JTextField textFieldJ1 = new JTextField();
		textFieldJ1.setColumns(10);
		sousPanJ1.add(textFieldJ1,c);
		
		donnerContrainte(c,0,1,1,1,0,0);
		sousPanJ1.add(choixCouleur1,c);
		
		
        /*
         * Placement des éléments dans le panel Joueur 2
         * 
         */
		donnerContrainte(c,0,0,1,1,0,0);
		JTextField textFieldJ2 = new JTextField();
		textFieldJ2.setColumns(10);
		sousPanJ2.add(textFieldJ2,c);
		
		donnerContrainte(c,0,1,1,1,0,0);
		sousPanJ2.add(choixCouleur2,c);
		
	     /*
         * Placement des éléments dans le panel Boutons
         * 
         */
		
		donnerContrainte(c,0,0,1,1,0,0);
		JToggleButton boutonAnnuler = new JToggleButton();
		boutonAnnuler.addActionListener(this);
		boutonAnnuler.setText("Annuler");
		panelBoutons.add(boutonAnnuler,c);
		
		donnerContrainte(c,1,0,1,1,0,0);
		JToggleButton boutonOk = new JToggleButton();
		boutonOk.setText("OK");
		boutonOk.addActionListener(this);
		panelBoutons.add(boutonOk,c);

		
		
        donnerContrainte(c,0,0,1,1,100,20);
		panel.add(sousPanJ1,c);
		donnerContrainte(c,1,0,1,1,100,20);
		panel.add(sousPanJ2,c);
		donnerContrainte(c,0,1,1,1,100,20);
		panel.add(panelBoutons,c);


		return panel;
		
		/*
		if(choixCouleur1.getSelectedItem()==choixCouleur2.getSelectedItem()){
			boutonOk.disable();
		}
		else{
			boutonOk.enable();
		}
		*/
		
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

	/*
	 * Récupérer couleur sélectionner
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getActionCommand();
		JComboBox cb = (JComboBox)e.getSource();
        String nameColor = (String)cb.getSelectedItem();
        String nameCombo = cb.getName();

		System.out.println("source : "+source);
		System.out.println("couleur: "+nameCombo + " " + nameColor);
		
		if (source == "OK")
			{
				if(nameCombo == "choixCouleur1")
				{
					if(nameColor == "Rouge")
					{
						this.getFenetre().getControleur().getPartie().getJ1().setR(255);
						this.getFenetre().getControleur().getPartie().getJ1().setG(0);
						this.getFenetre().getControleur().getPartie().getJ1().setB(0);

					}
					if(nameColor == "Bleu")
					{
						this.getFenetre().getControleur().getPartie().getJ1().setR(0);
						this.getFenetre().getControleur().getPartie().getJ1().setG(0);
						this.getFenetre().getControleur().getPartie().getJ1().setB(255);
					}
					if(nameColor == "Blanc")
					{
						this.getFenetre().getControleur().getPartie().getJ1().setR(255);
						this.getFenetre().getControleur().getPartie().getJ1().setG(255);
						this.getFenetre().getControleur().getPartie().getJ1().setB(255);
					}
					if(nameColor == "Noir")
					{
						this.getFenetre().getControleur().getPartie().getJ1().setR(0);
						this.getFenetre().getControleur().getPartie().getJ1().setG(0);
						this.getFenetre().getControleur().getPartie().getJ1().setB(0);
					}
				}
				if(nameCombo == "choixCouleur2")
				{
					if(nameColor == "Rouge")
					{
						this.getFenetre().getControleur().getPartie().getJ2().setR(255);
						this.getFenetre().getControleur().getPartie().getJ2().setG(0);
						this.getFenetre().getControleur().getPartie().getJ2().setB(0);

					}
					if(nameColor == "Bleu")
					{
						this.getFenetre().getControleur().getPartie().getJ2().setR(0);
						this.getFenetre().getControleur().getPartie().getJ2().setG(0);
						this.getFenetre().getControleur().getPartie().getJ2().setB(255);
					}
					if(nameColor == "Blanc")
					{
						this.getFenetre().getControleur().getPartie().getJ2().setR(255);
						this.getFenetre().getControleur().getPartie().getJ2().setG(255);
						this.getFenetre().getControleur().getPartie().getJ2().setB(255);
					}
					if(nameColor == "Noir")
					{
						this.getFenetre().getControleur().getPartie().getJ2().setR(0);
						this.getFenetre().getControleur().getPartie().getJ2().setG(0);
						this.getFenetre().getControleur().getPartie().getJ2().setB(0);
					}
				}
			}
		if (source == "Annuler")
			{}
		}


	public FenetrePrincipale getFenetre() {
		return fenetre;
	}
	}
