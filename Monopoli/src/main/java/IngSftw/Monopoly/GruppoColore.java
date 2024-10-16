package IngSftw.Monopoly;


import java.util.*;
import java.time.*;

public class GruppoColore {
	
    private ArrayList<Cantiere> cantieri;
    private String name;

    public GruppoColore(String name) {
    	this.name = name;
    }
    
    public void addMember(Cantiere cantiere) {
    	this.cantieri.add(cantiere);
    }

    public ArrayList<Cantiere> getMembers() {
    	return cantieri;
    }

    public String getName() {
    	return this.name;
    }

    public int size() {
    	
    }
}