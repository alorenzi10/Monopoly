package Model;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextField;
import View.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class NuovaPartita extends JPanel {

	 private static final long serialVersionUID = 1L;
	 private JPanel setUp;  // Panel interno per i vari step del processo
	 private static int numGiocatori;  // Variabile per memorizzare il numero di giocatori
	 private JTextField[] playerNames;  // Array per i campi di testo dei nomi dei giocatori
	 String[] nomiGiocatori;
	 private ArrayList<Player> giocatori = new ArrayList<>();
	 private MonopolyGUI monopolyGUI = new MonopolyGUI();
     int indice = 0;
     static String[] pedineScelte;

    public NuovaPartita() {
    	
    	setOpaque(false);
    	setBounds(0, 0, 1540, 845);
    	setLayout(null);
        JLabel labelInizioPartita = new JLabel("Inizio partita");
        labelInizioPartita.setBounds(623, 58, 294, 64);
        labelInizioPartita.setFont(new Font("Tahoma", Font.PLAIN, 53));
        add(labelInizioPartita);
        
        setUp = new JPanel();
        setUp.setBounds(184, 152, 1200, 656);
        setUp.setOpaque(false);
        add(setUp);

        // Mostra la schermata per selezionare il numero di giocatori
        selezionaNumGiocatori();
    }

    // Prima schermata: Selezione del numero di giocatori
    private void selezionaNumGiocatori() {
        setUp.setLayout(null);
        
        JLabel lblSelezionaGiocatori = new JLabel("Inserisci il numero di giocatori");
        lblSelezionaGiocatori.setBounds(396, 30, 408, 50);
        lblSelezionaGiocatori.setFont(new Font("Tahoma", Font.PLAIN, 30));
        setUp.add(lblSelezionaGiocatori);
        
        // Pulsanti per selezionare il numero di giocatori        
        JPanel panel_bottoni = new JPanel();
        panel_bottoni.setBounds(88, 179, 1024, 163);
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
        btnEsci.setBounds(500, 415, 200, 60);
        btnEsci.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btnEsci.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Chiude l'applicazione
            }
        });
        setUp.add(btnEsci);
        
        // Bottone indietro
        JButton btn_indietro = new JButton("Indietro");
        btn_indietro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		vaiIndietro();
        	}
        });
        btn_indietro.setBounds(50, 415, 200, 60);
        btn_indietro.setFont(new Font("Tahoma", Font.PLAIN, 30));
        setUp.add(btn_indietro);

        setUp.revalidate();  // Aggiorna il pannello
        setUp.repaint();
    }

    
    // Seconda schermata: Inserimento dei nomi dei giocatori
    private void inserimentoNomiGiocatori() {
        setUp.removeAll();  // Pulisci il pannello
        setUp.setLayout(null);
        
        JLabel lblInserisciNomi = new JLabel("Inserisci i nomi dei giocatori:");
        lblInserisciNomi.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblInserisciNomi.setBounds(396, 30, 408, 50);
        setUp.add(lblInserisciNomi);

        nomiGiocatori = new String[numGiocatori];
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
        btnConferma.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btnConferma.setBounds(950, 550, 200, 60);
        btnConferma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	// Controllo che i campi dei nomi giocatori non siano vuoti
            	
            	if (controlloNomeGiocatori()) {
                    JOptionPane.showMessageDialog(NuovaPartita.this, "Nomi confermati!");
                    
                    handlePlayerNames();  // Metodo per gestire i nomi inseriti
                    scegliPedina();
                } else {
                    JOptionPane.showMessageDialog(NuovaPartita.this, "Tutti i nomi devono essere riempiti!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        setUp.add(btnConferma);
        
     // Bottone indietro
        JButton btn_indietro = new JButton("Indietro");
        btn_indietro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		vaiIndietro();
        	}
        });
        btn_indietro.setBounds(50, 550, 200, 60);
        btn_indietro.setFont(new Font("Tahoma", Font.PLAIN, 30));
        setUp.add(btn_indietro);        
    
        revalidate();
        repaint();   
    }
    
    
    public void scegliPedina() {
    	
    	setUp.removeAll();  // Pulisci il pannello
        setUp.setLayout(null);
        
        pedineScelte = new String[numGiocatori];
        
    	JLabel lblSceltaPedine = new JLabel("Scelta pedine");
        lblSceltaPedine.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblSceltaPedine.setBounds(415, 30, 408, 50);
        setUp.add(lblSceltaPedine);
        
        JButton btnConferma = new JButton("Conferma");
        btnConferma.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btnConferma.setBounds(950, 550, 200, 60);
        btnConferma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	creaTabellone();
           	}
        });
        
        JLabel lblTurnoGiocatore = new JLabel(nomiGiocatori[indice] + " scegli la pedina");
        lblTurnoGiocatore.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTurnoGiocatore.setBounds(415, 100, 408, 50);
        setUp.add(lblTurnoGiocatore);
        
        // Pedine da scegliere
        JPanel panel_pedine = new JPanel();
        panel_pedine.setBounds(100, 400, 1000, 100);
        panel_pedine.setOpaque(false);
        panel_pedine.setLayout(null);
        setUp.add(panel_pedine);
        
        JButton btnCane = new JButton("");
        btnCane.setBounds(50, 20, 60, 60);
        try {
			btnCane.setIcon(new ImageIcon(ImageIO.read(new File("./icons/cane.png")).getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        panel_pedine.add(btnCane);
        
        JButton btnCappello = new JButton("");
        btnCappello.setIcon(new ImageIcon("./icons/cappello.png"));
        btnCappello.setBounds(170, 20, 60, 60);
        panel_pedine.add(btnCappello);
        
        JButton btnCariola = new JButton("");
        btnCariola.setIcon(new ImageIcon("./icons/cariola.png"));
        btnCariola.setBounds(290, 20, 60, 60);
        panel_pedine.add(btnCariola);
        
        JButton btnNave = new JButton("");
        btnNave.setIcon(new ImageIcon("./icons/nave.png"));
        btnNave.setBounds(770, 20, 60, 60);
        panel_pedine.add(btnNave); 
        
        JButton btnDitale = new JButton("");
        btnDitale.setIcon(new ImageIcon("./icons/ditale.png"));
        btnDitale.setBounds(410, 20, 60, 60);
        panel_pedine.add(btnDitale); 
        
        JButton btnFerro = new JButton();
        btnFerro.setIcon(new ImageIcon("./icons/ferro_da_stiro.png"));
        btnFerro.setBounds(530, 20, 60, 60);
        panel_pedine.add(btnFerro); 
        
        JButton btnMacchina = new JButton("");
        btnMacchina.setIcon(new ImageIcon("./icons/macchina.png"));
        btnMacchina.setBounds(650, 20, 60, 60);
        panel_pedine.add(btnMacchina);
        
        JButton btnStivale = new JButton("");
        btnStivale.setIcon(new ImageIcon("./icons/stivale.png"));
        btnStivale.setBounds(890, 20, 60, 60);
        panel_pedine.add(btnStivale);

       
        btnCane.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnCane.setVisible(false);
        		pedineScelte[indice] = "Cane";
        		aggiornaTurno(lblTurnoGiocatore, btnConferma);
        	}
        });

        btnCappello.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnCappello.setVisible(false);
        		pedineScelte[indice] = "Cappello";
        		aggiornaTurno(lblTurnoGiocatore, btnConferma);
        	}
        });
        
        btnCariola.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnCariola.setVisible(false);
        		pedineScelte[indice] = "Cariola";
        		aggiornaTurno(lblTurnoGiocatore, btnConferma);
        	}
        });
        
        btnNave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnNave.setVisible(false);
        		pedineScelte[indice] = "Nave";
        		aggiornaTurno(lblTurnoGiocatore, btnConferma);
        	}
        });
        
        btnDitale.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnDitale.setVisible(false);
        		pedineScelte[indice] = "Ditale";
        		aggiornaTurno(lblTurnoGiocatore, btnConferma);
        	}
        });
        
        btnFerro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnFerro.setVisible(false);
        		pedineScelte[indice] = "Ferro";
        		aggiornaTurno(lblTurnoGiocatore, btnConferma);
        	}
        });
        
        btnMacchina.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnMacchina.setVisible(false);
        		pedineScelte[indice] = "Macchina";
        		aggiornaTurno(lblTurnoGiocatore, btnConferma);
        	}
        });
        
        btnStivale.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnStivale.setVisible(false);
        		pedineScelte[indice] = "Stivale";
        		aggiornaTurno(lblTurnoGiocatore, btnConferma);
        	}
        });
        setUp.revalidate();  // Aggiorna il pannello
        setUp.repaint();
       
    }
    
    
 // Funzione per aggiornare il turno di scelta
    public void aggiornaTurno(JLabel lblTurnoGiocatore, JButton btnConferma) {
    	indice++;  // Passa al giocatore successivo
        if (indice < numGiocatori) {
            // Aggiorna la label per il turno del prossimo giocatore
        	lblTurnoGiocatore.setText(nomiGiocatori[indice] + " scegli la pedina");
        } else {
            // Tutti i giocatori hanno scelto, abilita il bottone "Conferma"
        	lblTurnoGiocatore.setText("Tutti i giocatori hanno scelto");
            btnConferma.setEnabled(true);  // Abilita il bottone di conferma
            creaTabellone();
        }
    }
    
    
    public static String[] getPedine() {
    	return pedineScelte;
    }
    
    public static int getNumGiocatore() {
    	return numGiocatori;
    }

    
    protected void creaTabellone() {
    	removeAll();
    	setLayout(null);
        MonopolyGUI monopolyGUI = new MonopolyGUI();
        monopolyGUI.setBounds(0, 0, getWidth(), getHeight());  // Imposta le dimensioni di MonopolyGUI
        add(monopolyGUI);
        revalidate();
        repaint();      
	}

    
	public void vaiIndietro() {
    	
    	removeAll();
        MenuIniziale menuIniziale = new MenuIniziale();
		menuIniziale.setBounds(0, 0, 1920, 1080);
		add(menuIniziale);
		revalidate();
		repaint();
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
        for (int i = 0; i < numGiocatori; i++) {
            nomiGiocatori[i] = playerNames[i].getText();
            System.out.println("Giocatore " + (i + 1) + ": " + nomiGiocatori[i]);
        }
    }
}