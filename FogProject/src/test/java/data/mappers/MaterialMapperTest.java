package data.mappers;

import data.DatabaseConnector;
import data.models.Material;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
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
            
            sqlScript = new BufferedReader(new InputStreamReader(new FileInputStream("../Database/GeneratedDummyData.sql"), "UTF-8"));
            while ((sqlStatement = sqlScript.readLine()) != null)
            {
                sqlStatements += sqlStatement;
            }
                        
            DatabaseConnector dbc = new DatabaseConnector();
            dbc.setDataSource(new TestDataSourceMySQL().getDataSource());
            try (Connection con = dbc.open()) {
                con.prepareStatement(sqlStatement).executeUpdate();
            }
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
    //@Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        List<Material> result = materialMapper.getAll();
        assertTrue(result.size() > 10);
    }

    /**
     * Test of getById method, of class MaterialMapper.
     */
    //@Test
    public void testGetById() throws Exception {
        System.out.println("getById");
        String ref = "1000";
        Material result = materialMapper.getById(ref);
        assertEquals("25x200 mm. trykimp. Brædt", result.getName());
    }

    /**
     * Test of getAllByCategory method, of class MaterialMapper.
     */
    //@Test
    public void testGetAllByCategory() throws Exception {
        System.out.println("getAllByCategory");
        int id = 11;
        ArrayList<Material> result = materialMapper.getAllByCategory(id);
        assertTrue(result.size() > 3);
    }    
}
