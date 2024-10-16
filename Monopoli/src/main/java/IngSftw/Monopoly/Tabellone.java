package IngSftw.Monopoly;

public class Tabellone {
    private final int NUM_SQUARES = 40;
    private final int POS_VIA = 0;
    private final int POS_PRIGIONE = 10;
    private final int POS_VAI_IN_PRIGIONE = 30;
    
    private Casella[] squares = new Casella[NUM_SQUARES];
    private GruppoColore marrone = new GruppoColore("marrone");
    private GruppoColore azzurro = new GruppoColore("azzurro");
    private GruppoColore viola = new GruppoColore("viola");
    private GruppoColore arancio = new GruppoColore("arancio");
    private GruppoColore rosso = new GruppoColore("rosso");
    private GruppoColore giallo = new GruppoColore("giallo");
    private GruppoColore verde = new GruppoColore("verde");
    private GruppoColore blu = new GruppoColore("blu");

    public Tabellone(Dadi dice) {
    	squares[0] = new Casella("Via");
		squares[1] = new Cantiere("Old Kent Rd", "kent", 60, 50, new int[] {2,10,30,90,160,250}, marrone, 50);
		squares[2] = new Probabilita();
		squares[3] = new Cantiere("Whitechapel Rd", "whitechapel", 60, 50, new int[] {4,20,60,180,320,450}, marrone, 50);
		squares[4] = new Tassa("Income Tassa",200);
		squares[5] = new Stazione("King's Cross Stazione", "kings", 200, 100, new int[] {25,50,100,200});
		squares[6] = new Cantiere("The Angel Islington", "angel", 100, 50, new int[] {6,30,90,270,400,550}, azzurro, 50);
		squares[7] = new Imprevisti();
		squares[8] = new Cantiere("Euston Rd", "euston", 100, 50, new int[] {6,30,90,270,400,550}, azzurro, 50);
		squares[9] = new Cantiere("Pentonville Rd", "pentonville", 120, 60, new int[] {8,40,100,300,450,600}, azzurro, 50);
		squares[10] = new Casella("Jail");
		squares[11] = new Cantiere("Pall Mall", "mall", 140, 70, new int[] {10,50,150,450,625,750}, viola, 100);
		squares[12] = new Societa("Electric Co", "electric", 150, 75, new int[] {4,10}, dice);
		squares[13] = new Cantiere("Whitehall", "whitehall", 140, 70, new int[] {10,50,150,450,625,750}, viola, 100);
		squares[14] = new Cantiere("Northumberland Ave", "northumberland", 160, 80, new int[] {12,60,180,500,700,900}, viola, 100);
		squares[15] = new Stazione("Marylebone Stazione", "maryledone", 200, 100, new int[] {25,50,100,200});
		squares[16] = new Cantiere("Bow St", "bow", 180, 90, new int[] {14,70,200,550,750,950}, arancio, 100);
		squares[17] = new Probabilita();
		squares[18] = new Cantiere("Marlborough St", "marlborough", 180, 90, new int[] {14,70,200,550,750,950}, arancio, 100);
		squares[19] = new Cantiere("Vine St", "vine", 200, 100, new int[] {16,80,220,600,800,1000}, arancio, 100);
		squares[20] = new Casella("Free Parking");
		squares[21] = new Cantiere("Strand", "strand", 220, 110, new int[] {18,90,250,700,875,1050}, rosso, 150);
		squares[22] = new Imprevisti();
		squares[23] = new Cantiere("Fleet St", "fleet", 220, 110, new int[] {18,90,250,700,875,1050}, rosso, 150);
		squares[24] = new Cantiere("Trafalgar Sq", "trafalgar", 240, 120, new int[] {20,100,300,750,925,1100}, rosso, 150);
		squares[25] = new Stazione("Fenchurch St Stazione", "fenchurch", 200, 100, new int[] {25,50,100,200});
		squares[26] = new Cantiere("Leicester Sq", "leicester", 260, 150, new int[] {22,110,330,800,975,1150}, giallo, 150);
		squares[27] = new Cantiere("Coventry St", "coverntry", 260, 150, new int[] {22,110,330,800,975,1150}, giallo, 150);
		squares[28] = new Societa("Water Works", "water", 150, 75, new int[] {4,10}, dice);
		squares[29] = new Cantiere("Piccadilly", "piccadilly", 280, 150, new int[] {22,120,360,850,1025,1200}, giallo, 150);
		squares[30] = new VaiInPrigione();
		squares[31] = new Cantiere("Regent St", "regent", 300, 200, new int[] {26,130,390,900,1100,1275}, verde, 200);
		squares[32] = new Cantiere("Oxford St", "oxford", 300, 200, new int[] {26,130,390,900,1100,1275}, verde, 200);
		squares[33] = new Probabilita();
		squares[34] = new Cantiere("Bond St", "bond", 320, 200, new int[] {28,150,450,1000,1200,1400}, verde, 200);
		squares[35] = new Stazione("Liverpool St Stazione", "liverpool", 200, 100, new int[] {25,50,100,200});
		squares[36] = new Imprevisti();
		squares[37] = new Cantiere("Park Lane", "park", 350, 175, new int[] {35,175,500,1100,1300,1500}, blu, 200);
		squares[38] = new Tassa("Super Tassa",100);
		squares[39] = new Cantiere("Mayfair", "mayfair", 400, 200, new int[] {50,200,600,1400,1700,2000}, blu, 200);
		
    }

    public Casella getSquare(int index) {
    	return squares[index];
    }

    public Proprieta getProperty(int index) {
    	return (Proprieta) squares[index];
    }

    public Proprieta getProperty(String shortName) {
    	Proprieta property = null;
		for (Casella c : squares) {
			if (c instanceof Proprieta) {
				Proprieta p = (Proprieta) c;
				if (p.equals(shortName)) {
					property = p;
				}
			}
		}
		return property;
    }

    public boolean isProperty(int index) {
    	return squares[index] instanceof Proprieta;
    }

    public boolean isProperty(String shortName) {
    	boolean found = false;
		for (Casella c : squares) {
			if (c instanceof Proprieta) {
				Proprieta p = (Proprieta) c;
				if (p.equals(shortName)) {	
					found = true;
				}
			}
		}
		return found;
    }

    public boolean isCantiere(String shortName) {
    	return isProperty(shortName) && getProperty(shortName) instanceof Cantiere;
    }

    public boolean isStation(String shortName) {
    	return isProperty(shortName) && getProperty(shortName) instanceof Stazione;
    }

    public boolean isUtility(String shortName) {
    	return isProperty(shortName) && getProperty(shortName) instanceof Societa;
    }
}