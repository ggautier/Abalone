package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerOption implements ActionListener {

private FenetreOption fenetre;
	
	public ActionListenerOption(FenetreOption f){
		this.fenetre=f;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
				
		String couleurJ1 = new String(this.fenetre.getChoixCouleur1().getSelectedItem().toString());
		String couleurJ2 = new String(this.fenetre.getChoixCouleur2().getSelectedItem().toString());
		
		//String nomJ1 = new String(this.fenetre.getTextFieldJ1().getText().toString());
		
		System.out.println(this.fenetre.getTextFieldJ1().getText());
		
		//System.out.println(nomJ1);
	}
	

}
