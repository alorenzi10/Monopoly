package Model;

import java.util.ArrayList;

public class GruppoColore {
	
    private ArrayList<Cantiere> cantieri; // Lista di cantieri che appartengono a questo gruppo
    private String nome; // Il nome del gruppo di colore (es. "Verde", "Blu", ecc.)

    public GruppoColore(String nome) {
    	this.nome = nome;
    	this.cantieri= new ArrayList<>();
    }
    
    public void aggiungiMembro(Cantiere cantiere) {
    	this.cantieri.add(cantiere);
    }

    public ArrayList<Cantiere> getMembri() {
    	return cantieri;
    }

    public String getNome() {
    	return this.nome;
    }

    public int size() {
    	return this.cantieri.size();
    }
}