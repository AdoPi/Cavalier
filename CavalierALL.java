/**
* Classe CavalierALL permettant de trouver toutes les solutions au probleme du cavalier
* Nous avons choisi de faire heriter cette classe de Cavalier car elle a un fonctionnement
* similaire a la classe Cavalier, ce qui permet d'eviter du code trop repetitif
* @author Adonis NAJIMI, Imene AMALOU S3B
**/

public class CavalierALL extends Cavalier{

	protected int compteur; // compteur de solutions

	/**
	* Ce constructeur appelle le constructeur de Cavalier
	* Il permet donc d'avoir acces a toutes les initalisations necessaires
	* De plus, il initialise le nombre de solutions trouvees "compteur" a 0
	* @param nblignes nombre de lignes (et colonnes) du tableau
	* @param ligne_depart ligne sur laquelle le cavalier commence à bouger
	* @param colonne_depart colonne sur laquelle le cavalier commence à bouger
	**/
	public CavalierALL(int taille, int ligne_depart, int colonne_depart) {	
		super(taille,ligne_depart,colonne_depart);
		compteur=0;
	}

	/**
	* Methode permettant de chercher toutes les solutions au probleme du cavalier
	* a chaque solution trouvee, le compteur representant le nombre de solutions est incremente
	**/
	public void chercherToutesSolutions() {
		
		if (tab_solution[i][j]>=(n*n) ) {
			compteur++;
		}
		else {
			//initialisations 

			//valeur du numéro de la case suivante en fonction de la case actuelle
			int num_case_suiv=0;
			//si on est au départ la case actuelle vaut 1 et la suivante vaut 2
			
			//on incrémente de 1 et on obtient le numéro de la nouvelle case
			num_case_suiv=tab_solution[i][j]+1;

			//parcours de tous les candidats
			for(int k=0;k<8;k++)
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
						chercherToutesSolutions();
						//restitution de la ligne et de la colonne
						i=ancien_i;
						j=ancien_j;
						//effacer solution=> supprimer case ajoutee !!!! on s'en fout de si ça fonctionne ou pas 
						tab_solution[ligne_candidate][colonne_candidate]=0;
					}
				}
			}
		}
	}

	/** 
	* methode affichant le nombre de solutions posssibles pour un echiquier 
	**/
	public void imprimeNbSolutions() {
		System.out.println(compteur);
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
				CavalierALL cv = new CavalierALL(taille,ligne_depart,colonne_depart);
				cv.chercherToutesSolutions();
				cv.imprimeNbSolutions();
			}
			else 
				throw new Exception();
		} catch(Exception e) {
				System.err.println("ERREUR -> java Cavalier nombreEntier:Ligne nombreEntier:Colonne nombreEntier:TailleEchequier ");
		}
	}
}