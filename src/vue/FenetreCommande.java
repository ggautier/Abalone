package vue;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import controleur.Controleur;

import modele.Joueur;
/**
 * <b>FenetreCommande est la classe qui affichera le futur t'chat.</b>
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
public class FenetreCommande extends JPanel implements ActionListener {
	/**
	 *  panneaux permettant de placer les différents éléments sur la FenetreCommande
	 *  
	 */
	private JPanel			joueur1, joueur2, action;
	/**
	 * Labels pour afficher les noms des joueurs et leur scores
	 */
	private JLabel 			nomJoueur1, nomJoueur2, billeJoueur1, billeJoueur2;
	/**
	 *  Titre pour les différents pannels 
	 */
	private TitledBorder	title;
	/**
	 * 	Boutons pour les actions
	 */
	private JButton			previous, unselect, hint;
	
	protected JLabel		labelTempsCoupJ1, labelTempsGlobalJ1, labelTempsCoupJ2, labelTempsGlobalJ2;

	
	/**
	 *  fenetre source	
	 * 
	 * @see FenetrePrincipale
	 */
	protected FenetrePrincipale fenetre;
	
	/**
	 * Constructeur de la fenetre principale 
	 * 
	 * @param fen : nom de la fenetre
	 */
	public FenetreCommande(FenetrePrincipale fen)
	{
		super();
		this.fenetre = fen;

		//Dï¿½claration du layout de la partie commande
		this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        //Dï¿½claration des diffï¿½rents sous-parties de commande, GridBagLayout
        this.joueur1 = new JPanel();
        this.joueur1.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder("Joueur 1");
        this.joueur1.setBorder(title);
        this.joueur2 = new JPanel();
        this.joueur2.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder("Joueur 2");
        this.joueur2.setBorder(title);
        this.action = new JPanel();
        this.action.setLayout(new GridBagLayout());
        title = BorderFactory.createTitledBorder("Action");
        this.action.setBorder(title);
        
        billeJoueur1 = new JLabel(Integer.toString(fenetre.getControleur().getPartie().getJ1().getScore()));
        billeJoueur2 = new JLabel(Integer.toString(fenetre.getControleur().getPartie().getJ2().getScore()));
        
        nomJoueur1 = new JLabel(this.fenetre.getControleur().getPartie().getJ1().getNom());
        nomJoueur2 = new JLabel(this.fenetre.getControleur().getPartie().getJ2().getNom());
        
        labelTempsGlobalJ1 = new JLabel(this.fenetre.getControleur().getPartie().getJ1().getTempsRestantGlobal());
        labelTempsGlobalJ2 = new JLabel(this.fenetre.getControleur().getPartie().getJ2().getTempsRestantGlobal());
        
        previous = new JButton("Cancel");
        previous.addActionListener(this);
                
        unselect = new JButton("Unselect");
        unselect.addActionListener(this);

        hint = new JButton("Conseil");
        hint.addActionListener(this);


        donnerContrainte(c,0,0,2,1,100,100);
        this.joueur1.add(nomJoueur1,c);
        donnerContrainte(c,0,1,2,1,100,100);
        this.joueur1.add(labelTempsGlobalJ1,c);
        donnerContrainte(c,0,2,1,1,100,100);
        this.joueur1.add(new JLabel("Score : "),c);
        donnerContrainte(c,1,2,1,1,100,100);
        this.joueur1.add(billeJoueur1,c);
        
        donnerContrainte(c,0,0,2,1,100,100);
        this.joueur2.add(nomJoueur2,c);
        donnerContrainte(c,0,1,2,1,100,100);
        this.joueur2.add(labelTempsGlobalJ2,c);
        donnerContrainte(c,0,2,1,1,100,100);
        this.joueur2.add(new JLabel("Score : "),c);
        donnerContrainte(c,1,2,1,1,100,100);
        this.joueur2.add(billeJoueur2,c);
        
        donnerContrainte(c,0,0,1,1,0,0);
        this.action.add(previous,c);
        donnerContrainte(c,1,0,1,1,0,0);
        this.action.add(unselect,c);
        donnerContrainte(c,0,1,2,1,0,0);
        this.action.add(hint,c);
     

        donnerContrainte(c,0,0,1,1,100,20);
		this.add(joueur1,c);
		donnerContrainte(c,0,1,1,1,100,20);
		this.add(joueur2,c);
		donnerContrainte(c,0,2,1,1,100,80);
		this.add(action,c);
                
	}
	
	/**
	 * Affiche score des joueurs
	 * 
	 * @see Controleur
	 */
	public void repaint() {
		if (fenetre != null) {
			billeJoueur1.setText(Integer.toString(fenetre.getControleur().getPartie().getJ1().getScore()));
			billeJoueur2.setText(Integer.toString(fenetre.getControleur().getPartie().getJ2().getScore()));
			
			labelTempsGlobalJ1.setText(this.fenetre.getControleur().getPartie().getJ1().getTempsRestantGlobal());
			labelTempsGlobalJ2.setText(this.fenetre.getControleur().getPartie().getJ2().getTempsRestantGlobal());
		}
	}
	
	/**
	 * Contraintes de placement dans la fenetre
	 * 
	 * @param gbc : ensemble des contraintes
	 * @param gx : contraintes sur x
	 * @param gy : contraintes sur y
	 * @param gw : contraintes sur la largeur
	 * @param gh : contraintes sur la hauteur
	 * @param wx : taille en % sur x
	 * @param wy : taille en % sur y
	 */
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

	/**
	 * Actif dès l'action de l'utilisateur sur un widget.
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getActionCommand().toString();

		System.out.println(source);
		if(source == "Cancel")
		{	
			try 
			{
				this.fenetre.getControleur().getPartie().quickLoad();
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
		if(source == "Unselect")
		{	
			this.fenetre.getControleur().deselectionner();
		}
		if(source == "Conseil")
		{	
			/*
			 * A faire 
			 * */
		}
	}
	/**
	 * retourne le nom du joueur 1
	 * 
	 * @return nom du joueur 1
	 */
	public JLabel getNomJoueur1() {
		return nomJoueur1;
	}

	/**
	 * retourne le nom du joueur 2
	 * 
	 * @return nom du joueur 2
	 */
	public JLabel getNomJoueur2() {
		return nomJoueur2;
	}

	public JButton getPrevious() {
		return previous;
	}

	public void setPrevious(JButton previous) {
		this.previous = previous;
	}


	public String getLabelTempsCoupJ1() {
		return labelTempsCoupJ1.getText();
	}


	public void setLabelTempsCoupJ1(String labelTempsCoupJ1) {
		this.labelTempsCoupJ1.setText(labelTempsCoupJ1);
	}


	public String getLabelTempsGlobalJ1() {
		return labelTempsGlobalJ1.getText();
	}


	public void setLabelTempsGlobalJ1(String labelTempsGlobalJ1) {
		this.labelTempsGlobalJ1.setText(labelTempsGlobalJ1);
		
	}


	public String getLabelTempsCoupJ2() {
		return labelTempsCoupJ2.getText();
	}


	public void setLabelTempsCoupJ2(String labelTempsCoupJ2) {
		this.labelTempsCoupJ2.setText(labelTempsCoupJ2);
	}


	public String getLabelTempsGlobalJ2() {
		return labelTempsGlobalJ2.getText();
	}


	public void setLabelTempsGlobalJ2(String labelTempsGlobalJ2) {
		this.labelTempsGlobalJ2.setText(labelTempsGlobalJ2);
	}
	
}
