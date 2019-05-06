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

    public void add(Request request) throws RequestExceptions {
        try (Connection con = new DBConnector().getConnection()) {

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

    public static void main(String[] args) throws RequestExceptions {
        RequestMapper rm = new RequestMapper();
        //rm.getAll();
        List<Request> requests = new ArrayList();
        for (Request r : requests) {
            rm.getAll();
        }
    }
}
