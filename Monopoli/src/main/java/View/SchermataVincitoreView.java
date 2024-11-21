package View;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class SchermataVincitoreView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public SchermataVincitoreView(String nomeVincitore) {
		setBounds(0, 0, 1540, 840);
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		
		JLabel lblNomeGiocatore = new JLabel(nomeVincitore);
		lblNomeGiocatore.setFont(new Font("Monopoly Inline", Font.PLAIN, 60));
		lblNomeGiocatore.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeGiocatore.setBackground(new Color(255, 0, 0));
		lblNomeGiocatore.setBounds(477, 193, 585, 106);
		add(lblNomeGiocatore);
		
		JLabel lblCongratulazioni = new JLabel("Congratulazioni!");
		lblCongratulazioni.setHorizontalAlignment(SwingConstants.CENTER);
		lblCongratulazioni.setBackground(new Color(255, 0, 0));
		lblCongratulazioni.setFont(new Font("Monopoly Inline", Font.PLAIN, 99));
		lblCongratulazioni.setBounds(362, 5, 816, 116);
		add(lblCongratulazioni);
		
		JLabel lblHaiVinto = new JLabel("Hai vinto!");
		lblHaiVinto.setBackground(new Color(255, 0, 0));
		lblHaiVinto.setFont(new Font("Monopoly Inline", Font.PLAIN, 70));
		lblHaiVinto.setBounds(650, 379, 239, 82);
		add(lblHaiVinto);
		
		JPanel panel_bottoni = new JPanel();
		panel_bottoni.setBounds(362, 570, 816, 180);
		add(panel_bottoni);
		panel_bottoni.setLayout(null);
		panel_bottoni.setBackground(new Color(0, 0, 0, 0));
		
		JButton btnMenuPrincipale = new JButton(" Menu principale");
		btnMenuPrincipale.setBounds(84, 56, 396, 67);
		btnMenuPrincipale.setFont(new Font("Monopoly Inline", Font.PLAIN, 50));
		try {
			btnMenuPrincipale.setIcon(new ImageIcon(ImageIO.read(new File("./icons/home.png")).getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel_bottoni.add(btnMenuPrincipale);
		
		JButton btnEsci = new JButton(" Esci");
		btnEsci.setBounds(564, 56, 168, 67);
		btnEsci.setFont(new Font("Monopoly Inline", Font.PLAIN, 50));
		try {
			btnEsci.setIcon(new ImageIcon(ImageIO.read(new File("./icons/exit.png")).getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel_bottoni.add(btnEsci);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane = new JPanel() {
        	private static final long serialVersionUID = 1L;
        	protected void paintComponent(Graphics g) {
        		super.paintComponent(g);
        		ImageIcon icon = new ImageIcon("./icons/sfondo_menu.jpg"); //carica l'immagine di sfondo
        		Image image = icon.getImage();
        		int panelWidth = getWidth();
        		int imageWidth = image.getWidth(this);
                int imageHeight = image.getHeight(this);
                int newHeight = (imageHeight * panelWidth) / imageWidth;
                g.drawImage(image, 0, 0, panelWidth, newHeight, this); 
        	}
        };

        
	}
}