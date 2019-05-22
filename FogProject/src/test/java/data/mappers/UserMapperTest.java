package data.mappers;

import data.DatabaseConnector;
import data.TestDataSourceMySQL;
import data.exceptions.UsersException;
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
public class UserMapperTest {
    
    private static UserMapper userMapper;
    
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
        
        userMapper = new UserMapper(new TestDataSourceMySQL().getDataSource());
    }
    
    /**
     * Test of getAll method, of class UserMapper.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        List result = userMapper.getAll();
        assertNotNull(result);
        assertTrue(result.size() > 10);
    }

    /**
     * Test of getSingle method, of class UserMapper.
     */
    @Test
    public void testGetSingle() throws Exception {
        System.out.println("getById");
        String email = "admin@a.dk";
        User result = userMapper.getSingle(email);
        assertNotNull(result);
        email = "admin@b.dk";
        result = userMapper.getSingle(email);
        assertNull(result);
    }

    /**
     * Test of add method, of class UserMapper.
     */
    @Test
    public void testAdd() throws Exception {
        System.out.println("add");
        try {
            User user = new User("test@test.dk", "1234", RoleEnum.ADMIN, "", "", "", "");
            userMapper.add(user);
            
            User result = userMapper.getSingle("test@test.dk");
            assertNotNull(result);
        } catch (UsersException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test of validateUser method, of class UserMapper.
     */
    @Test
    public void testValidateUser() throws Exception {
        System.out.println("validateUser");
        String email = "admin@a.dk";
        String password = "81DC9BDB52D04DC20036DBD8313ED055";
        boolean result = userMapper.validateUser(email, password);
        assertTrue(result);
        email = "admin@b.dk";
        password = "81DC9BDB52D04DC20036DBD8313ED055";
        result = userMapper.validateUser(email, password);
        assertFalse(result);
    }

    /**
     * Test of changePassword method, of class UserMapper.
     */
    @Test
    public void testChangePassword() throws Exception {
        System.out.println("changePassword");
        String email = "bsm@e.dk";
        String password = "4321";
        int expResult = 1;
        int result = userMapper.changePassword(email, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of changeUserRole method, of class UserMapper.
     */
    @Test
    public void testChangeUserRole() throws Exception {
        System.out.println("changeUserRole");
        String email = "bsm@e.dk";
        RoleEnum role = RoleEnum.CUSTOMER;
        int expResult = 1;
        int result = userMapper.changeUserRole(email, role);
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class UserMapper.
     */
    @Test
    public void testRemove() throws Exception {
        System.out.println("remove");
        String email = "Remove@remove.dk";
        userMapper.remove(email);
        User user = userMapper.getSingle(email);
        assertNull(user);
    }
    
}
