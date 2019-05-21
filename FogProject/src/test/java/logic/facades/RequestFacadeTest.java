package logic.facades;

import com.github.javafaker.Faker;
import data.DatabaseConnector;
import data.TestDataSourceMySQL;
import data.exceptions.RequestException;
import data.models.Request;
import data.models.RoleEnum;
import data.models.User;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andreas Vikke
 */
public class RequestFacadeTest {

    private static RequestFacade requestFacade;

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

        requestFacade = new RequestFacade(new TestDataSourceMySQL().getDataSource());
    }

    /**
     * Test of getAll method, of class RequestFacade.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        List<Request> result = requestFacade.getAll();
        assertNotNull(result);
        assertTrue(result.size() > 10);
    }

    /**
     * Test of getSingle method, of class RequestFacade.
     */
    @Test
    public void testGetSingle() throws Exception {
        System.out.println("getSingle");
        Integer id = 4;
        Request result = requestFacade.getSingle(id);
        assertNotNull(result);
    }

    /**
     * Test of update method, of class RequestFacade.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Faker faker = new Faker();
        
        Request result = requestFacade.getSingle(4);
        int width = faker.number().numberBetween(500, 780);
        int length = faker.number().numberBetween(100, 210);
        result.setWidth(width);
        result.setLength(length);
        requestFacade.update(result);
        
        Request result2 = requestFacade.getSingle(4);
        assertEquals(result2.getWidth(), width);
        assertEquals(result2.getLength(), length);
    }

    /**
     * Test of add method, of class RequestFacade.
     */
    @Test
    public void testAdd() throws Exception {
        System.out.println("add");
        try {
            Request request = new Request(100, 100, 100, 100, "1013", 0, "Test", new User("test2@test.dk", "1234", RoleEnum.ADMIN, "", "", "", ""));
            requestFacade.add(request);
        } catch (RequestException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test of remove method, of class RequestFacade.
     */
    @Test
    public void testRemove() throws Exception {
        System.out.println("remove");
        int id = 1;
        requestFacade.remove(id);
        Request request = requestFacade.getSingle(id);
        assertNull(request);
    }

}
