package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class MenuIniziale extends JPanel {

    private static final long serialVersionUID = 1L;
	private JButton btnNuovaPartita;
	private JButton btnCaricaPartita;
	private JButton btnEsci;
	
    public MenuIniziale() {
    	
    	setOpaque(false);
        setBounds(0, 0, 1920, 1080);
        setLayout(null);

        JLabel title_label = new JLabel("");
        title_label.setIcon(new ImageIcon("./icons/monopoly_menu.png"));
        title_label.setHorizontalAlignment(SwingConstants.CENTER);
        title_label.setBounds(180, 10, 1180, 415);
        add(title_label);
        
        btnNuovaPartita = new JButton("Nuova partita");
        btnNuovaPartita.setBounds(590, 435, 360, 80);
        btnNuovaPartita.setBackground(new Color(240, 240, 240));
        btnNuovaPartita.setFont(new Font("Tahoma", Font.PLAIN, 39));
        btnNuovaPartita.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        add(btnNuovaPartita);
        
        btnCaricaPartita = new JButton("Carica partita");
        btnCaricaPartita.setBounds(590, 545, 360, 80);
        btnCaricaPartita.setBackground(new Color(240, 240, 240));
        btnCaricaPartita.setFont(new Font("Tahoma", Font.PLAIN, 39));
        btnCaricaPartita.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        add(btnCaricaPartita);
        
        btnEsci = new JButton("Esci");
        btnEsci.setBounds(590, 655, 360, 80);
        btnEsci.setBackground(new Color(240, 240, 240));
        btnEsci.setFont(new Font("Tahoma", Font.PLAIN, 39));
        btnEsci.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        add(btnEsci);
    }
    
    public void addNuovaPartitaListener(ActionListener listener) {
    	
    	btnNuovaPartita.addActionListener(listener);
    }
    
    public void addCaricaPartitaListener(ActionListener listener) {
    	
    	btnCaricaPartita.addActionListener(listener);
    }
    
    public void addEsciListener(ActionListener listener) {
    	
    	btnEsci.addActionListener(listener);
    }
}