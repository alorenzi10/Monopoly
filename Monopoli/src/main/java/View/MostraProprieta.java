package View;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;

public class MostraProprieta extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public MostraProprieta() {
		setLayout(null);

		setBounds(0, 0, 1920, 1080);
		
		JPanel panel_proprieta_giocatori = new JPanel();
		panel_proprieta_giocatori.setBounds(160, 212, 1600, 656);
		add(panel_proprieta_giocatori);
		panel_proprieta_giocatori.setLayout(null);
		
		JButton btnChiudi = new JButton("X");
		btnChiudi.setFont(new Font("Monopoly Inline", Font.PLAIN, 50));
		btnChiudi.setBounds(1525, 10, 65, 65);
		panel_proprieta_giocatori.add(btnChiudi);
		
		JPanel panel_proprieta = new JPanel();
		panel_proprieta.setBounds(10, 10, 1505, 636);
		panel_proprieta_giocatori.add(panel_proprieta);
		panel_proprieta.setLayout(new GridLayout(2, 3, 0, 0));
	}
}
