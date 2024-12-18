package View;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;

/**
 * Classe per la schermata del vincitore.
 * Mostra un messaggio di congratulazioni, il nome del vincitore,
 * e consente di scegliere tra tornare al menu principale o uscire dal gioco.
 */
public class SchermataVincitoreView extends JPanel {

	private static final long serialVersionUID = 1L;
	// Istanza Singleton della vista
	private static SchermataVincitoreView schermataVincitoreView;
	private JPanel contentPane;
	private JButton btnMenuPrincipale, btnEsci;
	private JLabel lblNomeGiocatore;
	
	 /**
     * Costruttore privato per il pattern Singleton.
     * Inizializza i componenti dell'interfaccia grafica.
     */
	private SchermataVincitoreView() {
		// Configurazione di base del pannello
		setBounds(0, 0, 1540, 840);
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		// Etichetta di congratulazioni
		JLabel lblCongratulazioni = new JLabel("Congratulazioni!");
		lblCongratulazioni.setForeground(new Color(255, 255, 255));
		lblCongratulazioni.setHorizontalAlignment(SwingConstants.CENTER);
		lblCongratulazioni.setBackground(new Color(224, 0, 0));
		lblCongratulazioni.setFont(new Font("Monopoly Inline", Font.PLAIN, 99));
		lblCongratulazioni.setBounds(434, 74, 671, 116);
		lblCongratulazioni.setBorder(new MatteBorder(3, 3, 3, 3, Color.black));
		lblCongratulazioni.setOpaque(true);
		add(lblCongratulazioni);
		// Etichetta per il nome del vincitore
		lblNomeGiocatore = new JLabel();
		lblNomeGiocatore.setBackground(new Color(224, 0, 0));
		lblNomeGiocatore.setForeground(new Color(255, 255, 255));
		lblNomeGiocatore.setFont(new Font("Monopoly Inline", Font.PLAIN, 65));
		lblNomeGiocatore.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeGiocatore.setOpaque(true);
		lblNomeGiocatore.setBorder(new MatteBorder(3, 3, 3, 3, Color.black));
		lblNomeGiocatore.setBounds(451, 264, 638, 91);
		add(lblNomeGiocatore);
		// Etichetta "Hai vinto!"
		JLabel lblHaiVinto = new JLabel("Hai vinto!");
		lblHaiVinto.setForeground(new Color(255, 255, 255));
		lblHaiVinto.setHorizontalAlignment(SwingConstants.CENTER);
		lblHaiVinto.setBackground(new Color(224, 0, 0));
		lblHaiVinto.setFont(new Font("Monopoly Inline", Font.PLAIN, 70));
		lblHaiVinto.setBounds(626, 429, 287, 82);
		lblHaiVinto.setBorder(new MatteBorder(3, 3, 3, 3, Color.black));
		lblHaiVinto.setOpaque(true);
		add(lblHaiVinto);
		// Pannello contenente i bottoni
		JPanel panel_bottoni = new JPanel();
		panel_bottoni.setBounds(362, 585, 816, 180);
		add(panel_bottoni);
		panel_bottoni.setLayout(null);
		panel_bottoni.setBackground(new Color(0, 0, 0, 0));
		// Bottone per tornare al menu principale
		btnMenuPrincipale = new JButton(" Menu principale");
		btnMenuPrincipale.setBounds(84, 56, 396, 67);
		btnMenuPrincipale.setFont(new Font("Monopoly Inline", Font.PLAIN, 50));
		try {
			btnMenuPrincipale.setIcon(new ImageIcon(ImageIO.read(new File("./icons/home.png")).getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		btnMenuPrincipale.setBackground(new Color(192, 226, 202));
		btnMenuPrincipale.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		panel_bottoni.add(btnMenuPrincipale);
		// Bottone per uscire dall'applicazione
		btnEsci = new JButton(" Esci");
		btnEsci.setBounds(564, 56, 168, 67);
		btnEsci.setFont(new Font("Monopoly Inline", Font.PLAIN, 50));
		try {
			btnEsci.setIcon(new ImageIcon(ImageIO.read(new File("./icons/exit.png")).getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		btnEsci.setBackground(new Color(192, 226, 202));
		btnEsci.setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
		panel_bottoni.add(btnEsci);		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		  // Pannello principale con immagine di sfondo
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
	/**
     * Metodo per ottenere l'istanza Singleton della vista.
     * @return L'istanza di SchermataVincitoreView.
     */
	public synchronized static SchermataVincitoreView getSchermataVincitoreView() {
		if(schermataVincitoreView == null) {
			schermataVincitoreView = new SchermataVincitoreView();
		}
		return schermataVincitoreView;
	}
	 /**
     * Aggiorna il nome del vincitore visualizzato nella schermata.
     * @param nomeVincitore Il nome del vincitore.
     */
	public void aggiornaVincitore(String nomeVincitore) {
		lblNomeGiocatore.setText(nomeVincitore);
	}
	/**
     * Aggiunge un listener al bottone "Menu principale".
     * @param listener Listener da associare al bottone.
     */
	public void addBtnMenuPrincipale(ActionListener listener) {
		btnMenuPrincipale.addActionListener(listener);
	}
	/**
     * Aggiunge un listener al bottone "Esci".
     * @param listener Listener da associare al bottone.
     */
	public void addBtnEsci(ActionListener listener) {
		btnEsci.addActionListener(listener);
	}
}
