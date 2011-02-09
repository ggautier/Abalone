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

import javax.swing.*;


public class FenetreOption extends JDialog {
	
	private JPanel 		panel;
	private JPanel		sousPan;
	
	private JTextField textFieldJ2 ;
	
	public FenetreOption(String titre)
	{	
	JDialog dialog = new JDialog();
	dialog.setSize(300, 200);
	dialog.setTitle("Options");
	dialog.setVisible(true);
	
	panel = new JPanel();
	
	GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    
    donnerContrainte(c,0,0,1,1,100,100);
	JTextField textFieldJ1 = new JTextField();
	textFieldJ1.setColumns(10);
	panel.add(textFieldJ1,c);
	
	donnerContrainte(c,1,0,1,1,100,100);
	JTextField textFieldJ2 = new JTextField();
	textFieldJ2.setColumns(10);
	panel.add(textFieldJ2,c);
	
	donnerContrainte(c,0,0,1,1,100,100);
	JLabel label = new JLabel("test");
	panel.add(label,c);
	
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