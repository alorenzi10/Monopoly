package Model;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
        setBounds(100, 100, 1170, 599);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 1136, 542);
        contentPane.add(panel);
        panel.setLayout(null);
        
        
        JLabel title_label = new JLabel("Monopoly");
        title_label.setBounds(418, 5, 299, 85);
        title_label.setFont(new Font("Tahoma", Font.PLAIN, 70));
        panel.add(title_label);
        
        JButton btnNuovaPartita = new JButton("Nuova partita");
        btnNuovaPartita.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		creaNuovaPartita(panel);
        	}
        });
        btnNuovaPartita.setBounds(434, 145, 267, 57);
        btnNuovaPartita.setFont(new Font("Tahoma", Font.PLAIN, 39));
        panel.add(btnNuovaPartita);
        
        JButton btnCaricaPartita = new JButton("Carica partita");
        btnCaricaPartita.setBounds(434, 245, 267, 57);
        btnCaricaPartita.setFont(new Font("Tahoma", Font.PLAIN, 39));
        panel.add(btnCaricaPartita);
        
        JButton btnEsci = new JButton("Esci");
        btnEsci.setBounds(434, 345, 267, 57);
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
        NuovaPartita nuovaPartita = new NuovaPartita();
        panel.setLayout(new BorderLayout());
        panel.add(nuovaPartita.getContentPane(), BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }
}
