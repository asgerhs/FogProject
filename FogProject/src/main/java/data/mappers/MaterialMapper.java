package data.mappers;

import data.DBConnector;
import data.MapperError;
import data.models.Material;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
    public List<Material> getMaterials() throws SQLException {
        try (Connection con = connector.getConnection()) {
            List<Material> materials = new ArrayList();
            String qry = "SELECT * FROM stock";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);

            while (rs.next()) {
                materials.add(new Material(rs.getString("name"),
                    rs.getInt("length"),
                    rs.getString("unit"),
                    rs.getString("description")));
            }
            return materials;
        }
    }

    /**
     * Returns specific material based on id
     *
     * @param id, specific material with said id
     * @return material matching id in param
     * @throws SQLException
     */
    public List<Material> getMaterialById(int id) throws MapperError {
        List<Material> materials = new ArrayList();
        try (Connection con = connector.getConnection()) {
            String qry = "SELECT * FROM stock WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(qry);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                materials.add(new Material(rs.getString("name"),
                    rs.getInt("length"),
                    rs.getString("unit"),
                    rs.getString("description")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Date date = new Date();
            Instant instant = date.toInstant();
            String s = instant.toString();
            throw new MapperError("Error occoured while getting data from database: " + s);
        }
        return materials;

    }

    public static void main(String[] args) throws SQLException, MapperError {
        MaterialMapper mm = new MaterialMapper();
        List<Material> mml = mm.getMaterialById(900);
        for (Material m : mml) {
            System.out.println(m);
        }


    }
}
