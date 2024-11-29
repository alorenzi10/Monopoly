package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import java.awt.Color;
/**
 * Classe che rappresenta la schermata iniziale del gioco.
 * Mostra tre opzioni principali:
 * - Avviare una nuova partita
 * - Caricare una partita salvata
 * - Uscire dal gioco
 */
public class MenuInizialeView extends JPanel {

	private static final long serialVersionUID = 1L;
	 // Singola istanza della classe (Pattern Singleton)
	private static MenuInizialeView menuInizialeView;
	// Bottoni per le opzioni principali
	private JButton btnNuovaPartita;
	private JButton btnCaricaPartita;
	private JButton btnEsci;
	
	/**
     * Costruttore privato (Singleton).
     * Configura la vista della schermata iniziale e i suoi componenti grafici.
     */
	private MenuInizialeView() {
		// Configurazione base del pannello
		setOpaque(false); // Sfondo trasparente
		setBounds(0, 0, 1920, 1080);// Dimensioni del pannello
		setLayout(null); // Layout assoluto per posizionare gli elementi
		
		// Etichetta per il titolo con immagine
		JLabel title_label = new JLabel("");
		title_label.setIcon(new ImageIcon("./icons/monopoly_menu.png")); // Icona del titolo
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setBounds(180, 10, 1180, 415);  // Posizione e dimensioni
		add(title_label);
		
		// Bottone "Nuova Partita"
		btnNuovaPartita = new JButton("Nuova partita");
		btnNuovaPartita.setForeground(new Color(0, 0, 0));// Colore del testo
		btnNuovaPartita.setBorder(new MatteBorder(2, 2, 2, 2, Color.black)); // Bordo
		btnNuovaPartita.setBounds(590, 435, 360, 80); // Posizione e dimensioni
		btnNuovaPartita.setBackground(new Color(192, 226, 202));  // Colore di sfondo
		btnNuovaPartita.setFont(new Font("Monopoly Inline", Font.PLAIN, 55)); // Font
		add(btnNuovaPartita);
		
		// Bottone "Carica Partita"
		btnCaricaPartita = new JButton("Carica partita");
		btnCaricaPartita.setForeground(new Color(0, 0, 0));
		btnCaricaPartita.setBounds(590, 545, 360, 80);
		btnCaricaPartita.setBackground(new Color(192, 226, 202));
		btnCaricaPartita.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btnCaricaPartita.setFont(new Font("Monopoly Inline", Font.PLAIN, 55));
		add(btnCaricaPartita);
		
		// Bottone "Esci"
		btnEsci = new JButton("Esci");
		btnEsci.setForeground(new Color(0, 0, 0));
		btnEsci.setBounds(590, 655, 360, 80);
		btnEsci.setBackground(new Color(192, 226, 202));
		btnEsci.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btnEsci.setFont(new Font("Monopoly Inline", Font.PLAIN, 55));
		add(btnEsci);

	}
	
	/**
     * Metodo per ottenere l'istanza della vista (Singleton).
     * @return L'istanza della classe MenuInizialeView
     */
	public synchronized static MenuInizialeView getMenuInizialeView() {
		if(menuInizialeView==null) {
			menuInizialeView= new MenuInizialeView();
		}
		return menuInizialeView;
		
	}
	 /**
     * Aggiunge un listener al bottone "Nuova Partita".
     * @param listener ActionListener associato all'azione
     */
	public void addNuovaPartitaListener(ActionListener listener) {
		btnNuovaPartita.addActionListener(listener);
	}
    /**
     * Aggiunge un listener al bottone "Carica Partita".
     * @param listener ActionListener associato all'azione
     */
	public void addCaricaPartitaListener(ActionListener listener) {
		btnCaricaPartita.addActionListener(listener);
	}
    /**
     * Aggiunge un listener al bottone "Esci".
     * @param listener ActionListener associato all'azione
     */
	public void addEsciListener(ActionListener listener) {
		btnEsci.addActionListener(listener);
	}
}