package vue;
import java.awt.*;

import javax.swing.*;
/**
 * <b>FenetreCommande est la classe qui affichera le futur t'chat et il affiche le tour du joueur</b>
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

public class FenetreInfo extends JPanel{
	
	private FenetrePrincipale	fenetre;
	private JPanel		texte, misc;
	private JTextArea 	zoneTexte;
	private JLabel		tourDeJeu;
	
	public FenetreInfo(FenetrePrincipale fen)
	{
		super();
		
		this.fenetre = fen;
		
		this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        this.texte = new JPanel();
        this.texte.setLayout(new GridBagLayout());
        this.misc = new JPanel();
        this.misc.setLayout(new GridBagLayout());

        
        zoneTexte = new JTextArea();
        zoneTexte.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(zoneTexte);
        
        donnerContrainte(c,0,0,1,1,100,100);
		this.texte.add(scrollPane,c);
		
		//this.misc.setBackground(Color.BLACK);
		tourDeJeu = new JLabel();
		//this.getFenetre().getControleur().getPartie().getJCourant().getNom();
		JLabel labelTour = new JLabel("Au tour de ");
		this.tourDeJeu.setText(this.getFenetre().getControleur().getPartie().getJCourant().getNom() + " - " + ((this.getFenetre().getControleur().getPartie().getJCourant().isHumain()) ? "humain" : "cpu")
				+"["+(this.getFenetre().getControleur().getPartie().aMonTour() ? "Vous" : "Adversaire")+"]");
		
        donnerContrainte(c,0,0,1,1,0,0);
		this.misc.add(labelTour,c);
		
        donnerContrainte(c,1,0,1,1,0,0);
		this.misc.add(tourDeJeu,c);
		
		donnerContrainte(c,0,0,1,1,70,100);
		this.add(texte,c);
		donnerContrainte(c,1,0,1,1,30,100);
		this.add(misc,c);
	}
	
	public FenetrePrincipale getFenetre() {
		return fenetre;
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

	public JTextArea getZoneTexte() {
		return zoneTexte;
	}

	public void setZoneTexte(JTextArea zoneTexte) {
		this.zoneTexte = zoneTexte;
	}

	public JLabel getTourDeJeu() {
		return tourDeJeu;
	}

	public void setTourDeJeu(JLabel tourDeJeu) {
		this.tourDeJeu = tourDeJeu;
	}
	
	

}
