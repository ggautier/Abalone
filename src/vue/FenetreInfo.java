package vue;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Calendar;

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

public class FenetreInfo extends JPanel implements ActionListener {
	
	private FenetrePrincipale	fenetre;
	private JPanel		texte, misc;
	private JTextArea 	zoneChat;
	private JTextField	zoneMSG;
	private JButton		envoyerMSG;
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

        
        zoneChat = new JTextArea();
        zoneChat.setEditable(false);
        
        zoneMSG = new JTextField();
        
        envoyerMSG = new JButton("Envoyer");
        envoyerMSG.addActionListener(this);
        envoyerMSG.setMnemonic(KeyEvent.VK_ENTER);
        
        JScrollPane scrollPane = new JScrollPane(zoneChat);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        
        donnerContrainte(c,0,0,2,1,7,6);
		this.texte.add(scrollPane,c);
		
        donnerContrainte(c,0,1,1,1,5,1);
		this.texte.add(zoneMSG,c);
		
        donnerContrainte(c,1,1,1,1,2,1);
		this.texte.add(envoyerMSG,c);
		
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

	public JTextArea getZoneChat() {
		return zoneChat;
	}

	public void setZoneChat(JTextArea zoneChat) {
		this.zoneChat = zoneChat;
	}

	public JLabel getTourDeJeu() {
		return tourDeJeu;
	}

	public void setTourDeJeu(JLabel tourDeJeu) {
		this.tourDeJeu = tourDeJeu;
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getActionCommand().toString();

		System.out.println(source);
		if(source == "Envoyer" && !this.zoneMSG.getText().equals(""))	{
			this.message(this.fenetre.getControleur().getPartie().getJoueurPhysique().getNom()+" : "+this.zoneMSG.getText());
			if (this.getFenetre().getControleur().getPartie().getOnlineMode() > 0) {
				try {
					this.getFenetre().getControleur().getConnexion().envoyer_msg("\n"+
							this.fenetre.getControleur().getPartie().getJoueurPhysique().getNom()+" :"+
							this.zoneMSG.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			this.zoneMSG.setText("");
			this.getFenetre().rafraichir();
		}
	}

	public JButton getEnvoyerMSG() {
		return envoyerMSG;
	}

	public void setEnvoyerMSG(JButton envoyerMSG) {
		this.envoyerMSG = envoyerMSG;
	}
	
	public void message(String message) {
		Calendar cal = Calendar.getInstance();
		
		this.zoneChat.setText(this.zoneChat.getText()+"\n["
				+cal.get(Calendar.HOUR_OF_DAY)+":"
				+cal.get(Calendar.MINUTE)+":"
				+cal.get(Calendar.SECOND)+"] "
				+message);
	}

}
