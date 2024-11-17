package Model;

public class Tabellone {
    public final static int NUM_SQUARES = 40;
    
    public final static int POS_VIA = 0;
    public final static int POS_PRIGIONE = 10;
    public final static int POS_VAI_IN_PRIGIONE = 30;
    public final static int POS_LARGO_COLOMBO = 24;
    public static final int POS_PARCO_DELLA_VITTORIA = 39;
	public static final int POS_STAZIONE_NORD = 5;
	public static final int POS_CORSO_ATENEO = 13;
    
    private Casella[] squares;
    private GruppoColore marrone;
    private GruppoColore azzurro;
    private GruppoColore viola;
    private GruppoColore arancio;
    private GruppoColore rosso;
    private GruppoColore giallo;
    private GruppoColore verde;
    private GruppoColore blu;

    public Tabellone(Dadi dice) {
    	
    	squares = new Casella[NUM_SQUARES];
        marrone = new GruppoColore("marrone");
        azzurro = new GruppoColore("azzurro");
        viola = new GruppoColore("viola");
        arancio = new GruppoColore("arancio");
        rosso = new GruppoColore("rosso");
        giallo = new GruppoColore("giallo");
        verde = new GruppoColore("verde");
        blu = new GruppoColore("blu");

    	squares[0] = new Casella("Via");
    	
    	//Cantiere (name, price, mortgageValue, rentTable, GruppoColore, buildPrice)

		squares[1] = new Cantiere("Vicolo corto",1, 60, 30, new int[] {2,10,30,90,160,250}, marrone, 50);
		//Assegna name=Probabilità con super
		squares[2] = new Probabilita();
		squares[3] = new Cantiere("Vicolo stretto",2, 60, 30, new int[] {4,20,60,180,320,450}, marrone, 50);
		//tassa(name, ammount)
		squares[4] = new Tassa("Tassa patrimoniale",200);
		//Stazione(name, price, mortgageValue, rentTable)
		squares[5] = new Stazione("Stazione nord", 200, 100, new int[] {25,50,100,200});
		squares[6] = new Cantiere("Bastioni Gran Sasso",3, 100, 50, new int[] {6,30,90,270,400,550}, azzurro, 50);
   
		//Assegna name=Imprevisti con super
		squares[7] = new Imprevisti();
		squares[8] = new Cantiere("Viale Monterosa",4, 100, 50, new int[] {6,30,90,270,400,550}, azzurro, 50);
		squares[9] = new Cantiere("Viale Vesuvio",5, 120, 60, new int[] {8,40,100,300,450,600}, azzurro, 50);
		squares[10] = new Casella("Prigione");
		squares[11] = new Cantiere("Via Accademia",6, 140, 70, new int[] {10,50,150,450,625,750}, viola, 100);
		//Società(name, price, mortgageValue, rentTable, dado)

		squares[12] = new Societa("Società elettrica", 150, 75, new int[] {4,10} );
		squares[13] = new Cantiere("Corso ateneo",7,  140, 70, new int[] {10,50,150,450,625,750}, viola, 100);
		squares[14] = new Cantiere("Piazza università",8, 160, 80, new int[] {12,60,180,500,700,900}, viola, 100);
		squares[15] = new Stazione("Stazione est", 200, 100, new int[] {25,50,100,200});
		squares[16] = new Cantiere("Via Verdi",9, 180, 90, new int[] {14,70,200,550,750,950}, arancio, 100);

		squares[17] = new Probabilita();
		squares[18] = new Cantiere("Corso Raffaello",10, 180, 90, new int[] {14,70,200,550,750,950}, arancio, 100);
		squares[19] = new Cantiere("Piazza Dante", 11,200, 100, new int[] {16,80,220,600,800,1000}, arancio, 100);
		squares[20] = new Casella("Parcheggio gratuito");
		squares[21] = new Cantiere("Via Marco Polo",12, 220, 110, new int[] {18,90,250,700,875,1050}, rosso, 150);
		squares[22] = new Imprevisti();

		squares[23] = new Cantiere("Corso Magellano",13, 220, 110, new int[] {18,90,250,700,875,1050}, rosso, 150);
		squares[24] = new Cantiere("Largo Colombo", 14, 240, 120, new int[] {20,100,300,750,925,1100}, rosso, 150);
		squares[25] = new Stazione("Stazione sud", 200, 100, new int[] {25,50,100,200});
		squares[26] = new Cantiere("Viale Costantino",15, 260, 150, new int[] {22,110,330,800,975,1150}, giallo, 150);
		squares[27] = new Cantiere("Viale Traiano",16,  260, 150, new int[] {22,110,330,800,975,1150}, giallo, 150);
		squares[28] = new Societa("Società acqua potabile", 150, 75, new int[] {4,10});
		squares[29] = new Cantiere("Piazza Giulio Cesare",17, 280, 150, new int[] {22,120,360,850,1025,1200}, giallo, 150);

		squares[30] = new VaiInPrigione();
		squares[31] = new Cantiere("Via Roma",18, 300, 200, new int[] {26,130,390,900,1100,1275}, verde, 200);
		squares[32] = new Cantiere("Corso Impero", 19, 300, 200, new int[] {26,130,390,900,1100,1275}, verde, 200);
		squares[33] = new Probabilita();

		squares[34] = new Cantiere("Largo Augusto",20, 320, 200, new int[] {28,150,450,1000,1200,1400}, verde, 200);
		squares[35] = new Stazione("Stazione ovest", 200, 100, new int[] {25,50,100,200});
		squares[36] = new Imprevisti();
		squares[37] = new Cantiere("Viale dei giardini", 21, 350, 175, new int[] {35,175,500,1100,1300,1500}, blu, 200);
		squares[38] = new Tassa("Tassa di lusso", 100);
		squares[39] = new Cantiere("Parco della vittoria", 22, 400, 200, new int[] {50,200,600,1400,1700,2000}, blu, 200);
		}
    
    public GruppoColore getMarrone() {
    	return marrone;
    }
    public GruppoColore getAzzurro() {
    	return azzurro;
    }
    public GruppoColore getViola() {
    	return viola;
    }
    public GruppoColore getArancione() {
    	return arancio;
    }
    public GruppoColore getRosso() {
    	return rosso;
    }
    public GruppoColore getGiallo() {
    	return giallo;
    }
    public GruppoColore getVerde() {
    	return verde;
    }
    public GruppoColore getBlu() {
    	return blu;
    }

	public Casella getSquare(int i) {
		return squares[i];
	}

}