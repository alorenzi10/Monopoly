package View;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
/**
 * Classe per mostrare la scelta del numero di giocatori tramite bottoni da 2 a 6
 * e la possibilità di tornare indietro al menu iniziale
 */
public class NuovaPartita extends JPanel {

	 private static final long serialVersionUID = 1L;
	 public JPanel setUp;  // Panel interno per i vari step del processo
	 
     private JButton btn_2;
     private JButton btn_3;
     private JButton btn_4;
     private JButton btn_5;
     private JButton btn_6;
     private JButton btnEsci;
     private JButton btnIndietro;

    public NuovaPartita() {
    	
    	setOpaque(false);
    	setBounds(0, 0, 1920, 1080);
    	setLayout(null);
        JLabel labelInizioPartita = new JLabel("Inizio partita");
        labelInizioPartita.setBounds(623, 58, 294, 64);
        labelInizioPartita.setFont(new Font("Tahoma", Font.PLAIN, 50));
        add(labelInizioPartita);
        
        setUp = new JPanel();
        setUp.setBounds(184, 152, 1200, 656);
        setUp.setOpaque(false);
        add(setUp);
        
        setUp.setLayout(null);
        
        JLabel lblSelezionaGiocatori = new JLabel("Inserisci il numero di giocatori");
        lblSelezionaGiocatori.setBounds(396, 30, 408, 50);
        lblSelezionaGiocatori.setFont(new Font("Tahoma", Font.PLAIN, 30));
        setUp.add(lblSelezionaGiocatori);
        
        // Pulsanti per selezionare il numero di giocatori        
        JPanel panel_bottoni = new JPanel();
        panel_bottoni.setBounds(88, 179, 1024, 163);
        panel_bottoni.setOpaque(false);
        setUp.add(panel_bottoni);
        panel_bottoni.setLayout(null);
        
        btn_2 = new JButton("2");
        btn_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btn_2.setBounds(87, 56, 100, 50);
        panel_bottoni.add(btn_2);
        
        btn_3 = new JButton("3");
        btn_3.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btn_3.setBounds(274, 56, 100, 50);
        panel_bottoni.add(btn_3);
        
        btn_4 = new JButton("4");
        btn_4.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btn_4.setBounds(461, 56, 100, 50);
        panel_bottoni.add(btn_4);
        
        btn_5 = new JButton("5");
        btn_5.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btn_5.setBounds(648, 56, 100, 50);
        panel_bottoni.add(btn_5);
        
        btn_6 = new JButton("6");
        btn_6.setFont(new Font("Tahoma", Font.PLAIN, 30));
        btn_6.setBounds(835, 56, 100, 50);
        panel_bottoni.add(btn_6);

        // Pulsante Esci
        btnEsci = new JButton("Esci");
        btnEsci.setBounds(500, 415, 200, 60);
        btnEsci.setFont(new Font("Tahoma", Font.PLAIN, 30));
        setUp.add(btnEsci);
        
        // Bottone indietro
        btnIndietro = new JButton("Indietro");
        btnIndietro.setBounds(50, 415, 200, 60);
        btnIndietro.setFont(new Font("Tahoma", Font.PLAIN, 30));
        setUp.add(btnIndietro);

        setUp.revalidate();  // Aggiorna il pannello
        setUp.repaint();
    }
   
	public JButton getBtnIndietro() {
		return btnIndietro;
	}


	public void addBtn2(ActionListener listener) {
    	
    	btn_2.addActionListener(listener);
    }
	public void addBtn3(ActionListener listener) {
    	
    	btn_3.addActionListener(listener);
    }
	public void addBtn4(ActionListener listener) {
	
		btn_4.addActionListener(listener);	
	}
	public void addBtn5(ActionListener listener) {
	
		btn_5.addActionListener(listener);
	}
	public void addBtn6(ActionListener listener) {
	
		btn_6.addActionListener(listener);
	}

}