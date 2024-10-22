package Model;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;

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
    	setResizable(false);
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
        	
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        contentPane.add(panel, BorderLayout.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.NONE;
        
        Dimension buttonSize = new Dimension(360, 80); //dimensione predefinita dei bottoni
        
        JLabel title_label = new JLabel("");
        title_label.setIcon(new ImageIcon("./icons/monopoly_menu.jpg"));
        title_label.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(title_label, gbc);
        
        JButton btnNuovaPartita = new JButton("Nuova partita");
        btnNuovaPartita.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		creaNuovaPartita(panel);
        	}
        });
        btnNuovaPartita.setBackground(new Color(240, 240, 240));
        btnNuovaPartita.setFont(new Font("Tahoma", Font.PLAIN, 39));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        btnNuovaPartita.setPreferredSize(buttonSize);
        panel.add(btnNuovaPartita, gbc);
        
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
        btnCaricaPartita.setBackground(new Color(240, 240, 240));
        btnCaricaPartita.setFont(new Font("Tahoma", Font.PLAIN, 39));
        btnCaricaPartita.setPreferredSize(buttonSize);
        gbc.gridy = 2;
        panel.add(btnCaricaPartita, gbc);
        
        JButton btnEsci = new JButton("Esci");
        btnEsci.setBackground(new Color(240, 240, 240));
        btnEsci.setFont(new Font("Tahoma", Font.PLAIN, 39));
        btnEsci.setPreferredSize(buttonSize);
        btnEsci.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Chiude l'applicazione
            }
        });
        gbc.gridy = 3;
        panel.add(btnEsci, gbc);
    }
    
    public void creaNuovaPartita(JPanel panel) {
    	
    	panel.removeAll();
        NuovaPartita nuovaPartita = new NuovaPartita();
        panel.setLayout(new BorderLayout());
        panel.add(nuovaPartita.getContentPane(), BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }
    
    public void caricaPartita(JPanel panel) throws SQLException {
    	
    	panel.removeAll();
        CaricaPartita caricaPartita = new CaricaPartita();
        panel.setLayout(new BorderLayout());
        panel.add(caricaPartita.getContentPane(), BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }
}
