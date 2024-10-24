package Model;

public class Tassa extends Casella {

    private int totale;

    public Tassa(String name, int totale) {
    	super(name);
    	this.totale = totale;
    }

	public int getTotale() {
		return totale;
	}
}