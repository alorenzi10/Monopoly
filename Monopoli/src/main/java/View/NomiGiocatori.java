package View;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NomiGiocatori extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel setUp;  // Panel interno per i vari step del processo
     private JButton btnIndietro;
     private JButton btnConferma;
     
     static int numGiocatori; //Alcuni variabili che poi passeremo al modello
     private JTextField[] playerNames;  // Array per i campi di testo dei nomi dei giocatori
	 
	 public NomiGiocatori(int num) {
		 
		 	setOpaque(false);
	    	setBounds(0, 0, 1920, 1080);
	    	setLayout(null);
	    	
	        setUp = new JPanel();
	        setUp.setBounds(184, 152, 1200, 656);
	        setUp.setOpaque(false);
	        setUp.setLayout(null);
	        add(setUp);

	    	int numGiocatori=num;
	        
	        btnIndietro = new JButton("Indietro");
	        btnIndietro.setBounds(50, 550, 200, 60);
	        btnIndietro.setFont(new Font("Tahoma", Font.PLAIN, 30));
	        setUp.add(btnIndietro);  
	        
	        JLabel lblInserisciNomi = new JLabel("Inserisci i nomi dei giocatori:");
	        lblInserisciNomi.setFont(new Font("Tahoma", Font.PLAIN, 30));
	        lblInserisciNomi.setBounds(396, 30, 408, 50);
	        setUp.add(lblInserisciNomi);

	        // Crea un array di JTextField per i nomi dei giocatori
	        playerNames = new JTextField[numGiocatori];
	        
	        for (int i = 0; i < numGiocatori; i++) {
	            JLabel lblNomeGiocatore = new JLabel("Giocatore " + (i + 1) + ":");
	            lblNomeGiocatore.setFont(new Font("Tahoma", Font.PLAIN, 20));
	            lblNomeGiocatore.setBounds(200, 150 + (i * 40), 150, 30);
	            setUp.add(lblNomeGiocatore);
	            
	            playerNames[i] = new JTextField();
	            playerNames[i].setBounds(350, 150 + (i * 40), 300, 30);
	            setUp.add(playerNames[i]);
	        }

	        // Pulsante per confermare i nomi
	        btnConferma = new JButton("Conferma");
	        btnConferma.setFont(new Font("Tahoma", Font.PLAIN, 30));
	        btnConferma.setBounds(950, 550, 200, 60);
	        setUp.add(btnConferma);

	        revalidate();
	        repaint();   
	    }
	 
	 	public void completaInserimento() {
	 		JOptionPane.showMessageDialog(NomiGiocatori.this, "Tutti i nomi devono essere riempiti!", "Errore", JOptionPane.ERROR_MESSAGE);
	 	}

	    public static int getNumGiocatori() {
			return numGiocatori;
		}

		public JTextField[] getCampoNomi() {
			return playerNames;
			
		}
		
		public void addBtnIndietro(ActionListener listener) {
	    	
	    	btnIndietro.addActionListener(listener);
	    }
		
		public void addBtnConferma(ActionListener listener) {
	    	
	    	btnConferma.addActionListener(listener);
	    }
	    
}
