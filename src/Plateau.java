import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * <b>Plateau est la classe representant la totalite des cases du plateau de jeu.</b>
 * <p>
 * Un plateau est caracterise par les billes qu'ils contient.
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
	private Bille[][] plateau;
	
	/**
	 * Constructeur de la classe PLateau
	 * 
	 * @param nomFicConfig Le fichier d'initialisation du plateau
	 */
	public Plateau(String nomFicConfig, Joueur joueur1, Joueur joueur2) {
		this.plateau = new Bille[9][9];
		this.init(nomFicConfig, joueur1, joueur2);
		this.afficher();
	}
	
	/**
	 * Accesseur des billes
	 * 
	 * @param ligne La ligne sur laquelle se trouve la bille a recuperer
	 * @param colonne La colonne sur laquelle se trouve la bille a recuperer
	 * 
	 * @return La bille aux coordonnees passees en parametres
	 * 
	 * @see Bille
	 */
	public Bille getBille(int ligne, int colonne) {
		return this.plateau[ligne][colonne];
	}
	
	/**
	 * Mutateur des billes
	 * 
	 * @param ligne La ligne sur laquelle se trouve la bille a modifier.
	 * @param colonne La colonne sur laquelle se trouve la bille a modifier.
	 * @param newBille La nouvelle bille.
	 * 
	 * @see Bille
	 */
	private void setBille(int ligne, int colonne, Bille newBille) {
		this.plateau[ligne][colonne] = newBille;
	}
	
	/**remplir
	 * Remplit le plateau a partir d'un fichier de configuration
	 * 
	 * @param fichierConf Chemin du fichier de configuration.
	 * 
	 * @return
	 * 		<ul>
	 * 			<li>true si le remplissage du plateau reussit</li>
	 * 			<li>false sinon</li>
	 * 		</ul>
	 */
	public boolean init(String fichierConf, Joueur joueur1, Joueur joueur2) {
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(fichierConf));
			
			int nbLignes = 0;
			String ligne;
			Stack<String> lignes = new Stack<String>();
			
			// LECTURE DE LA DECLARATION DU PLATEAU
			// (9 PREMIERES LIGNES)
			
			while(nbLignes < 9) {
				ligne = buffer.readLine();
				
				if(ligne.isEmpty())	// Les lignes vides sont ignorees.
					continue;
				
				lignes.push(ligne);	// Stockage des lignes dans une pile
				
				nbLignes++;
			}
			
			buffer.close();
			
			StringTokenizer stk;
			
			int nbBilles;
			nbLignes = 0;
			
			while((lignes.isEmpty() == false) && (nbLignes < 9)) {
				
				String bille;
				nbBilles = 0;
				stk = new StringTokenizer(lignes.pop());
				
				while(stk.hasMoreTokens()) {
					bille = stk.nextToken();
					
					if(bille.equals("0"))
						this.setBille(nbLignes, nbBilles, new Bille(nbLignes, nbBilles, joueur1));
					
					else if(bille.equals("1"))
						this.setBille(nbLignes, nbBilles, new Bille(nbLignes, nbBilles, joueur2));
					
					nbBilles++;	// nombre de bille lues pour la ligne courante
				}
				
				nbLignes++; // nombre de lignes lues
			}
		}
		
		catch (FileNotFoundException e) {
			System.out.println("Erreur : fichier de plateau \"" + fichierConf + "\" introuvable");
			return false;
		}
		
		catch (IOException e) {
			System.out.println("Erreur lors de la lecture du fichier \"" + fichierConf + "\"");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Teste si une case est vide.
	 * 
	 * @param ligne La ligne de la case a tester
	 * @param colonne La colonne de la classe a tester
	 * 
	 * @return
	 * 		<ul>
	 * 			<li>true si la case aux coordonnees passees en paramet9re est vide</li>
	 * 			<li>false sinon</li>
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
	
	public void afficher() {
		int nbBilles = 0;
		
		for(int i = 0 ; i < 9 ; i++)
		{
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
			
			for(int j = 0 ; j < nbBilles ; j++)
				System.out.print(plateau[i][j] + " ");
			
			System.out.println();
		}
	}
}
