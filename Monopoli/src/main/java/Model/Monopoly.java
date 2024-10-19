/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package Model;


import java.util.*;

public class Monopoly {
	
    private final int MONEY_START=1500; //soldi quando si crea una partita
    private final int MONEY_VIA=200; //soldi che si ricevono quando si passa/transita dal via
    private final int CAUZIONE_PRIGIONE=50; //costo per uscire dalla prigione
    public int numero_giocatori; 
    private int currPlayer; //turno del giocatore nella arraylist
    private Dadi dice;
    private Tabellone board;
    private MazzoProbabilita mazzoProbabilita;
    private MazzoImprevisti mazzoImprevisti;
    private boolean gameOver=false;
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
}
}
  