package Model;

public class Carta {
    private int tipo;
    private String message;
    private int actionID;
    private int parameter;
    private int[] parameters;
    private int destinazione;
    
    //diversi tipi di carte
    public Carta() {
    	message = "";
    }
    
    public Carta(int type, String message, int actionID) {
    	this.tipo = type;
		this.message = message;
		this.actionID = actionID;
    }
    
    public Carta(int type, String message, int actionID, int parameter) {
		this.tipo = type;
		this.message = message;
		this.actionID = actionID;
		this.parameter = parameter;
	}

    public Carta(int type, String message, int actionID, int[] parameters) {
		this.tipo = type;
		this.message = message;
		this.actionID = actionID;
		this.parameters = parameters;
	}
    
    public Carta(int type, String message, int actionID, int parameter, int destinazione) {
		this.tipo = type;
		this.message = message;
		this.actionID = actionID;
		this.parameter = parameter;
		this.destinazione=destinazione;
	}
    
    public int getAction () {
    	return actionID;
    }

	public int getDestination() {
		return destinazione; //ritorna la destinazione 
	}

	public int getCostoCase() {
		return parameters[0];
	}
	
	public int getCostoAlberghi() {
		return parameters[1];
	}

	public String getMessaggio() {
		return message;
	}
	
	public int getQtaSoldi() {
		return parameter;
	}

	public int getTipo() {
		return tipo;
	}
}