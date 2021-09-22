package forza;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/** Rappresenta la partita, ovvero il campo in cui si svolge l'azione
 * @author Federico Iltchev
 */
public class Gioco {
	public static void main(String[] args) throws IOException {
		/*  Creo la piattaforma del gioco */
		
		Gestore eseguibile = new  Gestore();
		Griglia griglia1 = new Griglia();
		eseguibile.setOggetto(griglia1);
		
		/* Definisco Giocatore 1  */
		System.out.println("Inserire il nome del Giocatore 1: ");
		Scanner scan1 = new Scanner(System.in);
		String s = scan1.next();
		Player Gioc1 = new Player(s);
		
		/* Definisco Giocatore 2 */
		System.out.println("Inserire il nome del Giocatore 2: ");
		Scanner scan2 = new Scanner(System.in);
		String s1 = scan2.next();
		Player Gioc2 = new Player(s1);
		
		char playerColour = 'R';
		Gioc1.setColore(playerColour);
		Gioc2.setColore('B');
		boolean winner = false;		
		
		/* chiedo se il giocatore vuole iniziare una partita o continuarne una vecchia */
		System.out.println("Inserire 1 per cominciare una nuova partita, 0 per continuarne una sospesa: ");
		boolean controllaDecisione = false;
		int decisorePartita;
		do {
			decisorePartita = eseguibile.controllaInput();
			if( (decisorePartita<0) || (decisorePartita>1) ) {
				System.out.println("Oops! Sembra che tu abbia inserita un valore diverso da 0 o 1!");
			} else {
				controllaDecisione = true;
			}
		}while(!controllaDecisione);
		
		int turn = 0;			
		if (decisorePartita == 0) {
			/* chiamo metodo per scorrere partita vecchia */
			eseguibile.caricaPartita();	//crea oggetto Gestore e fai in modo che Riprendi ci si applichi
			}	
		else if (decisorePartita == 1) {
			turn = 1;
			
		}	
		boolean finish = false;
			while (winner == false && turn <= 43 && finish == false) {
				boolean controllore = false;
				int play;
				do {
					turn = eseguibile.gestisceTurni(griglia1.getMatr());
	
					griglia1.printGriglia();
					
					/* gestisce i turni */
					if (turn%2==0) {
						playerColour = Gioc2.getColore();
						System.out.print(Gioc2.getNome() + ", colore " + Gioc2.getColore() + ", scegli una colonna o digita il codice '1234' per sospenderla: ");
					} else {
						playerColour = Gioc1.getColore();
						System.out.print(Gioc1.getNome() + ", colore " + Gioc1.getColore() + ", scegli una colonna o digita il codice '1234' per sospenderla: ");
					}
					play = eseguibile.controllaInput();
					
					play = eseguibile.gestisciPlay(play, turn, griglia1.getMatr(), finish);
					/* TEST
					if (play == 9){
						continue;
					}*/
					controllore = griglia1.controllaColonne(play,griglia1.getMatr());
				} while (controllore == false);
				griglia1.inserisci(play, playerColour);
				
				/* controlla se ha vinto qualcuno */
				winner = Griglia.vinceQualcuno(playerColour,griglia1.getMatr());
				turn++;	
				
				//TEST
				if (winner == true) {
					eseguibile.proclamoVincitore(playerColour, Gioc1,Gioc2);
					finish = eseguibile.decidiFine(finish);
					if(finish == false) {
						winner = false;
						turn =1;
					}
				}
				
				if (turn == 43 && winner == false) {
					eseguibile.getOggetto().printGriglia();
					System.out.println("Pareggio!!");
					finish = eseguibile.decidiFine(finish);
					if(finish == false) {
						turn = 1;
					}
				}
				
			} 
			eseguibile.getOggetto().printGriglia();
			}	
}

