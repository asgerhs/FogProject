/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import data.DatabaseConnector;
import data.mappers.RequestMapper;
import data.mappers.TestDataSourceMySQL;
import data.models.Material;
import data.models.Part;
import data.models.PartList;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author William Sehested Huusfeldt
 */
public class AdvancedCalculatorTest {
    
    private static PartList pl;
    private ArrayList<Part> wood;
    private ArrayList<Part> misc;
    
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Setup test for calculating PartList");
        
//        pl = new PartList();
//        pl.addMiscPart(new Part());
//        pl.addWoodPart(new Part());
    }
    
    

    /**
     * Test of getParts method, of class AdvancedCalculator.
     * 
     */
    @Test
    public void testGetPrice() {
        System.out.println("Tests for price");
        AdvancedCalculator instance = new AdvancedCalculator(7800, 6500, false, 0, 0, false);
        
        int price = 0;
        int expectedTotalPrice = 1906912;
        
        for (Part p : instance.getParts().getWoodList()) {
            price += p.getPrice();
        }
        for (Part p : instance.getParts().getMiscList()) {
            price += p.getPrice();
        }
        
        assertEquals(expectedTotalPrice, price);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
