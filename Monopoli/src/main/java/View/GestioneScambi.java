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
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GestioneScambi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		add(panel_sfondo, 1);
		
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
		panel_gestione_scambi.setBounds(850, 82, 680, 680);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		panel_gestione_scambi.setBorder(blackline);
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
		lblScambiGiocatori.setBounds(212, 32, 256, 43);
		panel_gestione_scambi.add(lblScambiGiocatori);
		
		JLabel lblScegliGiocatore = new JLabel("Scegli un giocatore con cui contrattare:");
		lblScegliGiocatore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScegliGiocatore.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblScegliGiocatore.setBounds(10, 111, 460, 31);
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
		btnFine.setBounds(10, 627, 140, 43);
		panel_gestione_scambi.add(btnFine);
		
		JButton btnNewButton = new JButton("Alessandro");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(212, 280, 256, 59);
		panel_gestione_scambi.add(btnNewButton);

	}
}
