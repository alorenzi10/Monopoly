package Controller;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import Model.Monopoly;
import View.CaricaPartitaView;
import View.NuovaPartitaView;
import View.SchermataDiGioco;
public class CaricaPartitaController {
	private SchermataDiGioco frame;
	private static CaricaPartitaView caricaPartita;
	
	public CaricaPartitaController(CaricaPartitaView caricaPartita, SchermataDiGioco frame) {
		CaricaPartitaController.caricaPartita = caricaPartita;
		this.frame = frame;
		
		aggiornaTabella();
		
		frame.add(caricaPartita);
		frame.revalidate();
        frame.repaint();
        
        CaricaPartitaController.caricaPartita.getBtnIndietro().addActionListener(e->tornaMenuIniziale());
        caricaPartita.addBtnCarica(new BtnCarica());
        caricaPartita.addBtnElimina(new BtnElimina());
        
	}
	
	public void aggiornaTabella() {
		try {	
			FileReader reader=new FileReader("partiteMonopoli.json");
			JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            
            // Iterazione su ciascun elemento (stringa JSON)
			if(jsonArray.size()>0) {
            for (JsonElement element : jsonArray) {
                // Parsing della stringa come JSON
                String jsonString = element.getAsString();	
                JsonElement jsonObject = JsonParser.parseString(jsonString);
                
                // Estrazione dei campi
                String nomePartita = jsonObject.getAsJsonObject().get("nomePartita").getAsString();
                String salvataggioDateTime = jsonObject.getAsJsonObject().get("salvataggioDateTime").getAsString();
                String numGiocatori= jsonObject.getAsJsonObject().get("numero_giocatori").getAsString();
                caricaPartita.aggiungiATabella(nomePartita,numGiocatori, salvataggioDateTime);
            	}
			}else {
				caricaPartita.mostraLabel();
			}

        } catch (Exception e) {
            e.printStackTrace();
            File file=new File("partiteMonopoli.json");
			Gson gson=new Gson();
				try {
					file.createNewFile();
					
					JsonArray jsonArray=new JsonArray();
					try(FileWriter writer=new FileWriter("partiteMonopoli.json")){
						gson.toJson(jsonArray,writer);
					}
				}
				catch(IOException e1){
					e1.printStackTrace();
				}
				caricaPartita.mostraLabel();
        }
	}
	
	public void tornaMenuIniziale() {
		caricaPartita.setVisible(false);
		MenuController.getMenuIniziale().setVisible(true);
		
	}
	
	private class BtnCarica implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String nomeCaricamento=caricaPartita.getCarica();
			boolean trovato=false;
			Monopoly monopoly=null;
			List<int[]> coppie = new ArrayList<>();
			
			try {	
	            // Leggi il file JSON
	            FileReader reader = new FileReader("partiteMonopoli.json");
	            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
	            Gson gson = new Gson();
	            
	            for (JsonElement element : jsonArray) {
	            	String jsonString = element.getAsString();	
	                JsonElement jsonObject = JsonParser.parseString(jsonString);
	                String nomePartita = jsonObject.getAsJsonObject().get("nomePartita").getAsString();

	                if (nomePartita.equals(nomeCaricamento)) {
	                	trovato=true;
	                	monopoly=gson.fromJson(jsonObject, Monopoly.class);
	                	
	                	JsonArray giocatoriArray = jsonObject.getAsJsonObject().getAsJsonArray("players");

	                    // Itera sui giocatori
	                    for (JsonElement giocatoreElement : giocatoriArray) {
	                        JsonObject giocatore = giocatoreElement.getAsJsonObject();
	                        String nomeGiocatore = giocatore.get("name").getAsString();
	                        

	                        // Estrai la lista delle proprietà del giocatore
	                        JsonArray proprietaArray = giocatore.getAsJsonArray("listaProprieta");

	                        // Itera sulle proprietà e filtra i cantieri
	                        for (JsonElement proprietaElement : proprietaArray) {
	                            JsonObject proprieta = proprietaElement.getAsJsonObject();

	                                if (proprieta.has("id") && proprieta.has("numCostruzioni")) {
	                                    int id = proprieta.get("id").getAsInt();
	                                    int numCostruzioni = proprieta.get("numCostruzioni").getAsInt();
	                                   
	                                    coppie.add(new int[] {id, numCostruzioni});
	                                }
	                        }

	                        // Aggiungi una riga vuota per separare i giocatori
	                        
	                    }
	                }
	            }
			
			}catch (IOException e1) {
	            e1.printStackTrace();
	        }
			if(trovato) {
				caricaPartita.setVisible(false);
				new MonopolyController(frame, monopoly, coppie);
			}
			else {
				JOptionPane.showMessageDialog(caricaPartita, "Partita non trovata");
			}
			
		}
	}
	private class BtnElimina implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String nomeRimozione=caricaPartita.getElimina();
			try {
	            // Leggi il file JSON
	            FileReader reader = new FileReader("partiteMonopoli.json");
	            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
	            
	            // Trova e rimuovi la partita
	            JsonArray updatedArray = new JsonArray();
	            
	            for (JsonElement element : jsonArray) {
	            	String jsonString = element.getAsString();	
	                JsonElement jsonObject = JsonParser.parseString(jsonString);
	                String nomePartita = jsonObject.getAsJsonObject().get("nomePartita").getAsString();
	                
	                // Se il nome della partita non corrisponde, la aggiungi all'array aggiornato
	                if (!nomePartita.equals(nomeRimozione)) {
	                    updatedArray.add(element);
	                }
	            }
	            
	            // Scrivere il nuovo JSON nel file
	            FileWriter writer = new FileWriter("partiteMonopoli.json");
	            Gson gson = new Gson();
	            gson.toJson(updatedArray, writer);
	            writer.close();

	            
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
			caricaPartita.nuovaModello();
			aggiornaTabella();
			caricaPartita.setUp.revalidate();
			caricaPartita.setUp.repaint();
			
	}
	}
}