package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

	public class jsonDb {
	
	  private static final String SAVE_FILE = "partiteMonopoli.json";
	  private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	  
	  
	  public static void salvaPartita(Monopoly monopoly) throws IOException {
	        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
	            gson.toJson( monopoly, writer);
	            System.out.println("Dati di gioco salvati con successo!");
	        }
	    }
	  
	
}
