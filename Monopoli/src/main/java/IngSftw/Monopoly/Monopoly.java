/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package IngSftw.Monopoly;


import java.util.*;

public class Monopoly {
	
    private final int MONEY_START=1500;
    private final int MONEY_VIA=200;
    private final int CAUZIONE_PRIGIONE=50;
    public int numero_giocatori;
    private int currPlayer;
    private Dadi dice;
    private Tabellone board;
    private UI ui;
    private MazzoProbabilita mazzoProbabilita;
    private MazzoImprevisti mazzoImprevisti;
    private boolean gameOver;
    private boolean turnFinished;
    
    ArrayList<Player> players = new ArrayList<Player>();
    
    //crea nuova partita
    public Monopoly(int numero_giocatori, String[] nomi){
    	//crea i giocatori e assegna loro i soldi iniziali, crea il tabllone, che inizializza a sua vola le caselle
    	//crea i mazzi di probabilità e imprevisti
    	Player newPlayer;
    	this.numero_giocatori=numero_giocatori;
    	for(int i=0; i<numero_giocatori; i++) {
    		newPlayer= new Player(i, nomi[i], MONEY_START, false, 0);
    		players.add(newPlayer);
    	}
    	dice=new Dadi();
    	board=new Tabellone(dice);
    	mazzoImprevisti=new MazzoImprevisti();
    	mazzoProbabilita=new MazzoProbabilita();
    	
    	//decidi il primo giocatore
    	Random random=new Random();
    	currPlayer=random.nextInt(numero_giocatori-1);
    }

    public final int getMONEY_START() {
        return MONEY_START;
    }

    public final int getMONEY_VIA() {
        return MONEY_VIA;
    }

    public final int getCAUZIONE_PRIGIONE() {
        return CAUZIONE_PRIGIONE;
    }

    public Player getCurrPlayer() {
        return currPlayer;
    }

    public Dadi getDice() {
        return dice;
    }

    public Tabellone getBoard() {
        return board;
    }

    public UI getUi() {
        return ui;
    }

    public MazzoProbabilita getMazzoProbabilita() {
        return mazzoProbabilita;
    }

    public MazzoImprevisti getMazzoImprevisti() {
        return mazzoImprevisti;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isTurnFinished() {
        return turnFinished;
    }

    public void setCurrPlayer(Player currPlayer) {
        this.currPlayer = currPlayer;
    }

    public void setDice(Dadi dice) {
        this.dice = dice;
    }

    public void setBoard(Tabellone board) {
        this.board = board;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public void setMazzoProbabilita(MazzoProbabilita mazzoProbabilita) {
        this.mazzoProbabilita = mazzoProbabilita;
    }

    public void setMazzoImprevisti(MazzoImprevisti mazzoImprevisti) {
        this.mazzoImprevisti = mazzoImprevisti;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setTurnFinished(boolean turnFinished) {
        this.turnFinished = turnFinished;
    }

    // ----------- << method.annotations@AAAAAAGSUXhKgqovqp4= >>
    // ----------- >>
    public void inputNames() {
    // ----------- << method.body@AAAAAAGSUXhKgqovqp4= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUXps4q0CWSo= >>
    // ----------- >>
    public void giveStartMoney() {
    // ----------- << method.body@AAAAAAGSUXps4q0CWSo= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUXsn7K37n6s= >>
    // ----------- >>
    public void decideStarter() {
    // ----------- << method.body@AAAAAAGSUXsn7K37n6s= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUXsqXq4fXTU= >>
    // ----------- >>
    public void checkPassedGo() {
    // ----------- << method.body@AAAAAAGSUXsqXq4fXTU= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUXsr3a5Dfgg= >>
    // ----------- >>
    public void cardAction() {
    // ----------- << method.body@AAAAAAGSUXsr3a5Dfgg= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUXstyq5ncjU= >>
    // ----------- >>
    public void squareArrival() {
    // ----------- << method.body@AAAAAAGSUXstyq5ncjU= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUXsvgq6LSuQ= >>
    // ----------- >>
    public void rollDiceCommand() {
    // ----------- << method.body@AAAAAAGSUXsvgq6LSuQ= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUX213a/GOrs= >>
    // ----------- >>
    public void buyPropertyCommand() {
    // ----------- << method.body@AAAAAAGSUX213a/GOrs= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUX2376/qFkE= >>
    // ----------- >>
    public void buildCommand() {
    // ----------- << method.body@AAAAAAGSUX2376/qFkE= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUX26BbAOBTg= >>
    // ----------- >>
    public void demolishCommand() {
    // ----------- << method.body@AAAAAAGSUX26BbAOBTg= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUX79R7EaFDE= >>
    // ----------- >>
    public void setBankrupt() {
    // ----------- << method.body@AAAAAAGSUX79R7EaFDE= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUX7+0bE+KP0= >>
    // ----------- >>
    public void mortgageCommand() {
    // ----------- << method.body@AAAAAAGSUX7+0bE+KP0= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUYLAPLkNrsQ= >>
    // ----------- >>
    public void redeemCommand() {
    // ----------- << method.body@AAAAAAGSUYLAPLkNrsQ= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUX8AGbFiRzg= >>
    // ----------- >>
    public void freeJailExitCommand() {
    // ----------- << method.body@AAAAAAGSUX8AGbFiRzg= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUYKX07jaUFM= >>
    // ----------- >>
    public void payFineCommand() {
    // ----------- << method.body@AAAAAAGSUYKX07jaUFM= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUYLCHbkxEuQ= >>
    // ----------- >>
    public void setTurnFinished() {
    // ----------- << method.body@AAAAAAGSUYLCHbkxEuQ= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUYTANLnbw1w= >>
    // ----------- >>
    public void processTurn() {
    // ----------- << method.body@AAAAAAGSUYTANLnbw1w= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUYUeF7oOmf0= >>
    // ----------- >>
    public void nextPlayer() {
    // ----------- << method.body@AAAAAAGSUYUeF7oOmf0= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUYUgdroyRcY= >>
    // ----------- >>
    public void decideWinner() {
    // ----------- << method.body@AAAAAAGSUYUgdroyRcY= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGSUYYBzLrCUfY= >>
    // ----------- >>
    public void setGameOver() {
    // ----------- << method.body@AAAAAAGSUYYBzLrCUfY= >>
    // ----------- >>
    }
// ----------- << class.extras@AAAAAAGSUWFLXJSMmPQ= >>
// ----------- >>
}