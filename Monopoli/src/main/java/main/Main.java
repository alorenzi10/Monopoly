package main;


import java.io.IOException;
import Controller.MenuController;
import View.SchermataDiGioco;


public class Main {
	
    public static void main(String args[]) throws IOException  {
    	SchermataDiGioco frame = new SchermataDiGioco();
    	new MenuController(frame);
    }
}