package logic.facades;

import data.DatabaseConnector;
import data.TestDataSourceMySQL;
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
public class UserFacadeTest {

    private static UserFacade userFacade;

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

        userFacade = new UserFacade(new TestDataSourceMySQL().getDataSource());
    }

    /**
     * Test of getAll method, of class UserFacade.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        List result = userFacade.getAll();
        assertNotNull(result);
        assertTrue(result.size() > 10);
    }

    /**
     * Test of getSingle method, of class UserFacade.
     */
    @Test
    public void testGetSingle() throws Exception {
        System.out.println("getSingle");
        String email = "admin@a.dk";
        User result = userFacade.getSingle(email);
        assertNotNull(result);
    }

    /**
     * Test of validateUser method, of class UserFacade.
     */
    @Test
    public void testValidateUser() throws Exception {
        System.out.println("validateUser");
        String email = "admin@a.dk";
        String password = "1234";
        boolean result = userFacade.validateUser(email, password);
        assertTrue(result);
    }

    /**
     * Test of changePassword method, of class UserFacade.
     */
    @Test
    public void testChangePassword() throws Exception {
        System.out.println("changePassword");
        String email = "bsm@e.dk";
        String password = "4321";
        int expResult = 1;
        int result = userFacade.changePassword(email, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of changeUserRole method, of class UserFacade.
     */
    @Test
    public void testChangeUserRole() throws Exception {
        System.out.println("changeUserRole");
        String email = "bsm@e.dk";
        RoleEnum role = RoleEnum.CUSTOMER;
        int expResult = 1;
        int result = userFacade.changeUserRole(email, role);
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class UserFacade.
     */
    @Test
    public void testRemove() throws Exception {
        System.out.println("remove");
        String email = "Remove@remove.dk";
        userFacade.remove(email);
        User user = userFacade.getSingle("Remove@remove.dk");
        assertNull(user);
    }

}
