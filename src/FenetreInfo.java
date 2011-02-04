import java.awt.*;

import javax.swing.*;

public class FenetreInfo extends JPanel{
	
	private JPanel	info;
	
	public FenetreInfo()
	{
		super();
		
		info = new JPanel();
		info.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        this.setBackground((Color.BLUE));
	}

}
