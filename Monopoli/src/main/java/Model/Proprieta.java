package Model;

public class Proprieta extends Casella {
	
    private transient final double VALORE_DISIPOTECA = 1.1;
    private boolean isOwned;
    private transient int costo;
    private transient Player possessore;
    private boolean ipotecata;
    private transient int prezzoIpoteca;

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
	
	public void setPosseduta(boolean state) {
		isOwned = state;
	}

	public Player getPossessore() {
		return possessore;
	}

	public int getAffitto() { //da sistemare
		return 0;
	}

	public int getCosto() {
		return costo;
	}

	public boolean isIpotecata() {
		return ipotecata;
	}

	public void setIpotecata(boolean condizione) {
		ipotecata = condizione;
	}

	public int getPrezzoIpoteca() {
		return prezzoIpoteca;
	}
	
	

	public int getCostoDisipoteca() {
		return (int) (prezzoIpoteca*VALORE_DISIPOTECA);
	}

	public void setProprietario(Player player) {
		possessore = player;
		isOwned = true;
	}

}