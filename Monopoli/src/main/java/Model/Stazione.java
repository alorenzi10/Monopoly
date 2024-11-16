package Model;

public class Stazione extends Proprieta {
    private int[] rentTable;

    public Stazione(String name, int price, int mortgageValue, int[] rentTable) {
    	super(name, price, mortgageValue);
		this.rentTable = rentTable;
    }
    
    public int getAffitto() {
		return rentTable[super.getPossessore().getNumStazioniPossedute()-1];
    	
    }

}