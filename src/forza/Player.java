package forza;

/** Rappresenta il giocatore.
 * @author Federico Iltchev  
 */
public class Player {
	String nome;
	char colore;

	public Player(String name) {
		this.nome = name;
	}
	
	/**Ritorna il nome del Player
	 *@return nome una Stringa che contiene il nome del giocatore
	 */
	public String getNome() {
		return nome;
	}
	
	/***
	 * Ritorna il colore del Player
	 * @return colore un carattere che identifica il colore del giocatores 
	 */
	public char getColore() {
		return colore;
	}

	/***
	 * Permette di modificare il colore del Player
	 * @param colore un carattere contenente il colore del giocatore
	 */
	void setColore(char colore) {
		this.colore = colore;
	}
	
	
}
