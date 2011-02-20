package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


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

	// Action effectu� au clique du boutton annuler
	// le bouton OK a son propre listener qui prend la fenetre en param
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if (source.equals(this.playAgain))
			try {
				this.fenetre.getControleur().getPartie().chargerParFichier("./data/plateau/defaut.plt");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		else if (source.equals(this.quit)) 
			this.fenetre.dispose(); // Un truc pour quitter ici.
		
		this.fenetre.rafraichir();
		this.dispose();

		
	}
}
