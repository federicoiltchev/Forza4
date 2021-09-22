package forza;

/***
 * Rappresenta una Griglia, cioè un array di char, piattaforma del gioco
 * @author Federico Iltchev
 */
public class Griglia {
		final static int MAX_COL = 7;	//per gestire le colonne piene
		final static int MAX_ROW = 6;	//per gestire le righe piene

		char matr[][];   
		
		/**
		 * Crea un oggetto di tipo Griglia, con una matrice piena di spazi al suo interno
		 */
		public Griglia() {
			this.matr = new char[MAX_ROW][MAX_COL];
			//crea matrice piena di spazi vuoti
			for  (int row = 0; row < MAX_ROW; row++){
				for  (int col = 0; col < MAX_COL; col++){
					matr[row][col] = ' ';
				}
			}
		}
		
		/** Consente di accedere all'array di char, e sostituirlo con uno diverso
		 * @param matr un array di char che contiene una matrice
		 */
		public void setMatr(char[][] matr) {
			this.matr = matr;
		}
		
		/**
		 * Inserisci elemento nella griglia, solo per colonna
		 * @param ncol un intero che corrisponde al numero di colonna
		 * @param player un elementp di tipo char che corrisponde al
		 * colore del giocatore(R o B)
		 */
		public void inserisci(int ncol, char player) {	
			for  (int row = matr.length-1; row >= 0; row--){
				if(matr[row][ncol] == ' '){
					matr[row][ncol] = player;
					break;
				}
			}
		}
		
		/** Ritorna l'array di char, la piattaforma di gioco
		 * @return l'attuale Griglia
		 */
		public char[][] getMatr() {
			return matr;
		}
		
		/** Consente di stampare a video l'attributo dell'oggettio Griglia 
		 */
		public void printGriglia() {
			System.out.println();
			//System.out.println(" 0 1 2 3 4 5 6");
			//System.out.println("<------------->");
			//System.out.println("---------------");
			for (int row = 0; row < MAX_ROW; row++) {
				System.out.print("|");
				for (int col = 0; col < MAX_COL; col++) {
					System.out.print(this.matr[row][col]);
					System.out.print("|");
				}
				System.out.println();
				System.out.println("---------------");
			}
			System.out.println("<------------->");
			System.out.println(" 1 2 3 4 5 6 7");
			System.out.println();
			
		}
		
		/** Controlla che il numero di colonna inserita in input sia corretto
		 * @param col un intero che rappresenta il numero di colonna
		 * @param matr un array di char che rappresenta la griglia
		 * @return var un booleano che torna vero se l'input è corretto, falso negli altri casi
		 */
		public boolean controllaColonne (int col, char[][] matr){
			boolean var;
			//esiste la colonna
			if (col < 0 || col >= MAX_COL){
				var = false;
				return var;
			}	
			//colonna piena
			if (matr[0][col] != ' '){
				var = false;
				return var;
			}
			var = true;
			return var;
		} 
		
		/** Controlla che ci siano 4 pedine dello stesso colore vicine
		 * @param player un dato di tipo char che identifica il giocatore(R o B)
		 * @param matr un array di char che rappresenta la griglia
		 * @return var un booleano che torna true se c'è un vincitore, false altrimenti
		 */
		public static boolean vinceQualcuno(char player, char[][] matr){
			boolean var = false;
			//controlla a destra
			for (int row = 0; row<MAX_ROW; row++){
				for (int col = 0;col < MAX_COL - 3;col++){
					if (matr[row][col] == player   && matr[row][col+1] == player &&matr[row][col+2] == player &&matr[row][col+3] == player){
						var = true;
						return var;
					}
				}			
			}
			//controlla giù
			for (int row = 0; row < MAX_ROW - 3; row++){
				for (int col = 0; col < MAX_COL; col++){
					if (matr[row][col] == player && matr[row+1][col] == player &&matr[row+2][col] == player &&matr[row+3][col] == player){
						var = true;
						return var;
					}
				}
			}
			//controlla diagonale su
			for (int row = 3; row < MAX_ROW; row++) {
				for (int col = 0; col < MAX_COL - 3; col++) {
					if (matr[row][col] == player && matr[row-1][col+1] == player &&matr[row-2][col+2] == player &&matr[row-3][col+3] == player){
						var = true;
						return var;
					}
				}
			}
			//controlla diagonale giu
			for (int row = 0; row < MAX_ROW - 3; row++) {
				for (int col = 0; col < MAX_COL - 3; col++) {
					if (matr[row][col] == player && matr[row+1][col+1] == player &&matr[row+2][col+2] == player &&matr[row+3][col+3] == player){
						var = true;
						return var;
					}
				}
			}
			var = false;
			return var;
		}
		
		/** Permette di svuotare un oggetto Griglia
		 * delle pedine che si accumulano sulla Griglia
		 * @param matrix un array di char che rappresenta la griglia
		 * @return matrix un array di char che corrisponde alla matrice 
		 * composta solo di spazi vuoti
		 */
		public char[][] svuotaGriglia(char[][] matrix) {
			for (int i=0;i<MAX_ROW;i++) {
				for (int j=0;j<MAX_COL; j++) {
					matrix[i][j] = ' ';
				}
			}
			return matrix;
		}
		
		/** Permette di svuotare un oggetto Griglia
		 * delle pedine che si accumulano sulla Griglia
		 * @return matrix un array di char che corrisponde alla matrice 
		 * composta solo di spazi vuoti
		 */
		public char[][] svuotaGriglia() {
			for (int i=0;i<MAX_ROW;i++) {
				for (int j=0;j<MAX_COL; j++) {
					this.matr[i][j] = ' ';
				}
			}
			return this.matr;
		}
		
}