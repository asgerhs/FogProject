package logic.facades;

import data.DatabaseConnector;
import data.TestDataSourceMySQL;
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
public class MaterialFacadeTest {

    private static MaterialFacade materialFacade;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Setup Test MySQL Database");

        BufferedReader sqlScript;
        try {
            sqlScript = new BufferedReader(new InputStreamReader(new FileInputStream("../Database/FogProject_Script.sql"), "UTF-8"));

            String sqlStatements = "";
            String sqlStatement = "";
            while ((sqlStatement = sqlScript.readLine()) != null) {
                sqlStatements += sqlStatement;
            }

            sqlScript = new BufferedReader(new InputStreamReader(new FileInputStream("../Database/GeneratedDummyData.sql"), "UTF-8"));
            while ((sqlStatement = sqlScript.readLine()) != null) {
                sqlStatements += sqlStatement;
            }

            DatabaseConnector dbc = new DatabaseConnector();
            dbc.setDataSource(new TestDataSourceMySQL().getDataSource());
            try (Connection con = dbc.open()) {
                con.prepareStatement(sqlStatements).executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        materialFacade = new MaterialFacade(new TestDataSourceMySQL().getDataSource());
    }

    /**
     * Test of getAll method, of class MaterialFacade.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        List<Material> result = materialFacade.getAll();
        assertNotNull(result);
        assertTrue(result.size() > 10);
    }

    /**
     * Test of getSingle method, of class MaterialFacade.
     */
    @Test
    public void testGetSingle() throws Exception {
        System.out.println("getSingle");
        String ref = "1005";
        Material result = materialFacade.getSingle(ref);
        assertEquals("45x95 mm. Reglar ub.", result.getName());
    }

    /**
     * Test of getAllByCategory method, of class MaterialFacade.
     */
    @Test
    public void testGetAllByCategory() throws Exception {
        System.out.println("getAllByCategory");
        int id = 11;
        ArrayList<Material> result = materialFacade.getAllByCategory(id);
        assertTrue(result.size() > 3);
    }
}
