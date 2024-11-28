package Model;

public class Stazione extends Proprieta {
    private int[] valoreIpoteca;

    public Stazione(String nome, int prezzo, int prezzoIpoteca, int[] valoreIpoteca) {
    	super(nome, prezzo, prezzoIpoteca);
		this.valoreIpoteca = valoreIpoteca;
    }
    
    public int getAffitto() {
		return valoreIpoteca[super.getPossessore().getNumStazioniPossedute()-1];
    	
    }

    public String getNome() {
    	return super.getNome();
    }
}