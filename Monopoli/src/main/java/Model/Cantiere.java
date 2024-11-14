package Model;

public class Cantiere extends Proprieta {
    
    private int[] tabellaAffitti; //0- affitto, 1-4 case, 5 albergo
    private GruppoColore gruppoColore;
    private int numCostruzioni;
    private int costoCasa;
    private final int MAX_NUM_UNITS = 5;
 
    public Cantiere(String name, int price, int mortgageValue, int[] tabellaAffitti, GruppoColore colourGroup, int costoCasa) {
		super(name, price, mortgageValue);
		this.tabellaAffitti = tabellaAffitti;
		this.gruppoColore = colourGroup;
		this.costoCasa = costoCasa;
		this.numCostruzioni = 0;
		colourGroup.addMember(this);
	}
    
    public int getAffitto(){
    	int affitto;
    	if (numCostruzioni==0 && super.getPossessore().possessoreGruppo(this)==false) {
    		affitto = tabellaAffitti[0];
    	}else if (numCostruzioni==0 && super.getPossessore().possessoreGruppo(this)==true) { 
    		//se il possessore ha tutte le proprieta del gruppo l'affitto raddoppia
    		affitto = tabellaAffitti[0]*2;
    	}else {
    		affitto = tabellaAffitti[numCostruzioni];
    	}
    	return affitto;
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

	public GruppoColore getGruppoColore() {
		return gruppoColore;
	}

	public boolean haCase() {
		boolean b = false;
		if(numCostruzioni > 0) {
			b = true;
		}
		return b;
	}

	public int getNumCostruzioni() {
		return numCostruzioni;
	}

	public int getCostoCasa() {
		return costoCasa;
	}

	public void costruisci() {
		numCostruzioni++;
	}

	public void demolisci() {
		numCostruzioni--;
	}

}