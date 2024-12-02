package Model;

import java.util.ArrayList;

public class GruppoColore {
	
    private ArrayList<Cantiere> cantieri;
    private String nome;

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