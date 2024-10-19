package Model;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
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
        labelInizioPartita.setFont(new Font("Tahoma", Font.PLAIN, 53));
        labelInizioPartita.setBounds(385, 10, 294, 64);
        contentPane.add(labelInizioPartita);
        
        setUp = new JPanel();  // Pannello dinamico per cambiare le visualizzazioni
        setUp.setBounds(10, 84, 1044, 485);
        contentPane.add(setUp);
        setUp.setLayout(new GridLayout(1, 0, 0, 0));

        // Mostra la schermata per selezionare il numero di giocatori
        selezionaNumGiocatori();
    }

    // Prima schermata: Selezione del numero di giocatori
    private void selezionaNumGiocatori() {
        setUp.removeAll();  // Rimuove tutti gli elementi dal pannello
        setUp.setLayout(null);  // Disattiva il layout manager per posizionare gli elementi a mano
        
        JLabel lblSelezionaGiocatori = new JLabel("Inserisci il numero di giocatori");
        lblSelezionaGiocatori.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblSelezionaGiocatori.setBounds(350, 50, 450, 50);
        setUp.add(lblSelezionaGiocatori);
        
        // Pulsanti per selezionare il numero di giocatori
        for (int i = 2; i <= 6; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.setFont(new Font("Tahoma", Font.PLAIN, 25));
            btn.setBounds(150 + (i - 2) * 150, 150, 100, 50);
            final int NUM_GIOCATORI = i;
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    numGiocatori = NUM_GIOCATORI;  // Memorizza il numero di giocatori selezionato
                    showNameInputScreen();  // Passa alla schermata per inserire i nomi
                }
            });
            setUp.add(btn);
        }

        // Pulsante Esci
        JButton btnEsci = new JButton("Esci");
        btnEsci.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnEsci.setBounds(400, 300, 200, 50);
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
    private void showNameInputScreen() {
        setUp.removeAll();  // Pulisci il pannello
        setUp.setLayout(null);  // Disattiva il layout manager
        
        JLabel lblInserisciNomi = new JLabel("Inserisci i nomi dei giocatori:");
        lblInserisciNomi.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblInserisciNomi.setBounds(300, 50, 400, 50);
        setUp.add(lblInserisciNomi);

        // Crea un array di JTextField per i nomi dei giocatori
        playerNames = new JTextField[numGiocatori];
        
        for (int i = 0; i < numGiocatori; i++) {
            JLabel lblPlayer = new JLabel("Giocatore " + (i + 1) + ":");
            lblPlayer.setFont(new Font("Tahoma", Font.PLAIN, 20));
            lblPlayer.setBounds(200, 150 + (i * 50), 150, 30);
            setUp.add(lblPlayer);
            
            playerNames[i] = new JTextField();
            playerNames[i].setBounds(350, 150 + (i * 50), 300, 30);
            setUp.add(playerNames[i]);
        }

        // Pulsante per confermare i nomi
        JButton btnConferma = new JButton("Conferma");
        btnConferma.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnConferma.setBounds(400, 400, 200, 50);
        btnConferma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handlePlayerNames();  // Metodo per gestire i nomi inseriti
            }
        });
        setUp.add(btnConferma);

        setUp.revalidate();  // Aggiorna il pannello
        setUp.repaint();
    }

    // Gestione dei nomi dei giocatori inseriti
    private void handlePlayerNames() {
        String[] nomiGiocatori = new String[numGiocatori];
        for (int i = 0; i < numGiocatori; i++) {
            nomiGiocatori[i] = playerNames[i].getText();
            System.out.println("Giocatore " + (i + 1) + ": " + nomiGiocatori[i]);  // Stampare i nomi (puoi aggiungere altre logiche qui)
        }

        // Qui potresti procedere con l'inizio della partita o passare a un'altra fase del gioco.
    }
}
