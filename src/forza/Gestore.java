package forza;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** Rappresenta il Gestore delle logiche del gioco
 * @author Federico Iltchev
 */
public class Gestore {
		Griglia oggetto;
		
		/***
		 * Crea un oggetto di tipo Gestore
		 */
		public Gestore() {
			this.oggetto = new Griglia();
		}
	
		/** 
		 * @return la griglia che rappresenta la piattaforma del gioco
		 */
		public Griglia getOggetto() {
			return oggetto;
		}
	
		/** 
		 * Da la possibilità di sostituire la griglia.
		 * @param oggetto è un oggetto di tipo Griglia che contiene la matrice
		 */
		public void setOggetto(Griglia oggetto) {
			this.oggetto = oggetto;
		}
		
		/**
		 * Scorre la matrice e mi dice, contando quante 'B' e quante 'R' ci sono,
		 *il turno a cui siamo
		 *@param matr è un array di char che contiene la matrice
		 *@return il turno successivo
		 */
		public int gestisceTurni(char[][] matr) {
			int turno = 0;
			for (int i=0; i<matr.length;i++) {
				for (int j=0; j<matr[0].length;j++) {
					if((matr[i][j] == 'B') || (matr[i][j] == 'R')) {
						turno++;
					}
				}
			}
			turno++;
			return turno;
		}
		
		/***
		 * Controlla che l'input sia un intero, e non qualsiasi altro tipo
		 * @return play un intero che contiene il numero di colonna
		 */
		public int controllaInput() {
			int play;
			Scanner in1 = new Scanner(System.in);
			do {
				try {
					String z = in1.nextLine();
					play = Integer.parseInt(z);
					break;
		
				} catch (Exception e) {
					System.out.println("Oops! Sembra che tu abbia inserito un valore diverso da un intero. Riprova con un intero!");
				}
			} while (true);
			return play;
		}
		
		/**
		 * Controllare se esiste un file con il nome che gli viene dato in input
		 *@param s una stringa che contiene il nome che vorremmo dare al file
		 *@return exist true se esiste, false se non esiste
		 */
		public boolean checkFileExists(String s) {
			File tempFile = new File(s + ".fdf");
			boolean exist = tempFile.exists();
			return exist;				//Se true file esiste, else non esiste
			}
		
		/**
		 * Scrive la matrice nel file che diamo in input
		 *@param filename nome del file su cui scriveremo la matrice
		 *@param matrix un array di array di char che contiene la matrice
		 */
		public static void writeMatrix(File filename, char[][] matrix) {
		    try {
		        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		        for (int i = 0; i < matrix.length; i++) {
		            for (int j = 0; j < matrix[i].length; j++) {
	                    bw.write(matrix[i][j] + ",");
		            }
			        bw.newLine();
		        }
		        bw.flush();
		        bw.close();
		    } catch (IOException e) {}
		}
			
		/**
		 * Importa la matrice da un file e la da in output
		 * @param filename un File che contiene la matrice
		 * @throws FileNotFoundException se il file non viene trovato nella directory
		 * @return myArray un array di char con la partita corrispondente al filename
		 */
		public char[][] recuperaMatr(File filename) throws FileNotFoundException{
			Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
			int rows = 6; int cols = 7;
			char[][] myArray = new char[rows][cols];				//creo la matrice di array
			int VirgolCount = 0;
			while(sc.hasNextLine()) {							//controllo che ci sia una riga successiva nel file
				for (int i=0; i<rows; i++) {						
		            String line = sc.nextLine(); //line è un indirizzo di memoria
		            //a questo punto ho una una stringa con una riga di matrice senza virgole
		           for ( int j=0; j<line.length(); j++) {        	   
		        	    if(line.charAt(j) == ',') {
		        	    	VirgolCount++;
		        	    	continue;
		        	    }
		        	    myArray[i][j-VirgolCount] = line.charAt(j);		//comando per scorrere la stringa e prendere ogni singolo valore
		           }
		           VirgolCount = 0;
		         }
		      }
		   return myArray;   
		}
	
		/**
		 * Salva la partita in un file fdf
		 *@param m un oggetto di tipo Griglia che contiene un array di char
		 */
		public void sospendi(Griglia m){
			Scanner in = new Scanner(System.in);
			boolean Cont = false;
			System.out.println("Quale nome vuoi dare alla partita? ");
			while(Cont == false) {
				String answer = in.next();
				boolean Available = checkFileExists(answer);
				if (Available) {
					System.out.println("Esiste una partita con questo nome, inserisci un altro nome");
				} else {
					File nuovo = new File(answer + ".fdf"); //prende il nome come parametro
					writeMatrix(nuovo, m.matr);	
					System.out.println("Partita salvata con nome: " + nuovo);
					Cont = true;
				}
			}
		}

		/** 
		 * Estrae la matrice, un array di char, dal file che viene dato in input
		 * @throws FileNotFoundException se il file non viene trovato nella directory
		 * con lo stesso nome nella directory
		 */
		public void caricaPartita() throws FileNotFoundException {
			char[][] myArray = null;
			Scanner in = new Scanner(System.in);
			boolean Cont = false;
			System.out.println("Quale partita vuoi riprendere?\n Inserire il nome SENZA estensione: ");
			while(Cont == false) {
				String s = in.nextLine();
				boolean exist = checkFileExists(s);
				//se il file esiste
				if (exist) {
					File tempFile = new File(s + ".fdf");
					myArray = this.recuperaMatr(tempFile);
					Cont = true;
				} else {
					System.out.println("Non esiste un partita con questo nome!\n RITENTA");
				}
			}
			oggetto.setMatr(myArray);
			this.setOggetto(oggetto);
		}
		
		/**
		 * Gestisce il dato intero dato in input che viene 
		 * inserito durante l'esecuzione del gioco, 
		 * @param play un intero che rappresenta il valore inserito dall'utante
		 * @param turn un intero che rappresenta il turno in cui si trova la partita
		 * @param griglia un array di array di char che contiene la matrice
		 * @param finish un booleano di cui ho bisogno per chiamare il metodo decidiFine
		 * @return play un intero che corripsonde al numero di colonna in cui inserire la pedina
		 */
		public int gestisciPlay(int play, int turn, char[][] griglia, boolean finish) {
			play--;
			if (play < 0) {
				System.out.println("\nNumero Troppo piccolo! Il numero minimo di colonne è 1! RITENTA!");
			} else if (play == 1233) {	
				/*caso sospensione partita solo se entrambi hanno fatto almeno una mossa */
				if (turn>2) {
					Scanner scan4 = new Scanner(System.in);	this.sospendi(this.getOggetto());
					this.decidiFine(finish);
				} else {
					System.out.println("Spiacenti, per poter salvare la partita entrambi i giocatori devono aver effettuato almeno una mossa!");
				}
			} else if (play > 6) { 				
				System.out.println("\nNumero Troppo grande! Il numero massimo di colonne è 7! RITENTA!");
			} else if ((this.getOggetto().getMatr()[0][play] == (' ')) == false) {
				System.out.println("\nLa colonna inserita è piena! RITENTA!");
			} 			
		return play;
		}
		
		/**
		 * Gestisce la possibilità di cominciare una nuova partita
		 * @param finish un booleano che viene usato per rappresentare
		 * la volontà di continuare la partita
		 * @return finish un booleano che tornerò false nel caso in cui si voglia continuare la partita
		 */
		public boolean decidiFine(boolean finish) {
			Scanner scan5 = new Scanner(System.in);
			System.out.println("Vuoi continuare a giocare? ");
			String decisione = scan5.next();
			if ((decisione.equals("SI")) || decisione.equals("Si") || decisione.equals("Sì") || decisione.equals("si") || decisione.equals("sì")) {
				//crea nuova partita
				this.getOggetto().svuotaGriglia();
			} else {
					//fine gioco
					System.out.println("Alla prossima!");
					System.exit(0);
			}
			return finish;
		}
		
		/**
		 * Proclama il vincitore della Partita
		 * @param playerColour un char che rappresenta il colore del giocatore
		 * @param Gioc1 un oggetto di tipo Giocatore che rappresenta il primo giocatore
		 * @param Gioc2 un oggetto di tipo giocatore che rappresenta il secondo giocatore
		 */
		public void proclamoVincitore(char playerColour, Player Gioc1, Player Gioc2) {
				if (playerColour=='R'){
					System.out.println(Gioc1.getNome() + " HA VINTO!!!!");
				} else {
					System.out.println(Gioc2.getNome() + " HA VINTO!!!!");
				}
		}	
}
