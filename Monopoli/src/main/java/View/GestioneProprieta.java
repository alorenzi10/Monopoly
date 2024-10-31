package View;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GestioneProprieta extends JPanel {
	
	

	public GestioneProprieta() {
		setLayout(null);
		setBounds(0, 0, 1540, 845);
		try {
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JPanel panel_sfondo = new JPanel();
		panel_sfondo.setBounds(0, 0, 1540, 845);
		add(panel_sfondo);
		panel_sfondo.setLayout(null);
		
		
		
		
		
		
		JPanel panel_conferma_bancarotta = new JPanel();
		panel_conferma_bancarotta.setBounds(716, 246, 601, 348);
		panel_sfondo.add(panel_conferma_bancarotta);
		panel_conferma_bancarotta.setLayout(null);
		
		JLabel lblConfermaBancarotta = new JLabel("Confermi di voler dichiarare bancarotta?");
		lblConfermaBancarotta.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblConfermaBancarotta.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfermaBancarotta.setBounds(65, 22, 471, 53);
		panel_conferma_bancarotta.add(lblConfermaBancarotta);
		
		JButton btnConfermaBancarotta = new JButton("Confermo");
		btnConfermaBancarotta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConfermaBancarotta.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnConfermaBancarotta.setBounds(47, 234, 230, 82);
		panel_conferma_bancarotta.add(btnConfermaBancarotta);
		
		JButton btnNoBancarotta = new JButton("No, torna al gioco");
		btnNoBancarotta.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNoBancarotta.setBounds(324, 236, 230, 82);
		panel_conferma_bancarotta.add(btnNoBancarotta);
		
		JLabel lblIconaBancarotta = new JLabel("");
		lblIconaBancarotta.setBounds(250, 102, 100, 100);
		panel_conferma_bancarotta.add(lblIconaBancarotta);
		
		
		
		
		
		
		
		JPanel panel = new JPanel() {
			protected void paintComponent(Graphics g) {
			}
		};
		panel.setLayout(null);
		panel.setBounds(125, 125, 525, 525);
		panel_sfondo.add(panel);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAnnulla.setBounds(10, 472, 145, 43);
		panel.add(btnAnnulla);
		
		JLabel lblGestisciLeProprieta = new JLabel("Gestisci le tue proprietà");
		lblGestisciLeProprieta.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestisciLeProprieta.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblGestisciLeProprieta.setBounds(129, 27, 266, 31);
		panel.add(lblGestisciLeProprieta);
		
		JButton btnCostruisci = new JButton("Costruisci");
		btnCostruisci.setBounds(39, 120, 203, 82);
		panel.add(btnCostruisci);
		btnCostruisci.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnIpoteca = new JButton("Ipoteca");
		btnIpoteca.setBounds(281, 120, 203, 82);
		panel.add(btnIpoteca);
		btnIpoteca.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnDemolisci = new JButton("Demolisci");
		btnDemolisci.setBounds(39, 322, 203, 82);
		panel.add(btnDemolisci);
		btnDemolisci.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnDisipoteca = new JButton("Disipoteca");
		btnDisipoteca.setBounds(281, 322, 203, 82);
		panel.add(btnDisipoteca);
		btnDisipoteca.setFont(new Font("Tahoma", Font.PLAIN, 20));
		try {
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getComando() {
		return comando;
	}
}
