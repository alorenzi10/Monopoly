package Model;

public class Proprieta extends Casella {
	
    private final double VALORE_DISIPOTECA = 1.1;
    private boolean isOwned;
    private int price;
    private Player owner;
    private boolean mortgaged;
    private int mortgageValue;

    public Proprieta(String name, int price, int mortgageValue) {
    	super(name);
    	this.price = price;
    	isOwned = false;
    	owner = null;
    	mortgaged = false;
    	this.mortgageValue = mortgageValue;
    }

}