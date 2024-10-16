package IngSftw.Monopoly;

import java.util.*;
import java.time.*;

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
	}

    public boolean canBuild(int numToBuild) {
    	return true;
    }

    public void build(int numToBuild) {

    }
    
    public boolean canDemolish(int numToDemolish) {
    	return true;
    }

    public void demolish(int numToDemolish) {
    	
    }
  
    public void demolishAll() {
    	
    }
 
    public int getNumBuildings() {
    	return this.numBuildings;
    }
    
    public int getBuildingPrice() {
    	return 
    }
    
    public boolean hasBuildings() {
    }
    
    public int getNumHouses() {
    }
    
    public int getNumHotels() {
    }
    
    public GruppoColore getColourGroup() {
    }
    
    public int getRent() {
    }
    
    public String toString() {
    }
}