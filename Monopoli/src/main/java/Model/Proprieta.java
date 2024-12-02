package Model;

public class Proprieta extends Casella {
	
    private transient final double VALORE_DISIPOTECA = 1.1;
    private boolean isPosseduta;
    private transient int costo;
    private transient Player possessore;
    private boolean ipotecata;
    private transient int prezzoIpoteca;

    public Proprieta(String nome, int costo, int prezzoIpoteca) {
    	super(nome);
    	this.costo = costo;
    	isPosseduta = false;
    	possessore = null;
    	ipotecata = false;
    	this.prezzoIpoteca = prezzoIpoteca;
    }

	public boolean posseduta() {
		return isPosseduta;
	}
	
	public void setPosseduta(boolean stato) {
		isPosseduta = stato;
	}

	public Player getPossessore() {
		return possessore;
	}

	public void setProprietario(Player player) {
		possessore = player;
		isPosseduta = true;
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


}