package View;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import java.awt.Color;


public class CaricaPartitaView extends JPanel {

	private static final long serialVersionUID = 1L;
	private static CaricaPartitaView caricaPartitaView;
	private JLabel lblNoPartiteSalvate;
	private boolean labelAttivo=false;
	private JPanel setUp;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnIndietro, btnCarica, btnElimina;
	private JTextField carica, elimina;
	private JScrollPane scrollPane;
	
	private CaricaPartitaView() {
		
		setOpaque(false);
		setBounds(0, 0, 1920, 485);	
		setLayout(null);
	
        setUp = new JPanel();
        setUp.setBounds(0, 0, 1920, 485);
        setUp.setOpaque(false);
        setUp.setLayout(null);
        add(setUp);
        
        JLabel lblCaricaPartita = new JLabel("Carica partita");
        lblCaricaPartita.setForeground(new Color(255, 255, 255));
        lblCaricaPartita.setBackground(new Color(224, 0, 0));
        lblCaricaPartita.setOpaque(true);
        lblCaricaPartita.setHorizontalAlignment(SwingConstants.CENTER);
        lblCaricaPartita.setBounds(724, 41, 471, 87);
        lblCaricaPartita.setBorder(new MatteBorder(3, 3, 3, 3, Color.black));
        lblCaricaPartita.setFont(new Font("Monopoly Inline", Font.PLAIN, 80));
        setUp.add(lblCaricaPartita);
        
        btnIndietro = new JButton("Indietro");
        btnIndietro.setBackground(new Color(192, 226, 202));
        btnIndietro.setBounds(10, 415, 174, 61);
        btnIndietro.setFont(new Font("Monopoly Inline", Font.PLAIN, 45));
        setUp.add(btnIndietro);
        
        btnCarica = new JButton("Carica");
        btnCarica.setBackground(new Color(192, 226, 202));
        btnCarica.setBounds(10, 215, 161, 60);
        btnCarica.setFont(new Font("Monopoly Inline", Font.PLAIN, 45));
        setUp.add(btnCarica);
        
        btnElimina = new JButton("Elimina");
        btnElimina.setBackground(new Color(192, 226, 202));
        btnElimina.setBounds(10, 315, 161, 60);
        btnElimina.setFont(new Font("Monopoly Inline", Font.PLAIN, 45));
        setUp.add(btnElimina);
        
        carica = new JTextField();
        carica.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
        carica.setBounds(191, 215, 152, 60);
        setUp.add(carica);
        
        elimina = new JTextField();
        elimina.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
        elimina.setBounds(191, 315, 152, 60);
        setUp.add(elimina);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(660, 150, 600, 300);
        setUp.add(scrollPane);
        
        table = new JTable();
        nuovoModello();
        scrollPane.setViewportView(table);
        scrollPane.revalidate();
        scrollPane.repaint();
	}
	
	public static synchronized CaricaPartitaView getCaricaPartitaView() {
		if(caricaPartitaView==null) {
			caricaPartitaView=new CaricaPartitaView();
		}
		return caricaPartitaView;
	}
	
	public void aggiungiATabella(String nome,String num, String data) {
		boolean daAggiungere=true;
		Object[] row=new Object[3];
		row[0]=nome;
		row[1]=num;
		row[2]=data;
		for(int i=0; i<model.getRowCount(); i++) {
			if(model.getValueAt(i, 0).equals(nome)) {
				daAggiungere=false;
			}
		}
		if(daAggiungere) {
			model.addRow(row);
		}
	}
	
	public void nuovoModello() {
		model=new DefaultTableModel() {
			private static final long serialVersionUID = 3070725680915610586L;

			@Override
			 public boolean isCellEditable(int row, int column) {
				 return false;
			 }
		};
        model.addColumn("Nome");
        model.addColumn("Num. Giocatori");
        model.addColumn("Oraio di salvataggio");
        table.setModel(model);
	}
	
	public void mostraLabel() {

		lblNoPartiteSalvate = new JLabel("Non ci sono partite salvate");
		lblNoPartiteSalvate.setFont(new Font("Monopoly Inline", Font.PLAIN, 30));
		lblNoPartiteSalvate.setBounds(630, 150, 600, 300);
		setUp.add(lblNoPartiteSalvate);
		setUp.setComponentZOrder(lblNoPartiteSalvate, 0);
		labelAttivo=true;
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
	
	public JPanel getSetUp() {
		return setUp;
	}
	
 }
