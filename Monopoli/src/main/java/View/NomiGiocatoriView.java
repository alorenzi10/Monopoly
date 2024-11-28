package View;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import java.awt.Color;

/**
 * Classe per scegliere il nome dei giocatori tramite JTextField
 */

public class NomiGiocatoriView extends JPanel {

	private static final long serialVersionUID = 1L;
	private static NomiGiocatoriView nomiGiocatoriView;
	private JPanel setUp;  // Panel interno per i vari step del processo
	private JButton btnIndietro;
	private JButton btnConferma;

	//Alcuni variabili che poi passeremo al modello
	private JTextField[] playerNames;  // Array per i campi di testo dei nomi dei giocatori
	private JLabel[] labelArray;

	private NomiGiocatoriView() {

		setOpaque(false);
		setBounds(0, 0, 1920, 1080);
		setLayout(null);

		setUp = new JPanel();
		setUp.setBounds(184, 152, 1200, 656);
		setUp.setOpaque(false);
		
		add(setUp);
		setUp.setLayout(null);

		btnIndietro = new JButton("Indietro");
		btnIndietro.setBackground(new Color(192, 226, 202));
		btnIndietro.setBounds(50, 550, 225, 80);
		btnIndietro.setFont(new Font("Monopoly Inline", Font.PLAIN, 45));
		btnIndietro.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		setUp.add(btnIndietro);  

		JLabel lblInserisciNomi = new JLabel("Inserisci i nomi dei giocatori:");
		lblInserisciNomi.setForeground(new Color(255, 255, 255));
		lblInserisciNomi.setBackground(new Color(224, 0, 0));
		lblInserisciNomi.setBorder(new MatteBorder(3, 3, 3, 3, Color.black));
		lblInserisciNomi.setOpaque(true);
		lblInserisciNomi.setHorizontalAlignment(SwingConstants.CENTER);
		lblInserisciNomi.setFont(new Font("Monopoly Inline", Font.PLAIN, 45));
		lblInserisciNomi.setBounds(354, 60, 507, 65);
		setUp.add(lblInserisciNomi);

		// Pulsante per confermare i nomi
		btnConferma = new JButton("Conferma");
		btnConferma.setBackground(new Color(192, 226, 202));
		btnConferma.setFont(new Font("Monopoly Inline", Font.PLAIN, 45));
		btnConferma.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btnConferma.setBounds(950, 550, 225, 80);
		setUp.add(btnConferma);

		setUp.revalidate();
		setUp.repaint();   
	}
	
	public void creaJTextField(int numGiocatori1) {
		
		if(labelArray!=null && playerNames!=null) {
			for(JLabel a: labelArray) {
				setUp.remove(a);
			}
			for(JTextField b: playerNames) {
				setUp.remove(b);
			}
		}
		// Crea un array di JTextField per i nomi dei giocatori
		int numGiocatori=numGiocatori1;
		playerNames = new JTextField[numGiocatori];
		labelArray=new JLabel[numGiocatori];

		for (int i = 0; i < numGiocatori; i++) {
			JLabel lblNomeGiocatore = new JLabel("Giocatore " + (i + 1) + ":");
			lblNomeGiocatore.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
			lblNomeGiocatore.setBounds(195, 150 + (i * 50), 155, 41);
			lblNomeGiocatore.setBackground(new Color(224, 0, 0));
			lblNomeGiocatore.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
			lblNomeGiocatore.setForeground(new Color(255, 255, 255));
			lblNomeGiocatore.setOpaque(true);
			lblNomeGiocatore.setHorizontalAlignment(SwingConstants.CENTER);
			labelArray[i] = lblNomeGiocatore;
			setUp.add(lblNomeGiocatore);

			playerNames[i] = new JTextField();
			playerNames[i].setFont(lblNomeGiocatore.getFont());
			playerNames[i].setBounds(355, 150 + (i * 50), 300, 40);
			setUp.add(playerNames[i]);
		}
		setUp.revalidate();
		setUp.repaint();   
	}
	
	public synchronized static NomiGiocatoriView getNomiGiocatoriView() {
		if(nomiGiocatoriView == null) {
			nomiGiocatoriView = new NomiGiocatoriView();
		}
		return nomiGiocatoriView;
	}

	// Metodo che mostra un avviso quando manca l'assegnazione del nome ad uno o più giocatori
	public void completaInserimento() {
		JOptionPane.showMessageDialog(NomiGiocatoriView.this, "Tutti i nomi devono essere riempiti!", "Errore", JOptionPane.ERROR_MESSAGE);
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
