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

import javax.swing.*;


public class FenetreSauvegarder extends JDialog {
	
	private JPanel 		panel;
	private JPanel		sousPan;
	private JButton		bouton1;
	private JTextField  textFieldJ2 ;
	
	public FenetreSauvegarder(String titre)
	{	
	JDialog dialog = new JDialog();
	dialog.setSize(300, 200);
	dialog.setTitle("Options");
	dialog.setVisible(true);
	dialog.setContentPane(buildContentPane());
	}
	
	private JPanel buildContentPane(){
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    donnerContrainte(c,0,0,1,1,0,0);
		JLabel label = new JLabel("Joueur 1");
		panel.add(label);
		
	    donnerContrainte(c,0,1,1,1,0,0);
		JTextField textFieldJ1 = new JTextField();
		textFieldJ1.setColumns(10);
		panel.add(textFieldJ1,c);
		
		donnerContrainte(c,1,0,1,1,0,0);
		JLabel label2 = new JLabel("Joueur 2");
		panel.add(label2);
		
		donnerContrainte(c,1,1,1,1,0,0);
		JTextField textFieldJ2 = new JTextField();
		textFieldJ2.setColumns(10);
		panel.add(textFieldJ2,c);
		
		donnerContrainte(c,4,2,1,1,0,0);
		JToggleButton boutonOk = new JToggleButton();
		boutonOk.setName("OK");
		boutonOk.setText("OK");
		panel.add(boutonOk,c);

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