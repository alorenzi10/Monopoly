package Model;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1078, 616);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel labelInizioPartita = new JLabel("Inizio partita");
        labelInizioPartita.setBounds(375, 10, 294, 64);
        labelInizioPartita.setFont(new Font("Tahoma", Font.PLAIN, 53));
        contentPane.add(labelInizioPartita);
        
        setUp = new JPanel();
        setUp.setBounds(10, 84, 1044, 485);
        contentPane.add(setUp);

        // Mostra la schermata per selezionare il numero di giocatori
        selezionaNumGiocatori();
    }

    // Prima schermata: Selezione del numero di giocatori
    private void selezionaNumGiocatori() {
        setUp.removeAll();
        setUp.setLayout(null);
        
        JLabel lblSelezionaGiocatori = new JLabel("Inserisci il numero di giocatori");
        lblSelezionaGiocatori.setBounds(318, 30, 408, 50);
        lblSelezionaGiocatori.setFont(new Font("Tahoma", Font.PLAIN, 30));
        setUp.add(lblSelezionaGiocatori);
        
        // Pulsanti per selezionare il numero di giocatori
        for (int i = 2; i <= 6; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.setFont(new Font("Tahoma", Font.PLAIN, 25));
            btn.setBounds(150 + (i - 2) * 150, 150, 100, 50);
            final int NUM_GIOCATORI = i; //ogni pulsante ha il suo
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    numGiocatori = NUM_GIOCATORI;  // Memorizza fuori dal ciclo il numero di giocatori selezionato 
                    inserimentoNomiGiocatori();  // Passa alla schermata per inserire i nomi
                }
            });
            setUp.add(btn);
        }

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
