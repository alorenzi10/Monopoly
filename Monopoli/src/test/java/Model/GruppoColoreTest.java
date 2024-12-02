package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GruppoColoreTest {

    private GruppoColore gruppoColore;
    private Cantiere site1;
    private Cantiere site2;

    @BeforeEach
    void setUp() {
        gruppoColore = new GruppoColore("Blu");
        site1 = new Cantiere("Via Verdi", 11, 300, 150, new int[] {11, 12, 13, 14, 15, 16} , gruppoColore, 50);
        site2 = new Cantiere("Via Azzurra", 12, 350, 175, new int[] {17, 18, 19, 20, 21, 22} , gruppoColore, 60);
    }
    
    //Contolla che site1 e site2 vengano aggiungi al guppo
    @Test
    void testAddMember() {
        assertTrue(gruppoColore.getMembri().contains(site1));
        assertTrue(gruppoColore.getMembri().contains(site2));
    }

    //Controlla che vengano restituiti i cantieri corretti
    @Test
    void testGetMembri() {
        ArrayList<Cantiere> membri = gruppoColore.getMembri();

        assertEquals(2, membri.size());
        assertEquals(site1, membri.get(0));
        assertEquals(site2, membri.get(1));
    }

    @Test
    void testGetName() {
        assertEquals("Blu", gruppoColore.getNome());
    }

    @Test
    void testSize() {
        assertEquals(2, gruppoColore.size());
    }
}
