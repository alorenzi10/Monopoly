package Model;

public class Cantiere extends Proprieta {
    
    private int[] tabellaAffitti;
    private GruppoColore gruppoColore;
    private int numCostruzioni;
    private int buildPrice;
    private final int MAX_NUM_UNITS = 5;
 
    public Cantiere(String name, int price, int mortgageValue, int[] tabellaAffitti, GruppoColore colourGroup, int buildPrice) {
		super(name, price, mortgageValue);
		this.tabellaAffitti = tabellaAffitti;
		this.gruppoColore = colourGroup;
		this.buildPrice = buildPrice;
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

	public boolean puoCostruire(int i) {
		return false;
	}

	public boolean haCase() {
		boolean b = false;
		if(numCostruzioni > 0) {
			b = true;
		}
		return b;
	}

}