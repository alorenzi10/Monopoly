package Model;

public class Proprieta extends Casella {
	
    private final double VALORE_DISIPOTECA = 1.1;
    private boolean isOwned;
    private int costo;
    private Player possessore;
    private boolean mortgaged;
    private int mortgageValue;

    public Proprieta(String name, int costo, int mortgageValue) {
    	super(name);
    	this.costo = costo;
    	isOwned = false;
    	possessore = null;
    	mortgaged = false;
    	this.mortgageValue = mortgageValue;
    }

	public boolean posseduta() {
		return isOwned;
	}

	public Player getPossessore() {
		return possessore;
	}

	public int getAffito() {
		return 0;
	}

	public int getCosto() {
		return costo;
	}

}