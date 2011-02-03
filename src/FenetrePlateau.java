import java.awt.*;
import javax.swing.*;

public class FenetrePlateau extends JPanel{

	private JPanel	plateau;
	
	private int[][]		tabjeu;
	private int[]		tabCase;
	private int[]		tabCaseAlt;
	
	public FenetrePlateau()
	{
		plateau = new JPanel();
        plateau.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
		
		tabjeu = new int[11][19];
        
       /* for (int i=0;i<11;i++){//on parcours tout le tableau
        	for (int j=0;j<19;j++){
                JToggleButton jb = new JToggleButton();//on crée un JToggleButton
                jb.setName(String.valueOf(i) + String.valueOf(j));//on lui donne un nom en fonction de sa position (ce sera ses coordonnées
                jb.setText(jb.getName());
                if (isOut(i,j))
                { 
                	tabjeu[i][j]=8;
                	jb.setEnabled(false);
                }
                else 
                if (trou(i,j))
                {
                	tabjeu[i][j]=0;
                	jb.setBackground(java.awt.Color.LIGHT_GRAY);
                	jb.setEnabled(false);
                }
                else if(noire(i,j))
                {
                	tabjeu[i][j]=1;
                	jb.setBackground(java.awt.Color.BLACK);
                }
                else if(rouge(i,j))
                {
                	tabjeu[i][j]=2;
                	jb.setBackground(java.awt.Color.RED);
                }
                else
                {
                	tabjeu[i][j]=9;
                	jb.setBackground(java.awt.Color.GRAY);
                	jb.setEnabled(false);
                } 
                plateau.add(jb);
        	}
        }*/
        
		tabCase = new int[9];
		tabCaseAlt = new int[9];
		
		tabCase[0] = 5;
		tabCaseAlt[0] = 4;
		tabCase[1] = 6;
		tabCaseAlt[1] = 3;
		tabCase[2] = 7;
		tabCaseAlt[2] = 2;
		tabCase[3] = 8;
		tabCaseAlt[3] = 1;
		tabCase[4] = 9;
		tabCaseAlt[4] = 0;
		tabCase[5] = 8;
		tabCaseAlt[5] = 1;
		tabCase[6] = 7;
		tabCaseAlt[6] = 2;
		tabCase[7] = 6;
		tabCaseAlt[7] = 3;
		tabCase[8] = 5;
		tabCaseAlt[8] = 4;

        
        for (int i=0; i<9; i++)
        {
        	for (int j=0; j<tabCase[i]; j++)
        	{	
        		JToggleButton jb = new JToggleButton();//on crée un JToggleButton
        		jb.setName(String.valueOf(i) + String.valueOf(j));
        		jb.setText(jb.getName());
        		
        		donnerContrainte(c,tabCaseAlt[i]+(2*j),i,2,1,0,0);
        		plateau.add(jb,c);
        	}
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
