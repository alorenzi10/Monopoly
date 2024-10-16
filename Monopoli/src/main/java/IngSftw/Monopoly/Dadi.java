package IngSftw.Monopoly;

import java.util.Random;

public class Dadi {
	
	private int dado1;
	private int dado2;
	private Random random;

    public Dadi() {
    	random=new Random();
    }

    public void roll() {
    	dado1=random.nextInt(6)+1;
    	dado2=random.nextInt(6)+1;
    }
    
    public int getTotal() {
    	return dado1+dado2;
    }
    
    public int getDado1() {
    	return dado1;
    }
    
    public int getDado2() {
    	return dado2;
    }
  
    public boolean isDouble() {
    	return dado1==dado2;
    }
    
    
}