package Model;

public class Cantiere extends Proprieta {
    
    private int[] rentTable;
    private GruppoColore colourGroup;
    private int numCostruzioni;
    private int buildPrice;
    private final int MAX_NUM_UNITS = 5;
 
    public Cantiere(String name, int price, int mortgageValue, int[] rentTable, GruppoColore colourGroup, int buildPrice) {
		super(name, price, mortgageValue);
		this.rentTable = rentTable;
		this.colourGroup = colourGroup;
		this.buildPrice = buildPrice;
		this.numCostruzioni = 0;
		colourGroup.addMember(this);
	}

	public int getNumCase() {
		int numCase;
		if (numCostruzioni < 5) {
			numCase = numCostruzioni;
		}
		else {numCase=0;}
		return numCase;
	}
	
	public int getNumAlberghi() {
		int numAlberghi;
		if (numCostruzioni == 5) {
			numAlberghi = 1;
		}
		else {numAlberghi=0;}
		return numAlberghi;
	}

}