package data.mappers;

import data.DBConnector;
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
     * @return all materials
     * @throws SQLException 
     */
    public List<Material> getMaterials() throws SQLException {
        try (Connection con = connector.getConnection()) {
            List<Material> materials = new ArrayList();
            String qry = "SELECT * FROM stock";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);

            while (rs.next()) {
                materials.add(new Material(rs.getString("name"),
                rs.getInt("length"),
                rs.getInt("qty"),
                rs.getString("unit"),
                rs.getString("description")));
            }
            return materials;
        }
    }

    /**
     * Returns specific material based on id
     * @param id, specific material with said id
     * @return material matching id in param
     * @throws SQLException 
     */
    public List<Material> getMaterialById(int id) throws SQLException {
        try (Connection con = connector.getConnection()) {
            List<Material> materials = new ArrayList();
            String qry = "SELECT * FROM stock WHERE id = ?";
            
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                materials.add(new Material(rs.getString("name"),
                rs.getInt("length"),
                rs.getInt("qty"),
                rs.getString("unit"),
                rs.getString("description")));
            }
            return materials;
        }
    }

    public static void main(String[] args) throws SQLException {
        MaterialMapper mm = new MaterialMapper();
        List<Material> mml = mm.getMaterials();
        for (Material m : mml) {
            System.out.println(m);
        }

    }
}