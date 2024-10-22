package Model;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class NuovaPartita extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel setUp;  // Panel interno per i vari step del processo
    private int numGiocatori;  // Variabile per memorizzare il numero di giocatori
    private JTextField[] playerNames;  // Array per i campi di testo dei nomi dei giocatori

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NuovaPartita frame = new NuovaPartita();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public NuovaPartita() {
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setOpaque(false);
        setContentPane(contentPane);
        
        JLabel labelInizioPartita = new JLabel("Inizio partita");
        labelInizioPartita.setFont(new Font("Tahoma", Font.PLAIN, 53));
        contentPane.add(labelInizioPartita);
        
        setUp = new JPanel(new GridBagLayout());
        setUp.setOpaque(false);
        contentPane.add(setUp, BorderLayout.CENTER);

        // Mostra la schermata per selezionare il numero di giocatori
        selezionaNumGiocatori();
    }

    // Prima schermata: Selezione del numero di giocatori
    private void selezionaNumGiocatori() {
    	setUp.removeAll();
        setUp.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        
        JLabel lblSelezionaGiocatori = new JLabel("Inserisci il numero di giocatori");
        lblSelezionaGiocatori.setFont(new Font("Tahoma", Font.PLAIN, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5; // Occupa 5 colonne
        gbc.fill = GridBagConstraints.HORIZONTAL; // Si espande orizzontalmente
        gbc.anchor = GridBagConstraints.CENTER; // Centra l'etichetta
        setUp.add(lblSelezionaGiocatori, gbc);
        
     // Pulsanti per selezionare il numero di giocatori
        gbc.gridwidth = 1; // Ogni pulsante occupa una colonna
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE; // Non si espande
        gbc.weightx = 0; // Assegna uno spazio uguale a ciascun pulsante
        gbc.anchor = GridBagConstraints.CENTER; // Centra i pulsanti
        
        // Pulsanti per selezionare il numero di giocatori
        for (int i = 2; i <= 6; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.setFont(new Font("Tahoma", Font.PLAIN, 25));
            gbc.gridx = i + 2;
            setUp.add(btn, gbc);
            final int NUM_GIOCATORI = i; //ogni pulsante ha il suo
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    numGiocatori = NUM_GIOCATORI;  // Memorizza fuori dal ciclo il numero di giocatori selezionato 
                    inserimentoNomiGiocatori();  // Passa alla schermata per inserire i nomi
                }
            });
        }

        // Pulsante Esci
        JButton btnEsci = new JButton("Esci");
        btnEsci.setFont(new Font("Tahoma", Font.PLAIN, 25));
        gbc.gridx = 2; // Centra il pulsante
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0; // Non assegna spazio aggiuntivo
        setUp.add(btnEsci, gbc);
        btnEsci.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Chiude l'applicazione
            }
        });
        
        setUp.revalidate();  // Aggiorna il pannello
        setUp.repaint();
    }

    // Seconda schermata: Inserimento dei nomi dei giocatori
    private void inserimentoNomiGiocatori() {
        setUp.removeAll();  // Pulisci il pannello
        setUp.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        
        JLabel lblInserisciNomi = new JLabel("Inserisci i nomi dei giocatori:");
        lblInserisciNomi.setFont(new Font("Tahoma", Font.PLAIN, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        setUp.add(lblInserisciNomi, gbc);

        // Crea un array di JTextField per i nomi dei giocatori
        playerNames = new JTextField[numGiocatori];
        
        for (int i = 0; i < numGiocatori; i++) {
            JLabel lblNomeGiocatore = new JLabel("Giocatore " + (i + 1) + ":");
            lblNomeGiocatore.setFont(new Font("Tahoma", Font.PLAIN, 20));
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            setUp.add(lblNomeGiocatore, gbc);
            
            playerNames[i] = new JTextField();
            gbc.gridx = 1;
            setUp.add(playerNames[i], gbc);

        }

        // Pulsante per confermare i nomi
        JButton btnConferma = new JButton("Conferma");
        btnConferma.setFont(new Font("Tahoma", Font.PLAIN, 25));
        gbc.gridx = 0;
        gbc.gridy = numGiocatori + 2;
        gbc.gridwidth = 2; // Occupa due colonne
        setUp.add(btnConferma, gbc);
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
