package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import View.CaricaPartitaView;

public class GestioneDb2 {
	
	 public static String DB_REL_FILE = "./db/mydb.db3";
	 public static String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;
	 static boolean creata=false;

	public static Connection connect() {
		/*if(creata==false) {
			//GestioneDb2(); //singleton tabella? //da fare
		} */
		Connection con=null;
		try {
			Class.forName("org.sqlite.JDBC");
			con=DriverManager.getConnection(DB_URL);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e+"");
		}
		return con;
	
	}
	
	//per salvare partita. da fare
	public static void insert(String nome) { //titolo,     Oggetto monopoly.......
		Connection con=connect();
		PreparedStatement ps=null;
		String sql="INSERT INTO partite(Titolo) VALUES(?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, nome);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	public static DefaultTableModel readData() { //nome, Oggetto monopoly
		
		Connection con=connect();
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
        model.addColumn("Titolo");
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql="SELECT *FROM partite";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
            	Object[] row = new Object[2];
            	row[0]=rs.getString("ID");
            	row[1]= rs.getString("Titolo");
            	model.addRow(row); // Recupera i dati dal database
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return model;
	}
	
	public static void deleteRow(int id) {
		Connection con=connect();
		PreparedStatement ps=null;
		
		String sql="DELETE FROM partite WHERE ID=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			int prova=ps.executeUpdate();
			if(prova==0) {
				CaricaPartitaView.Avviso("L'id che vuoi eliminare non esiste");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}