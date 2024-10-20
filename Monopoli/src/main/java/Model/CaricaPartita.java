package Model;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;

public class CaricaPartita extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel setUp;
	private JTable table;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CaricaPartita frame = new CaricaPartita();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public CaricaPartita() throws SQLException {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1078, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCaricaPartita = new JLabel("Carica partita");
        lblCaricaPartita.setBounds(376, 10, 311, 64);
        lblCaricaPartita.setFont(new Font("Tahoma", Font.PLAIN, 53));
        contentPane.add(lblCaricaPartita);
        
        setUp = new JPanel();
        setUp.setBounds(10, 84, 1044, 485);
        contentPane.add(setUp);
        setUp.setLayout(null);
        
        btnNewButton = new JButton("<- Indietro");
        btnNewButton.setBounds(54, 41, 107, 27);
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        setUp.add(btnNewButton);
        
		// Panel con barra di scorrimento
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(296, 92, 738, 383);
        setUp.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        
        //mostraSalvataggi();
       CreazioneDB.visualizzaDati(table);
        
	}

	public void mostraSalvataggi() throws SQLException {
		
		setUp.setLayout(null);
		
		JLabel lblSelezionaSalvataggio = new JLabel("Partite salvate:");
		lblSelezionaSalvataggio.setBounds(421, 30, 202, 50);
		lblSelezionaSalvataggio.setFont(new Font("Tahoma", Font.PLAIN, 30));
        setUp.add(lblSelezionaSalvataggio);
        
        int numeroRighe = CreazioneDB.contaRighe();
        
        if(numeroRighe == -1) { // Se non ci sono partite salvate allora mostra la label
        	JLabel lblNoPartiteSalvate = new JLabel("non ci sono partite salvate");
        	lblNoPartiteSalvate.setFont(new Font("Tahoma", Font.PLAIN, 30));
        	lblNoPartiteSalvate.setBounds(626, 23, 360, 64);
        	setUp.add(lblNoPartiteSalvate);
        } else {
        	 table = new JTable(new DefaultTableModel(new Object[]{"Data salvataggio", "Giocatori"}, numeroRighe)); // Conta quante righe ha il db e ne crea lo stesso numero nella JTable
        	 table.setBounds(10, 90, 1024, 385);
        	 setUp.add(table);
        }
        
       
        
        
	}
}
