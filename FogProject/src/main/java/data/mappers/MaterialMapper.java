package data.mappers;

import data.DBConnector;
import data.exceptions.MapperExceptions;
import data.interfaces.MapperInterface;
import data.models.Material;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Asger Hermind Sørensen & Martin Frederiksen
 */
public class MaterialMapper implements MapperInterface<Material> {

  private static Logger logger = Logger.getLogger(MaterialMapper.class.getName());

    public MaterialMapper() {
        try {

            FileHandler handler = new FileHandler("Fog-MaterialMapper-log.%u.%g.txt",
                    1024 * 1024, 10);
            logger.addHandler(handler);

            handler.setFormatter(new SimpleFormatter());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error in logger", new IOException("Error: "));

        }
    }

    /**
     * Returns all materials in database
     *
     * @return all materials
     * @throws data.exceptions.MapperExceptions
     */
    @Override
    public List<Material> getAll() throws MapperExceptions {
        try (Connection con = new DBConnector().getConnection()) {
            List<Material> materials = new ArrayList();
            String qry = "SELECT * FROM stock";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);

            while (rs.next()) {
                materials.add(new Material(rs.getString("ref"),
                        rs.getString("name"),
                        rs.getInt("length"),
                        rs.getInt("amount"),
                        rs.getString("unit")));
            }
            return materials;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error in getAll Method.", new SQLException("Error: "));

            throw new MapperExceptions("Error occoured while getting data from database");

        }
    }

    /**
     * Returns specific material based on id
     *
     * @param id, specific material with said id
     * @return material matching id in param
     */
    @Override
    public Material getById(int id) throws MapperExceptions {
        try (Connection con = new DBConnector().getConnection()) {

            String qry = "SELECT * FROM stock WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(qry);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new Material(rs.getString("ref"),
                        rs.getString("name"),
                        rs.getInt("length"),
                        rs.getInt("amount"),
                        rs.getString("unit"));
            }
            return null;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error in getByID Method.", new SQLException("Error: "));
            throw new MapperExceptions("Error occoured while getting data from database");
        }
    }

    public ArrayList<Material> getAllByCategory(int id) throws MapperExceptions {
        try (Connection con = new DBConnector().getConnection()) {
            ArrayList<Material> materials = new ArrayList();
            String qry = "SELECT * FROM stock JOIN stockToCategory ON ref = stockRef WHERE categoryId = ?";

            PreparedStatement ps = con.prepareStatement(qry);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                materials.add(new Material(rs.getString("ref"),
                        rs.getString("name"),
                        rs.getInt("length"),
                        rs.getInt("amount"),
                        rs.getString("unit")));
            }

            return materials;
        } catch (SQLException ex) {
              logger.log(Level.SEVERE, "Error in getAllByCategory Method.", new SQLException("Error: "));
            throw new MapperExceptions("Error occoured while getting data from database");
        }
    }

    public static void main(String[] args) throws MapperExceptions {
        MaterialMapper mm = new MaterialMapper();
        List<Material> mml = mm.getAll();
        List<Material> mmll = mm.getAllByCategory(2);
        Material mmlll = mm.getById(1);
        mml = mm.getAll();
        mmll = mm.getAllByCategory(2);
        mmlll = mm.getById(2);

        System.out.println(mmlll);
    }
}
