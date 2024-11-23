package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class CaricaPartitaView extends JPanel {

	private static final long serialVersionUID = 1L;
	public JPanel setUp;
	private static JTable table;
	private static DefaultTableModel model;
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
        lblCaricaPartita.setFont(new Font("Tahoma", Font.PLAIN, 40));
        setUp.add(lblCaricaPartita);
        
        btnIndietro = new JButton("Indietro");
        btnIndietro.setBounds(10, 415, 152, 60);
        btnIndietro.setFont(new Font("Tahoma", Font.PLAIN, 30));
        setUp.add(btnIndietro);
        
        btnCarica = new JButton("Carica");
        btnCarica.setBounds(10, 215, 152, 60);
        btnCarica.setFont(new Font("Tahoma", Font.PLAIN, 30));
        setUp.add(btnCarica);
        
        btnElimina = new JButton("Elimina");
        btnElimina.setBounds(10, 315, 152, 60);
        btnElimina.setFont(new Font("Tahoma", Font.PLAIN, 30));
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
        model=new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Num. Giocatori");
        model.addColumn("Oraio di salvataggio");
        table.setModel(model);
        scrollPane.setViewportView(table);
        scrollPane.revalidate();
        scrollPane.repaint();
	}
	
	public void aggiungiATabella(String nome,String num, String data) {
		Object[] row=new Object[3];
		row[0]=nome;
		row[1]=num;
		row[2]=data;
		model.addRow(row);
	}
	
	public void nuovaModello() {
		model=new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Num. Giocatori");
        model.addColumn("Oraio di salvataggio");
        table.setModel(model);
	}
	public void aggiorna() {
		 scrollPane.revalidate();
	     scrollPane.repaint();
	}
	public void mostraLabel() {
		
		JLabel lblNoPartiteSalvate = new JLabel("non ci sono partite salvate");
		lblNoPartiteSalvate.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNoPartiteSalvate.setBounds(630, 150, 600, 300);
		setUp.add(lblNoPartiteSalvate);
		setUp.setComponentZOrder(lblNoPartiteSalvate, 0);
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
