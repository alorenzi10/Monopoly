package Model;

public class Cantiere extends Proprieta {

	private transient int[] tabellaAffitti; //0- affitto, 1-4 case, 5 albergo
	private transient GruppoColore gruppoColore;
	private int numCostruzioni;
	private int costoCasa;
	private transient final int NUM_MAX_UNITA = 5;
	private final int id;

	public Cantiere(String name, int id, int prezzo, int valoreIpoteca, int[] tabellaAffitti, GruppoColore gruppoColore, int costoCasa) {
		super(name, prezzo, valoreIpoteca);
		this.id = id;
		this.tabellaAffitti = tabellaAffitti;
		this.gruppoColore = gruppoColore;
		this.costoCasa = costoCasa;
		this.numCostruzioni = 0;
		gruppoColore.addMember(this);
	}

	public boolean haCase() {
		boolean b = false;
		if(numCostruzioni > 0) {
			b = true;
		}
		return b;
	}

	public void costruisci() {
		numCostruzioni++;
	}

	public void demolisci() {
		numCostruzioni--;
	}

	public int getAffitto(){
		int affitto;
		if (numCostruzioni == 0 && super.getPossessore().possessoreGruppo(this) == false) {
			affitto = tabellaAffitti[0];
		}else if (numCostruzioni == 0 && super.getPossessore().possessoreGruppo(this) == true) { 
			//se il possessore ha tutte le proprieta del gruppo l'affitto raddoppia
			affitto = tabellaAffitti[0] * 2;
		}else {
			affitto = tabellaAffitti[numCostruzioni];
		}
		return affitto;
	}

	public int getNumCase() {
		int numCase;
		if (numCostruzioni < NUM_MAX_UNITA) {
			numCase = numCostruzioni;
		}
		else {numCase = 0;}
		return numCase;
	}

	public int getNumAlberghi() {
		int numAlberghi;
		if (numCostruzioni == NUM_MAX_UNITA) {
			numAlberghi = 1;
		}
		else {numAlberghi = 0;}
		return numAlberghi;
	}

	public GruppoColore getGruppoColore() {
		return gruppoColore;
	}

	public int getNumCostruzioni() {
		return numCostruzioni;
	}

	public int getId() {
		return id;
	}

	public int getCostoCasa() {
		return costoCasa;
	}
	
	public int getPrezzoIpoteca() {
		return super.getPrezzoIpoteca();
	}



}