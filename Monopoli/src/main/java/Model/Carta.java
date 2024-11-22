package Model;

public class Carta {
	private int tipo;
	private String messaggio;
	private int IDAzione;
	private int parametro;
	private int[] parameters;
	private int destinazione;

	//diversi tipi di carte
	public Carta() {
		messaggio = "";
	}

	public Carta(int tipo, String messaggio, int actionID) {
		this.tipo = tipo;
		this.messaggio = messaggio;
		this.IDAzione = actionID;
	}

	public Carta(int tipo, String messaggio, int actionID, int parameter) {
		this.tipo = tipo;
		this.messaggio = messaggio;
		this.IDAzione = actionID;
		this.parametro = parameter;
	}

	public Carta(int tipo, String messaggio, int actionID, int[] parameters) {
		this.tipo = tipo;
		this.messaggio = messaggio;
		this.IDAzione = actionID;
		this.parameters = parameters;
	}

	public Carta(int tipo, String messaggio, int actionID, int parameter, int destinazione) {
		this.tipo = tipo;
		this.messaggio = messaggio;
		this.IDAzione = actionID;
		this.parametro = parameter;
		this.destinazione=destinazione;
	}

	public int getAzione () {
		return IDAzione;
	}

	public int getDestinazione() {
		return destinazione; //ritorna la destinazione 
	}

	public int getCostoCase() {
		return parameters[0];
	}

	public int getCostoAlberghi() {
		return parameters[1];
	}

	public String getMessaggio() {
		return messaggio;
	}

	public int getQtaSoldi() {
		return parametro;
	}

	public int getTipo() {
		return tipo;
	}
}