package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * <b>Plateau est la classe representant la totalite des cases du plateau de jeu.</b>
 * <p>
 * Un plateau est caracterise par les billes qu'il contient.
 * </ul>
 * </p>
 * 
 * @see Bille
 * 
 * @author Lenogue Matthieu
 * @author Gautier Quentin
 * @author Gautier Gaetan
 * @author Ouary Maxime
 * 
 * @version 1.0
 */

public class Plateau {
	
	/**
	 * Le plateau en tant que tel.
	 */
	protected Bille[][] plateau;
	
	protected Partie partie; // A supprimer
	
	/**
	 * Constructeur de la classe PLateau
	 * 
	 * @param nomFicConfig Le fichier d'initialisation du plateau
	 * @param joueur1 : le premier joueur de la partie
	 * @param joueur2 : le second joueur de la partie
	 * @throws Exception 
	 */
	public Plateau(String nomFicConfig, Joueur joueur1, Joueur joueur2) throws Exception {
		this.plateau = new Bille[9][9];
		this.init(nomFicConfig, joueur1, joueur2);
	}
	
	/**
	 * Renvoie une bille placee sur le plateau.
	 * 
	 * @param ligne La ligne sur laquelle se trouve la bille a recuperer
	 * @param colonne La colonne sur laquelle se trouve la bille a recuperer
	 * 
	 * @return Une bille du plateau
	 * 
	 * @see Bille
	 */
	public Bille getBille(int ligne, int colonne) {
		return this.plateau[ligne][colonne];
	}
	
	/**
	 * Modifie une bille du plateau.
	 * 
	 * @param ligne La ligne sur laquelle se trouve la bille a modifier.
	 * @param colonne La colonne sur laquelle se trouve la bille a modifier.
	 * @param newBille La nouvelle bille.
	 * 
	 * @see Bille
	 */
	public void setBille(int ligne, int colonne, Bille newBille) {
		//if (!this.getPartie().getControleur().isOut(ligne,colonne)) {
			this.plateau[ligne][colonne] = newBille;
			if (newBille != null) {
				newBille.setX(ligne);
				newBille.setY(colonne);
			}
		//}
		//System.out.println("Placement d'une bille en "+ligne+"-"+colonne);
	}
	
	
	/**
	 * Remplit le plateau a partir d'un fichier de configuration.
	 * 
	 * @param fichierConf Chemin du fichier de configuration.
	 * 
	 * @return
	 * 		<ul>
	 * 			<li>True si le remplissage du plateau reussit,</li>
	 * 			<li>False sinon.</li>
	 * 		</ul>
	 * 
	 * @throws IOException
	 */
	public boolean init(String fichierConf, Joueur joueur1, Joueur joueur2) throws IOException {
		BufferedReader buffer = new BufferedReader(new FileReader(fichierConf));
		
		int numLigne = 0;
		
		String ligne;
		LinkedList<String> lignes = new LinkedList<String>();
		
		// LECTURE DE LA DECLARATION DU PLATEAU
		// (9 PREMIERES LIGNES)
		
		while(numLigne < 9) {
			ligne = buffer.readLine();
			
			if((ligne == null) || (ligne.isEmpty()))
				return false;
			
			lignes.add(ligne);
			
			numLigne++;
		}
		
		buffer.close();
		
		if(numLigne != 9)
			return false;
		
		numLigne = 0;
		
		while((lignes.isEmpty() == false) && (numLigne < 9)) {
			ligne = lignes.removeFirst();
			
			if(ligne.length() != 9)
				return false;
			
			for(int numColonne = 0 ; numColonne < ligne.length() ; numColonne++){
				if(ligne.charAt(numColonne) == '-')
					this.setBille(numLigne, numColonne, new Bille(numLigne, numColonne, joueur1));
				else if(ligne.charAt(numColonne) == '+')
					this.setBille(numLigne, numColonne, new Bille(numLigne, numColonne, joueur2));
				else
					this.setBille(numLigne, numColonne, null);
			}
			
			numLigne++;
		}
		
		if(numLigne == 9)
			return true;
		else
			return false;
	}
	
	// Temporaire, pour les tests (desole de foutre la zone)
	public boolean remplir() {
		for(int i=0; i < 9; i++)
			for(int j=0; j < 9; j++)
				setBille(i,j,new Bille(i, j, null));
		
		
		return true;
	}
	
	
	public String toString() {
		String str = "";
		for(int i=0; i < 9; i++) {
			for(int j=0; j < 9; j++)
				if (caseVide(i,j))
					str+="o";
				else
					str+= getBille(i,j).toString();
			str+="\n";
		}
		return str;
	}
	
	
	/**
	 * Teste si une case est vide.
	 * 
	 * @param ligne : la ligne de la case a tester
	 * @param colonne : la colonne de la classe a tester
	 * 
	 * @return
	 * 		<ul>
	 * 			<li>True si la case est vide,</li>
	 * 			<li>False sinon.</li>
	 * 		</ul>
	 */
	public boolean caseVide(int ligne, int colonne) {
		try {
			return this.plateau[ligne][colonne] == null;
		}
		
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Erreur lors du test de vacuite : coordonnees hors plateau");
			return false;
		}
	}

	public Bille[][] getPlateau() {
		return plateau;
	}

	public void setPlateau(Bille[][] plateau) {
		this.plateau = plateau;
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}
	
	/*
	public void afficher() {
		int nbBilles = 0;
		int test = 0;
		
		for(int i = 0 ; i < 9 ; i++) {
			switch(i) {
				case 0 :
					nbBilles = 5; break;
				case 1 :
					nbBilles = 6; break;
				case 2 :
					nbBilles = 7; break;
				case 3 :
					nbBilles = 8; break;
				case 4 :
					nbBilles = 9; break;
				case 5 :
					nbBilles = 8; break;
				case 6 :
					nbBilles = 7; break;
				case 7 :
					nbBilles = 6; break;
				case 8 :
					nbBilles = 5;
			}
			


			for (int b=0; b<9-nbBilles; b++)
				System.out.print("  ");
			
			for(int j = 0 ; j < nbBilles ; j++) {
				if (i > 4)
					test = j + i - 4;
				else
					test = j;
						
				if (plateau[i][test] != null) {
					if (partie.getControleur().isSelectionnee(plateau[i][test]))
						System.out.print("["+plateau[i][test]+"]" + " ");
					else if (partie.getControleur().isVisee(plateau[i][test]))
						System.out.print("{"+plateau[i][test]+"}" + " ");
					else
						System.out.print("("+plateau[i][test]+")" + " ");			
				}
				else
					System.out.print("(o) ");
			}
			
			System.out.println();
		}
	}
	*/
}
