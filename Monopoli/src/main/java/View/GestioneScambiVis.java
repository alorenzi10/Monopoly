package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class GestioneScambiVis extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public GestioneScambiVis() {
		setLayout(null);
		
		JPanel panel_gestione_scambi = new JPanel();
		panel_gestione_scambi.setBounds(30, 30, 720, 720);
		add(panel_gestione_scambi);
		panel_gestione_scambi.setLayout(null);
		
		JLabel lblContrattazione = new JLabel("Contrattazione");
		lblContrattazione.setBounds(261, 10, 197, 37);
		lblContrattazione.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrattazione.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_gestione_scambi.add(lblContrattazione);
		
		JLabel lblOfferta = new JLabel("Offro:");
		lblOfferta.setBounds(148, 89, 65, 31);
		lblOfferta.setHorizontalAlignment(SwingConstants.CENTER);
		lblOfferta.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_gestione_scambi.add(lblOfferta);
		
		JPanel panel_proprieta_offerta = new JPanel();
		panel_proprieta_offerta.setBounds(10, 130, 340, 497);
		panel_gestione_scambi.add(panel_proprieta_offerta);
		panel_proprieta_offerta.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(64, 446, 212, 36);
		panel_proprieta_offerta.add(textField);
		textField.setColumns(10);
		
		JLabel lbl€ = new JLabel("€");
		lbl€.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbl€.setBounds(290, 452, 21, 25);
		panel_proprieta_offerta.add(lbl€);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(13, 60, 320, 288);
		panel_proprieta_offerta.add(scrollPane);
		
		JLabel lblSelezionaProprieta = new JLabel("Seleziona la proprietà che vuoi scambiare");
		lblSelezionaProprieta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelezionaProprieta.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelezionaProprieta.setBounds(3, 28, 333, 22);
		panel_proprieta_offerta.add(lblSelezionaProprieta);
		
		JLabel lblDenaro = new JLabel("Seleziona denaro");
		lblDenaro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDenaro.setBounds(101, 411, 137, 22);
		panel_proprieta_offerta.add(lblDenaro);
		
		JLabel lblNewLabel = new JLabel("e/o");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(152, 358, 36, 31);
		panel_proprieta_offerta.add(lblNewLabel);
		
		JLabel lblPerDenaro = new JLabel("In cambio di:");
		lblPerDenaro.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerDenaro.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPerDenaro.setBounds(470, 89, 145, 31);
		panel_gestione_scambi.add(lblPerDenaro);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAnnulla.setBounds(86, 650, 140, 43);
		panel_gestione_scambi.add(btnAnnulla);
		
		JPanel panel_proprieta_offerta_1 = new JPanel();
		panel_proprieta_offerta_1.setLayout(null);
		panel_proprieta_offerta_1.setBounds(372, 130, 340, 497);
		panel_gestione_scambi.add(panel_proprieta_offerta_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(64, 446, 212, 36);
		panel_proprieta_offerta_1.add(textField_1);
		
		JLabel lbl€_1 = new JLabel("€");
		lbl€_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbl€_1.setBounds(286, 452, 21, 25);
		panel_proprieta_offerta_1.add(lbl€_1);
		
		JLabel lblSelezionaProprieta_1 = new JLabel("Seleziona la proprietà che vuoi scambiare");
		lblSelezionaProprieta_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelezionaProprieta_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelezionaProprieta_1.setBounds(3, 28, 333, 22);
		panel_proprieta_offerta_1.add(lblSelezionaProprieta_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 60, 320, 288);
		panel_proprieta_offerta_1.add(scrollPane_1);
		
		JLabel lblNewLabel_1 = new JLabel("e/o");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(152, 358, 36, 31);
		panel_proprieta_offerta_1.add(lblNewLabel_1);
		
		JLabel lblDenaro_1 = new JLabel("Seleziona denaro");
		lblDenaro_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDenaro_1.setBounds(101, 411, 137, 22);
		panel_proprieta_offerta_1.add(lblDenaro_1);
		
		JButton btnAccettaOfferta = new JButton("Accetta");
		btnAccettaOfferta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAccettaOfferta.setBounds(492, 650, 140, 43);
		panel_gestione_scambi.add(btnAccettaOfferta);

	}
}
