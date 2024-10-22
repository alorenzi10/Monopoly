package Model;

public class Cantiere extends Proprieta {
    
    private int[] rentTable;
    private GruppoColore colourGroup;
    private int numBuildings;
    private int buildPrice;
    private final int MAX_NUM_UNITS = 5;
 
    public Cantiere(String name, int price, int mortgageValue, int[] rentTable, GruppoColore colourGroup, int buildPrice) {
		super(name, price, mortgageValue);
		this.rentTable = rentTable;
		this.colourGroup = colourGroup;
		this.buildPrice = buildPrice;
		this.numBuildings = 0;
		colourGroup.addMember(this);
	}

}