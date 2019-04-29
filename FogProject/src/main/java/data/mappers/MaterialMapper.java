package data.mappers;

import data.DBConnector;
import data.exceptions.MapperExceptions;
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
 * @author Asger Hermind Sørensen
 */
public class MaterialMapper {

    private DBConnector connector = new DBConnector();

    /**
     * Returns all materials in database
     *
     * @return all materials
     * @throws SQLException
     */
    public List<Material> getMaterials() throws MapperExceptions {
        try (Connection con = connector.getConnection()) {
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
            ex.printStackTrace();
            throw new MapperExceptions("Error occoured while getting data from database");
        }
    }

    /**
     * Returns specific material based on id
     *
     * @param id, specific material with said id
     * @return material matching id in param
     * @throws SQLException
     */
    public Material getMaterialById(int id) throws MapperExceptions {
        try (Connection con = connector.getConnection()) {
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
            ex.printStackTrace();
            throw new MapperExceptions("Error occoured while getting data from database");
        }
    }

    public static void main(String[] args) throws MapperExceptions {
        MaterialMapper mm = new MaterialMapper();
        List<Material> mml = mm.getMaterials();
        //List<Material> mml = mm.getMaterialById(30);
        for (Material m : mml) {
            System.out.println(m);
        }
    }
}
