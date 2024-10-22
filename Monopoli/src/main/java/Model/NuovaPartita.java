package Model;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class NuovaPartita extends JPanel {

	 private static final long serialVersionUID = 1L;
	 private JPanel setUp;  // Panel interno per i vari step del processo
	 private int numGiocatori;  // Variabile per memorizzare il numero di giocatori
	 private JTextField[] playerNames;  // Array per i campi di testo dei nomi dei giocatori

    public NuovaPartita() {
    	
    	setOpaque(false);
    	setBounds(0, 0, 1540, 845);
    	setLayout(null);
        JLabel labelInizioPartita = new JLabel("Inizio partita");
        labelInizioPartita.setBounds(623, 58, 294, 64);
        labelInizioPartita.setFont(new Font("Tahoma", Font.PLAIN, 53));
        add(labelInizioPartita);
        
        setUp = new JPanel();
        setUp.setBounds(248, 208, 1044, 521);
        setUp.setOpaque(false);
        add(setUp);

        // Mostra la schermata per selezionare il numero di giocatori
        selezionaNumGiocatori();
    }

    // Prima schermata: Selezione del numero di giocatori
    private void selezionaNumGiocatori() {
        setUp.setLayout(null);
        
        JLabel lblSelezionaGiocatori = new JLabel("Inserisci il numero di giocatori");
        lblSelezionaGiocatori.setBounds(318, 30, 408, 50);
        lblSelezionaGiocatori.setFont(new Font("Tahoma", Font.PLAIN, 30));
        setUp.add(lblSelezionaGiocatori);
        
        // Pulsanti per selezionare il numero di giocatori        
        JPanel panel_bottoni = new JPanel();
        panel_bottoni.setBounds(10, 90, 1024, 163);
        panel_bottoni.setOpaque(false);
        setUp.add(panel_bottoni);
        panel_bottoni.setLayout(null);
        
        JButton btn_2 = new JButton("2");
        btn_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btn_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numGiocatori = 2;
                inserimentoNomiGiocatori();  // Passa alla schermata per inserire i nomi
            }
        });
        btn_2.setBounds(87, 56, 100, 50);
        panel_bottoni.add(btn_2);
        
        JButton btn_3 = new JButton("3");
        btn_3.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btn_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numGiocatori = 3;  
                inserimentoNomiGiocatori();  // Passa alla schermata per inserire i nomi
            }
        });
        btn_3.setBounds(274, 56, 100, 50);
        panel_bottoni.add(btn_3);
        
        JButton btn_4 = new JButton("4");
        btn_4.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btn_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numGiocatori = 4; 
                inserimentoNomiGiocatori();  // Passa alla schermata per inserire i nomi
            }
        });
        btn_4.setBounds(461, 56, 100, 50);
        panel_bottoni.add(btn_4);
        
        JButton btn_5 = new JButton("5");
        btn_5.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btn_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numGiocatori = 5; 
                inserimentoNomiGiocatori();  // Passa alla schermata per inserire i nomi
            }
        });
        btn_5.setBounds(648, 56, 100, 50);
        panel_bottoni.add(btn_5);
        
        JButton btn_6 = new JButton("6");
        btn_6.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btn_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numGiocatori = 6;  
                inserimentoNomiGiocatori();  // Passa alla schermata per inserire i nomi
            }
        });
        btn_6.setBounds(835, 56, 100, 50);
        panel_bottoni.add(btn_6);

        // Pulsante Esci
        JButton btnEsci = new JButton("Esci");
        btnEsci.setBounds(422, 300, 200, 50);
        btnEsci.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnEsci.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Chiude l'applicazione
            }
        });
        setUp.add(btnEsci);

        setUp.revalidate();  // Aggiorna il pannello
        setUp.repaint();
    }

    // Seconda schermata: Inserimento dei nomi dei giocatori
    private void inserimentoNomiGiocatori() {
        setUp.removeAll();  // Pulisci il pannello
        setUp.setLayout(null);
        
        JLabel lblInserisciNomi = new JLabel("Inserisci i nomi dei giocatori:");
        lblInserisciNomi.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblInserisciNomi.setBounds(300, 50, 400, 50);
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
        JButton btnConferma = new JButton("Conferma");
        btnConferma.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnConferma.setBounds(400, 400, 200, 50);
        btnConferma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	// Controllo che i campi dei nomi giocatori non siano vuoti
            	
            	if (controlloNomeGiocatori()) {
                    JOptionPane.showMessageDialog(NuovaPartita.this, "Nomi confermati!");
                } else {
                    JOptionPane.showMessageDialog(NuovaPartita.this, "Tutti i nomi devono essere riempiti!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            	
                handlePlayerNames();  // Metodo per gestire i nomi inseriti
            }
        });
        setUp.add(btnConferma);

        setUp.revalidate();  // Aggiorna il pannello
        setUp.repaint();
        
    }
    
    // Funzione controllo campi nomi
    private boolean controlloNomeGiocatori() {
        for (JTextField field : playerNames) {
            if (field.getText().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    // Gestione dei nomi dei giocatori inseriti
    private void handlePlayerNames() {
        String[] nomiGiocatori = new String[numGiocatori];
        for (int i = 0; i < numGiocatori; i++) {
            nomiGiocatori[i] = playerNames[i].getText();
            System.out.println("Giocatore " + (i + 1) + ": " + nomiGiocatori[i]);
        }
    }
    
}