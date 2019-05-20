/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import data.DatabaseConnector;
import data.exceptions.MaterialException;
import data.mappers.MaterialMapper;
import data.mappers.RequestMapper;
import data.mappers.TestDataSourceMySQL;
import data.models.Material;
import data.models.Part;
import data.models.PartList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import logic.facades.MaterialFacade;
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
    private ArrayList<Material> materials;
    private MaterialFacade mf;
    private static MaterialMapper materialMapper;
    
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Setup test for calculating PartList");
        System.out.println("Setup Test MySQL Database");
        
        BufferedReader sqlScript;
        try
        {
            sqlScript = new BufferedReader(new InputStreamReader(new FileInputStream("../Database/FogProject_Script.sql"), "UTF-8"));
            
            String sqlStatements = "";
            String sqlStatement = "";
            while ((sqlStatement = sqlScript.readLine()) != null)
            {
                sqlStatements += sqlStatement;
            }
            
            sqlScript = new BufferedReader(new InputStreamReader(new FileInputStream("../Database/GeneratedDummyData.sql"), "UTF-8"));
            while ((sqlStatement = sqlScript.readLine()) != null)
            {
                sqlStatements += sqlStatement;
            }
                        
            DatabaseConnector dbc = new DatabaseConnector();
            dbc.setDataSource(new TestDataSourceMySQL().getDataSource());
            try (Connection con = dbc.open()) {
                con.prepareStatement(sqlStatements).executeUpdate();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        materialMapper = new MaterialMapper(new TestDataSourceMySQL().getDataSource());
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
    
    /**
     * 
     */
    @Test
    public void testLathsTrue() throws MaterialException {
        HashMap<String, ArrayList> miscHash = new HashMap<String, ArrayList>();
        AdvancedCalculator instanceWithAngle = new AdvancedCalculator(7800, 6500, false, 0, 0, true);
        for (int i = 0; i < instanceWithAngle.getParts().getMiscList().size(); i++) {
            miscHash.put(instanceWithAngle.getParts().getMiscList().get(i).getMaterial().getRef(), instanceWithAngle.getParts().getMiscList());
        }
        assertTrue(miscHash.containsKey("1062"));
    }

    @Test
    public void testLathsFalse() throws MaterialException {
        HashMap<String, ArrayList> miscHash = new HashMap<String, ArrayList>();
        AdvancedCalculator instance = new AdvancedCalculator(7800, 6500, false, 0, 0, false);
        for (int i = 0; i < instance.getParts().getMiscList().size(); i++) {
            miscHash.put(instance.getParts().getMiscList().get(i).getMaterial().getRef(), instance.getParts().getMiscList());
        }
        assertFalse(miscHash.containsKey("1062"));
    }
}
