package Model;


import java.util.*;

import View.MonopolyGUI;

import java.awt.EventQueue;
import java.time.*;

public class Main {
	
    private Monopoly monopoly;

    public static void main(String args[]) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonopolyGUI frame = new MonopolyGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}