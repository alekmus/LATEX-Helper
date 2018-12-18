/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoTests;

import helper.dao.MathDao;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Aleksi
 */
public class MathDaoTest {
    private MathDao madao;
   
    
    @Before
    public void setUp() {
        madao = new MathDao("tester.db");
    }
    
    @Test
    public void mathDaoConnectstoDBandReturnsValue() {
        assertEquals("\\frac{a}{b}",madao.find("Fraction"));
    }
    
    @Test 
    public void mathDaoRetrievesNotations(){
        ArrayList list = new ArrayList();
        try {
            list = madao.notations();
        } catch (Exception e) {
            
        }
        boolean gets = list.contains("Sum");
        gets = gets && list.contains("Fraction");
        gets = gets && list.contains("Product");
        assertTrue(gets);
    }
}
