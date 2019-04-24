package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Asger Hermind Sørensen
 */
public class MaterialMapper {

    DBConnector connector = new DBConnector();

    public List<Material> getMaterials() throws SQLException {

        Connection con = connector.getConnection();
        List<Material> materials = new ArrayList();
        String qry = "SELECT * FROM stock";
        PreparedStatement ps = con.prepareStatement(qry);
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
    
    
    public List<Material> getMaterialByID(int id) throws SQLException{
        Connection con = connector.getConnection();
        List<Material> materials = new ArrayList();
        String qry = "SELECT * FROM stock WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(qry);
                
        ps.setInt(1, id);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            materials.add(new Material(rs.getString("name"),
                    rs.getInt("length"),
                    rs.getInt("qty"),
                    rs.getString("unit"),
                    rs.getString("description")));
        }
        
        return materials;
        
    }
    
    public static void main(String[] args) throws SQLException {
        MaterialMapper mm = new MaterialMapper();
        List<Material> mml = mm.getMaterialByID(4);
        for(Material m : mml){
            System.out.println(m);
        }
        
    }
}
