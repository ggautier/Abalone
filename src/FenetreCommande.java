import java.awt.*;

import javax.swing.*;

public class FenetreCommande extends JPanel{
	
	private JPanel	commande, joueur1, joueur2, action;
	
	public FenetreCommande()
	{
		super();
		
		this.commande = new JPanel();
		this.commande.setLayout(new BorderLayout());
        
        this.joueur1 = new JPanel();
        this.joueur1.setLayout(new GridBagLayout());
        this.joueur2 = new JPanel();
        this.joueur2.setLayout(new GridBagLayout());
        this.action = new JPanel();
        this.action.setLayout(new GridBagLayout());
        
        this.joueur1.setBackground(Color.RED);
        this.joueur2.setBackground(Color.ORANGE);
        this.action.setBackground(Color.GREEN);

        this.joueur1.add(new JLabel("Salut les deg\n"));
        
        this.joueur2.add(new JLabel("Salut les deg\n"));

        this.action.add(new JLabel("Salut les deg\n"));

        
		this.add("NORTH",joueur1);
		this.add("CENTER",joueur2);
		this.add("SOUTH",action);
        
        this.setBackground(Color.BLACK);
	}
}
