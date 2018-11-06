/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aleksi
 */
public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void paateLuoRahatOikein(){
        assertEquals(100000, paate.kassassaRahaa());       
    }
   
    @Test
    public void paateLuoTyhjatLounaat(){
         assertEquals(0,paate.edullisiaLounaitaMyyty()+paate.maukkaitaLounaitaMyyty());   
    }
    @Test 
    public void kateisOstoToimiiEdullisesti(){
        assertEquals(60,paate.syoEdullisesti(300));
        assertEquals(100240,paate.kassassaRahaa());
    }
    
    @Test 
    public void kateisOstoToimiiMaukkaasti(){
        assertEquals(100,paate.syoMaukkaasti(500));
        assertEquals(100400,paate.kassassaRahaa());
    }
    
    @Test
    public void edullisetKasvattaaMyyntiä(){
        paate.syoEdullisesti(300);
        assertEquals(1,paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaatKasvattaaMyyntiä(){
        paate.syoMaukkaasti(600);
        assertEquals(1,paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void riitamatonEiKasvataMyyntiä(){
        assertEquals(10,paate.syoMaukkaasti(10));
        assertEquals(10,paate.syoEdullisesti(10));
        assertEquals(0,paate.edullisiaLounaitaMyyty()+paate.maukkaitaLounaitaMyyty());
        assertEquals(100000,paate.kassassaRahaa());
    }
    
    @Test
    public void kortinSummaVahenee(){
        assertTrue(paate.syoMaukkaasti(kortti));
        assertEquals(600,kortti.saldo());
    }
    
    @Test
    public void lounaatKasvavatKorttiostonYhteydessa(){
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(1,paate.edullisiaLounaitaMyyty());
        assertEquals(1,paate.maukkaitaLounaitaMyyty());   
    }
    
    @Test
    public void kortinMaukasYlitysEiMeneNegatiiviseksi(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        assertTrue(!paate.syoMaukkaasti(kortti));
        assertEquals(200,kortti.saldo());
        assertEquals(2,paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortinEdullinenYlitysEiMeneNegatiiviseksi(){
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        assertTrue(!paate.syoEdullisesti(kortti));
        assertEquals(40,kortti.saldo());
        assertEquals(4,paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kassaEiKasvaKorteilla(){
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(100000,paate.kassassaRahaa());
    }
    
    @Test
    public void lataaminenKasvattaaKorttiajaKassaa(){
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(1100,kortti.saldo());
        assertEquals(100100,paate.kassassaRahaa());
    }
    
    @Test
    public void nollanLataaminenEiKasvataKorttia(){
        paate.lataaRahaaKortille(kortti, 0);
        assertEquals(1000,kortti.saldo());
    }
    
    @Test
    public void negatiivisenLataaminenEiTeeMitaan(){
        paate.lataaRahaaKortille(kortti, -1);
        assertEquals(1000,kortti.saldo());
    }
}
