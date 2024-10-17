package IngSftw.Monopoly;

public class Tabellone {
    public final int NUM_SQUARES = 40;
    
    public final static int POS_VIA = 0;
    public final int POS_PRIGIONE = 10;
    public final int POS_VAI_IN_PRIGIONE = 30;
    
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
    	
    	//Cantiere (name, price, mortgageValue, rentTable, GruppoColore, buildPrice)
		squares[1] = new Cantiere("Vicolo corto", 60, 30, new int[] {2,10,30,90,160,250}, marrone, 50);
		//Assegna name=Probabilità con super
		squares[2] = new Probabilita();
		squares[3] = new Cantiere("Vicolo stretto", 60, 30, new int[] {4,20,60,180,320,450}, marrone, 50);
		//tassa(name, ammount)
		squares[4] = new Tassa("Tassa patrimoniale",200);
		//Stazione(name, price, mortgageValue, rentTable)
		squares[5] = new Stazione("Stazione sud", 200, 100, new int[] {25,50,100,200});
		squares[6] = new Cantiere("Bastoni Gran Sasso", 100, 50, new int[] {6,30,90,270,400,550}, azzurro, 50);
		//Assegna name=Imprevisti con super
		squares[7] = new Imprevisti();
		squares[8] = new Cantiere("Viale Monteosa", 100, 50, new int[] {6,30,90,270,400,550}, azzurro, 50);
		squares[9] = new Cantiere("Viale Vesuvio", 120, 60, new int[] {8,40,100,300,450,600}, azzurro, 50);
		squares[10] = new Casella("Prigione");
		squares[11] = new Cantiere("Via Accademia", 140, 70, new int[] {10,50,150,450,625,750}, viola, 100);
		//Società(name, price, mortgageValue, rentTable, dado)
		squares[12] = new Societa("Società elettrica", 150, 75, new int[] {4,10}, dice);
		squares[13] = new Cantiere("Corso ateneo",  140, 70, new int[] {10,50,150,450,625,750}, viola, 100);
		squares[14] = new Cantiere("Piazza università", 160, 80, new int[] {12,60,180,500,700,900}, viola, 100);
		squares[15] = new Stazione("Stazione ovest", 200, 100, new int[] {25,50,100,200});
		squares[16] = new Cantiere("Via Verdi", 180, 90, new int[] {14,70,200,550,750,950}, arancio, 100);
		squares[17] = new Probabilita();
		squares[18] = new Cantiere("Corso Raffaello", 180, 90, new int[] {14,70,200,550,750,950}, arancio, 100);
		squares[19] = new Cantiere("Piazza Dante", 200, 100, new int[] {16,80,220,600,800,1000}, arancio, 100);
		squares[20] = new Casella("Parcheggio gratuito");
		squares[21] = new Cantiere("Via Marco Polo", 220, 110, new int[] {18,90,250,700,875,1050}, rosso, 150);
		squares[22] = new Imprevisti();
		squares[23] = new Cantiere("Corso Magellano", 220, 110, new int[] {18,90,250,700,875,1050}, rosso, 150);
		squares[24] = new Cantiere("Largo Colombo", 240, 120, new int[] {20,100,300,750,925,1100}, rosso, 150);
		squares[25] = new Stazione("Stazione nord", 200, 100, new int[] {25,50,100,200});
		squares[26] = new Cantiere("Viale Costantino", 260, 150, new int[] {22,110,330,800,975,1150}, giallo, 150);
		squares[27] = new Cantiere("Viale Traiano", 260, 150, new int[] {22,110,330,800,975,1150}, giallo, 150);
		squares[28] = new Societa("Società acqua potabile", 150, 75, new int[] {4,10}, dice);
		squares[29] = new Cantiere("Piazza Giulio Cesare", 280, 150, new int[] {22,120,360,850,1025,1200}, giallo, 150);
		squares[30] = new VaiInPrigione();
		squares[31] = new Cantiere("Via Roma", 300, 200, new int[] {26,130,390,900,1100,1275}, verde, 200);
		squares[32] = new Cantiere("Corso Impero", 300, 200, new int[] {26,130,390,900,1100,1275}, verde, 200);
		squares[33] = new Probabilita();
		squares[34] = new Cantiere("Largo Augusto", 320, 200, new int[] {28,150,450,1000,1200,1400}, verde, 200);
		squares[35] = new Stazione("Stazione est", 200, 100, new int[] {25,50,100,200});
		squares[36] = new Imprevisti();
		squares[37] = new Cantiere("Viale dei giardini", 350, 175, new int[] {35,175,500,1100,1300,1500}, blu, 200);
		squares[38] = new Tassa("Tassa di lusso",100);
		squares[39] = new Cantiere("Parco della vittoria", 400, 200, new int[] {50,200,600,1400,1700,2000}, blu, 200);
		
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