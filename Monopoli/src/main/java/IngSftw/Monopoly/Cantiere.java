package IngSftw.Monopoly;

public class Cantiere extends Proprieta {
    
    private int[] rentTable;
    private GruppoColore colourGroup;
    private int numBuildings;
    private int buildPrice;
    private final int MAX_NUM_UNITS = 5;
 
    public Cantiere(String name, String shortName, int price, int mortgageValue, int[] rentTable, GruppoColore colourGroup, int buildPrice) {
		super(name, price, shortName, mortgageValue);
		this.rentTable = rentTable;
		this.colourGroup = colourGroup;
		this.buildPrice = buildPrice;
		this.numBuildings = 0;
		colourGroup.addMember(this);
	}

    public boolean canBuild(int numToBuild) {
    	return (numBuildings + numToBuild) <= MAX_NUM_UNITS;
    }

    public void build(int numToBuild) {
    	if(canBuild(numToBuild))
    		numBuildings += numToBuild;
    }
    
    public boolean canDemolish(int numToDemolish) {
    	return (numToDemolish <= numBuildings);
    }

    public void demolish(int numToDemolish) {
    	if(canDemolish(numToDemolish))
    		numBuildings -= numToDemolish;
    }
  
    public void demolishAll() {
    	numBuildings = 0;
    }
 
    public int getNumBuildings() {
    	return this.numBuildings;
    }
    
    public int getBuildingPrice() {
    	return this.buildPrice;
    }
    
    public boolean hasBuildings() {
    	return (numBuildings >0);
    }
    
    public int getNumHouses() {
    	int numHouses;
		if (numBuildings < 5) { numHouses = numBuildings; } 
		 else { numHouses = 0; }
		return numHouses;
    }
    
    public int getNumHotels() {
    	int numHotels;
		if (numBuildings == 5) { numHotels = 1; } 
		 else { numHotels = 0; }
		return numHotels;
    }
    
    public GruppoColore getColourGroup() {
    	return this.colourGroup;
    }
    
    public int getRent() {//da sistemare
    	int rent = 0;
    	if((numBuildings == 0 && super.getOwner().isGroupOwner(this))) {
    		rent = rentTable[0];
    	}//else if
		return rent;
    }
    
    public String toString() {
    	return super.toString();
    }
}