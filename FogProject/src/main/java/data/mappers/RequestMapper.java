package data.mappers;

import data.DBConnector;
import data.exceptions.RequestExceptions;
import data.interfaces.MapperInterface;
import data.models.Request;
import java.sql.Connection;
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
            String qry = "SELECT * FROM stock";
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
                        rs.getString("email"))
                        rs.getString("note"));
            }
            return requests;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RequestExceptions("Error occoured while getting data from database");
        }
    }

    @Override
    public Request getById(int id) throws RequestExceptions {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void add(Request request) throws RequestExceptions {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
