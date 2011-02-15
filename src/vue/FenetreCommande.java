package vue;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class FenetreCommande extends JPanel{
	
	private JPanel			joueur1, joueur2, action;
	private JLabel 			nomJoueur1, nomJoueur2, billeJoueur1, billeJoueur2;
	private TitledBorder	title;
	private JButton			previous, next, hint;
	protected FenetrePrincipale fenetre;
	
	public FenetreCommande(FenetrePrincipale fen)
	{
		super();
		this.fenetre = fen;

		//D�claration du layout de la partie commande
		this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        //D�claration des diff�rents sous-parties de commande, GridBagLayout
        this.joueur1 = new JPanel();
        this.joueur1.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder("Joueur1");
        this.joueur1.setBorder(title);
        this.joueur2 = new JPanel();
        this.joueur2.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder("Joueur2");
        this.joueur2.setBorder(title);
        this.action = new JPanel();
        this.action.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder("Action");
        this.action.setBorder(title);
        
        billeJoueur1 = new JLabel(Integer.toString(fenetre.getControleur().getPartie().getJ1().getScore()));
        billeJoueur2 = new JLabel(Integer.toString(fenetre.getControleur().getPartie().getJ2().getScore()));
        
        nomJoueur1 = new JLabel("Joueur1");
        nomJoueur2 = new JLabel("Joueur2");
        
        previous = new JButton("Cancel");
        next = new JButton("Next");
        hint = new JButton("Conseil");

        donnerContrainte(c,0,0,2,1,100,100);
        this.joueur1.add(nomJoueur1,c);
        donnerContrainte(c,0,1,1,1,100,100);
        this.joueur1.add(new JLabel("Score : "),c);
        donnerContrainte(c,1,1,1,1,100,100);
        this.joueur1.add(billeJoueur1,c);
        
        donnerContrainte(c,0,0,2,1,100,100);
        this.joueur2.add(nomJoueur2,c);
        donnerContrainte(c,0,1,1,1,100,100);
        this.joueur2.add(new JLabel("Score : "),c);
        donnerContrainte(c,1,1,1,1,100,100);
        this.joueur2.add(billeJoueur2,c);
        
        donnerContrainte(c,0,0,1,1,0,0);
        this.action.add(previous,c);
        donnerContrainte(c,1,0,1,1,0,0);
        this.action.add(next,c);
        donnerContrainte(c,0,1,2,1,0,0);
        this.action.add(hint,c);
     

        donnerContrainte(c,0,0,1,1,100,20);
		this.add(joueur1,c);
		donnerContrainte(c,0,1,1,1,100,20);
		this.add(joueur2,c);
		donnerContrainte(c,0,2,1,1,100,80);
		this.add(action,c);
                
	}
	
	public void repaint() {
		if (fenetre != null) {
		billeJoueur1.setText(Integer.toString(fenetre.getControleur().getPartie().getJ1().getScore()));
		billeJoueur2.setText(Integer.toString(fenetre.getControleur().getPartie().getJ2().getScore()));
		}
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
