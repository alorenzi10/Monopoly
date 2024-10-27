package View;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Model.GestioneDB;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class CaricaPartita extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JPanel setUp;
	private static JTable table;
	private JButton btn_indietro;
	static JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	
	public CaricaPartita() throws SQLException {
		
		setOpaque(false);
		setBounds(0, 0, 1540, 845);	
		setLayout(null);
		JLabel lblCaricaPartita = new JLabel("Carica partita");
        lblCaricaPartita.setBounds(614, 32, 311, 64);
        lblCaricaPartita.setFont(new Font("Tahoma", Font.PLAIN, 53));
        add(lblCaricaPartita);
        
        setUp = new JPanel();
        setUp.setBounds(248, 180, 1044, 485);
        setUp.setOpaque(false);
        setUp.setLayout(null);
        add(setUp);
        
        btn_indietro = new JButton("Indietro");
        btn_indietro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		vaiIndietro();
        	}
        });
        btn_indietro.setBounds(10, 415, 152, 60);
        btn_indietro.setFont(new Font("Tahoma", Font.PLAIN, 30));
        setUp.add(btn_indietro);
        
		// Panel con barra di scorrimento
        scrollPane = new JScrollPane();
        scrollPane.setBounds(258, 51, 738, 383);
        setUp.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        
        GestioneDB.visualizzaDati(table);
	}
	
    public void vaiIndietro() {
    	
    	removeAll();
        MenuIniziale menuIniziale = new MenuIniziale();
		menuIniziale.setBounds(0, 0, 1920, 1080);
		add(menuIniziale);
		revalidate();
		repaint();
    }

	public static void mostraLabel() throws SQLException {
				
		JLabel lblNoPartiteSalvate = new JLabel("non ci sono partite salvate");
		lblNoPartiteSalvate.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNoPartiteSalvate.setBounds(320, 80, 357, 41);
		setUp.add(lblNoPartiteSalvate);
		setUp.setComponentZOrder(lblNoPartiteSalvate, 0);
	}
}
