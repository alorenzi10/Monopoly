package Model;

public class Societa extends Proprieta {
	
    private int[] rentTable;
    private Dadi dice;

    public Societa(String name, int price, int mortgageValue, int[] rentTable, Dadi dice) {
    	super(name, price, mortgageValue);
		this.rentTable = rentTable;
		this.dice = dice;
    }
    
    public int getAffitto() {
    	return dice.getTotal()*getMoltiplicatoreAffitto();
    }

	private int getMoltiplicatoreAffitto() {
		return rentTable[super.getPossessore().getNumSocietaPossedute()-1];
	}

}