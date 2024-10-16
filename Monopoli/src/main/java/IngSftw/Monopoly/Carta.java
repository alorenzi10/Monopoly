package IngSftw.Monopoly;

import java.util.*;
import java.time.*;

public class Carta {
	
    private int type;
    private String message;
    private int actionID;
    private int parameter;
    private int parameters;

    public Carta() {
    }
    
    public Carta(int type, String message, int actionID) {
    	
    }
    
    public Carta(int type, String message, int actionID, int parameter) {
		super();
		this.type = type;
		this.message = message;
		this.actionID = actionID;
		this.parameter = parameter;
	}

	public int getType() {
    	return this.type;
    }
    
    public int getAction() {
    	return this.actionID;
    }
    
    public int getDestination() {
    	
    }
    
    public int getNumSpaces() {
    	
    }
    
    public int getAmount() {
    	
    }
    
    public int getHouseCost() {
    	
    }

    public int getHotelCost() {
    	return 
    }
    
    public String toString() {
    	
    }
}