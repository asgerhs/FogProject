package data.mappers;

import data.DatabaseConnector;
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
import javax.sql.DataSource;

/**
 *
 * @author William Sehested Huusfeldt
 */
public class RequestMapper implements MapperInterface<Request, Integer> {
    
    DatabaseConnector dbc = new DatabaseConnector();
    
    public RequestMapper(DataSource ds) {
        dbc.setDataSource(ds);
    }

    @Override
    public List<Request> getAll() throws RequestExceptions {
        try (Connection con = dbc.open()) {
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
                        rs.getString("zipCity"),
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
    public Request getById(Integer id) throws RequestExceptions {
        try (Connection con = dbc.open()) {

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
                        rs.getString("zipCity"),
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
    
    public void updateRequest(Request rqst, int Id) throws SQLException, RequestExceptions{
        try(Connection con = dbc.open()){
            
            String qry = "UPDATE requests SET width = ?, length = ?, shedWidth = ?, shedLength = ?,"
                    + " roof = ?, angle = ? where id = ?;";
            
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setInt(1, rqst.getLength());
            ps.setInt(2, rqst.getWidth());
            ps.setInt(3, rqst.getShedWidth());
            ps.setInt(4, rqst.getShedLength());
            ps.setString(5, rqst.getRoof());
            ps.setInt(6, rqst.getAngle());
            ps.setInt(7, Id);
            
            ps.executeUpdate();
            
        }catch(SQLException ex){
            ex.printStackTrace();
            throw new RequestExceptions("Error occoured while updating data in database");
        }
    }
    
    
    public void add(Request request) throws RequestExceptions {
        try (Connection con = dbc.open()) {

            String qry = "INSERT INTO requests"
                    + "(width, length, shedWidth, shedLength, roof, angle, name, address, zipCity, phone, email, note)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement ps = con.prepareStatement(qry);
            ps.setInt(1, request.getWidth());
            ps.setInt(2, request.getLength());
            ps.setInt(3, request.getShedWidth());
            ps.setInt(4, request.getShedLength());
            ps.setString(5, request.getRoof());
            ps.setInt(6, request.getAngle());
            ps.setString(7, request.getName());
            ps.setString(8, request.getAddress());
            ps.setString(9, request.getZipCity());
            ps.setString(10, request.getPhone());
            ps.setString(11, request.getEmail());
            ps.setString(12, request.getNote());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RequestExceptions("Error occoured while adding request to database");
        }
    }
}
