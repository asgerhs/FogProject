package data.mappers;

import com.github.javafaker.Faker;
import data.DatabaseConnector;
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
public class RequestMapperTest {
    
    private static RequestMapper requestMapper;
    
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
                con.prepareStatement(sqlStatements).executeUpdate();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        requestMapper = new RequestMapper(new TestDataSourceMySQL().getDataSource());
    }
    
    /**
     * Test of getAll method, of class RequestMapper.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        List<Request> result = requestMapper.getAll();
        assertNotNull(result);
        assertTrue(result.size() > 10);
    }

    /**
     * Test of getById method, of class RequestMapper.
     */
    @Test
    public void testGetById() throws Exception {
        System.out.println("getById");
        Integer id = 4;
        Request result = requestMapper.getSingle(id);
        assertNotNull(result);
    }

    /**
     * Test of updateRequest method, of class RequestMapper.
     */
    @Test
    public void testUpdateRequest() throws Exception {
        System.out.println("updateRequest");
        Faker faker = new Faker();
        
        Request result = requestMapper.getSingle(1);
        int width = faker.number().numberBetween(500, 780);
        int length = faker.number().numberBetween(100, 210);
        result.setWidth(width);
        result.setLength(length);
        requestMapper.update(result);
        
        Request result2 = requestMapper.getSingle(1);
        assertEquals(result2.getWidth(), width);
        assertEquals(result2.getLength(), length);
    }

    /**
     * Test of add method, of class RequestMapper.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        try {
            Request request = new Request(100, 100, 100, 100, "1013", 0, "Test", new User("test@test.dk", "1234", RoleEnum.ADMIN, "", "", "", ""));
            requestMapper.add(request);
        } catch (RequestException ex) {
            fail(ex.getMessage());
        }
    }
}
