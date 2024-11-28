package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DadiTest {
	
	private Dadi dadi;
	int dado1, dado2, totale;
	
	@BeforeEach
	void setUp() {
		dadi = new Dadi();
	}
	
	//Controllo del valore dei dadi su 100 lanci
	@Test
	public void testDadi() {
		
		for(int i=0; i<1; i++) {
			dadi.roll();
			dado1 = dadi.getDado1();
			dado2 = dadi.getDado2();
			totale = dadi.getTotal();
			
			assertTrue(dado1 >=1 && dado1 <= 6);
			assertTrue(dado2 >=1 && dado2 <= 6);
			assertTrue(totale >=2 && totale <= 12);
		}
	}

	//Controlla che ritorna un valore booleano. Vero se i dadi hanno entrambi gli stessi valori e falso se sono diversi
    @Test
    public void testDadiDoppi () {

    	Dadi dadi = new Dadi();
    	dadi.roll();
    	boolean uguali = dadi.isDouble();
        assertNotNull(uguali);
        for(int i=0; i<100; i++) {
			dadi.roll();
			if(dadi.getDado1()==dadi.getDado2()) {
				assertTrue(dadi.isDouble());
			}else {
				assertFalse(dadi.isDouble());
			}
		}
        
    }
}
