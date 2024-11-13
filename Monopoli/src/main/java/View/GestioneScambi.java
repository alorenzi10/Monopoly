package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestioneScambi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDigitareLaQuantit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestioneScambi frame = new GestioneScambi();
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
	public GestioneScambi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel panel_sfondo = new JPanel();
		panel_sfondo.setBounds(0, 0, 1540, 845);
		panel_sfondo.setOpaque(false);
		panel_sfondo.setLayout(null);
		getContentPane().add(panel_sfondo, 1);
		
		JPanel panel_gestione_scambi = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
        		ImageIcon icon = new ImageIcon("./icons/sfondo2.png"); //carica l'immagine di sfondo
        		Image image = icon.getImage();
        		int panelWidth = getWidth();
        		int imageWidth = image.getWidth(this);
                int imageHeight = image.getHeight(this);
                int newHeight = (imageHeight * panelWidth) / imageWidth;
                g.drawImage(image, 0, 0, panelWidth, newHeight, this); 
			}
		};
		panel_gestione_scambi.setLayout(null);
		panel_gestione_scambi.setBounds(125, 125, 520, 520);
		panel_gestione_scambi.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLACK)); 
		panel_sfondo.add(panel_gestione_scambi);
		
		for (int i = 0; i < NuovaPartita.numGiocatori; i++) {// listener
			if(NuovaPartita.getNomiGiocatori(i) == Monopoly.getGiCorrente().nome)
				i++;
            JButton btnNomeGiocatore = new JButton(NuovaPartita.getNomiGiocatori(i));
            btnNomeGiocatore.setFont(new Font("Tahoma", Font.PLAIN, 20));
            btnNomeGiocatore.setBounds(212, 204 + (i * 75), 256, 60);
            panel_gestione_scambi.add(btnNomeGiocatore);
            
        }

		
		JLabel lblScambiGiocatori = new JLabel("Scambi tra giocatori");
		lblScambiGiocatori.setHorizontalAlignment(SwingConstants.CENTER);
		lblScambiGiocatori.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblScambiGiocatori.setBounds(132, 32, 256, 43);
		panel_gestione_scambi.add(lblScambiGiocatori);
		
		JLabel lblScegliGiocatore = new JLabel("Scegli un giocatore con cui contrattare:");
		lblScegliGiocatore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScegliGiocatore.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblScegliGiocatore.setBounds(25, 111, 470, 31);
		panel_gestione_scambi.add(lblScegliGiocatore);
		
		JButton btnFine = new JButton("Fine");
		btnFine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonsState(true);
				remove(panel_sfondo);
				revalidate();
				repaint();
			}
		});
		btnFine.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFine.setBounds(10, 467, 140, 43);
		panel_gestione_scambi.add(btnFine);
		
		JButton btnNewButton = new JButton("Alessandro");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(171, 204, 180, 40);
		panel_gestione_scambi.add(btnNewButton);
		
		JPanel panel_gestione_scambi_1 = new JPanel() {
			protected void paintComponent(Graphics g) {
			}
		};
		panel_gestione_scambi_1.setLayout(null);
		panel_gestione_scambi_1.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLACK));
		panel_gestione_scambi_1.setBounds(799, 83, 520, 520);
		panel_sfondo.add(panel_gestione_scambi_1);
		
		JLabel lblContrattazione = new JLabel("Contrattazione");
		lblContrattazione.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrattazione.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblContrattazione.setBounds(132, 32, 256, 43);
		panel_gestione_scambi_1.add(lblContrattazione);
		
		JLabel lblOfferta = new JLabel("Offro:");
		lblOfferta.setHorizontalAlignment(SwingConstants.CENTER);
		lblOfferta.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblOfferta.setBounds(25, 111, 470, 31);
		panel_gestione_scambi_1.add(lblOfferta);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAnnulla.setBounds(10, 467, 140, 43);
		panel_gestione_scambi_1.add(btnAnnulla);
		
		JPanel panel_proprieta_offerta = new JPanel();
		panel_proprieta_offerta.setBounds(10, 150, 500, 106);
		panel_gestione_scambi_1.add(panel_proprieta_offerta);
		
		JPanel panel_denaro_offerta = new JPanel();
		panel_denaro_offerta.setBounds(10, 266, 500, 191);
		panel_gestione_scambi_1.add(panel_denaro_offerta);
		panel_denaro_offerta.setLayout(null);
		
		JLabel lblPerDenaro = new JLabel("In cambio di:");
		lblPerDenaro.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerDenaro.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPerDenaro.setBounds(61, 10, 377, 50);
		panel_denaro_offerta.add(lblPerDenaro);
		
		JTextField txtDigitareLaQuantit = new JTextField();
		txtDigitareLaQuantit.setText("Digitare la quantità di denaro desiderata");
		txtDigitareLaQuantit.setBounds(144, 64, 212, 36);
		panel_denaro_offerta.add(txtDigitareLaQuantit);
		txtDigitareLaQuantit.setColumns(10);
		
		JLabel lbl€ = new JLabel("€");
		lbl€.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbl€.setBounds(366, 70, 21, 25);
		panel_denaro_offerta.add(lbl€);
		
		JButton btnAccettaOfferta = new JButton("Accetta");
		btnAccettaOfferta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAccettaOfferta.setBounds(73, 124, 106, 36);
		panel_denaro_offerta.add(btnAccettaOfferta);
		
		JButton btnRifiutaOfferta = new JButton("Rifiuta");
		btnRifiutaOfferta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRifiutaOfferta.setBounds(304, 124, 106, 36);
		panel_denaro_offerta.add(btnRifiutaOfferta);

	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
