package Model;

import java.io.File;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class CreazioneDB {

    public static String DB_REL_FILE = "./db/mydb.db3";
    public static String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;
    
    private static String NomeTabella = "PARTITE";
    
    public static void creaTabella(Connection conn) throws SQLException {
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
    public static void inserisciDati(Connection conn) {
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
    public static void visualizzaDati(Connection conn) {
    	String sqlSelect = "SELECT * FROM "+NomeTabella;
        try (Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sqlSelect)) {

        	//da far fare alla GUI
            System.out.println("Risultati della query:");
            while (resultSet.next()) {
                String idPartita = resultSet.getString("ID_PARTITA");
                String player = resultSet.getString("PLAYER");
                int posPedina = resultSet.getInt("POS_PEDINA");
                int soldi = resultSet.getInt("SOLDI");

                System.out.println("ID_PARTITA: " + idPartita + ", PLAYER: " + player + 
                                   ", POS_PEDINA: " + posPedina + ", SOLDI: " + soldi);
            }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        } finally {
        	System.out.println("Esecuzione terminata.");
        }
    }
    
    //se si vogliono cancellare salvataggi???
    public static void eliminaDati(Connection conn) throws SQLException {
    	try (Scanner scanner = new Scanner(System.in)) {
    		String sqlDelete = "DELETE FROM "+NomeTabella+" WHERE ID_PARTITA = ?";
    		PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
    		System.out.println("Enter the ID of the row to delete:");
    		String idDaEliminare = scanner.nextLine();
    		pstmt.setString(1, idDaEliminare);
    		int affectedRows = pstmt.executeUpdate(); 	
    		if (affectedRows > 0) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("No record found with the provided ID.");
            }
        }catch (SQLException e) {
        	System.out.println(e.getMessage());
        } finally {
        	System.out.println("Esecuzione terminata.");
        }
    }
}
