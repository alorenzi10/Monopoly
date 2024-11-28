package View;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import java.awt.Color;
/**
 * Classe per mostrare la scelta del numero di giocatori tramite bottoni da 2 a 6
 * e la possibilità di tornare indietro al menu iniziale
 */
public class NuovaPartitaView extends JPanel {

	private static final long serialVersionUID = 1L;
	private static NuovaPartitaView nuovaPartitaView;
	public JPanel setUp;  // Panel interno per i vari step del processo

	private JButton btn_2;
	private JButton btn_3;
	private JButton btn_4;
	private JButton btn_5;
	private JButton btn_6;
	private JButton btnEsci;
	private JButton btnIndietro;

	private NuovaPartitaView() {

		setOpaque(false);
		setBounds(0, 0, 1920, 1080);
		setLayout(null);
		
		JLabel labelInizioPartita = new JLabel("Inizio partita");
		labelInizioPartita.setForeground(new Color(255, 255, 255));
		labelInizioPartita.setBackground(new Color(224, 0, 0));
		labelInizioPartita.setHorizontalAlignment(SwingConstants.CENTER);
		labelInizioPartita.setBounds(550, 36, 469, 106);
		labelInizioPartita.setOpaque(true);
		labelInizioPartita.setBorder(new MatteBorder(3, 3, 3, 3, Color.black));
		labelInizioPartita.setFont(new Font("Monopoly Inline", Font.PLAIN, 90));
		add(labelInizioPartita);

		setUp = new JPanel();
		setUp.setBounds(184, 152, 1200, 656);
		setUp.setOpaque(false);
		add(setUp);

		setUp.setLayout(null);

		JLabel lblSelezionaGiocatori = new JLabel("Inserisci il numero di giocatori");
		lblSelezionaGiocatori.setForeground(Color.WHITE);
		lblSelezionaGiocatori.setBackground(new Color(224, 0, 0));
		lblSelezionaGiocatori.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelezionaGiocatori.setBounds(340, 30, 520, 63);
		lblSelezionaGiocatori.setBorder(new MatteBorder(3, 3, 3, 3, Color.black));
		lblSelezionaGiocatori.setOpaque(true);
		lblSelezionaGiocatori.setFont(new Font("Monopoly Inline", Font.PLAIN, 45));
		setUp.add(lblSelezionaGiocatori);

		// Pulsanti per selezionare il numero di giocatori        
		JPanel panel_bottoni = new JPanel();
		panel_bottoni.setBounds(88, 179, 1024, 163);
		panel_bottoni.setOpaque(false);
		setUp.add(panel_bottoni);
		panel_bottoni.setLayout(null);

		btn_2 = new JButton("2");
		btn_2.setBackground(new Color(192, 226, 202));
		btn_2.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btn_2.setFont(new Font("Monopoly Inline", Font.PLAIN, 45));
		btn_2.setBounds(66, 44, 125, 75);
		panel_bottoni.add(btn_2);

		btn_3 = new JButton("3");
		btn_3.setBackground(new Color(192, 226, 202));
		btn_3.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btn_3.setFont(new Font("Monopoly Inline", Font.PLAIN, 45));
		btn_3.setBounds(257, 44, 125, 75);
		panel_bottoni.add(btn_3);

		btn_4 = new JButton("4");
		btn_4.setBackground(new Color(192, 226, 202));
		btn_4.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btn_4.setFont(new Font("Monopoly Inline", Font.PLAIN, 45));
		btn_4.setBounds(448, 44, 125, 75);
		panel_bottoni.add(btn_4);

		btn_5 = new JButton("5");
		btn_5.setBackground(new Color(192, 226, 202));
		btn_5.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btn_5.setFont(new Font("Monopoly Inline", Font.PLAIN, 45));
		btn_5.setBounds(639, 44, 125, 75);
		panel_bottoni.add(btn_5);

		btn_6 = new JButton("6");
		btn_6.setBackground(new Color(192, 226, 202));
		btn_6.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btn_6.setFont(new Font("Monopoly Inline", Font.PLAIN, 45));
		btn_6.setBounds(830, 44, 125, 75);
		panel_bottoni.add(btn_6);

		// Pulsante Esci
		btnEsci = new JButton("Esci");
		btnEsci.setBounds(500, 415, 225, 80);
		btnEsci.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btnEsci.setBackground(new Color(192, 226, 202));
		btnEsci.setFont(new Font("Monopoly Inline", Font.PLAIN, 45));
		setUp.add(btnEsci);
		
		// Bottone indietro
		btnIndietro = new JButton("Indietro");
		btnIndietro.setBackground(new Color(192, 226, 202));
		btnIndietro.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btnIndietro.setBounds(50, 415, 225, 80);
		btnIndietro.setFont(new Font("Monopoly Inline", Font.PLAIN, 45));
		setUp.add(btnIndietro);

		setUp.revalidate();  // Aggiorna il pannello
		setUp.repaint();
	}
	
	public synchronized static NuovaPartitaView getNuovaPartitaView() {
		if(nuovaPartitaView==null) {
			nuovaPartitaView=new NuovaPartitaView();
		}
		return nuovaPartitaView;
	}

	public JButton getBtnIndietro() {
		return btnIndietro;
	}

	public void addBtn2(ActionListener listener) {

		btn_2.addActionListener(listener);
	}
	public void addBtn3(ActionListener listener) {

		btn_3.addActionListener(listener);
	}
	public void addBtn4(ActionListener listener) {

		btn_4.addActionListener(listener);	
	}
	public void addBtn5(ActionListener listener) {

		btn_5.addActionListener(listener);
	}
	public void addBtn6(ActionListener listener) {

		btn_6.addActionListener(listener);
	}
	public void addBtnEsci(ActionListener listener) {
		btnEsci.addActionListener(listener);
	}
}