package Controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javax.swing.JLabel;

import org.junit.jupiter.api.Test;

import Model.Monopoly;
import View.MonopolyGUI;
import View.SchermataDiGioco;

class MonopolyControllerTest {

	private MonopolyController monopolyController;
    private SchermataDiGioco frame;
    private Monopoly monopoly;
    private MonopolyGUI monopolyGUI;

    void setUp() {
        // Creazione dell'ambiente necessario
        frame = new SchermataDiGioco(); 
        monopolyController = new MonopolyController(frame);
        monopoly = monopolyController.getMonopoly(); // Accesso al modello
        monopolyGUI = MonopolyController.getMonopolyGUI(); // Accesso alla GUI
    }

    
	// Voglio testare che all'inizio del gioco tutte le pedine siano alla posizone 0
    @Test
    void testPedineInizialmenteInPosizioneZero() {
        // Creo una lista di pedine
        ArrayList<JLabel> pedine = new ArrayList<>();
        pedine.add(new JLabel(""));
        // Controlla che ciascuna pedina sia inizialmente nella casella Via!
        for (JLabel pedina : pedine) {
        	assert.cont
           
        }
    }
}
