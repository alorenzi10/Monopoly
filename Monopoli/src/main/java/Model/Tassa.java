package Model;

public class Tassa extends Casella {

    private int totale;

    public Tassa(String nome, int totale) {
    	super(nome);
    	this.totale = totale;
    }

	public int getTotale() {
		return totale;
	}
}