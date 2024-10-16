package IngSftw.Monopoly;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DadiTest {
	
	@Test
	public void testDadi() {
		Dadi dadi=new Dadi();
		int dado1, dado2, totale;
		
		//controllo del valore dei dadi su 100 lanci
		for(int i=0; i<1; i++) {
			dadi.roll();
			dado1=dadi.getDado1();
			dado2=dadi.getDado2();
			totale=dadi.getTotal();
			
			assertTrue(dado1 >=1 && dado1<=6);
			assertTrue(dado2 >=1 && dado2<=6);
			assertTrue(totale >=2 && totale<=12);
			
		}
	}

	//controlla che ritorna un valore booleano
    @Test
    public void testDadiDoppi () {
		//controlla che ritorna un valore booleano
    	Dadi dadi=new Dadi();
    	dadi.roll();
    	boolean uguali=dadi.isDouble();
    	
        assertNotNull(uguali);
        
    }
}
