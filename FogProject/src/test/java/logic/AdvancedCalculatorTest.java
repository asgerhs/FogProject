package logic;

import data.models.Part;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author William Sehested Huusfeldt
 */
public class AdvancedCalculatorTest {
    
    
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Setup test for calculating PartList");
    }
    
    

    /**
     * Tests if the summed price for the carport is equal to the expected price. 
     *  It sums up all prices of each material multiplied by its quantity. 
     * The test passes if the two variables are idential.
     * 
     */
    @Test
    public void testGetPrice() {
        System.out.println("Tests for price");
        AdvancedCalculator instance = new AdvancedCalculator(7800, 6500, false, 0, 0, false, 0);
        
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
     * Test for calcLathsRoof method, it will return true if the carport has an angled roof,
     * it will return falls if it doesn't. 
     * The test checks if a material for said method is added to the partlist.
     */
    @Test
    public void testLathsTrue() {
        HashMap<String, ArrayList> miscHash = new HashMap<String, ArrayList>();
        AdvancedCalculator instance = new AdvancedCalculator(7800, 6500, false, 0, 0, true, 20);
        for (int i = 0; i < instance.getParts().getMiscList().size(); i++) {
            miscHash.put(instance.getParts().getMiscList().get(i).getMaterial().getRef(), instance.getParts().getMiscList());
        }
        assertTrue(miscHash.containsKey("1062"));
    }
    
    /**
     * Test for calcLathsRoof method, it will return true if the carport doesn't have an angled roof,
     * it will return falls if it does. 
     * The test checks if a material for said method is added to the partlist.
     */
    @Test
    public void testLathsFalse() {
        HashMap<String, ArrayList> woodHash = new HashMap<String, ArrayList>();
        AdvancedCalculator instance = new AdvancedCalculator(5400, 3000, false, 0, 0, false, 0);
        for (Part p : instance.getParts().getWoodList()) {
            woodHash.put(p.getMaterial().getRef(), instance.getParts().getWoodList());
        }
        assertFalse(woodHash.containsKey("1015"));
    }
    
    /**
     * Test for shed, will return true if the carport contains a shed, meaning shed specifik methods will run.
     *  Will return false if carport doesn't contain shed.
     *  The test checks if a shed specific material is added to the partlist.
     */
    @Test
    public void testShedTrue() {
        HashMap<String, ArrayList> woodHash = new HashMap<String, ArrayList>();
        AdvancedCalculator instance = new AdvancedCalculator(6800, 3500, true, 2400, 3000, false, 0);
        for (int i = 0; i < instance.getParts().getMiscList().size(); i++) {
            woodHash.put(instance.getParts().getMiscList().get(i).getMaterial().getRef(), instance.getParts().getMiscList());
        }
        assertTrue(woodHash.containsKey("1040"));
    }
    
    /**
     * Test for shed, will return true if the carport doesn't contain a shed, meaning shed specific methods won't will run.
     *  Will return false if carport contains shed.
     *  The test checks if a shed specific material is added to the partlist.
     */
    @Test
    public void testShedFalse() {
        HashMap<String, ArrayList> woodHash = new HashMap<String, ArrayList>();
        AdvancedCalculator instance = new AdvancedCalculator(4900, 3300, false, 0, 0, true, 30);
        for (int i = 0; i < instance.getParts().getMiscList().size(); i++) {
            woodHash.put(instance.getParts().getMiscList().get(i).getMaterial().getRef(), instance.getParts().getMiscList());
        }
        assertFalse(woodHash.containsKey("1004"));
    }
    
    @Test
    public void calcRaftersTest() {
        AdvancedCalculator instance = new AdvancedCalculator(5000, 3600, false, 0, 0, false, 0);
        int count = 0;
        for (Part p : instance.getParts().getWoodList()) {
            if (p.getMaterial().getRef().equals("1007") || p.getMaterial().getRef().equals("1008"))
                count += p.getQty();
        }
        assertEquals(14, count);
    }
    
    @Test
    public void calcPostsTest() {
        AdvancedCalculator instance = new AdvancedCalculator(5000, 3600, false, 0, 0, false, 0);
        int count = 0;
        for (Part p : instance.getParts().getWoodList()) {
            if (p.getMaterial().getRef().equals("1009"))
                count += p.getQty();
        }
        assertEquals(4, count);
        
        instance = new AdvancedCalculator(6300, 4000, false, 0, 0, false, 0);
        count = 0;
        for (Part p : instance.getParts().getWoodList()) {
            if (p.getMaterial().getRef().equals("1009"))
                count += p.getQty();
        }
        assertEquals(6, count);
        
        instance = new AdvancedCalculator(4250, 2500, true, 2500, 2500, false, 0);
        count = 0;
        for (Part p : instance.getParts().getWoodList()) {
            if (p.getMaterial().getRef().equals("1009"))
                count += p.getQty();
        }
        assertEquals(6, count);
    }
    
    @Test
    public void calcBoltsTest() {
        AdvancedCalculator instance = new AdvancedCalculator(5000, 3600, false, 0, 0, false, 0);
        int count = 0;
        for (Part p : instance.getParts().getMiscList()) {
            if (p.getMaterial().getRef().equals("1036"))
                count += p.getQty();
        }
        assertEquals(8, count);
        
        instance = new AdvancedCalculator(6300, 4000, false, 0, 0, false, 0);
        count = 0;
        for (Part p : instance.getParts().getMiscList()) {
            if (p.getMaterial().getRef().equals("1036"))
                count += p.getQty();
        }
        assertEquals(16, count);
        
        instance = new AdvancedCalculator(4250, 2500, true, 2500, 2500, false, 0);
        count = 0;
        for (Part p : instance.getParts().getMiscList()) {
            if (p.getMaterial().getRef().equals("1036"))
                count += p.getQty();
        }
        assertEquals(8, count);
    }
    
}
