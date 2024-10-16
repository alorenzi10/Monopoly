package IngSftw.Monopoly;

public class Stazione extends Proprieta {
    private int[] rentTable;

    public Stazione(String name, String shortName, int price, int mortgageValue, int[] rentTable) {
    	super(name, price, shortName, mortgageValue);
		this.rentTable = rentTable;
    }
    public int getRent() {
    	return rentTable[super.getOwner().getNumStationsOwned()-1];
    }
}