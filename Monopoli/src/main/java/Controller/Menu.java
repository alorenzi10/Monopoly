package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import View.MenuIniziale;
import View.NuovaPartita;

public class Menu {
	
	private MenuIniziale frame;
	
	public Menu(MenuIniziale frame) {
	this.frame=frame;
	frame.getBtnNuovaPartita().addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Apri la nuova finestra
            frame.NuovaPartita();
        }
    });
	}
}
