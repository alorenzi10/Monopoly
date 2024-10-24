/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package Model;


import java.util.*;
import java.time.*;

public class Player {

    private int id;
    private String name;
    private int wallet;
    private boolean inPrigione;
    private boolean tiri;
    private int location;
    private boolean passatoVia;
    private ArrayList<Proprieta> proprieta;
    private final int MAX_EXIT_JAIL_ATTEMPTS=3;
    private int tentativiUscitaPrigione;
    private ArrayList<Carta> cards;
    Pedina pedina;
    
    public Player(int id, String name, int wallet, boolean isInJail, int location, Pedina pedina) {
    	this.id = id;
    	this.name = name;
    	this.wallet = wallet;
    	this.inPrigione = inPrigione;
    	this.location = location;
    	this.pedina = pedina;
    }
    
    public int getWallet() {
    	return wallet;
    }
    
    public void doTransaction(int totale) {
    	this.wallet = wallet + totale;
    }
    
    public int getLocation() {
    	return location;
    }
    
    public void muovi(int spostamento) {
    	this.location = location + spostamento;
    	if (location >= Tabellone.NUM_SQUARES) {
    		location = location - 40;
    		passatoVia = true;
    	}
    	else {
    		passatoVia = false;
    	}
    	if (location < 0) {
    		location = location + 40;
    	}
    }

	public String getName() {
		return name;
	}

	public boolean passaggioVia() {
		return passatoVia;
	}

	public void vaiInPrigione() {
		location = Tabellone.POS_PRIGIONE;
		inPrigione = true;
		tentativiUscitaPrigione = 0;
	}

	public void addCarta(Carta carta) {
		cards.add(carta);
	}

	public int getNumCasePossesso() {
		int numCase = 0;
		for (Proprieta p : proprieta) {
			if (p instanceof Cantiere) {
				numCase = numCase + ((Cantiere) p).getNumCase();
			}
		}
		return numCase;
	}
	
	public int getNumAlberghiPossesso() {
		int numAlberghi = 0;
		for (Proprieta p : proprieta) {
			if (p instanceof Cantiere) {
				numAlberghi = numAlberghi + ((Cantiere) p).getNumCase();
			}
		}
		return numAlberghi;
	}
    
    
    

}