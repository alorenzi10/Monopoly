package View;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SchermataDiGioco extends JFrame {

	private static final long serialVersionUID = 1L; 
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public SchermataDiGioco() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Massimizza la finestra
		setBounds(0, 0, 1920, 1080);
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

		setContentPane(contentPane);
		contentPane.setLayout(null);		
		contentPane.setBounds(0, 0, 1540, 845); 
		contentPane.setOpaque(false);
	}
}
