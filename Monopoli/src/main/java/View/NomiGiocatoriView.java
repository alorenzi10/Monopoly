package View;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 * Classe per scegliere il nome dei giocatori tramite JTextField
 */

public class NomiGiocatoriView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel setUp;  // Panel interno per i vari step del processo
	private JButton btnIndietro;
	private JButton btnConferma;

	static int numGiocatori; //Alcuni variabili che poi passeremo al modello
	private JTextField[] playerNames;  // Array per i campi di testo dei nomi dei giocatori

	public NomiGiocatoriView(int num) {

		setOpaque(false);
		setBounds(0, 0, 1920, 1080);
		setLayout(null);

		setUp = new JPanel();
		setUp.setBounds(184, 152, 1200, 656);
		setUp.setOpaque(false);
		setUp.setLayout(null);
		add(setUp);

		int numGiocatori = num;

		btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(50, 550, 225, 80);
		btnIndietro.setFont(new Font("Monopoly Inline", Font.PLAIN, 45));
		setUp.add(btnIndietro);  

		JLabel lblInserisciNomi = new JLabel("Inserisci i nomi dei giocatori:");
		lblInserisciNomi.setHorizontalAlignment(SwingConstants.CENTER);
		lblInserisciNomi.setFont(new Font("Monopoly Inline", Font.PLAIN, 45));
		lblInserisciNomi.setBounds(354, 30, 492, 114);
		setUp.add(lblInserisciNomi);

		// Crea un array di JTextField per i nomi dei giocatori
		playerNames = new JTextField[numGiocatori];

		for (int i = 0; i < numGiocatori; i++) {
			JLabel lblNomeGiocatore = new JLabel("Giocatore " + (i + 1) + ":");
			lblNomeGiocatore.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
			lblNomeGiocatore.setBounds(200, 150 + (i * 50), 150, 45);
			setUp.add(lblNomeGiocatore);

			playerNames[i] = new JTextField();
			playerNames[i].setFont(lblNomeGiocatore.getFont());
			playerNames[i].setBounds(355, 150 + (i * 50), 300, 40);
			setUp.add(playerNames[i]);
		}

		// Pulsante per confermare i nomi
		btnConferma = new JButton("Conferma");
		btnConferma.setFont(new Font("Monopoly Inline", Font.PLAIN, 45));
		btnConferma.setBounds(950, 550, 225, 60);
		setUp.add(btnConferma);

		revalidate();
		repaint();   
	}

	// Metodo che mostra un avviso quando manca l'assegnazione del nome ad uno o più giocatori
	public void completaInserimento() {
		JOptionPane.showMessageDialog(NomiGiocatoriView.this, "Tutti i nomi devono essere riempiti!", "Errore", JOptionPane.ERROR_MESSAGE);
	}

	public static int getNumGiocatori() {
		return numGiocatori;
	}

	// Metodo che ritorna le stringhe dei JTextField
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
