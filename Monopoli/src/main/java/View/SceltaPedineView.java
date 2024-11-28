package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.NomiGiocatoriController;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class SceltaPedineView extends JPanel  {
	/**
	 * Classe per far scegliere ai giocatori la loro pedina (tramite bottone)
	 * seguendo l'ordine di inserimento dei nomi
	 */
	private static final long serialVersionUID = 1L;
	private static SceltaPedineView sceltaPedineView;
	private JPanel setUp;
	private JLabel lblTurnoGiocatore;

	public JButton btnCane;
	public JButton btnCappello;
	public JButton btnCariola;
	public JButton btnNave;
	public JButton btnDitale;
	public JButton btnFerro;
	public JButton btnMacchina;
	public JButton btnStivale;

	private String[] nomiGiocatori;

	private SceltaPedineView() {

		setOpaque(false);
		setBounds(0, 0, 1920, 1080);
		setLayout(null);

		setUp = new JPanel();
		setUp.setBounds(184, 152, 1200, 656);
		setUp.setOpaque(false);
		setUp.setLayout(null);
		add(setUp);

		JLabel lblSceltaPedine = new JLabel("Scelta pedine");
		lblSceltaPedine.setHorizontalAlignment(SwingConstants.CENTER);
		lblSceltaPedine.setFont(new Font("Monopoly Inline", Font.PLAIN, 80));
		lblSceltaPedine.setBounds(384, 30, 443, 92);
		lblSceltaPedine.setOpaque(true);
		lblSceltaPedine.setBackground(new Color(224, 0, 0));
		lblSceltaPedine.setForeground(new Color(255, 255, 255));
		lblSceltaPedine.setBorder(new MatteBorder(3, 3, 3, 3, Color.black));
		setUp.add(lblSceltaPedine);

		lblTurnoGiocatore = new JLabel();
		lblTurnoGiocatore.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurnoGiocatore.setFont(new Font("Monopoly Inline", Font.PLAIN, 50));
		lblTurnoGiocatore.setBounds(232, 175, 735, 77);
		lblTurnoGiocatore.setOpaque(true);
		lblTurnoGiocatore.setBackground(new Color(224, 0, 0));
		lblTurnoGiocatore.setForeground(new Color(255, 255, 255));
		lblTurnoGiocatore.setBorder(new MatteBorder(3, 3, 3, 3, Color.black));
		setUp.add(lblTurnoGiocatore);

		// Pedine da scegliere
		JPanel panel_pedine = new JPanel();
		panel_pedine.setBounds(32, 334, 1136, 166);
		panel_pedine.setOpaque(false);
		panel_pedine.setLayout(null);
		setUp.add(panel_pedine);

		btnCane = new JButton("");
		btnCane.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btnCane.setIcon(new ImageIcon("./icons/cane.png"));
		btnCane.setBounds(55, 43, 80, 80);
		btnCane.setBackground(new Color(192, 226, 202));
		panel_pedine.add(btnCane);

		btnCappello = new JButton("");
		btnCappello.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btnCappello.setIcon(new ImageIcon("./icons/cappello.png"));
		btnCappello.setBounds(190, 43, 80, 80);
		btnCappello.setBackground(new Color(192, 226, 202));
		panel_pedine.add(btnCappello);

		btnCariola = new JButton("");
		btnCariola.setIcon(new ImageIcon("./icons/cariola.png"));
		btnCariola.setBounds(325, 43, 80, 80);
		btnCariola.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btnCariola.setBackground(new Color(192, 226, 202));
		panel_pedine.add(btnCariola);

		btnNave = new JButton("");
		btnNave.setIcon(new ImageIcon("./icons/nave.png"));
		btnNave.setBounds(460, 43, 80, 80);
		btnNave.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btnNave.setBackground(new Color(192, 226, 202));
		panel_pedine.add(btnNave); 

		btnDitale = new JButton("");
		btnDitale.setIcon(new ImageIcon("./icons/ditale.png"));
		btnDitale.setBounds(595, 43, 80, 80);
		btnDitale.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btnDitale.setBackground(new Color(192, 226, 202));
		panel_pedine.add(btnDitale); 

		btnFerro = new JButton();
		btnFerro.setIcon(new ImageIcon("./icons/ferro_da_stiro.png"));
		btnFerro.setBounds(730, 43, 80, 80);
		btnFerro.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btnFerro.setBackground(new Color(192, 226, 202));
		panel_pedine.add(btnFerro); 

		btnMacchina = new JButton("");
		btnMacchina.setIcon(new ImageIcon("./icons/macchina.png"));
		btnMacchina.setBounds(865, 43, 80, 80);
		btnMacchina.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btnMacchina.setBackground(new Color(192, 226, 202));
		panel_pedine.add(btnMacchina);

		btnStivale = new JButton("");
		btnStivale.setIcon(new ImageIcon("./icons/stivale.png"));
		btnStivale.setBounds(1000, 43, 80, 80);
		btnStivale.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		btnStivale.setBackground(new Color(192, 226, 202));
		panel_pedine.add(btnStivale);

		setUp.revalidate();  // Aggiorna il pannello
		setUp.repaint();

	}

	public void resetBottoni() {
		btnCane.setVisible(true);
		btnCappello.setVisible(true);
		btnCariola.setVisible(true);
		btnNave.setVisible(true);
		btnDitale.setVisible(true);
		btnFerro.setVisible(true);
		btnMacchina.setVisible(true);
		btnStivale.setVisible(true);
		lblTurnoGiocatore.setText(nomiGiocatori[0] + " scegli la pedina");
	}

	public synchronized static SceltaPedineView getSceltaPedineView() {
		if(sceltaPedineView==null) {
			sceltaPedineView=new SceltaPedineView();
		}
		sceltaPedineView.nomiGiocatori=NomiGiocatoriController.getNomiGiocatoriController().getNomiGiocatori();
		return sceltaPedineView;
	}

	public void aggiornaTurno(int i) {
		lblTurnoGiocatore .setText(nomiGiocatori[i] + " scegli la pedina");
	}

	public void addBtnCane(ActionListener listener) {

		btnCane.addActionListener(listener);
	}
	public void addBtnCappello(ActionListener listener) {

		btnCappello.addActionListener(listener);
	}
	public void addBtnCariola(ActionListener listener) {

		btnCariola.addActionListener(listener);
	}
	public void addBtnNave(ActionListener listener) {

		btnNave.addActionListener(listener);
	}
	public void addBtnMacchina(ActionListener listener) {

		btnMacchina.addActionListener(listener);
	}
	public void addBtnFerro(ActionListener listener) {

		btnFerro.addActionListener(listener);
	}
	public void addBtnDitale(ActionListener listener) {

		btnDitale.addActionListener(listener);
	}
	public void addBtnStivale(ActionListener listener) {

		btnStivale.addActionListener(listener);
	}

}
