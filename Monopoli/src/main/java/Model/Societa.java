package Model;

public class Societa extends Proprieta {
	
    private transient int[] tabellaAffitti;
    private  transient Dadi dadi;

    public Societa(String nome, int prezzo, int prezzoIpoteca, int[] tabellaAffitti, Dadi dadi) {
    	super(nome, prezzo, prezzoIpoteca);
		this.tabellaAffitti = tabellaAffitti;
		this.dadi = dadi;
    }
    
    public int getAffitto() {
    	return dadi.getTotal()*getMoltiplicatoreAffitto();
    }

	private int getMoltiplicatoreAffitto() {
		return tabellaAffitti[super.getPossessore().getNumSocietaPossedute()-1];
	}

}