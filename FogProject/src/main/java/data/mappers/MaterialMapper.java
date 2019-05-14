package data.mappers;

import data.DBConnector;
import data.exceptions.MapperExceptions;
import data.interfaces.MapperInterface;
import data.models.Material;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asger Hermind Sørensen & Martin Frederiksen
 */
public class MaterialMapper implements MapperInterface<Material, Integer> {

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
                        rs.getString("unit"),
                        rs.getInt("price")));
            }
            return materials;
        } catch (SQLException ex) {
            ex.printStackTrace();
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
    public Material getById(Integer id) throws MapperExceptions {
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
                        rs.getString("unit"),
                        rs.getInt("price"));
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
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
            
            while(rs.next()){
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
            throw new MapperExceptions("Error occoured while getting data from database");
        }
    }

    public static void main(String[] args) throws MapperExceptions {
        MaterialMapper mm = new MaterialMapper();
        ArrayList<Material> mml = mm.getAllByCategory(1);
        mml = mm.getAllByCategory(2);
        
        System.out.println(mml);
    }
}
