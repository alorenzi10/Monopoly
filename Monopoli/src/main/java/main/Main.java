package main;


import java.io.IOException;
import Controller.MenuController;
import View.SchermataDiGioco;
/**
 * Classe per avviare il programma
  */

public class Main {
	
    public static void main(String args[]) throws IOException  {
    	/**
    	 * Creazione del frame che conterrà i vari pannelli della view
    	 * Creazione del controller per il menu iniziale
    	  */
    	SchermataDiGioco frame = new SchermataDiGioco();
    	new MenuController(frame); 
    }
}