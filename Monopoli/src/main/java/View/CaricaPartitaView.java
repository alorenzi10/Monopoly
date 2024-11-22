package View;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Model.GestioneDb2;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;

public class CaricaPartitaView extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JPanel setUp;
	private static JTable table;
	private JButton btnIndietro, btnCarica, btnElimina;
	private JTextField carica, elimina;
	static JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	
	public CaricaPartitaView() {
		
		setOpaque(false);
		setBounds(0, 0, 1920, 485);	
		setLayout(null);
	
        
        setUp = new JPanel();
        setUp.setBounds(0, 0, 1920, 485);
        setUp.setOpaque(false);
        setUp.setLayout(null);
        add(setUp);
        
        JLabel lblCaricaPartita = new JLabel("Carica partita");
        lblCaricaPartita.setBounds(740, 50, 400, 64);
        lblCaricaPartita.setFont(new Font("Monopoly Inline", Font.PLAIN, 40));
        setUp.add(lblCaricaPartita);
        
        btnIndietro = new JButton("Indietro");
        btnIndietro.setBounds(10, 415, 152, 60);
        btnIndietro.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
        setUp.add(btnIndietro);
        
        btnCarica = new JButton("Carica");
        btnCarica.setBounds(10, 215, 152, 60);
        btnCarica.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
        setUp.add(btnCarica);
        
        btnElimina = new JButton("Elimina");
        btnElimina.setBounds(10, 315, 152, 60);
        btnElimina.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
        setUp.add(btnElimina);
        
        carica=new JTextField();
        carica.setBounds(170, 215, 152, 60);
        setUp.add(carica);
        
        elimina=new JTextField();
        elimina.setBounds(170, 315, 152, 60);
        setUp.add(elimina);
        
		// Panel con barra di scorrimento
        scrollPane = new JScrollPane();
        scrollPane.setBounds(614, 150, 600, 300);
        setUp.add(scrollPane);
        
        table = new JTable();
       
        if(GestioneDb2.readData()==null) {
        	mostraLabel();
        }
       
        table.setModel(GestioneDb2.readData());
       
        scrollPane.setViewportView(table);
        scrollPane.revalidate();
        scrollPane.repaint();
	}
	public void aggiorna() {
		table.setModel(GestioneDb2.readData());
		 scrollPane.revalidate();
	        scrollPane.repaint();
	}
	public void mostraLabel() {
		JLabel lblNoPartiteSalvate = new JLabel("non ci sono partite salvate");
		lblNoPartiteSalvate.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
		lblNoPartiteSalvate.setBounds(320, 80, 357, 41);
		setUp.add(lblNoPartiteSalvate);
		setUp.setComponentZOrder(lblNoPartiteSalvate, 0);
	}
	
	public static void Avviso(String text) {
		JOptionPane.showMessageDialog(setUp, text);
	}
	public void addBtnCarica(ActionListener listener) {

		btnCarica.addActionListener(listener);
	}
	public void addBtnElimina(ActionListener listener) {

		btnElimina.addActionListener(listener);
	}
	
	public String getCarica() {
		return carica.getText();
		
	}
	
	public String getElimina() {
		return elimina.getText();
		
	}
	
	public JButton getBtnIndietro() {
		return btnIndietro;
	}
	
	
 }
