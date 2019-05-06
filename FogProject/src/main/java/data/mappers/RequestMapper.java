package data.mappers;

import data.DBConnector;
import data.exceptions.RequestExceptions;
import data.interfaces.MapperInterface;
import data.models.Request;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author William Sehested Huusfeldt
 */
public class RequestMapper implements MapperInterface<Request> {

    @Override
    public List<Request> getAll() throws RequestExceptions {
        try (Connection con = new DBConnector().getConnection()) {
            List<Request> requests = new ArrayList();
            String qry = "SELECT * FROM requests";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);

            while (rs.next()) {
                requests.add(new Request(
                        rs.getInt("width"),
                        rs.getInt("length"),
                        rs.getInt("shedWidth"),
                        rs.getInt("shedLength"),
                        rs.getString("roof"),
                        rs.getInt("angle"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("zipCode"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("note")));
            }
            return requests;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RequestExceptions("Error occoured while getting data from database");
        }
    }

    @Override
    public Request getById(int id) throws RequestExceptions {
        try (Connection con = new DBConnector().getConnection()) {
            
            String qry = "SELECT * FROM requests WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(qry);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            

            while (rs.next()) {
                return new Request(
                        rs.getInt("width"),
                        rs.getInt("length"),
                        rs.getInt("shedWidth"),
                        rs.getInt("shedLength"),
                        rs.getString("roof"),
                        rs.getInt("angle"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("zipCode"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("note"));
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RequestExceptions("Error occoured while getting data from database");
        }
    }
    
    public void updateRequest(Request rqst) throws SQLException, RequestExceptions{
        try(Connection con = new DBConnector().getConnection()){
            
            String qry = "UPDATE request SET width = ?, length = ?, shedWidth = ?, shedLength = ?,"
                    + " roof = ?, angle = ?";
            
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setInt(1, rqst.getLength());
            ps.setInt(2, rqst.getWidth());
            ps.setInt(3, rqst.getShedWidth());
            ps.setInt(4, rqst.getShedLength());
            ps.setString(5, rqst.getRoof());
            ps.setInt(6, rqst.getAngle());
            
            ps.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace();
            throw new RequestExceptions("Error occoured while updating data in database");
        }
    }
    
    
    public void add(Request request) throws RequestExceptions {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) throws RequestExceptions {
        RequestMapper rm = new RequestMapper();
        //rm.getAll();
        List<Request> requests = new ArrayList();
        for (Request r : requests) {
            rm.getAll();
        }
    }
}
