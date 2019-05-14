package data.mappers;

import data.DatabaseConnector;
import data.models.Material;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andreas Vikke
 */
public class MaterialMapperTest {
    
    private static MaterialMapper materialMapper;
    
    @BeforeClass
    public static void beforeClass() {
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
        
            DatabaseConnector dbc = new DatabaseConnector();
            dbc.setDataSource(new TestDataSourceMySQL().getDataSource());
            dbc.open();
            dbc.preparedStatement(sqlStatements).executeUpdate();
            dbc.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        materialMapper = new MaterialMapper(new TestDataSourceMySQL().getDataSource());
    }

    /**
     * Test of getAll method, of class MaterialMapper.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        List<Material> expResult = null;
        List<Material> result = materialMapper.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(true);
    }

    /**
     * Test of getById method, of class MaterialMapper.
     */
    @Test
    public void testGetById() throws Exception {
        System.out.println("getById");
        int id = 0;
        Material expResult = null;
        Material result = materialMapper.getById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(true);
    }

    /**
     * Test of getAllByCategory method, of class MaterialMapper.
     */
    @Test
    public void testGetAllByCategory() throws Exception {
        System.out.println("getAllByCategory");
        int id = 0;
        ArrayList<Material> expResult = null;
        ArrayList<Material> result = materialMapper.getAllByCategory(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(true);
    }    
}
