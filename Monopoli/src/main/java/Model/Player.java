package Model;

import java.util.*;

public class Player {

    private int id;
    private String name;
    private int wallet;
    private int location;
    private boolean passatoVia;
    private ArrayList<Proprieta> listaProprieta;
    private boolean inPrigione;
    private final int TENTATIVI_MASSIMI_PRIGIONE = 3;
    private int tentativiUscitaPrigione;
    private ArrayList<Carta> carte;
  
    public Player(int id, String name, int wallet, boolean isInJail, int location) {
    	this.id = id;
    	this.name = name;
    	this.wallet = wallet;
    	this.inPrigione = false;
    	this.location = 0;
    	this.listaProprieta=new ArrayList<>();
    	this.carte=new ArrayList<>();
    }
    		
    public boolean controlloFondi(int totale) {
    	if(wallet >= totale) {
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public void doTransaction(int totale) { // Bisognerebbe integrare dentro transaction il controllo dei fondi
    	wallet += totale; //da aggiungere controllo se bastano i soldi
    }
    
    public void muovi(int spostamento) { 
    	location += spostamento;
    	if (location >= Tabellone.NUM_SQUARES) {
    		location -= 40;
    		passatoVia = true;
    	}
    	else {
    		passatoVia = false;
    	}
    	if (location < 0) {
    		location = location + 40;
    	}
    }

	public boolean passaggioVia() {
		return passatoVia;
	}

	public void aggiungiProprieta(Proprieta proprieta) {
		proprieta.setProprietario(this);
		listaProprieta.add(proprieta);
	}
	
	public void rimuoviProprieta(Proprieta proprieta) {
		listaProprieta.remove(proprieta);
	}
	
	public boolean possessoreGruppo(Cantiere cantiere) {
		boolean haTutteProprieta = true;
		GruppoColore gc = cantiere.getGruppoColore();
		for (Cantiere cant : gc.getMembri()) { 
			if (!cant.posseduta() || (cant.posseduta() && cant.getPossessore()!=this)) { //controlla che la proprieta non sia posseduta o se è posseduta controlla che il possessore sia diverso dal player
				haTutteProprieta = false;
			}
		}
		return haTutteProprieta;
	}

	public void fallitoTentativo() {
		tentativiUscitaPrigione++;
	}
	
	public boolean tentativiTerminati() {
		boolean tentativiTerminati = false;
		if(tentativiUscitaPrigione >= TENTATIVI_MASSIMI_PRIGIONE) {
			tentativiTerminati = true;
		}
		return tentativiTerminati;
	}
	
	public void vaiInPrigione() {
		location = Tabellone.POS_PRIGIONE;
		inPrigione = true;
		tentativiUscitaPrigione = 0;
	}

	public void liberaDaPrigione() {
		inPrigione = false;
	}
	
	public int getId() {
		return id;
	}
	
    public int getWallet() {
    	return wallet;
    }
    
    public int getLocation() {
    	return location;
    }
    
    public String getName() {
		return name;
	}

	public boolean getInPrigione() {
		return inPrigione;
	}
	
	public int getTentativi() {
		return tentativiUscitaPrigione;
	}
	
	public void addCarta(Carta carta) {
		carte.add(carta);
	}
	
	public boolean haUscitaGratis() {
		boolean haCarta = false;
		if (carte.size()>0) {
			haCarta = carte.get(0).getAction() == Mazzo.AZIONE_ESCI_DAL_CARCERE;
		}
		return haCarta;
	}
	
	public Carta getCarta() {
		Carta carta = carte.get(0);
		carte.remove(0);
		return carta;
	}
	
    public int getNumCasePossedute() {
		int numCase = 0;
		for (Proprieta p : listaProprieta) {
			if (p instanceof Cantiere) {
				numCase += ((Cantiere) p).getNumCase();
			}
		}
		return numCase;
	}
    
	public int getNumAlberghiPosseduti() {
		int numAlberghi = 0;
		for (Proprieta prop : listaProprieta) {
			if (prop instanceof Cantiere) {
				numAlberghi += ((Cantiere) prop).getNumAlberghi();
			}
		}
		return numAlberghi;
	}

	public int getNumStazioniPossedute() {
		int nStazioni = 0;
		for (Proprieta p : listaProprieta) {
			if (p instanceof Stazione) {
				nStazioni++;
			}
		}
		return nStazioni;
	}
	
	public int getNumSocietaPossedute () {
		int nSocieta = 0;
		for (Proprieta p : listaProprieta) {
			if (p instanceof Societa) {
				nSocieta++;
			}
		}
		return nSocieta;
	}
	
	public ArrayList<Proprieta> getListaProprieta() {
		return listaProprieta;
	}
		
	public String toStringListaProprieta() {
		StringBuilder str = new StringBuilder();
	    for (Proprieta prop : getListaProprieta()) {
	        str.append(prop.getNome()).append(", ");
	    }
	    // Rimuove l'ultima virgola e spazio, se necessario
	    if (str.length() > 0) {
	        str.setLength(str.length() - 2);
	    }
	    return str.toString();
	}

	public ArrayList<String> getListaPropString(){
		
	    ArrayList<String> listaPropString = new ArrayList<>();
		for (Proprieta prop : getListaProprieta()) 
	        listaPropString.add(prop.getNome());
	    return listaPropString;
	}


}