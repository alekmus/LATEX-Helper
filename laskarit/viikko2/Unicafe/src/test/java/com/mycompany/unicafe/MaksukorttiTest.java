package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }
    
    @Test 
    public void saldoOikeinAlussa(){
        assertEquals(10,kortti.saldo());
    }
    
    @Test
    public void rahanLisaaminenKasvattaaSaldoaOikein(){
        kortti.lataaRahaa(10);
        assertEquals(20,kortti.saldo());
    }
    
    @Test
    public void saldoVäheneeOikein(){
        kortti.otaRahaa(5);
        assertEquals(5,kortti.saldo());
    }
    
    @Test
    public void saldoEiMeneNegatiiviseksi(){
        kortti.otaRahaa(15);
        assertEquals(10,kortti.saldo());
    }
    
    @Test
    public void rahanRiittaminenPalauttaaTrue(){
        assertTrue(kortti.otaRahaa(5));
    }
    
    @Test
    public void tulostusToimii(){
        assertEquals("saldo: 0.10",kortti.toString());
    }
    
    @Test
    public void rahanRiittamattomyysPalauttaaFalse(){
        assertTrue(!kortti.otaRahaa(15));
    }
    
    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
}
