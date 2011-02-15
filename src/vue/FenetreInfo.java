package vue;
import java.awt.*;

import javax.swing.*;

public class FenetreInfo extends JPanel{
	
	private JPanel		texte, misc;
	private JTextArea 	zoneTexte;
	
	public FenetreInfo()
	{
		super();
		
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
		
		donnerContrainte(c,0,0,1,1,75,100);
		this.add(texte,c);
		donnerContrainte(c,1,0,1,1,25,100);
		this.add(misc,c);
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
