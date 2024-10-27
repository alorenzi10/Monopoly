package Model;

import java.awt.EventQueue;

import View.SchermataDiGioco;

public class Main {
	
    public static void main(String args[]) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SchermataDiGioco frame = new SchermataDiGioco();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}