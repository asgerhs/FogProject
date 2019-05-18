package data.mappers;

import data.DatabaseConnector;
import data.exceptions.RequestException;
import data.exceptions.UsersException;
import data.interfaces.MapperInterface;
import data.models.Request;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.sql.DataSource;

/**
 *
 * @author William Sehested Huusfeldt & Martin Frederiksen
 */
public class RequestMapper implements MapperInterface<Request, Integer> {

    DatabaseConnector dbc = new DatabaseConnector();
    UserMapper userMapper;

    public RequestMapper(DataSource ds) {
        this.userMapper = new UserMapper(ds);
        dbc.setDataSource(ds);
    }

    private static Logger logger = Logger.getLogger(RequestMapper.class.getName());

    public RequestMapper() {
        try {
            FileHandler handler = new FileHandler("Logs/RequestMapper/MaterialMapper-log.%u.%g.txt",
                    1024 * 1024, 10);
            logger.addHandler(handler);
            handler.setFormatter(new SimpleFormatter());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error in logger", new IOException("Error: "));
        }
    }

    @Override
    public List<Request> getAll() throws RequestException {
        try (Connection con = dbc.open()) {
            List<Request> requests = new ArrayList();
            String qry = "SELECT * FROM `requests` LEFT JOIN `orders` ON `orders`.requestId = `requests`.id WHERE `orders`.id IS NULL;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);

            while (rs.next()) {
                Request r = new Request(
                        rs.getInt("width"),
                        rs.getInt("length"),
                        rs.getInt("shedWidth"),
                        rs.getInt("shedLength"),
                        rs.getString("roof"),
                        rs.getInt("angle"),
                        rs.getString("note"),
                        userMapper.getById(rs.getString("email")));
                r.setId(rs.getInt("id"));
                requests.add(r);

            }

            return requests;
        } catch (SQLException | UsersException ex) {
            logger.log(Level.SEVERE, "Error in getAll Method:", new SQLException("Error: "));
            throw new RequestException("Error occoured while getting data from database");
        }
    }

    @Override
    public Request getById(Integer id) throws RequestException {
        try (Connection con = dbc.open()) {
            String qry = "SELECT * FROM requests WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Request r = new Request(
                        rs.getInt("width"),
                        rs.getInt("length"),
                        rs.getInt("shedWidth"),
                        rs.getInt("shedLength"),
                        rs.getString("roof"),
                        rs.getInt("angle"),
                        rs.getString("note"),
                        userMapper.getById(rs.getString("email")));
                r.setId(rs.getInt("id"));
                return r;
            }
            return null;
        } catch (SQLException | UsersException ex) {
            logger.log(Level.SEVERE, "Error in getById Method:", new SQLException("Error: "));
            throw new RequestException("Error occoured while getting data from database");
        }
    }

    public void updateRequest(Request rqst, int Id) throws SQLException, RequestException {
        try (Connection con = dbc.open()) {

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

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error in updateRequest Method:", new SQLException("Error: "));
            throw new RequestException("Error occoured while updating data in database");
        }
    }

    public void add(Request request) throws RequestException {
        try (Connection con = dbc.open()) {

            try {
                con.setAutoCommit(false);
                if(request.getUser().getName() != null){
                    userMapper.add(request.getUser());
                }

                String qry = "INSERT INTO requests"
                        + "(width, length, shedWidth, shedLength, roof, angle, note, email) "
                        + "VALUES (?,?,?,?,?,?,?,?);";
                PreparedStatement ps = con.prepareStatement(qry);
                ps.setInt(1, request.getWidth());
                ps.setInt(2, request.getLength());
                ps.setInt(3, request.getShedWidth());
                ps.setInt(4, request.getShedLength());
                ps.setString(5, request.getRoof());
                ps.setInt(6, request.getAngle());
                ps.setString(7, request.getNote());
                ps.setString(8, request.getUser().getEmail());
                ps.executeUpdate();
            } catch (UsersException ex) {
                con.rollback();
                con.setAutoCommit(true);
                
                logger.log(Level.SEVERE, "Error in add Method:", new SQLException("Error: "));
                throw new RequestException("Error occoured while adding user to database - Email already in use");
            }
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error in add Method:", new SQLException("Error: "));
            throw new RequestException("Error occoured while adding request to database");
        }
    }

    public void remove(int id) throws RequestException {
        try (Connection con = dbc.open()) {
            String qry = "DELETE FROM requests WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RequestException("Error while removing request from database");
        }
    }
}
