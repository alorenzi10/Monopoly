package Model;

public class Carta {
	private int tipo;
	private String messaggio;
	private int IDAzione;
	private int parametro;
	private int[] parametri;
	private int destinazione;

	//diversi tipi di carte

	public Carta(int tipo, String messaggio, int IDAzione) {
		this.tipo = tipo;
		this.messaggio = messaggio;
		this.IDAzione = IDAzione;
	}

	public Carta(int tipo, String messaggio, int IDAzione, int parameter) {
		this.tipo = tipo;
		this.messaggio = messaggio;
		this.IDAzione = IDAzione;
		this.parametro = parameter;
	}

	public Carta(int tipo, String messaggio, int IDAzione, int[] parametri) {
		this.tipo = tipo;
		this.messaggio = messaggio;
		this.IDAzione = IDAzione;
		this.parametri = parametri;
	}

	public Carta(int tipo, String messaggio, int IDAzione, int parametro, int destinazione) {
		this.tipo = tipo;
		this.messaggio = messaggio;
		this.IDAzione = IDAzione;
		this.parametro = parametro;
		this.destinazione=destinazione;
	}

	public int getAzione () {
		return IDAzione;
	}

	public int getDestinazione() {
		return destinazione; //ritorna la destinazione 
	}

	public int getCostoCase() {
		return parametri[0];
	}

	public int getCostoAlberghi() {
		return parametri[1];
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