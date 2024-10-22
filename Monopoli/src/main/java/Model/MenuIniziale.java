package Model;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class MenuIniziale extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuIniziale frame = new MenuIniziale();
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
    public MenuIniziale() {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Massimizza la finestra
        setBounds(0, 0, 1920, 1080);
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
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1540, 845);
        contentPane.add(panel);
        panel.setLayout(null);
        panel.setOpaque(false);
        
        
        JLabel title_label = new JLabel("");
        title_label.setIcon(new ImageIcon("./icons/monopoly_menu.png"));
        title_label.setHorizontalAlignment(SwingConstants.CENTER);
        title_label.setBounds(180, 10, 1180, 415);
        panel.add(title_label);
        
        JButton btnNuovaPartita = new JButton("Nuova partita");
        btnNuovaPartita.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		creaNuovaPartita(panel);
        	}
        });
        btnNuovaPartita.setBounds(590, 435, 360, 80);
        btnNuovaPartita.setBackground(new Color(240, 240, 240));
        btnNuovaPartita.setFont(new Font("Tahoma", Font.PLAIN, 39));
        panel.add(btnNuovaPartita);
        
        JButton btnCaricaPartita = new JButton("Carica partita");
        btnCaricaPartita.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					caricaPartita(panel);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
        	}
        });
        btnCaricaPartita.setBounds(590, 545, 360, 80);
        btnCaricaPartita.setBackground(new Color(240, 240, 240));
        btnCaricaPartita.setFont(new Font("Tahoma", Font.PLAIN, 39));
        panel.add(btnCaricaPartita);
        
        JButton btnEsci = new JButton("Esci");
        btnEsci.setBounds(590, 655, 360, 80);
        btnEsci.setBackground(new Color(240, 240, 240));
        panel.add(btnEsci);
        btnEsci.setFont(new Font("Tahoma", Font.PLAIN, 39));
        btnEsci.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Chiude l'applicazione
            }
        });
    }
    
    public void creaNuovaPartita(JPanel panel) {
    	
    	panel.removeAll();
    	panel.setLayout(null);
        NuovaPartita nuovaPartita = new NuovaPartita();
        nuovaPartita.setBounds(0, 0, panel.getWidth(), panel.getHeight());  // Imposta le dimensioni di NuovaPartita
        panel.add(nuovaPartita);
        panel.revalidate();
        panel.repaint();
    }
    
    public void caricaPartita(JPanel panel) throws SQLException {
    	
    	panel.removeAll();
        panel.setLayout(null);
    	CaricaPartita caricaPartita = new CaricaPartita();
        caricaPartita.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        panel.add(caricaPartita);
        panel.revalidate();
        panel.repaint();
    }
}