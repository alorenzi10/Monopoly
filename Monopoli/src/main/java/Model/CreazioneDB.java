package Model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CreazioneDB {
	
	public static void main(String[] args) throws SQLException {
		creaTabella();
		inserisciDati();
	}

    public static String DB_REL_FILE = "./db/mydb.db3";
    public static String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;
    
    private final static String NomeTabella = "PARTITE";
    
    public static void creaTabella() throws SQLException {
    	
    	Connection conn = DriverManager.getConnection(DB_URL);
    	try {
    		String sqlCreateTable = "CREATE TABLE IF NOT EXISTS "+NomeTabella+" (" +
    				"ID_PARTITA TEXT PRIMARY KEY, " +
    				"PLAYER TEXT NOT NULL, " +
    				"POS_PEDINA INTEGER NOT NULL, " +
    				"SOLDI INTEGER NOT NULL)";// e altri parametri per caricare le partite
        
    		try (Statement stmt = conn.createStatement()) {
    			stmt.executeUpdate(sqlCreateTable);
    			System.out.println("Tabella creata con successo.");/**/
    		}
    	}catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Esecuzione terminata.");/**/
        }	
    }
    
    //per quando si decide di uscire e salvare la partita
    public static void inserisciDati() throws SQLException {
    	
    	Connection conn = DriverManager.getConnection(DB_URL);
    	try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Inserisci il nome del giocatore: ");//deve farlo la gui
            String username = scanner.nextLine();/**/

            System.out.println("Inserisci la posizione della pedina: ");
            int posPedina = scanner.nextInt();

            System.out.println("Inserisci la quantità di soldi: ");
            int soldi = scanner.nextInt();

            String currentDate = LocalDateTime.now().toString(); // Timestamp corrente come ID_PARTITA

            //da fare per tutti i campi
            String sqlInsert = "INSERT INTO "+NomeTabella+" (ID_PARTITA, PLAYER, POS_PEDINA, SOLDI) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
                pstmt.setString(1, currentDate);
                pstmt.setString(2, username);
                pstmt.setInt(3, posPedina);
                pstmt.setInt(4, soldi);
                pstmt.executeUpdate();
                System.out.println("Dati inseriti con successo.");/**/
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("Esecuzione terminata.");/**/
            }	
        }
    }
    
    //per quando nel menu si vuole far scegliere la partita da caricare
    public static void visualizzaDati(JTable table) throws SQLException {
    	
    	Connection conn = DriverManager.getConnection(DB_URL);
    	String sqlSelect = "SELECT * FROM "+NomeTabella;
    	
    	DefaultTableModel model = new DefaultTableModel();
        try (Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sqlSelect)) {

        	//da far fare alla GUI
            /*System.out.println("Risultati della query:");
            while (resultSet.next()) {			// Per il test
                String idPartita = resultSet.getString("ID_PARTITA");
                String player = resultSet.getString("PLAYER");
                int posPedina = resultSet.getInt("POS_PEDINA");
                int soldi = resultSet.getInt("SOLDI");

                System.out.println("ID_PARTITA: " + idPartita + ", PLAYER: " + player + 
                                   ", POS_PEDINA: " + posPedina + ", SOLDI: " + soldi);
            }*/
            
        	ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnLabel(i));
            }
            
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row); // Recupera i dati dal database
            }
        }
        
        table.setModel(model);
        conn.close();
            
     }
    
    //se si vogliono cancellare salvataggi(???)
    public static void eliminaDati() throws SQLException {
    	
    	Connection conn = DriverManager.getConnection(DB_URL);
    	try (Scanner scanner = new Scanner(System.in)) {
    		String sqlDelete = "DELETE FROM "+NomeTabella+" WHERE ID_PARTITA = ?";
    		PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
    		System.out.println("Enter the ID of the row to delete:");
    		String idDaEliminare = scanner.nextLine();
    		pstmt.setString(1, idDaEliminare);
    		int affectedRows = pstmt.executeUpdate(); 	
    		if (affectedRows > 0) {
                System.out.println("Salvataggio cancellato con successo.");
            } else {
                System.out.println("Non ci sono salvataggi con questo ID.");
            }
        }catch (SQLException e) {
        	System.out.println(e.getMessage());
        } finally {
        	System.out.println("Esecuzione terminata.");
        }
    }
    
    public static int contaRighe() throws SQLException {
    	
    	Connection conn = DriverManager.getConnection(DB_URL);
    	int rowCount = -1; // Default se non ci sono salvataggi precedenti
    	try {
			String sqlCountRows = "SELECT COUNT(*) AS result FROM "+NomeTabella;
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sqlCountRows);
			if(resultSet.next())
				rowCount = resultSet.getInt("result");
		} catch (SQLException e) {
        	System.out.println(e.getMessage());
		}
    	return rowCount;
    }
}
