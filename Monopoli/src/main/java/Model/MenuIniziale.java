package Model;

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
        
        JLabel title_label = new JLabel("Monopoly");
        title_label.setBounds(428, 10, 299, 92);
        title_label.setFont(new Font("Tahoma", Font.PLAIN, 70));
        contentPane.add(title_label);
        
        JButton btnNuovaPartita = new JButton("Nuova partita");
        btnNuovaPartita.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Apri la nuova finestra
                NuovaPartita nuovaPartita = new NuovaPartita();
                nuovaPartita.setVisible(true);
            }
        });
        btnNuovaPartita.setBounds(444, 129, 267, 62);
        btnNuovaPartita.setFont(new Font("Tahoma", Font.PLAIN, 39));
        contentPane.add(btnNuovaPartita);
        
        JButton btnCaricaPartita = new JButton("Carica partita");
        btnCaricaPartita.setBounds(444, 233, 267, 57);
        btnCaricaPartita.setFont(new Font("Tahoma", Font.PLAIN, 39));
        contentPane.add(btnCaricaPartita);
        
        JButton btnEsci = new JButton("Esci");
        btnEsci.setBounds(444, 338, 267, 57);
        btnEsci.setFont(new Font("Tahoma", Font.PLAIN, 39));
        btnEsci.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Chiude l'applicazione
            }
        });
        contentPane.add(btnEsci);
    }
}
