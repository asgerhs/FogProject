package data.mappers;

import data.DatabaseConnector;
import data.ExceptionLogger;
import data.exceptions.RequestException;
import data.exceptions.UsersException;
import data.interfaces.MapperInterface;
import data.models.LoggerEnum;
import data.models.Request;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import logic.facades.UserFacade;

/**
 *
 * @author William Sehested Huusfeldt, Martin Frederiksen
 */
public class RequestMapper implements MapperInterface<Request, Integer> {

    DatabaseConnector dbc = new DatabaseConnector();
    UserMapper userMapper;

    public RequestMapper(DataSource ds) {
        this.userMapper = new UserMapper(ds);
        dbc.setDataSource(ds);
    }

    /**
     * Gets All Request from the database
     * @return List of Request
     * @throws RequestException RequestException
     */
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
                        userMapper.getSingle(rs.getString("email")));
                r.setId(rs.getInt("id"));
                requests.add(r);

            }

            return requests;
        } catch (SQLException | UsersException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.REQUESTMAPPER, "Error in getAll Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new RequestException("Error occoured while getting data from database");
        }
    }

    /**
     * Gets a single Request by id form the database
     * @param id specific id
     * @return Single Request
     * @throws RequestException RequestException
     */
    @Override
    public Request getSingle(Integer id) throws RequestException {
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
                        userMapper.getSingle(rs.getString("email")));
                r.setId(rs.getInt("id"));
                return r;
            }
            return null;
        } catch (SQLException | UsersException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.REQUESTMAPPER, "Error in getSingle Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new RequestException("Error occoured while getting data from database");
        }
    }

    /**
     * Updates request in the database
     * @param rqst updated request
     * @throws RequestException RequestException
     */
    public void update(Request rqst) throws RequestException {
        try (Connection con = dbc.open()) {

            String qry = "UPDATE requests SET width = ?, length = ?, shedWidth = ?, shedLength = ?,"
                    + " roof = ?, angle = ?, note = ? WHERE id = ?;";

            PreparedStatement ps = con.prepareStatement(qry);
            ps.setInt(1, rqst.getWidth());
            ps.setInt(2, rqst.getLength());
            ps.setInt(3, rqst.getShedWidth());
            ps.setInt(4, rqst.getShedLength());
            ps.setString(5, rqst.getRoof());
            ps.setInt(6, rqst.getAngle());
            ps.setString(7, rqst.getNote());
            ps.setInt(8, rqst.getId());

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.REQUESTMAPPER, "Error in update Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new RequestException("Error occoured while updating data in database");
        }
    }

    /**
     * Adds new request to the database
     * @param request request to be added
     * @throws RequestException RequestException
     */
    @Override
    public void add(Request request) throws RequestException {
        try (Connection con = dbc.open()) {

            try {
                con.setAutoCommit(false);
                if (request.getUser().getName() != null) {
                    request.getUser().setPassword(UserFacade.encryptPassword(request.getUser().getPassword()));
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

                ExceptionLogger.log(LoggerEnum.REQUESTMAPPER, "Error in addUser Method: \n" + ex.getMessage(), ex.getStackTrace());
                throw new RequestException("Error occoured while adding user to database - Email already in use");
            }
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.REQUESTMAPPER, "Error in add Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new RequestException("Error occoured while adding request to database");
        }
    }

    /**
     * Removes request form the database
     * @param id id to be removed
     * @throws RequestException RequestException
     */
    public void remove(int id) throws RequestException {
        try (Connection con = dbc.open()) {
            String qry = "DELETE FROM requests WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.REQUESTMAPPER, "Error in remove Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new RequestException("Error while removing request from database");
        }
    }
}
