package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


public class FenetreOver extends JDialog implements ActionListener{
	
	private FenetrePrincipale		fenetre;
	private JDialog					dialog;
	private JPanel 					panel;
	private JLabel					text;
	private JButton					playAgain, quit;
	
	public FenetreOver(String titre, FenetrePrincipale fenetre)
	{	
	
		this.fenetre = fenetre;

		dialog = new JDialog();
		dialog.setSize(300, 200);
		dialog.setTitle(titre);
		dialog.setVisible(true);
		dialog.setAlwaysOnTop(true);
		dialog.setContentPane(buildContentPane());
	
	}
	

	private JPanel buildContentPane(){
		
		this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
		
        text = new JLabel(this.fenetre.getControleur().getPartie().getJCourant().getNom());
        
        donnerContrainte(c,0,0,2,1,0,0);
        panel.add(text,c);
        
        playAgain = new JButton();
        playAgain.setText("Rejouer");
        quit = new JButton();
		quit.setText("Quitter");
        
        donnerContrainte(c,0,1,1,1,0,0);
        panel.add(text,c);
        donnerContrainte(c,1,1,1,1,0,0);
        panel.add(text,c);

        
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

		
	}
}
