package IngSftw.Monopoly;


import java.util.*;
import java.time.*;

public class Tassa extends Casella {

    private int amount;

    public Tassa(String name, int amount) {
    	super(name);
    	this.amount = amount;
    }
    
    public int getAmount() {
        return this.amount;
    }
}