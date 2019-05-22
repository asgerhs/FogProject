package data.mappers;

import data.TestDataSourceMySQL;
import data.DatabaseConnector;
import data.exceptions.OrderException;
import data.models.Order;
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
public class OrderMapperTest {
    
    private static OrderMapper orderMapper;
    
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
        
        orderMapper = new OrderMapper(new TestDataSourceMySQL().getDataSource());
    }

    /**
     * Test of getAll method, of class OrderMapper.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        List<Order> result = orderMapper.getAll();
        assertNotNull(result);
        assertTrue(result.size() > 10);
    }

    /**
     * Test of getSingle method, of class OrderMapper.
     */
    @Test
    public void testGetSingle() throws Exception {
        System.out.println("getSingle");
        Integer id = 1;
        Order result = orderMapper.getSingle(id);
        assertNotNull(result);
        id = 0;
        result = orderMapper.getSingle(id);
        assertNull(result);
    }

    /**
     * Test of createOrder method, of class OrderMapper.
     */
    @Test
    public void testAdd() throws Exception {
        System.out.println("createOrder");
        try {
            int requestId = 18;
            Request request = new Request(0, 0, 0, 0, null, 0, null, null);
            request.setId(requestId);
            orderMapper.add(new Order(0, request));
        } catch (OrderException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test of getAllByUser method, of class OrderMapper.
     */
    @Test
    public void testGetAllByUser() throws Exception {
        System.out.println("getAllByUser");
        List<Order> result = orderMapper.getAllByUser(new User("admin@a.dk", "", RoleEnum.ADMIN, "", "", "", ""));
        assertNotNull(result);
        assertTrue(result.size() > 0);
    }
    
}
