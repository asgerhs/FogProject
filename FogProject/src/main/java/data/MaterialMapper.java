package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asger Hermind Sørensen
 */
public class MaterialMapper {

    DBConnector connector = new DBConnector();

    public List<material> getMaterials() throws SQLException {

        Connection con = connector.getConnection();
        List<material> materials = new ArrayList();
        String qry = "SELECT * FROM stock";
        PreparedStatement ps = con.prepareStatement(qry);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            materials.add(new material(rs.getString("name"),
                    rs.getInt("length"),
                    rs.getInt("qty"),
                    rs.getString("unit"),
                    rs.getString("description")));
        }

        return materials;

    }
    
    
    public List<material> getMaterialByID(int id) throws SQLException{
        Connection con = connector.getConnection();
        List<material> materials = new ArrayList();
        String qry = "SELECT * FROM stock WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(qry);
                
        ps.setInt(1, id);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            materials.add(new material(rs.getString("name"),
                    rs.getInt("length"),
                    rs.getInt("qty"),
                    rs.getString("unit"),
                    rs.getString("description")));
        }
        
        return materials;
        
    }
}
