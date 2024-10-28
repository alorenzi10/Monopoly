package Model;

public class Proprieta extends Casella {
	
    private final double VALORE_DISIPOTECA = 1.1;
    private boolean isOwned;
    private int costo;
    private Player possessore;
    private boolean ipotecata;
    private int prezzoIpoteca;

    public Proprieta(String name, int costo, int prezzoIpoteca) {
    	super(name);
    	this.costo = costo;
    	isOwned = false;
    	possessore = null;
    	ipotecata = false;
    	this.prezzoIpoteca = prezzoIpoteca;
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

	public boolean isIpotecata() {
		return ipotecata;
	}

	public void setIpotecata() {
		ipotecata = true;
	}

	public int getPrezzoIpoteca() {
		return prezzoIpoteca;
	}

	public int getCostoDisipoteca() {
		return (int) (prezzoIpoteca*VALORE_DISIPOTECA);
	}

}