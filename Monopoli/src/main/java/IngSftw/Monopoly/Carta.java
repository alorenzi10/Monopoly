package IngSftw.Monopoly;

public class Carta {
	
    private int type;
    private String message;
    private int actionID;
    private int parameter;
    private int[] parameters;
    
    //diversi tipi di carte
    public Carta() {
    	message = "";
    }
    
    public Carta(int type, String message, int actionID) {
    	this.type = type;
		this.message = message;
		this.actionID = actionID;
    }
    
    public Carta(int type, String message, int actionID, int parameter) {
		this.type = type;
		this.message = message;
		this.actionID = actionID;
		this.parameter = parameter;
	}

    public Carta(int type, String message, int actionID, int[] parameters) {
		this.type = type;
		this.message = message;
		this.actionID = actionID;
		this.parameters = parameters;
	}

	public int getType() {
    	return this.type;
    }
    
    public int getAction() {
    	return this.actionID;
    }
    
    public int getDestination() {
    	return this.parameter;
    }
    
    public int getNumSpaces() {
    	return this.parameter;
    }
    
    public int getAmount() {
    	return this.parameter;
    }
    
    public int getHouseCost() {
    	return this.parameters[0];
    }

    public int getHotelCost() {
    	return this.parameters[1];
    }
    
    public String toString() {
    	return message;
    }
}