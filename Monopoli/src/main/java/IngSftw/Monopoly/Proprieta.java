package IngSftw.Monopoly;

public class Proprieta extends Casella {
	
    private final double VALORE_DISIPOTECA = 1.1;
    private boolean isOwned;
    private int price;
    private Player owner;
    private String shortName;
    private boolean mortgaged;
    private int mortgageValue;

    public Proprieta(String name, int price, String shortName, int mortgageValue) {
    	super(name);
    	this.price = price;
    	this.shortName = shortName;
    	isOwned = false;
    	owner = null;
    	mortgaged = false;
    	this.mortgageValue = mortgageValue;
    }
    
    public String getShortName() {
    	return this.shortName;
    }

    public int getPrice() {
    	return this.price;
    }

    public int getRent() {
    	return 0;
    }

    public Player getOwner() {
    	return this.owner;
    }

    public boolean isOwned() {
    	return isOwned;
    }

    public void setOwner(Player player) {
    	this.owner = player;
    	isOwned = true;
    }

    public void releaseOwnership() {
    	this.owner = null;
    	this.isOwned = false;
    	this.mortgaged = false;
    }
    
    public void setMortgaged() {
    	this.mortgaged = true;
    }
    
    public boolean isMortgaged() {
    	return this.mortgaged;
    }
    
    public void setNotMortgaged() {
    	this.mortgaged = false;
    }
    
    public int getMortgagedValue() {
    	return this.mortgageValue;
    }
    
    public int getMortgageRedemption() {
    	return (int) (this.mortgageValue * VALORE_DISIPOTECA);
    }
    
    public boolean equals(String string) {
    	return shortName.equals(string);
    }

    public String toString() {
    	return super.toString();
    }
}