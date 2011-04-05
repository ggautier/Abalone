package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
/**
 * <b>FenetreOver est la classe qui affiche la fin de partie avec le nom du vainqueur
 * ainsi que la proposition de recommencer.</b>
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

public class FenetreOver extends JDialog implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FenetrePrincipale		fenetre;
	private JPanel 					panel;
	private JLabel					text;
	private JButton					playAgain, quit;
	
	public FenetreOver(String titre, FenetrePrincipale fenetre)
	{	
	
		this.fenetre = fenetre;

		this.setSize(300, 200);
		this.setTitle(titre);
	    this.setLocationRelativeTo(fenetre);
	    this.setLocation((int)fenetre.getLocation().getX()+200,(int)fenetre.getLocation().getY()+100);
	    this.setVisible(true);
	    this.setAlwaysOnTop(true);
	    this.setContentPane(buildContentPane());
	
	}
	

	private JPanel buildContentPane(){
		
		this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();

		
        //text = new JLabel(this.fenetre.getControleur().getPartie().getJCourant().getNom());
        text = new JLabel("Voulez-vous rejouer ?");
        
        donnerContrainte(c,0,0,2,1,0,0);
        panel.add(text,c);
        
        playAgain = new JButton();
        playAgain.setText("Rejouer");
        quit = new JButton();
		quit.setText("Quitter");
        
        donnerContrainte(c,0,1,1,1,0,0);
        panel.add(playAgain,c);
        donnerContrainte(c,1,1,1,1,0,0);
        panel.add(quit,c);
        
        playAgain.addActionListener(this);
        quit.addActionListener(this);
        
        
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
		
		Object source = e.getSource();
		this.fenetre.rafraichir();

		if (source.equals(this.playAgain)) {
			this.fenetre.getControleur().lancerPartie(this.fenetre.getControleur().getPartie().getOnlineMode());
			this.dispose();
		}
		else if (source.equals(this.quit)) 
			System.exit(0); 
		

	}
}
