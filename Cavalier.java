
/**
* Classe Cavalier permettant de trouver une solution au problème du cavalier
* @author Adonis NAJIMI, Imene AMALOU S3B
**/

public class Cavalier {
	// tableau final qui contiendra la solution au probleme, il represente egalement l'echiquier
	protected int[][] tab_solution; 
	//mouvements possibles pour le cavalier dans le sens trigonometrique
	protected int [] tab_mv_colonne={2,1,-1,-2,-2,-1,1,2};
	protected int [] tab_mv_ligne={-1,-2,-2,-1,1,2,2,1};
	protected int n; //taille echequier
	protected int i; //i : ligne actuelle
	protected int j; //j : colonne actuelle


	/**
	* Ce constructeur initialise et déclare le tableau des solutions. 
	* Il donne aussi la valeur 1 à la case de départ du cavalier.
	* @param nblignes nombre de lignes (et colonnes) du tableau
	* @param ligne_depart ligne sur laquelle le cavalier commence à bouger
	* @param colonne_depart colonne sur laquelle le cavalier commence à bouger
	**/
	public Cavalier(int nblignes,int ligne_depart,int colonne_depart) {
		n=nblignes;
		// tab_solution[i][j] i : ligne, j : colonne
		tab_solution=new int[nblignes][nblignes];

		//initialisation de toutes les cases du tableau de solutions à 0
		for(int i=0;i<tab_solution.length;i++)
			for(int j=0;j<tab_solution.length;j++)
				tab_solution[i][j]=0;

		tab_solution[ligne_depart][colonne_depart]=1;
		i=ligne_depart;
		j=colonne_depart;
	}


	/**
	* Methode permettant de resoudre le probleme du cavalier.
	* @return booleen permettant de savoir si la solution a ete trouvee
	**/
	public boolean chercherSolution() {
		/*
		* Nous avons choisi d'utiliser des attributs plutot que des parametres afin
		* d'eviter de devoir appeller le constructeur et la methode chercherSolution
		* avec les mêmes parametres "ligne_depart" et "colonne_depart"
		*/

		boolean infructueux;
		
		if (tab_solution[i][j]>=(n*n) ) {
			infructueux=false;
		}
		else {
			//initialisations 
			int k=0;
			//valeur du numéro de la case suivante en fonction de la case actuelle
			int num_case_suiv=0;

			//on incrémente de 1 et on obtient le numéro de la nouvelle case
			num_case_suiv=tab_solution[i][j]+1;


			infructueux=true;


			while(k<8 && infructueux)
			{	
				//choix du candidat : la case candidate
				int ligne_candidate=i+tab_mv_ligne[k];
				int colonne_candidate=j+tab_mv_colonne[k];

				//si le candidat est acceptable
				if ( ligne_candidate>=0 && ligne_candidate<n && colonne_candidate>=0 && colonne_candidate<n) {
					if (tab_solution[ligne_candidate][colonne_candidate]==0) {
						// si la place est disponible on enregistre le chemin 						
						tab_solution[ligne_candidate][colonne_candidate]=num_case_suiv;
						//sauvegarde de la ligne et de la colonne actuelle afin de les restituer apres l'appel recursif
						int ancien_i=i;
						int ancien_j=j;
						//changement de la ligne et de la colonne, la recursivite se fait ici
						i=ligne_candidate;
						j=colonne_candidate;
						infructueux=chercherSolution();
						//restitution de la ligne et de la colonne
						i=ancien_i;
						j=ancien_j;

						if (infructueux) {
							tab_solution[ligne_candidate][colonne_candidate]=0;
						}
					}
				}
				k=k+1; 	//si le candidat n'est pas acceptable on change de candidat

			}
		}
		return infructueux;
	}



	/** 
	* methode permettant d'imprimer le tableau du chemin parcouru par le cavalier
	* S'IL existe une solution au problème 
	* @param solutionTrouvee booleen permettant de verifier s'il existe une solution au problème du cavalier
	**/
	public void imprimeTableau(boolean solutionTrouvee) {
		StringBuffer sb = new StringBuffer();
		if (solutionTrouvee) {
			sb.append("\n");
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(tab_solution[i][j]<10)
						sb.append(" ");	//rajout d'un espace supplémentaire si le chiffre est inférieur à 10 comme demandé dans l'énoncé
					sb.append(tab_solution[i][j]+" ");
				}
				sb.append("\n"); //saut de ligne
			}
		}
		else {
			sb.append("Pas de solution \n");
		}

		System.out.println(sb.toString());
	}


	/**
	* Methode principale 
	**/
	public static void main(String[] args) {
		int taille=5;
		int ligne_depart=1;
		int colonne_depart=1;
		// test des arguments 
		try {
			if (args.length==3) {
				ligne_depart=Integer.parseInt(args[0])-1;
				colonne_depart=Integer.parseInt(args[1])-1;
				taille=Integer.parseInt(args[2]);
				Cavalier cv = new Cavalier(taille,ligne_depart,colonne_depart);
				cv.imprimeTableau(!cv.chercherSolution());
			}
			else 
				throw new Exception();
		} catch(Exception e) {
				System.err.println("ERREUR -> java Cavalier nombreEntier:Ligne nombreEntier:Colonne nombreEntier:TailleEchequier ");
			}
	}
}