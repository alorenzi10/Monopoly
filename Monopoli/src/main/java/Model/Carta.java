package Model;

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
}