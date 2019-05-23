package data.mappers;

import data.DatabaseConnector;
import data.ExceptionLogger;
import data.exceptions.MaterialException;
import data.interfaces.MapperInterface;
import data.models.LoggerEnum;
import data.models.Material;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Asger Hermind Sørensen & Martin Frederiksen
 */
public class MaterialMapper implements MapperInterface<Material, String> {

    DatabaseConnector dbc = new DatabaseConnector();

    public MaterialMapper(DataSource ds) {
        dbc.setDataSource(ds);
    }

    /**
     * Returns all materials from the database
     * @return List of Materials
     * @throws MaterialException
     */
    @Override
    public List<Material> getAll() throws MaterialException {
        try (Connection con = dbc.open()) {
            List<Material> materials = new ArrayList();
            String qry = "SELECT * FROM stock";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);

            while (rs.next()) {
                materials.add(new Material(rs.getString("ref"),
                        rs.getString("name"),
                        rs.getInt("length"),
                        rs.getInt("amount"),
                        rs.getString("unit"),
                        rs.getInt("price")));
            }
            return materials;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.MATERIALMAPPER, "Error in getAll Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new MaterialException("Error occoured while getting data from database");

        }
    }

    /**
     * Returns specific material based on id from Database
     * @param id, specific material id
     * @return Single Material
     */
    @Override
    public Material getSingle(String ref) throws MaterialException {
        try (Connection con = dbc.open()) {

            String qry = "SELECT * FROM stock WHERE ref = ?";

            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, ref);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new Material(rs.getString("ref"),
                        rs.getString("name"),
                        rs.getInt("length"),
                        rs.getInt("amount"),
                        rs.getString("unit"),
                        rs.getInt("price"));
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.MATERIALMAPPER, "Error in getSingle Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new MaterialException("Error occoured while getting data from database");
        }
    }

    /**
     * Returns List of all Materials in specific category from Database
     * @param categoryId  specific id of category
     * @return List of Materials
     * @throws MaterialException 
     */
    public ArrayList<Material> getAllByCategory(int categoryId)throws MaterialException {
        try (Connection con = dbc.open()) {
            ArrayList<Material> materials = new ArrayList();
            String qry = "SELECT * FROM stock JOIN stockToCategory ON ref = stockRef WHERE categoryId = ?";

            PreparedStatement ps = con.prepareStatement(qry);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                materials.add(new Material(rs.getString("ref"),
                        rs.getString("name"),
                        rs.getInt("length"),
                        rs.getInt("amount"),
                        rs.getString("unit"),
                        rs.getInt("price")));
            }

            return materials;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.MATERIALMAPPER, "Error in getAllByCategory Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new MaterialException("Error occoured while getting data from database");
        }
    }

    /**
     * Adds a new Material to the Database
     * @param material Material to be added
     * @throws MaterialException 
     */
    @Override
    public void add(Material material) throws MaterialException {
        try (Connection con = dbc.open()) {
            String qry = "INSERT INTO stock "
                    + "VALUES (?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, material.getRef());
            ps.setString(2, material.getName());
            ps.setInt(3, material.getLength());
            ps.setInt(4, material.getAmount());
            ps.setString(5, material.getUnit());
            ps.setInt(6, material.getPrice());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.MATERIALMAPPER, "Error in add Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new MaterialException("Error occoured while adding data to database");
        }
    }
}
