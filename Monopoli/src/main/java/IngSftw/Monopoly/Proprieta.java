package IngSftw.Monopoly;


import java.util.*;
import java.time.*;

public class Proprieta extends Casella {
	
    private final double VALORE_DISIPOTECA = 1.1;
    private boolean isOwned;
    private int price;
    private Player owner;
    private String shortName;
    private boolean mortgaged;
    private int mortgageValue;

    public Proprieta(String name, int price, String shortName, int mortgageValue) {
    
    }
    public String getShortName() {
    	return this.shortName;
    }

    public int getPrice() {
    	return this.price;
    }

    public int getRent() {
    	return (int) (price*VALORE_DISIPOTECA);
    }

    public Player getOwner() {
    	return this.owner;
    }

    public boolean isOwned() {

    }

    public void setOwner(Player player) {
    	this.owner = player;
    }
    // ----------- << method.annotations@AAAAAAGScSgcMwFRw7Y= >>
    // ----------- >>
    public void releaseOwnership() {
    	
    }
    
    public void setMortgaged() {
    }
    
    public void isMortgaged() {
    	
    }
    
    public void setNotMortgaged() {
    	
    }
    
    public int getMortgagedValue() {
    	
    }
    
    public int getMortgageRedemption() {

    }
    
    public boolean equals(String string) {
    	
    }
    // ----------- << method.annotations@AAAAAAGScSg4EAggNcQ= >>
    // ----------- >>
    public String toString() {
    	
    }
}