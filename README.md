# Forza 4

# Contenuti del File
---------------------

 * Introduzione
 * Requisiti
 * Installation
 * Funzionamento

## Introduzione

Si tratta di un gioco di allineamento, su una matrice di 6 righe e 7 colonne.
L'obiettivo è mettere in fila (orizzontale, verticale o diagonale) 4 delle proprie pedine.

## Requisiti

- Importa il [JDK](https://www.oracle.com/it/java/technologies/javase/javase-jdk8-downloads.html) adatto al tuo sistema operativo per far eseguire il file _Forza4.jar_

Installa la versione del JDK adatta al tuo sistema operativo e avvia il terminale.

## Installazione

Una volta scaricato il JDK, recati con il comando cd all'interno della cartella in cui hai salvato il file Jar. 
Se il file è salvato nella cartella "Desktop" per esempio:
```sh
cd Desktop
```

A questo punto chiamiamo il compiler java e eseguiamo il file jar, in modo da far partire l'esecuzione del gioco.

```sh
java -jar Forza4.jar
```
A questo punto dovrebbe uscire il messaggio:

```sh
"Inserire il nome del Giocatore1: "
```
che attesta la corretta installazione del file JDK e il corretto avvio del gioco.

## Funzionamento
Per prima cosa il programma chiede i nomi di entrambi i giocatori.
```sh
"Inserire il nome del Giocatore1: "
```
Una volta inseriti, chiede se si vuole iniziare una partita nuova, oppure continuare una partita sospesa.
Nel primo caso verrà subito stampata a video la Griglia.
In questo caso inserire un intero che rappresenti la colonna in cui inserire la pedina.
```sh
| | | | | | | |
---------------
| | | | | | | |
---------------
| | | | | | | |
---------------
| | | | | | | |
---------------
| | | | | | | |
---------------
| | | | | | | |
---------------
<------------->
 1 2 3 4 5 6 7

nome Gioc, colore Gioc.colore, scegli una colonna o digita il codice '1234' per sospenderla: 
```
Nel caso in cui si voglia continuare una partita sospesa, verrà invece richiesto il nome della partita. In tal caso sarà necessario appunto inserire il nome del file dato alla partita, SENZA estensione.
```sh
Quale partita vuoi riprendere?
 Inserire il nome SENZA estensione: 
```
Una volta che entrambi i giocatore avranno effettuato un mossa, verrà data la possibilità di sospendere la partita. In tal caso inserire il nome che si vuole dare, facendo attenzione a non usare nomi già utilizzati in precedenza.
In caso il salvataggio vada a buon fine, verrà stampato un messaggio di conferma, e verrà richiesto se si vuole continuare a giocare.
```sh
Quale nome vuoi dare alla partita? 
nomeFile
Partita salvata con nome: nomeFile.fdf
Vuoi continuare a giocare? 
```
Al termine della partita verrà nuovamente richiesto se si vuole continuare o meno a giocare.
Le partite sospese possono essere recuperate solo a inizio esecuzione. Se si sospende una partita, bisognerà terminare l'esecuzione e rieseguire il jar per continuare quella partita.
