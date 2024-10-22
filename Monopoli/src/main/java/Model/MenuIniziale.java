package Model;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class MenuIniziale extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Create the frame
     */
    public MenuIniziale() {
    	
    	setOpaque(false);
        setBounds(0, 0, 1920, 1080);
        setLayout(null);

        // Scritta titolo Monopoly
        JLabel title_label = new JLabel("");
        title_label.setIcon(new ImageIcon("./icons/monopoly_menu.png"));
        title_label.setHorizontalAlignment(SwingConstants.CENTER);
        title_label.setBounds(180, 10, 1180, 415);
        add(title_label);
        
        JButton btnNuovaPartita = new JButton("Nuova partita");
        btnNuovaPartita.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		creaNuovaPartita();
        	}
        });
        btnNuovaPartita.setBounds(590, 435, 360, 80);
        btnNuovaPartita.setBackground(new Color(240, 240, 240));
        btnNuovaPartita.setFont(new Font("Tahoma", Font.PLAIN, 39));
        add(btnNuovaPartita);
        
        JButton btnCaricaPartita = new JButton("Carica partita");
        btnCaricaPartita.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					caricaPartita();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
        	}
        });
        btnCaricaPartita.setBounds(590, 545, 360, 80);
        btnCaricaPartita.setBackground(new Color(240, 240, 240));
        btnCaricaPartita.setFont(new Font("Tahoma", Font.PLAIN, 39));
        add(btnCaricaPartita);
        
        JButton btnEsci = new JButton("Esci");
        btnEsci.setBounds(590, 655, 360, 80);
        btnEsci.setBackground(new Color(240, 240, 240));
        add(btnEsci);
        btnEsci.setFont(new Font("Tahoma", Font.PLAIN, 39));
        btnEsci.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Chiude l'applicazione
            }
        });
    }
    
    public void creaNuovaPartita() {    	
    	removeAll();
    	setLayout(null);
        NuovaPartita nuovaPartita = new NuovaPartita();
        nuovaPartita.setBounds(0, 0, getWidth(), getHeight());  // Imposta le dimensioni di NuovaPartita
        add(nuovaPartita);
        revalidate();
        repaint();
    }
    
    public void caricaPartita() throws SQLException {
    	removeAll();
        setLayout(null);
    	CaricaPartita caricaPartita = new CaricaPartita();
        caricaPartita.setBounds(0, 0, getWidth(), getHeight());
        add(caricaPartita);
        revalidate();
        repaint();
    }
}