package Model;

public class Societa extends Proprieta {
	
    private int[] rentTable;
    private Dadi dice;

    public Societa(String name, int price, int mortgageValue, int[] rentTable, Dadi dice) {
    	super(name, price, mortgageValue);
		this.rentTable = rentTable;
		this.dice = dice;
    }

}